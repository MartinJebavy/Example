/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author J3BY
 */
public class UmyjNakladniAuta {
    
    IAutomobil[] poleNakladniAuta;
    int delka;

    
    public UmyjNakladniAuta(SeznamAut seznam) {

        poleNakladniAuta = seznam.getPoleAutomobilu(TypAutomobilu.NAKLADNI_AUTOMOBIL);
    }

    public IAutomobil[] vratFrontu() {
        
        for (int j = 0; j < (poleNakladniAuta.length - 1); j++) {
            for (int i = 0; i < (poleNakladniAuta.length - 1); i++) {
                if (poleNakladniAuta[i].getDatumPoslednihoMyti().compareTo(poleNakladniAuta[i + 1].getDatumPoslednihoMyti()) > 0) {
                    IAutomobil pom = poleNakladniAuta[i];
                    poleNakladniAuta[i] = poleNakladniAuta[i + 1];
                    poleNakladniAuta[i + 1] = pom;
                }
            }
        }
        delka = 0;
        int k;
        for (k = 0; k < poleNakladniAuta.length; k++) {
            if (delka <= 460) {
                NakladniAutomobil nakl = (NakladniAutomobil) poleNakladniAuta[k];
                delka += nakl.getDelka();
            }else{
                
                break;
            }
        }
        
        IAutomobil[] navrat = new IAutomobil[k];
        System.arraycopy(poleNakladniAuta, 0, navrat, 0, navrat.length);

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
    public int getDelka() {
        return delka;
    }
}
