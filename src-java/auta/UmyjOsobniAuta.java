package auta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UmyjOsobniAuta {

    IAutomobil[] poleOsobniAuta;

    public UmyjOsobniAuta(SeznamAut seznam) {

        poleOsobniAuta = seznam.getPoleAutomobilu(TypAutomobilu.OSOBNI_AUTOMOBIL);
    }

    public IAutomobil[] vratFrontu() {
        IAutomobil[] navrat = null; 
        for (int j = 0; j < (poleOsobniAuta.length - 1); j++) {
            for (int i = 0; i < (poleOsobniAuta.length - 1); i++) {
                if (poleOsobniAuta[i].getDatumPoslednihoMyti().compareTo(poleOsobniAuta[i + 1].getDatumPoslednihoMyti()) > 0) {
                    IAutomobil pom = poleOsobniAuta[i];
                    poleOsobniAuta[i] = poleOsobniAuta[i + 1];
                    poleOsobniAuta[i + 1] = pom;
                }
            }
        }
        if (poleOsobniAuta.length >= 16) {
            navrat = new IAutomobil[16];
        }else if(poleOsobniAuta.length < 16){
            navrat = new IAutomobil[poleOsobniAuta.length];
        }
        
        System.arraycopy(poleOsobniAuta, 0, navrat, 0, navrat.length);

        return navrat;

    }

    public void nastavCasMyti(SeznamAut seznamVstup, IAutomobil[] navrat) throws ISeznamAutomobilu.AutomobilSeznamException, ParseException {
        Calendar kalendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        String datum = format1.format(kalendar.getTime());
        Date date1 = format1.parse(datum);

        for (int i = 0; i < navrat.length; i++) {
            navrat[i].setDatumPoslednihoMyti(date1);
            seznamVstup.vyradAuto(navrat[i].getSpz());
            seznamVstup.vlozAuto(navrat[i]);
        }

    }

}
