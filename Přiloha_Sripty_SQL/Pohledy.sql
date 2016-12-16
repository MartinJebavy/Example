
CREATE OR REPLACE VIEW SP_VEK ("JMENO", "PRIJMENI", "VEK") AS 
	select jmeno, prijmeni, to_char((sysdate-datum_narozeni)/365, '99.9') vek from SP_UZIVATELE
	order by vek asc;

CREATE OR REPLACE VIEW SP_VSECHNY_AKTIVNI_UKOLY("ID", "N�zev", "Popis", "Vytvo�eno", "Deadline", "Priorita", "Stav") AS
	select id_ukolu ID, SP_UKOLY.NAZEV N�zev, SP_UKOLY.POPIS Popis, to_char(DATUM_VYTVORENI, 'DD. MM. YYYY HH24:MI') Vytvo�eno, to_char(DEADLINE, 'DD. MM. YYYY HH24:MI') Deadline, SP_PRIORITY.STUPEN Priorita, SP_STAVY.NAZEV Stav
	from SP_UKOLY
  left join SP_STAVY using (ID_STAV)
  left join SP_PRIORITY using(ID_PRIORITY)
  where ID_STAV = 1 or ID_STAV = 4
	order by Vytvo�eno desc;
 

  CREATE OR REPLACE VIEW SP_VSECHNY_NEAKTIVNI_UKOLY("ID", "N�zev", "Popis", "Vytvo�eno", "Deadline", "Priorita", "Stav") AS
	select id_ukolu ID, SP_UKOLY.NAZEV N�zev, SP_UKOLY.POPIS Popis, to_char(DATUM_VYTVORENI, 'DD. MM. YYYY HH24:MI') Vytvo�eno, to_char(DEADLINE, 'DD. MM. YYYY HH24:MI') Deadline, SP_PRIORITY.STUPEN Priorita, SP_STAVY.NAZEV Stav
	from SP_UKOLY
  left join SP_STAVY using (ID_STAV)
  left join SP_PRIORITY using(ID_PRIORITY)
  where ID_STAV != 1 AND ID_STAV != 4
	order by Vytvo�eno desc;

CREATE OR REPLACE VIEW SP_VSECHNY_UKOLY_BEZ_UZIVATELU(Nazev, Popis, Vytvo�en, Deadline, Priorita) AS
	select NAZEV N�zev, SP_UKOLY.POPIS Popis, to_char(DATUM_VYTVORENI, 'DD. MM. YYYY HH24:MI') Vytvo�en, NVL(to_char(DEADLINE, 'DD. MM. YYYY HH24:MI'), 'Nem�') Deadline, SP_PRIORITY.STUPEN Priorita
	FROM SP_UKOLY
	left join SP_PRIORITY USING(ID_PRIORITY)
	where ID_STAV = 1 or ID_STAV = 4
  MINUS
	select NAZEV, SP_UKOLY.POPIS, to_char(DATUM_VYTVORENI, 'DD. MM. YYYY HH24:MI'), to_char(DEADLINE, 'DD. MM. YYYY HH24:MI'), SP_PRIORITY.STUPEN
	FROM SP_UKOLY
	left join SP_PRIORITY USING(ID_PRIORITY)
	join SP_UKOLY_UZIVATELU on SP_UKOLY.ID_UKOLU = SP_UKOLY_UZIVATELU.ID_UKOLU;