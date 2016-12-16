#pragma once
#ifndef CLENY_SPREZENI_H
#define CLENY_SPREZENI_H

class ClenSprezeni
{
protected:
	 size_t prioritaVeSprezeni;

public:	
	virtual void Vypis() const = 0;
	friend bool operator<(ClenSprezeni& sprezeni1, ClenSprezeni& sprezeni2);
};


class Vuz : public ClenSprezeni
{
	 const double vaha;
protected:
	Vuz(const double& _vaha);

public:
	virtual void Vypis() const override;
	double GetVaha()const;
};
class Santa : public Vuz{

public:
	Santa();
	virtual void Vypis() const override;

};
class Naklad : public Vuz {
	const double vahaDarku;

public:
	Naklad(const double& _vahaDarku);
	virtual void Vypis()const override;

};
class Technicky : public Vuz {
public:
	Technicky();
	virtual void Vypis()const override;
};

class TaznaSila : public ClenSprezeni{
	const double taznyVykon;

public:
	TaznaSila(const double& _taznyVykon);
	virtual void Vypis()const override;
	double GetTaznyVykon()const;
};
class Sob : public TaznaSila {

public:
	Sob();
	virtual void Vypis() const override;
};

class Los : public TaznaSila {

public:
	Los();
	virtual void Vypis() const override;
};

class StribrnyKralicek : public TaznaSila {

public:
	StribrnyKralicek();
	virtual void Vypis() const override;

};
#endif // !CLENY_SPREZENI_H