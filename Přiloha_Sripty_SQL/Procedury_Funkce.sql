
-- Rozhrani --
create or replace PACKAGE SP_PROCEDURY_FUNKCE as 

  function SP_DEJ_ZNAMENI(datumNarozeni in date) return varchar2;
  
  function SP_JE_SVATEK(datum in date) return varchar2;
  
  procedure SP_UDELIT_ODEBRAT_POCVHALU(rozdanoPochval out number, odebranoPochval out number);
  
  procedure SP_DEADLINE_UKOLU (pocetNesplnenych out number);
  
  procedure SP_POCET_MUZI_ZENY(pocetMuzu out number, pocetZen out number);
  
end SP_PROCEDURY_FUNKCE;
/



-- Telo --
create or replace package BODY SP_PROCEDURY_FUNKCE as 

  FUNCTION SP_DEJ_ZNAMENI(datumNarozeni IN DATE) RETURN VARCHAR2 IS
    id_znameni VARCHAR2(255);
    pomocneDatum DATE;
    mesic NUMBER;
    den NUMBER;
  BEGIN
    mesic := EXTRACT(MONTH FROM datumNarozeni);
    den := EXTRACT(DAY FROM datumNarozeni);
    pomocneDatum := TO_DATE(den||'.'||mesic||'.00');
    IF(mesic = 1) THEN
      IF (den < 20) THEN
        id_znameni := 'Kozoroh';
      ELSE
        id_znameni := 'Vodnáø';
      END IF;
    ELSE
    SELECT id_znameni INTO id_znameni FROM SP_ZNAMENI
      WHERE (pomocneDatum >= DATUM_OD AND pomocneDatum <= DATUM_DO);
      END IF;
    IF id_znameni IS NULL THEN
      RETURN 'Kozoroh';
    ELSE
    RETURN id_znameni;
    END IF;
  END SP_DEJ_ZNAMENI;
  
  function SP_JE_SVATEK(datum in date) return varchar2 as
    popisSvatku varchar2(255);
    pomocneDatum date;
    mesic number;
    den number;
    jeSvatek number;
  begin
    mesic := extract(Month FROM datum);
    den := extract(Day FROM datum);
    pomocneDatum := to_date(den||'.'||mesic||'.00');
    
    select count(*) into jeSvatek from SP_Svatky where id_svatku = pomocneDatum; 
    if jeSvatek <> 0 then
      select nazev into popisSvatku from SP_Svatky where id_svatku = pomocneDatum;   
    else
      popisSvatku := 'Dneska není žádný svátek';
    end if;
    
    return popisSvatku;
    
  end SP_JE_SVATEK;
  
  procedure SP_UDELIT_ODEBRAT_POCVHALU(rozdanoPochval out number, odebranoPochval out number) as 
  pocetPochval number;
  puvodnePochval number;
  begin
    rozdanoPochval := 0;
    odebranoPochval := 0;
    for x in (select * from SP_HODNOCENI)
      loop
         puvodnePochval := x.Pocet_Pochval;
         pocetPochval :=  ((x.uspesne_ukoly - mod(x.uspesne_ukoly,3))/3) - ((x.neuspesne_ukoly - mod(x.neuspesne_ukoly,3))/3);
               
          if pocetPochval >= puvodnePochval then
             rozdanoPochval := rozdanoPochval  + (pocetPochval - puvodnePochval);
              update SP_Hodnoceni set pocet_pochval = pocetPochval where id_uzivatele = x.id_uzivatele;          
          else
            if pocetPochval > 0 then
              odebranoPochval := odebranoPochval + (puvodnePochval - pocetPochval);
              update SP_Hodnoceni set pocet_pochval =  pocetPochval where id_uzivatele = x.id_uzivatele;
            else 
              odebranoPochval := odebranoPochval + puvodnePochval;
              update SP_Hodnoceni set pocet_pochval = 0 where id_uzivatele = x.id_uzivatele;
            end if;
          end if;                 
      end loop;
  end SP_UDELIT_ODEBRAT_POCVHALU;
  
  procedure SP_DEADLINE_UKOLU (pocetNesplnenych out number) as
    begin 
      pocetNesplnenych := 0;
      for x in(select * from SP_UKOLY)
        loop
          if x.id_stav = 1 then
            if x.deadline < sysdate then
              update SP_UKOLY set id_stav = 3 where id_ukolu = x.id_ukolu;
              pocetNesplnenych := pocetNesplnenych + 1;
            end if;
          end if;
        end loop;
  end SP_DEADLINE_UKOLU;
  
  procedure SP_POCET_MUZI_ZENY(pocetMuzu out number, pocetZen out number) as
  begin

    select count(*) into pocetMuzu from SP_uzivatele where pohlavi = 'Muž';
    select count(*) into pocetZen from SP_uzivatele where pohlavi = 'Žena';
  
  end SP_POCET_MUZI_ZENY;

end SP_PROCEDURY_FUNKCE;

/