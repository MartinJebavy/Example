/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abvs;

import abt.eTypProhl;
import apq.AbstrPriorQueue;
import apq.IAbstrPriorQueue;
import auta.Auto;
import auta.Barva;
import auta.ComparatorKM;
import auta.IAuto;
import auta.Osobni;
import auta.Uzitkova;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        IAbstrPriorQueue<IAuto> prioritniFronta = new AbstrPriorQueue(5, new ComparatorKM());

        IAuto o1 = new Osobni(Barva.CERNA, "O", 1, 362);
        IAuto o2 = new Osobni(Barva.ZLUTA, "M", 7, 362);
        IAuto o3 = new Osobni(Barva.HNEDA, "L", 4, 316);
        IAuto o4 = new Osobni(Barva.ZLUTA, "F", 8, 362);
        IAuto u5 = new Uzitkova(555, "E", 6, 325);
        IAuto o6 = new Osobni(Barva.CERNA, "C", 3, 362);
        IAuto o7 = new Osobni(Barva.ZLUTA, "G", 9, 362);
        IAuto o8 = new Osobni(Barva.HNEDA, "D", 10, 316);
        IAuto o9 = new Osobni(Barva.ZLUTA, "H", 11, 362);
        IAuto u10 = new Uzitkova(555, "I", 14, 2);
        IAuto o11 = new Osobni(Barva.CERNA, "J", 13, 362);
        IAuto o12 = new Osobni(Barva.ZLUTA, "K", 15, 362);
        IAuto o13 = new Osobni(Barva.HNEDA, "N", 2, 316);
        IAuto o14 = new Osobni(Barva.ZLUTA, "B", 12, 362);
        IAuto u15 = new Uzitkova(555, "A", 5, 2);

        Iterator<IAuto> iterator;
        prioritniFronta.vloz(o1);
        prioritniFronta.vloz(o2);
        prioritniFronta.vloz(o3);
        prioritniFronta.vloz(o4);
        prioritniFronta.vloz(u5);
        prioritniFronta.vloz(o6);
        prioritniFronta.vloz(o7);
        prioritniFronta.vloz(o8);
        prioritniFronta.vloz(o9);
        prioritniFronta.vloz(u10);
        prioritniFronta.vloz(o11);
        prioritniFronta.vloz(o12);
        prioritniFronta.vloz(o13);
        prioritniFronta.vloz(o14);
        prioritniFronta.vloz(u15);

//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
//        prioritniFronta.odeberMax();
        /*;*/
        iterator = prioritniFronta.vytvorIterator();
        while (iterator.hasNext()) {
            IAuto auto = iterator.next();
            System.out.println(auto.getPocetKilometru());
        }
        System.out.println("");
    }

}
