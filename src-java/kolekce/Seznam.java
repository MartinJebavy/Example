package kolekce;

import java.lang.reflect.Array;
import java.util.Iterator;

public class Seznam<E extends Comparable> implements ISeznam<E> {

    private Prvek<E> prvni, posledni;
    private int pocetPrvku;

    private Seznam() {
        this.pocetPrvku = 0;
        this.prvni = null;
        this.posledni = null;
    }

    public static Seznam create() {
        return new Seznam();
    }

    @Override
    public <K> E odeber(K klic) throws SeznamException, NullPointerException {
        return odeber_(klic);
    }

    private <K> E odeber_(K klic) throws NullPointerException, SeznamException {
        if (klic == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            throw new SeznamException();
        }

        E navratObjektu;
        Prvek<E> predchozi = prvni;
        Prvek<E> aktualni = prvni;
        if (prvni.data.compareTo(klic) == 0) {
            navratObjektu = prvni.data;
            prvni = prvni.dalsi;
            pocetPrvku--;
            return navratObjektu;
        } else {
            for (int i = 0; i < pocetPrvku; i++) {
                if (aktualni.data.compareTo(klic) == 0) {
                    navratObjektu = aktualni.data;
                    predchozi.dalsi = aktualni.dalsi;
                    pocetPrvku--;
                    return navratObjektu;
                }
                predchozi = aktualni;
                aktualni = aktualni.dalsi;
                
            }
        }
        throw new SeznamException();
    }

    @Override
    public <K> E najdi(K klic) throws SeznamException, NullPointerException {
        return najdi_(klic);
    }

    private <K> E najdi_(K klic) throws SeznamException, NullPointerException {
        if (klic == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            throw new SeznamException("V seznamu se nic nenachází");
        }

        Prvek<E> aktualni = prvni;

        while (aktualni != null) {
            if (aktualni.data.compareTo(klic) == 0) {
                return aktualni.data;
            } else {
                aktualni = aktualni.dalsi;
            }
        }
        return null;

    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < pocetPrvku) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), pocetPrvku);
        }
        int i = 0;

        for (Prvek pom = prvni; pom != null; pom = pom.dalsi) {
            a[i++] = (T) pom.data;
        }

        if (a.length > pocetPrvku) {
            a[pocetPrvku] = null;
        }

        return a;
    }

    @Override
    public int getPocetPrvku() {
        return pocetPrvku;
    }

    @Override
    public boolean jePrazdny() {
        return jePrazdny_();
    }

    private boolean jePrazdny_() {
        return pocetPrvku == 0;
    }

    @Override
    public void vloz(E element) throws KolekceException {
        vloz_(element);

    }

    private void vloz_(E element) throws KolekceException {
        if (element == null) {
            throw new NullPointerException();
        }

        Prvek vloz = new Prvek(element);
        if (jePrazdny()) {
            prvni = vloz;
            posledni = vloz;
        } else {
            posledni.dalsi = vloz;
            posledni = vloz;
        }
        pocetPrvku++;
    }

    @Override
    public void zrus() {
        zrus_();
    }

    private void zrus_() {
        pocetPrvku = 0;
        prvni = null;
        posledni = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Prohlidka();
    }

    @Override
    public StavKolekce getStav() {
        if (jePrazdny()) {
            return StavKolekce.PRAZDNY;
        } else {
            return StavKolekce.NEPRAZDNY;
        }

    }

    private class Prvek<E> {

        E data;
        Prvek<E> dalsi;

        public Prvek(E data) {
            if (data != null) {
                this.data = data;

            }

        }

    }

    private class Prohlidka implements Iterator<E> {

        Prvek<E> prochazejici = prvni;
        int pom = 0;

        @Override
        public boolean hasNext() {
            if (pom < pocetPrvku) {
                if (pom == 0) {
                    return true;
                } else {
                    prochazejici = prochazejici.dalsi;
                    return true;
                }
            } else {
                return false;
            }

        }

        @Override
        public E next() {
            if (pom == pocetPrvku) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                pom++;
                return prochazejici.data;
            }
        }

    }

}
