#include "Army.h"
using namespace BattleSimulatorJebavyMartin_2015;

void Army::ExecuteDamage(unsigned int incomingDamage){

	unsigned int defenseActualLine = units[FIRST_DEFENSE_LINE].numberUnits * units[FIRST_DEFENSE_LINE].unit->GetDefenseRating();
	unsigned int healthActualLine = units[FIRST_DEFENSE_LINE].numberUnits * units[FIRST_DEFENSE_LINE].unit->GetHealthPower();
	int modifiedIncomingDamage = incomingDamage - defenseActualLine;
	if (modifiedIncomingDamage > NULL)
	{
		if (modifiedIncomingDamage < healthActualLine)
		{
			unsigned int numberDiedUnits = modifiedIncomingDamage / units[FIRST_DEFENSE_LINE].unit->GetHealthPower();
			costArmy -= units[FIRST_DEFENSE_LINE].unit->GetCost() * numberDiedUnits;
			units[FIRST_DEFENSE_LINE].numberUnits -= numberDiedUnits;
		}
		else if (modifiedIncomingDamage == healthActualLine)
		{
			costArmy -= units[FIRST_DEFENSE_LINE].unit->GetCost() * units[FIRST_DEFENSE_LINE].numberUnits;
			units.pop_front();
		}
		else
		{
			costArmy -= units[FIRST_DEFENSE_LINE].unit->GetCost() * units[FIRST_DEFENSE_LINE].numberUnits;
			units.pop_front();
			modifiedIncomingDamage -= healthActualLine;
			if (units.size() != NULL)
			{
				ExecuteDamage(modifiedIncomingDamage);
			}

		}

	}


}

const NationalityUnit&::NationalityUnit::Gual = NationalityUnit("Gual");
const NationalityUnit&::NationalityUnit::Roman = NationalityUnit("Roman");
