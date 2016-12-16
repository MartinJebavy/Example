#include "ClenySprezeni.h"
#include <iostream>

#define vahaVozu 50
#define vahaSanty 150
#define vahaTechVuz 100
#define vykonSob 500
#define vykonLos 300
#define vykonStrKralicek 10

#define prioritaSob 1
#define prioritaLos 2
#define prioritaStrKralicek 3
#define prioritaSanta 4
#define prioritaTechnicky 5
#define prioritaNaklad 1006

using namespace std;

// deklarace tridy ClenSprezeni
bool operator<(ClenSprezeni& sprezeni1, ClenSprezeni& sprezeni2){
	return sprezeni1.prioritaVeSprezeni < sprezeni2.prioritaVeSprezeni;
}

// deklarace tridy Vuz
Vuz::Vuz(const double& _vaha):vaha(_vaha){}
void Vuz::Vypis() const {
	cout << "\tVaha vozu: " << vaha;
}
double Vuz::GetVaha()const{
	return vaha;
}

// deklarace tridy Santa
Santa::Santa() : Vuz(vahaSanty + vahaVozu){ prioritaVeSprezeni = prioritaSanta; }
void Santa::Vypis()const{
	cout << "Santa\t\t";
	Vuz::Vypis();
	cout << endl;
}

// deklarace tridy Naklad
Naklad::Naklad(const double& _vahaDarku) : vahaDarku(_vahaDarku), Vuz(_vahaDarku + vahaVozu){ prioritaVeSprezeni = prioritaNaklad - _vahaDarku; }
void Naklad::Vypis()const{
	cout << "Naklad\t\t";
	Vuz::Vypis();
	cout << "\t\tVaha darku: "<< vahaDarku<< endl;
}

// deklarace tridy Technicky
Technicky::Technicky() :Vuz(vahaTechVuz){ prioritaVeSprezeni = prioritaTechnicky; }
void Technicky::Vypis()const{
	cout << "Technicky\t";
	Vuz::Vypis();
	cout << endl;
}

// deklarace tridy TaznaSila
TaznaSila::TaznaSila(const double& _taznyVykon) : taznyVykon(_taznyVykon){}
void TaznaSila::Vypis()const{ 
	cout << "Tazny vykon: " << taznyVykon << endl; 
}
double TaznaSila::GetTaznyVykon()const{
	return taznyVykon;
}

// deklarace tridy Sob
Sob::Sob() :TaznaSila(vykonSob){ prioritaVeSprezeni = prioritaSob; }
void Sob::Vypis() const{
	cout << "Sob\t\t\t";
	TaznaSila::Vypis();
}

// deklarace tridy Los
Los::Los() :TaznaSila(vykonLos){ prioritaVeSprezeni = prioritaLos; }
void Los::Vypis() const{
	cout << "Los\t\t\t";
	TaznaSila::Vypis();
}

// deklarace tridy StribrnyKralicek
StribrnyKralicek::StribrnyKralicek() :TaznaSila(vykonStrKralicek){ prioritaVeSprezeni = prioritaStrKralicek; }
void StribrnyKralicek::Vypis() const{
	cout << "Stribrny Kralicek\t";
	TaznaSila::Vypis();
}







