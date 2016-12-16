#include "Battle.h"
using namespace BattleSimulatorJebavyMartin_2015;

void Battle::ExportArmyToDocument(Army& army){

	std::ofstream saveArmy;


	if (army.GetNationalityArmy().GetName() == NationalityUnit::Roman.GetName())
	{
		saveArmy.open(ROMAN_ARMY, std::ios::out);
	}
	else{
		saveArmy.open(GUAL_ARMY, std::ios::out);
	}


	saveArmy << army.GetNationalityArmy().GetName() << std::endl;
	saveArmy << army.GetNameArmy() << std::endl;
	saveArmy << army.GetCostArmyLimit() << std::endl;

	for (size_t i = 0; i < army.GetNumbersOfLines(); i++)
	{
		saveArmy << army.GetNameUnit(i) << std::endl;
		saveArmy << army.GetNumberUnitsInLine(i) << std::endl;
	}
	saveArmy.close();

}

void Battle::ImportArmyFromDocument(Army& army, const std::vector<BattleUnit*>& units){

	std::string lineInDocument;//new line in document save to lineInDocument
	unsigned int indexInVectorContainer = NULL;
	std::string nameArmy;
	unsigned int costArmyLimit;

	std::ifstream loadArmy;

	if (army.GetNationalityArmy().GetName() == NationalityUnit::Roman.GetName())
	{
		loadArmy.open(ROMAN_ARMY, std::ios::in);
	}
	else{
		loadArmy.open(GUAL_ARMY, std::ios::in);
	}


	getline(loadArmy, lineInDocument); //one jump over line

	getline(loadArmy, lineInDocument);
	nameArmy = lineInDocument;
	getline(loadArmy, lineInDocument);
	costArmyLimit = stoi(lineInDocument);

	army = Army(nameArmy, army.GetNationalityArmy(), costArmyLimit);

	getline(loadArmy, lineInDocument);
	while (!loadArmy.eof()){

		while (lineInDocument.compare(units[indexInVectorContainer]->GetNameUnit()))
		{
			indexInVectorContainer++;
		}
		getline(loadArmy, lineInDocument);
		army.AddNewArmyUnits(units[indexInVectorContainer], stoi(lineInDocument));
		indexInVectorContainer = NULL;
		getline(loadArmy, lineInDocument);
	}
	loadArmy.close();
}

