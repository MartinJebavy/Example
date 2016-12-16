#pragma once
#ifndef BATTLE_H
#define BATTLE_H
#include <fstream>
#include <vector>
#include "Army.h"
#define ROMAN_ARMY "RomanArmy.txt"
#define GUAL_ARMY "GualArmy.txt"

namespace BattleSimulatorJebavyMartin_2015{

	class Battle
	{
		Army Roman;
		Army Gual;
	public:
		Battle(Army& _Roman, Army& _Gual) : Roman(_Roman), Gual(_Gual){}

		Army& GetRomanArmy(){ return Roman; }
		Army& GetGualArmy(){ return Gual; }

		void OneRoundOfBattle(){
			unsigned int damageRomanArmyLine = Roman.GetFirstLineAttackPower();
			unsigned int damageGualArmyLine = Gual.GetFirstLineAttackPower();

			Roman.ExecuteDamage(damageGualArmyLine);
			Gual.ExecuteDamage(damageRomanArmyLine);
		}

		void ExportArmyToDocument(Army& army);

		void ImportArmyFromDocument(Army& army, const std::vector<BattleUnit*>& units);


		~Battle(){
		}

	};
}









#endif // !BATTLE_H
