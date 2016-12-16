package auta;

import kolekce.IKolekce;
import kolekce.ISeznam;
import kolekce.Seznam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class SeznamAut implements ISeznamAutomobilu {

    private static SeznamAut instance;
    private final ISeznam<IAutomobil> seznam;

    public SeznamAut() {
        seznam = Seznam.create();
    }

    public static SeznamAut getInstance() {
        if (instance == null) {
            instance = new SeznamAut();
        }
        return instance;
    }

    @Override
    public int getPocetAutSeznamu() {
        return seznam.getPocetPrvku();
    }

    @Override
    public Iterator<IAutomobil> getProhlidka() {
        return seznam.iterator();
    }

    @Override
    public IAutomobil najdiAuto(String spz) throws ISeznamAutomobilu.AutomobilSeznamException {
        try {
            return seznam.najdi(new Automobil(spz));
        } catch (ISeznam.SeznamException ex) {
            throw new NullPointerException();
        }
    }

    @Override
    public IAutomobil vyradAuto(String spz) throws ISeznamAutomobilu.AutomobilSeznamException {
        try {
            return seznam.odeber(new Automobil(spz));
        } catch (ISeznam.SeznamException ex) {
            throw new ISeznamAutomobilu.AutomobilSeznamException();
        }
    }

    @Override
    public void vlozAuto(IAutomobil element) throws ISeznamAutomobilu.AutomobilSeznamException {
        if (element == null) {
            throw new NullPointerException();
        }
        try {
            seznam.vloz(element);
        } catch (IKolekce.KolekceException ex) {
            throw new ISeznamAutomobilu.AutomobilSeznamException();
        }
    }

    @Override
    public void zrusCelySeznamAut() {
        seznam.zrus();
    }

    @Override
    public IAutomobil[] getPoleAutomobilu() {
        return seznam.toArray(new IAutomobil[0]);
    }

    public IAutomobil vratAuto(IAutomobil[] pole, int a) {
        return pole[a];
    }

    @Override
    public IAutomobil[] getPoleAutomobilu(TypAutomobilu typ) {
        IAutomobil[] aktualni, pom;
        aktualni = seznam.toArray(new IAutomobil[0]);
        pom = new IAutomobil[aktualni.length];
        int a = 0;
        for (int i = 0; i < aktualni.length; i++) {
            if (aktualni[i].getTyp() == typ) {
                pom[a] = aktualni[i];
                a++;
            }
        }
        if (pom == null) {
            throw new NullPointerException();
        }

        IAutomobil[] navrat = new IAutomobil[a];
        System.arraycopy(pom, 0, navrat, 0, navrat.length);
        return navrat;
    }

    @Override
    public Iterator<IAutomobil> iterator() {
        return seznam.iterator();
    }
    
    

    public void nactiSeznam(String text) throws AutomobilSeznamException, ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        String[] result = text.split(",");
        for (int i = 0; i < result.length; i = i + 6) {

            if (result[i].contains("N") == true) {
                Date date1 = format1.parse(result[i + 4]);
                Date date2 = format2.parse(result[i + 2]);
                NakladniAutomobil nakl = new NakladniAutomobil(Integer.parseInt(result[i + 5]), date2, result[i + 1], TypAutomobilu.NAKLADNI_AUTOMOBIL);
                nakl.setDatumPoslednihoMyti(date1);
                nakl.setStavTachometru(Integer.parseInt(result[i + 3]));
                vlozAuto(nakl);
            } else {
                Date date1 = format1.parse(result[i + 4]);
                Date date2 = format2.parse(result[i + 2]);
                OsobniAutomobil osobni = new OsobniAutomobil((Barva.valueOf(result[i + 5])), date2, result[i + 1], TypAutomobilu.OSOBNI_AUTOMOBIL);
                osobni.setDatumPoslednihoMyti(date1);
                osobni.setStavTachometru(Integer.parseInt(result[i + 3]));
                vlozAuto(osobni);
            }

        }

    }

    public OsobniAutomobil vytvorOS(String barva, String rokVyroby, String spz, String posledniMyti, String stavTachometru) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        Date date1 = format1.parse(posledniMyti);
        Date date2 = format2.parse(rokVyroby);
        OsobniAutomobil osobni = new OsobniAutomobil((Barva.valueOf(barva)), date2, spz, TypAutomobilu.OSOBNI_AUTOMOBIL);
        osobni.setDatumPoslednihoMyti(date1);
        osobni.setStavTachometru(Integer.parseInt(stavTachometru));
        return osobni;
    }
    public NakladniAutomobil vytvorNakl(String delka, String rokVyroby, String spz, String posledniMyti, String stavTachometru) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        Date date1 = format1.parse(posledniMyti);
        Date date2 = format2.parse(rokVyroby);
        NakladniAutomobil nakl = new NakladniAutomobil(Integer.parseInt(delka), date2, spz, TypAutomobilu.NAKLADNI_AUTOMOBIL);
        nakl.setDatumPoslednihoMyti(date1);
        nakl.setStavTachometru(Integer.parseInt(stavTachometru));
        return nakl;
    }

}
