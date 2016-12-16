#include "Sprezeni.h"
#include <string>
#include <stdexcept>
#include <iostream>

namespace PomocneFunkce {
	double celkovyVykon = 0;
	double celkovaVaha = 0;

	void VypisSprezeni(ClenSprezeni* clen){
		clen->Vypis();
	}

	void ZjistiVykonSprezeni(TaznaSila* clen){
		celkovyVykon += clen->GetTaznyVykon();
	}

	void ZjistiVahuSprezeni(Vuz* clen){
		celkovaVaha += clen->GetVaha();
	}

}

void Sprezeni::Vypis()const{
	void(*ukazNaFci)(ClenSprezeni*);
	ukazNaFci = PomocneFunkce::VypisSprezeni;
	kontejner.Vykonej(ukazNaFci);
}

double Sprezeni::ZjistitVykon()const{
	PomocneFunkce::celkovyVykon = 0;
	void(*ukazNaFci)(TaznaSila*);
	ukazNaFci = PomocneFunkce::ZjistiVykonSprezeni;
	kontejner.Vykonej(ukazNaFci);
	return PomocneFunkce::celkovyVykon;
}

double Sprezeni::ZjistitVahu()const{
	PomocneFunkce::celkovaVaha = 0;
	void(*ukazNaFci)(Vuz*);
	ukazNaFci = PomocneFunkce::ZjistiVahuSprezeni;
	
	kontejner.Vykonej(ukazNaFci);
	return PomocneFunkce::celkovaVaha;
}

void Sprezeni::OdstranNadvahu(){
	size_t* indexNakladu = new size_t(0);
	kontejner.DejPosledni<Vuz>(*indexNakladu);
	UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>>::iterator it = kontejner.begin();
	it[*indexNakladu];
	kontejner.Odeber(it);
	delete indexNakladu;
}

bool Sprezeni::JeLetuSchopny()const {
	return (this->ZjistitVykon() >= this->ZjistitVahu());
}


Sprezeni::Sprezeni(const UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>>& _kontejner) :kontejner(_kontejner){}

Sprezeni Elfove::sestav(std::string zadani){
	size_t santa = 0;
	size_t technikyVuz = 0;
	UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>> kontejner;
	if ((zadani.find_first_not_of(",SLKXT0123456789")) == std::string::npos)
	{
		for (size_t i = 0; i < zadani.size(); i++)
		{

			std::string cast = zadani.substr(i, (zadani.find(',', i) - i));
			i = zadani.find(',', i);

			if (i == std::string::npos)
			{
				i = zadani.size();
			}

			if (cast.size() >= 1 && isdigit(cast[0]))
			{
				for (size_t j = 0; j < cast.size(); j++)
				{
					if (!(isdigit(cast[j])))
					{
						throw std::invalid_argument("Neplatny Vstup!");
					}
				}
				double hodnota = std::stod(cast);
				if (hodnota > 950)
				{
					throw std::invalid_argument("Neplatny Vstup");
				}
				kontejner.Vloz(new Naklad(hodnota));
			}
			else if (cast.size() == 1){

				switch (cast[0])
				{
				case 'S': kontejner.Vloz(new Sob());
					break;
				case 'L': kontejner.Vloz(new Los());
					break;
				case 'K': kontejner.Vloz(new StribrnyKralicek());
					break;
				case 'X':
					if (santa == 0)
					{
						kontejner.Vloz(new Santa());
						santa++;
					}
					else{
						throw std::invalid_argument("Vstup obsahuje vice nez jednoho santu!");
					}

					break;
				case 'T':
					kontejner.Vloz(new Technicky());
					technikyVuz++;

					break;
				}
			}
			else{
				throw std::invalid_argument("Neplatny Vstup!");
			}


		}
	}
	else{
		throw std::invalid_argument("Neplatny Vstup!");
	}
	if (santa == 0)
	{
		throw std::invalid_argument("Chyby santa!");
	}
	else if (technikyVuz == 0)
	{
		throw std::invalid_argument("Chyby technicky vuz!");
	}
	return Sprezeni(kontejner);


}

Sprezeni::~Sprezeni(){
	for (UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>>::iterator it = kontejner.begin(); it != kontejner.end(); it = kontejner.begin()){
		kontejner.Odeber(it);
	}
}
