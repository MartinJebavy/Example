#pragma once

#ifndef BATTLE_UNITS_H
#define BATTLE_UNITS_H
#include <string>
#ifndef ARMYSTATS

#define ATTACK_POWER_ARCHER 50
#define HEALTH_POWER_ARCHER 100
#define DEFENSE_RATING_ARCHER 20
#define COST_ARCHER 150

#define ATTACK_POWER_CATAPULT 100
#define HEALTH_POWER_CATAPULT 500
#define DEFENSE_RATING_CATAPULT 30
#define COST_CATAPULT 800

#define ATTACK_POWER_SWORDSMAN 35
#define HEALTH_POWER_SWORDSMAN 200
#define DEFENSE_RATING_SWORDSMAN 40
#define COST_SWORDSMAN 200

#define ATTACK_POWER_KNIGHT 75
#define HEALTH_POWER_KNIGHT 300
#define DEFENSE_RATING_KNIGHT 40
#define COST_KNIGHT 400

#define ATTACK_POWER_CROSSBOWMAN 75
#define HEALTH_POWER_CROSSBOWMAN  125
#define DEFENSE_RATING_CROSSBOWMAN  30
#define COST_CROSSBOWMAN  200

#define ATTACK_POWER_FIREBALLISTA 80
#define HEALTH_POWER_FIREBALLISTA  400
#define DEFENSE_RATING_FIREBALLISTA 30
#define COST_FIREBALLISTA  600

#define ATTACK_POWER_MACEMAN 40
#define HEALTH_POWER_MACEMAN 170
#define DEFENSE_RATING_MACEMAN 30
#define COST_MACEMAN  100

#define ATTACK_POWER_PIKEMAN 45
#define HEALTH_POWER_PIKEMAN 250
#define DEFENSE_RATING_PIKEMAN 40
#define COST_PIKEMAN 250
#endif // !ARMYSTATS


namespace BattleSimulatorJebavyMartin_2015{

	class BattleUnit
	{
		std::string NameUnit;
		unsigned int AttackPower;
		unsigned int HealthPower;
		unsigned int DefenseRating;
		unsigned int Cost;

	protected:
		BattleUnit(std::string _NameUnit, unsigned int _AttackPower, unsigned int _HealthPower, unsigned int _DefenseRating, unsigned int _Cost)
			: NameUnit(_NameUnit), AttackPower(_AttackPower), HealthPower(_HealthPower), DefenseRating(_DefenseRating), Cost(_Cost){}

	public:

		virtual std::string GetString() const = 0;
		const unsigned int& GetAttackPower()const { return AttackPower; }
		const unsigned int& GetHealthPower()const { return HealthPower; }
		const unsigned int& GetDefenseRating()const{ return DefenseRating; }
		const unsigned int& GetCost()const{ return Cost; }
		const std::string& GetNameUnit()const { return NameUnit; }
	};

	namespace RomanUnits{

		class Archer : public BattleUnit
		{

		public:
			Archer() : BattleUnit("Archer", ATTACK_POWER_ARCHER, HEALTH_POWER_ARCHER, DEFENSE_RATING_ARCHER, COST_ARCHER){
			}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

		class Catapult : public BattleUnit
		{

		public:
			Catapult() : BattleUnit("Catapult", ATTACK_POWER_CATAPULT, HEALTH_POWER_CATAPULT, DEFENSE_RATING_CATAPULT, COST_CATAPULT){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

		class Swordsman : public BattleUnit
		{

		public:
			Swordsman() : BattleUnit("Swordsman", ATTACK_POWER_SWORDSMAN, HEALTH_POWER_SWORDSMAN, DEFENSE_RATING_SWORDSMAN, COST_SWORDSMAN){}
			std::string GetString() const override{ return BattleUnit::GetString(); }

		};

		class Knight : public BattleUnit
		{

		public:
			Knight() : BattleUnit("Knight", ATTACK_POWER_KNIGHT, HEALTH_POWER_KNIGHT, DEFENSE_RATING_KNIGHT, COST_KNIGHT){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

	}

	namespace GualUnits{

		class Crossbowman : public BattleUnit
		{
		public:
			Crossbowman() : BattleUnit("Crossbowman", ATTACK_POWER_CROSSBOWMAN, HEALTH_POWER_CROSSBOWMAN, DEFENSE_RATING_CROSSBOWMAN, COST_CROSSBOWMAN){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

		class  FireBallista : public BattleUnit
		{
		public:
			FireBallista() : BattleUnit("Fire Ballista", ATTACK_POWER_FIREBALLISTA, HEALTH_POWER_FIREBALLISTA, DEFENSE_RATING_FIREBALLISTA, COST_FIREBALLISTA){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

		class Maceman : public BattleUnit
		{
		public:
			Maceman() : BattleUnit("Maceman", ATTACK_POWER_MACEMAN, HEALTH_POWER_MACEMAN, DEFENSE_RATING_MACEMAN, COST_MACEMAN){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};

		class  Pikeman : public BattleUnit
		{
		public:
			Pikeman() : BattleUnit("Pikeman", ATTACK_POWER_PIKEMAN, HEALTH_POWER_PIKEMAN, DEFENSE_RATING_PIKEMAN, COST_PIKEMAN){}
			std::string GetString() const override{ return BattleUnit::GetString(); }
		};
	}
}

#endif // !BATTLE_UNITS_H
