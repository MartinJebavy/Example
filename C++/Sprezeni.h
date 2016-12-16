#pragma once
#ifndef SPREZENI_H
#define SPREZENI_H

#include "UsporadanyKontejner.h"
#include "ClenySprezeni.h"
#include <string>

class Sprezeni{
	UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>> kontejner;
	Sprezeni(const UsporadanyKontejner<ClenSprezeni*, DereferenceLess<ClenSprezeni*>>& _kontejner);
	
public:
	friend class Elfove;
	double ZjistitVykon()const;
	double ZjistitVahu()const;
	void Vypis()const;
	void OdstranNadvahu();
	bool JeLetuSchopny()const;
	~Sprezeni();
};


class Elfove{
public:
	static Sprezeni sestav(std::string zadani);
};


#endif // !SPREZENI_H