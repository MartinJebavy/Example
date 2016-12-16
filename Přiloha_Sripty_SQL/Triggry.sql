create or replace TRIGGER SP_Uzivatele_Validace
before insert or update or delete on SP_UZIVATELE
for each row
  declare 
    delkaHesla EXCEPTION;
    pocetLidiNaUkol number;
  begin
    if inserting or updating then
      :new.jmeno :=initcap(:new.jmeno);
      :new.prijmeni := initcap(:new.prijmeni);
   
      if length (:new.heslo ) < 5 then
        raise delkaHesla;
      end if;
        
      if updating and :old.id_uzivatele <> :new.id_uzivatele then 
        raise_application_error(-20001,'Menit ID_Uzivatele je zakazane');
      end if;
      
    else 
      for x in (select id_ukolu from SP_UKOLY_UZIVATELU where ID_UZIVATELE = :old.id_uzivatele)
        loop
          select count(id_uzivatele) into pocetLidiNaUkol from SP_UKOLY_UZIVATELU where id_ukolu = x.id_ukolu;
          if pocetLidiNaUkol = 1 then
            update SP_UKOLY set id_stav = 4 where id_ukolu = x.id_ukolu;
          end if;           
        end loop;
    
      delete from SP_UKOLY_UZIVATELU where id_uzivatele = :old.id_uzivatele;
      delete from SP_HODNOCENI where id_uzivatele = :old.id_uzivatele;
      delete from SP_OBRAZKY where id_uzivatele = :old.id_uzivatele;
      
    end if;
      
  exception
    when delkaHesla then raise_application_error(-20000,'Hesla musi mít minimálnì 5 znakù');
END;
/

create or replace TRIGGER SP_Vytvor_Hodnoceni
after insert on SP_UZIVATELE
for each row
begin
     insert into SP_Hodnoceni values (:new.id_uzivatele,0,0,0);     
end ;
/

create or replace TRIGGER SP_UKOLY_HODNOCENI
before delete or update on SP_UKOLY
for each row
  declare
  ukolProbiha exception;
  pocet number;
  
  begin
    if deleting then
      if :old.id_stav <> 5 then
        raise ukolProbiha;
      end if;
      delete from SP_UKOLY_UZIVATELU where id_ukolu = :old.id_ukolu;  
      
    else
      if :new.id_stav = 5 then
        for x in (select ID_UZIVATELE from SP_UKOLY_UZIVATELU where id_ukolu = :old.id_ukolu)
          loop
            if :old.id_stav = 2 then
              select uspesne_ukoly into pocet from SP_hodnoceni where id_uzivatele = x.id_uzivatele;
              pocet := pocet + 1;
              update SP_Hodnoceni set uspesne_ukoly = pocet where id_uzivatele = x.id_uzivatele;       
            elsif :old.id_stav = 3 then
              select neuspesne_ukoly into pocet from SP_hodnoceni where id_uzivatele = x.id_uzivatele;
              pocet := pocet + 1;
              update SP_Hodnoceni set neuspesne_ukoly = pocet where id_uzivatele = x.id_uzivatele;        
            end if;     
          end loop;
        delete from SP_UKOLY_UZIVATELU where id_ukolu = :old.id_ukolu;  
      end if;     
    end if;
    
  exception
    when ukolProbiha then raise_application_error(-20000,'Lze odebrat pouze smazaný úkol');
end;
/







