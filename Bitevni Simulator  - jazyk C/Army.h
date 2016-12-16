#pragma once
#ifndef ARMY_H
#define ARMY_H

#include <deque>
#include <iostream>
#include "BattleUnits.h"

#define FIRST_ATTACK_LINE 0
#define FIRST_DEFENSE_LINE 0
#define MAX_LUCKY_COEFICIENT 21
#define DECIMAL_NUMBER 10


	
namespace BattleSimulatorJebavyMartin_2015{	

	class NationalityUnit{

		std::string _name;
		NationalityUnit(const std::string& name)
		{
			_name = name;
		}
	public:
		const std::string& GetName()const{ return _name; }
		static const NationalityUnit& Gual;
		static const NationalityUnit& Roman;
	};
	
	class Army
	{
		

		struct ArmyUnit
		{
			unsigned int numberUnits;
			BattleUnit* unit;
		};

		std::string nameArmy;
		NationalityUnit nationalityArmy;
		unsigned int costArmy;
		unsigned int costArmyLimit;
		std::deque<ArmyUnit> units;

	public:
		Army(std::string _nameArmy, NationalityUnit _nationalityArmy, unsigned int _costArmyLimit)
			: nameArmy(_nameArmy), nationalityArmy(_nationalityArmy), costArmy(NULL), costArmyLimit(_costArmyLimit){}

		void AddNewArmyUnits(BattleUnit* _unit, unsigned int _numberUnits){
			unsigned int newCostArmy = costArmy + (_numberUnits * _unit->GetCost());
			if (costArmyLimit >= newCostArmy)
			{
				ArmyUnit newArmyUnit;
				newArmyUnit.numberUnits = _numberUnits;
				newArmyUnit.unit = _unit;
				costArmy = newCostArmy;
				units.push_back(newArmyUnit);
			}
			else{
				std::cout << "Cannot" << std::ends;
			}
		}

		void Print()const{
			std::cout << "Army: " << nameArmy << " (" << nationalityArmy.GetName() << ")" << std::endl;
			std::cout << "Cost: " << costArmy << std::endl;

			for (size_t i = 0; i < units.size(); i++)
			{
				std::cout << i + 1 << ". line: " << units[i].numberUnits << "x " << units[i].unit->GetString() << std::endl;
			}

			std::cout << std::endl;
		}

		const std::string& GetNameArmy()const{ return nameArmy; };
		const NationalityUnit& GetNationalityArmy()const{ return nationalityArmy; }
		const unsigned int& GetCostArmy()const{ return costArmy; }
		const unsigned int& GetCostArmyLimit()const{ return costArmyLimit; }

		const unsigned int& GetNumbersOfLines()const{ return units.size(); }
		const unsigned int& GetNumberUnitsInLine(int numberLine)const { return units[numberLine].numberUnits; }
		const std::string& GetNameUnit(int numberLine)const{ return units[numberLine].unit->GetNameUnit(); };

		const bool IsEmpty()const{ return units.empty(); }

		unsigned int GetFirstLineAttackPower()const{

			unsigned int attackPowerAllUnits = 0;
			unsigned int attackPowerUnit = units[FIRST_ATTACK_LINE].unit->GetAttackPower();
			for (size_t i = 0; i < units[FIRST_ATTACK_LINE].numberUnits; i++)
			{
				attackPowerAllUnits += attackPowerUnit * GetLuckyCoeficient();
			}
			return attackPowerAllUnits;
		}
		void ExecuteDamage(unsigned int incomingDamage);

		~Army(){}

	private:
		double GetLuckyCoeficient()const{
			double randomCoeficient = rand() % MAX_LUCKY_COEFICIENT;
			return randomCoeficient / DECIMAL_NUMBER;
		}

	};
}











#endif // !ARMY_H




