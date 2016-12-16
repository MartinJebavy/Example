package adl;

import java.io.Serializable;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author J3BY
 * @param <T>
 */
public class AbstrDoubleList<T> implements IAbstrDoubleList<T>, Serializable {

    private Prvek<T> prvni, posledni, aktualni;
    private int pocetPolozek;

    public AbstrDoubleList() {
        prvni = null;
        posledni = null;
        aktualni = null;
        pocetPolozek = 0;
    }

    private class Prvek<T> implements Serializable {

        T data;
        Prvek<T> naslednik;
        Prvek<T> predchudce;

        public Prvek(T data) {
            if (data != null) {
                this.data = data;
                naslednik = null;
                predchudce = null;
            }
        }
    }

    @Override
    public int getPocetPolozek() {
        return pocetPolozek;
    }

    @Override
    public void zrus() {
        pocetPolozek = 0;
        prvni = null;
        posledni = null;
        aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return pocetPolozek == 0;
    }

    @Override
    public void vlozPrvni(T prvek) {
        if (prvek == null) {
            throw new NullPointerException();
        }
        Prvek vlozNaPrvni = new Prvek(prvek);
        if (pocetPolozek == 0) {
            prvni = vlozNaPrvni;
            posledni = vlozNaPrvni;
            aktualni = vlozNaPrvni;
            pocetPolozek++;
        } else {
            vlozNaPrvni.naslednik = prvni;
            prvni.predchudce = vlozNaPrvni;
            vlozNaPrvni.predchudce = null;
            prvni = vlozNaPrvni;
            aktualni = prvni;
            pocetPolozek++;
        }
    }

    @Override
    public void vlozPosledni(T prvek) {
        if (prvek == null) {
            throw new NullPointerException();
        }
        Prvek vlozNaPosledni = new Prvek(prvek);

        if (pocetPolozek != 0) {
            posledni.naslednik = vlozNaPosledni;
            vlozNaPosledni.predchudce = posledni;
            vlozNaPosledni.naslednik = null;
            posledni = vlozNaPosledni;
            aktualni = posledni;
            pocetPolozek++;
        } else {
            vlozPrvni(prvek);
        }

    }

    @Override
    public void vlozNaslednika(T prvek) {
        if (prvek == null) {
            throw new NullPointerException();
        }
        Prvek vlozNaslednika = new Prvek(prvek);

        if (pocetPolozek != 0) {

            if (aktualni.naslednik != null) {
                vlozNaslednika.naslednik = aktualni.naslednik;
                vlozNaslednika.predchudce = aktualni;
                aktualni.naslednik.predchudce = vlozNaslednika;
                aktualni.naslednik = vlozNaslednika;
                aktualni = vlozNaslednika;
                pocetPolozek++;
            } else {
                vlozPosledni(prvek);
            }
        } else {
            vlozPrvni(prvek);
        }
    }

    @Override
    public void vlozPredchudce(T prvek) {
        if (prvek == null) {
            throw new NullPointerException();
        }
        Prvek vlozPredchudce = new Prvek(prvek);

        if (pocetPolozek != 0) {
            if (aktualni.predchudce != null) {
                vlozPredchudce.predchudce = aktualni.predchudce;
                vlozPredchudce.naslednik = aktualni;
                aktualni.predchudce.naslednik = vlozPredchudce;
                aktualni.predchudce = vlozPredchudce;
                aktualni = vlozPredchudce;
                pocetPolozek++;
            } else {
                vlozPrvni(prvek);
            }
        } else {
            vlozPrvni(prvek);
        }

    }

    @Override
    public T zpristupniAktualni() {
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() {
       
            aktualni = prvni;
            return aktualni.data;


    }

    @Override
    public T zpristupniPosledni() {

        aktualni = posledni;
        return aktualni.data;

    }

    @Override
    public T zpristupniNaslednika() {
        if (aktualni.naslednik != null) {
            aktualni = aktualni.naslednik;
        }
        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce() {
        if (aktualni.predchudce != null) {
            aktualni = aktualni.predchudce;
        }
        return aktualni.data;
    }

    @Override
    public T odeberAktualni() {
        T odebiranyPrvek = aktualni.data;
        if (aktualni.predchudce != null && aktualni.naslednik != null) {
            aktualni.predchudce.naslednik = aktualni.naslednik;
            aktualni.naslednik.predchudce = aktualni.predchudce;
            aktualni = prvni;
            pocetPolozek--;

        } else if (aktualni.predchudce == null) {
            odeberPrvni();
            aktualni = prvni;
        } else if (aktualni.naslednik == null) {
            odeberPosledni();
            aktualni = prvni;
        }
        return odebiranyPrvek;
    }

    @Override
    public T odeberPrvni() {
        T data = prvni.data;
        if (prvni.naslednik != null) {
            prvni.naslednik.predchudce = null;
            prvni = prvni.naslednik;
            aktualni = prvni;
        } else {
            prvni = null;
            aktualni = null;
            posledni = null;
        }
        pocetPolozek--;
        return data;

    }

    @Override
    public T odeberPosledni() {
        T odeberPosledni = posledni.data;
        if (posledni.predchudce != null) {
            posledni.predchudce.naslednik = null;
            posledni = posledni.predchudce;
            aktualni = posledni;
        } else {
            prvni = null;
            aktualni = null;
            posledni = null;
        }

        pocetPolozek--;
        return odeberPosledni;
    }

    @Override
    public T odeberNaslednika() {
        if (aktualni.naslednik != null) {
            if (aktualni.naslednik.naslednik != null) {
                T odeberNaslednika = aktualni.naslednik.data;
                aktualni.naslednik = aktualni.naslednik.naslednik;
                aktualni.naslednik.predchudce = aktualni;
                pocetPolozek--;
                return odeberNaslednika;
            } else {
                return odeberPosledni();
            }
        } else {
            return null;
        }
    }

    @Override
    public T odeberPredchudce() {
        if (aktualni.predchudce != null) {
            if (aktualni.predchudce.predchudce != null) {
                T odeberPredchudce = aktualni.predchudce.data;
                aktualni.predchudce = aktualni.predchudce.predchudce;
                aktualni.predchudce.naslednik = aktualni;
                pocetPolozek--;
                return odeberPredchudce;
            } else {
                return odeberPrvni();
            }
        } else {
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Prvek<T> prochazejici = prvni;
            int pomPocet = 0;

            @Override
            public boolean hasNext() {
                return pomPocet < pocetPolozek;
            }

            @Override
            public T next() {
                T ret = null;
                if (pomPocet < pocetPolozek) {
                    ret = prochazejici.data;
                    prochazejici = prochazejici.naslednik;
                    pomPocet++;
                }
                return ret;
            }
        };
    }

}
