#include "BattleUnits.h"

using namespace BattleSimulatorJebavyMartin_2015;

std::string BattleUnit::GetString() const {
	std::string text = NameUnit;
	text += " {AP: " + std::to_string(AttackPower);
	text += "  HP: " + std::to_string(HealthPower);
	text += "  DR: " + std::to_string(DefenseRating);
	text += "}";
	return text;
}