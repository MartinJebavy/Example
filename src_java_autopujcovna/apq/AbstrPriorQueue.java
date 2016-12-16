/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apq;

import adl.AbstrDoubleList;
import adl.IAbstrDoubleList;
import java.io.Serializable;
import java.util.Comparator;

import java.util.Iterator;

/**
 *
 * @author J3BY
 * @param <T>
 */
public class AbstrPriorQueue<T extends Comparator> implements IAbstrPriorQueue<T>, Serializable {

    private IAbstrDoubleList<T> utridenaFronta;
    private IAbstrDoubleList<T> neutridenaFronta;
    private int pocetUtridenychPrvku;
    private int maxUtridenychPrvku;
    private T hodnotaNaKonciUtrFr;
    private final Comparator comparator;

    public AbstrPriorQueue(int maxUtridenychPrvku, Comparator comparator) {
        utridenaFronta = new AbstrDoubleList();
        neutridenaFronta = new AbstrDoubleList();
        pocetUtridenychPrvku = 0;
        this.maxUtridenychPrvku = maxUtridenychPrvku;
        hodnotaNaKonciUtrFr = null;
        this.comparator = comparator;
    }

    @Override
    public void zrus() {
        utridenaFronta.zrus();
        neutridenaFronta.zrus();
        hodnotaNaKonciUtrFr = null;
        pocetUtridenychPrvku = 0;
        maxUtridenychPrvku = 0;
    }

    @Override
    public boolean jePrazdny() {
        return neutridenaFronta.jePrazdny() && utridenaFronta.jePrazdny();
    }

    @Override
    public void vloz(T prvek) {
        if (pocetUtridenychPrvku != 0) {
            T prvekSeznamu = utridenaFronta.zpristupniPrvni();
            if (comparator.compare(prvek, hodnotaNaKonciUtrFr) < 0) {
                for (int i = 0; i < maxUtridenychPrvku - 1; i++) {
                    if (comparator.compare(prvek, prvekSeznamu) < 0) {
                        utridenaFronta.vlozPredchudce(prvek);
                        pocetUtridenychPrvku++;
                        i = maxUtridenychPrvku;
                    } else {
                        prvekSeznamu = utridenaFronta.zpristupniNaslednika();
                    }
                }
                if (pocetUtridenychPrvku > maxUtridenychPrvku) {
                    prvekSeznamu = utridenaFronta.odeberPosledni();
                    hodnotaNaKonciUtrFr = utridenaFronta.zpristupniPosledni();
                    neutridenaFronta.vlozPosledni(prvekSeznamu);
                    pocetUtridenychPrvku--;
                }
            } else {
                neutridenaFronta.vlozPosledni(prvek);
            }
        } else {
            utridenaFronta.vlozPrvni(prvek);
            hodnotaNaKonciUtrFr = prvek;
            pocetUtridenychPrvku++;
        }

    }

    private void znovuUtrid() {
        if (!(neutridenaFronta.jePrazdny())) {
            T prvek;
            T nasledujiciPrvek;
            while (utridenaFronta.getPocetPolozek() != maxUtridenychPrvku && !(neutridenaFronta.jePrazdny())) {
                //hledani maxima
                prvek = neutridenaFronta.zpristupniPrvni();
                for (int i = 0; i < neutridenaFronta.getPocetPolozek() - 1; i++) {
                    nasledujiciPrvek = neutridenaFronta.zpristupniNaslednika();
                    if (comparator.compare(prvek, nasledujiciPrvek) > 0) {
                        prvek = nasledujiciPrvek;
                    }
                }
                //presouvani maxima
                nasledujiciPrvek = neutridenaFronta.zpristupniPrvni();
                for (int i = 0; i < neutridenaFronta.getPocetPolozek(); i++) {
                    if (comparator.compare(prvek, nasledujiciPrvek) == 0) {
                        neutridenaFronta.odeberAktualni();
                        //this.vloz(prvek);
                        utridenaFronta.vlozPosledni(prvek);
                        hodnotaNaKonciUtrFr = prvek;
                        pocetUtridenychPrvku++;
                        i = neutridenaFronta.getPocetPolozek();
                    } else {
                        nasledujiciPrvek = neutridenaFronta.zpristupniNaslednika();
                    }
                }
            }
        }
    }
//    private void znovuUtrid() {
//        if (!(neutridenaFronta.jePrazdny())) {
//            int pocetPrvkuNF = neutridenaFronta.getPocetPolozek();
//            for (int i = 0; i < pocetPrvkuNF; i++) {
//                this.vloz(neutridenaFronta.odeberPrvni());
//            }
//
//        }
//    }

    @Override
    public T odeberMax() {
        if (!(this.jePrazdny())) {

            T returnData = utridenaFronta.odeberPrvni();
            pocetUtridenychPrvku--;
            if (pocetUtridenychPrvku == 0) {
                this.znovuUtrid();
            }
            return returnData;
        }
        return null;
    }

    @Override
    public T zpristupni() {
        return utridenaFronta.zpristupniPrvni();
    }

    @Override
    public Iterator<T> vytvorIterator() {
        if (this.jePrazdny()) {
            return null;
        }
        return new Iterator<T>() {
            int celkovyPocetPrvku = utridenaFronta.getPocetPolozek() + neutridenaFronta.getPocetPolozek();
            int indexIteratoru = 0;
            T dataProchazena = utridenaFronta.zpristupniPrvni();

            @Override
            public boolean hasNext() {
                return indexIteratoru < celkovyPocetPrvku;
            }

            @Override
            public T next() {
                T returnData = dataProchazena;
                if (indexIteratoru < pocetUtridenychPrvku) {
                    if (indexIteratoru < pocetUtridenychPrvku - 1) {
                        dataProchazena = utridenaFronta.zpristupniNaslednika();
                    } else if (!(neutridenaFronta.jePrazdny())) {
                        dataProchazena = neutridenaFronta.zpristupniPrvni();
                    }
                    indexIteratoru++;
                    return returnData;
                } else {
                    if (indexIteratoru < celkovyPocetPrvku - 1) {
                        dataProchazena = neutridenaFronta.zpristupniNaslednika();
                    }
                    indexIteratoru++;
                    return returnData;
                }

            }
        };
    }

}
