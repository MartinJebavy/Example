#include "Sprezeni.h"
#include <iostream>
#include <stdexcept>
#include <string>
#include <ctime>

#define CAS_AKUTALIZACE 1 
using namespace std;

int timer(){
	time_t t = time(NULL);
	while (1){
		if (t + CAS_AKUTALIZACE == time(NULL)){
			return 1;
		}
	}
}

void OdstraneniNadvahy(Sprezeni& sprezeni){
	string anoNe;
	size_t pocetOdNakladu = 0;
	double pocetOdVahy = sprezeni.ZjistitVahu();
	cout << endl;
	cout << "Sprezeni neni letuschopne!" << endl;
	cout << "Chcete odstranit nadvahu? " << ends;
	while (anoNe.find_first_of("yn") == string::npos){
		cout << "[y/n] ->" << ends;
		cin >> anoNe;
	}
	if (anoNe.compare("y") == 0)
	{
		while (!(sprezeni.JeLetuSchopny()))
		{
			sprezeni.OdstranNadvahu();
			pocetOdNakladu++;
		}
		pocetOdVahy = pocetOdVahy - sprezeni.ZjistitVahu();
		cout << endl;
		cout << "Santovy nakladni sane -> Slozeni po odstraneni nadvahy" << endl;
		cout << "******************************************************" << endl;
		sprezeni.Vypis();
		cout << endl;
		cout << "Bylo odstraneno: " << pocetOdNakladu << "x naklad o celkove hmotnosti " << pocetOdVahy << endl;
	}
}


int main(int argc, char** argv){
	size_t vyber = 1;
	string vstup;
	Sprezeni sprezeni = Elfove::sestav("S,K,L,X,T,100,150,300,300");

	while (vyber != 0){
		cout << endl;
		cout << "Santovy nakladni sane" << endl;
		cout << "*********************" << endl;
		cout << "1 - Vytvorit sprezeni" << endl;
		cout << "2 - Vypsat slozeni" << endl;
		cout << "3 - Vlastnosti sprezeni" << endl;
		cout << "4 - Roznest darky" << endl;
		cout << "0 - Ukoncit program" << endl;
		cout << "Vyber moznost: " << ends;
		cin >> vstup;
		cout << endl;
		system("cls");
		try{
			if ((vstup.find_first_not_of("01234")) != std::string::npos){
				throw invalid_argument("Nekorektni volba");
			}
			else
			{
				vyber = stoul(vstup);
			}
			cout << endl;

			switch (vyber)
			{
			case 1:
				cout << "Santovy nakladni sane -> Vytvorit sprezeni" << endl;
				cout << "******************************************" << endl;
				cout << "Moznost volby -> {" << endl;
				cout << "S = Sob, L = Los, K = Stribrny kralicek, X = Santa, T = Technicky vuz " << endl;
				cout << "<1-950> = darky, cislo predstavuje hmotnost darku" << endl;
				cout << "Zpet do hlavniho menu - z }" << endl;;
				cout << "Priklad korektniho vstupu: S,L,S,L,X,T,T,100,500,300,500,600,100,500,S,S,L" << endl;
				cout << "Vyber moznost: " << ends;
				cin >> vstup;
				system("cls");
				if (vstup.compare("z") != 0)
				{
					sprezeni = Elfove::sestav(vstup);
					cout << "----Sprezeni Uspesne Vytvoreno----" << endl;
				}
				break;
			case 2:
				cout << "Santovy nakladni sane -> Vypsat slozeni" << endl;
				cout << "***************************************" << endl;
				sprezeni.Vypis();
				break;
			case 3:
				cout << "Santovy nakladni sane -> Vlastnosti sprezeni" << endl;
				cout << "********************************************" << endl;
				cout << "Celkovy vykon  ->   " << sprezeni.ZjistitVykon() << endl;
				cout << "Celkova vaha   ->   " << sprezeni.ZjistitVahu() << endl;
				cout << "Letu schopnost ->  " << ends;
				if (sprezeni.JeLetuSchopny())
				{
					cout << "ANO" << endl;
				}
				else{
					cout << "NE" << endl;
					OdstraneniNadvahy(sprezeni);
				}
				
				break;
			case 4:
				size_t pocetCarek = 1;
				cout << "Santovy nakladni sane -> Roznest darky" << endl;
				cout << "**************************************" << endl;
				if (sprezeni.JeLetuSchopny())
				{

					cout << "Darky se roznasi -> " << ends;
					while (pocetCarek != 11)
					{
						if (timer() == 1)
						{
							cout << "-" << ends;
							pocetCarek++;
						}
					}
					cout << " <- Darky rozneseny" << endl;
				}
				else{
					OdstraneniNadvahy(sprezeni);
				}
				break;
			}
		}
		catch (invalid_argument& ex){
			cout << "Invalid argument: " << ex.what() << endl;
		}

	}
	return 0;
}