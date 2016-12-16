#include "BattleSimulation.h"

#define FIRST_UNIT_ROMAN 0
#define LAST_UNIT_ROMAN 3
#define FIRST_UNIT_GUAL 4
#define LAST_UNIT_GUAL 7
using namespace BattleSimulatorJebavyMartin_2015;
using namespace BattleSimulatorJebavyMartin_2015::RomanUnits;
using namespace BattleSimulatorJebavyMartin_2015::GualUnits;

void secondMenu(Army& army, std::vector<BattleUnit*>& battleUnit, Battle& battle){
	int choose = 1;
	std::string nameArmy;
	unsigned int costArmyLimit;

	while (choose != 0){
		std::cout << "Menu Of " << army.GetNationalityArmy().GetName() << std::endl;
		std::cout << "1 - Create New Army" << std::endl;
		std::cout << "2 - Add New Attack Line" << std::endl;
		std::cout << "3 - Import Army From Document" << std::endl;
		std::cout << "4 - Export Army To Document" << std::endl;
		std::cout << "5 - Print" << std::endl;
		std::cout << "0 - Return" << std::endl;
		std::cout << "Enter choose: " << std::ends;
		std::cin >> choose;
		std::cout << std::endl;

		switch (choose)
		{
		case 1:
			std::cout << "Name Army: " << std::ends;
			std::cin >> nameArmy;
			std::cout << "Cost Army Limit: " << std::ends;
			std::cin >> costArmyLimit;
			if (army.GetNationalityArmy().GetName() == NationalityUnit::Roman.GetName())
			{
				army = Army(nameArmy, NationalityUnit::Roman, costArmyLimit);
			}
			else{
				army = Army(nameArmy, NationalityUnit::Gual, costArmyLimit);
			}
			
			std::cout << "New Army was created" << std::endl;
			std::cout << std::endl;
			break;
		case 2:
			int selectedUnit;
			unsigned int numbersOfUnit;

			std::cout << army.GetNationalityArmy().GetName() << " Units: " << std::endl;
			if (army.GetNationalityArmy().GetName() == NationalityUnit::Gual.GetName())
			{
				for (size_t i = FIRST_UNIT_GUAL; i <= LAST_UNIT_GUAL; i++)
				{
					std::cout << i << " - " << battleUnit[i]->GetNameUnit() << std::endl;
				}
			}
			else{
				for (size_t i = FIRST_UNIT_ROMAN; i <= LAST_UNIT_ROMAN; i++)
				{
					std::cout << i << " - " << battleUnit[i]->GetNameUnit() << std::endl;
				}
			}
			std::cout << "Chosse Unit: " << std::ends;
			std::cin >> selectedUnit;
			std::cout << "Numebers Of Units: " << std::ends;
			std::cin >> numbersOfUnit;
			std::cout << std::endl;

			army.AddNewArmyUnits(battleUnit[selectedUnit], numbersOfUnit);
			
			std::cout << "Add attack line" << std::endl;
			std::cout << std::endl;



			break;
		case 3:
			battle.ImportArmyFromDocument(army, battleUnit);
			std::cout << "Successful import" << std::endl;
			std::cout << std::endl;
			break;
		case 4:
			battle.ExportArmyToDocument(army);
			std::cout << "Successful export" << std::endl;
			std::cout << std::endl;
			break;
		case 5:
			army.Print();
			break;
		}
	}
}

int main(int argc, char** argv){
	srand((unsigned int)time(NULL));
	std::string nameArmyRoman;
	std::string nameArmyGual;
	unsigned int costArmyLimit;

	std::cout << "Name Roman Army: " << std::ends;
	std::cin >> nameArmyRoman;
	std::cout << "Name Gual Army: " << std::ends;
	std::cin >> nameArmyGual;
	std::cout << "Cost Army Limit: " << std::ends;
	std::cin >> costArmyLimit;
	std::cout << std::endl;
	Army roman = Army(nameArmyRoman, NationalityUnit::Roman, costArmyLimit);
	Army gual = Army(nameArmyGual, NationalityUnit::Gual, costArmyLimit);
	Battle battle = Battle(roman, gual);

	std::vector<BattleUnit*> battleUnit = { new Archer(), new Catapult(),
		new Knight(), new  Swordsman(),
		new  Crossbowman(), new FireBallista(),
		new  Maceman(), new Pikeman() };

	int choose = 1;
	while (choose != 0){
		std::cout << "1 - Admin Of Roman Army" << std::endl;
		std::cout << "2 - Admin Of Gual Army" << std::endl;
		std::cout << "3 - Perform simulation" << std::endl;
		std::cout << "0 - Exit" << std::endl;
		std::cout << "Enter choose: " << std::ends;
		std::cin >> choose;
		std::cout << std::endl;
		switch (choose)
		{
		case 1:
			secondMenu(battle.GetRomanArmy(), battleUnit, battle);
			break;
		case 2:
			secondMenu(battle.GetGualArmy(), battleUnit, battle);
			break;
		case 3:
			if (battle.GetRomanArmy().GetCostArmyLimit() == battle.GetGualArmy().GetCostArmyLimit())
			{
				BattleSimulation simulation = BattleSimulation(battle);
				simulation.PerformAttack();
				battle = simulation.GetBattle();

				if (battle.GetGualArmy().IsEmpty() && battle.GetRomanArmy().IsEmpty())
				{
					std::cout << "Result of Battle: Draw" << std::endl;
					std::cout << std::endl;
				}
				else if (battle.GetGualArmy().IsEmpty())
				{
					std::cout << "Result of Battle: Roman Wins" << std::endl;
					std::cout << std::endl;
				}
				else if (battle.GetRomanArmy().IsEmpty())
				{
					std::cout << "Result of Battle: Gual Wins" << std::endl;
					std::cout << std::endl;
				}
			}
			else{
				std::cout << "Unfair battle! Cost army limit must be same." << std::endl;
				std::cout << std::endl;
			}
			break;
		}
	}
	for (size_t i = 0; i < battleUnit.size(); i++)
	{
		delete battleUnit[i];
	}
	return 1;
}