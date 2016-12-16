package kolekce;

import java.util.Iterator;

public class Fronta<E> implements IKolekce<E> {

    private int kapacita, pocetPrvku;
    private E[] pole;
    private StavKolekce stav;

    public Fronta(int kapacita) {
        if (1 < kapacita) {
            this.pole = (E[]) (new Object[kapacita]);
            this.pocetPrvku = 0;
            this.kapacita = kapacita;
            this.stav = StavKolekce.PRAZDNY;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getPocetPrvku() {
        return pocetPrvku;
    }

    @Override
    public int getKapacita() {
        return kapacita;
    }

    @Override
    public boolean jePlny() {
        if (stav == StavKolekce.PLNY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean jePrazdny() {
        if (stav == StavKolekce.PRAZDNY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E odeber() throws KolekceException {
        
        E odeberPrvek;
        if (stav == StavKolekce.PRAZDNY) {
            stav = StavKolekce.PODTECENI;
            throw new KolekceException();
        }
        if (stav == StavKolekce.PRETECENI) {
            throw new KolekceException();
        }

        odeberPrvek = pole[0];
        pocetPrvku--;

        if (pocetPrvku == 0) {
            stav = StavKolekce.PRAZDNY;
        } else {
            for (int i = 0; i < pocetPrvku; i++) {
                pole[i] = pole[i + 1];
            }
            pole[pocetPrvku] = null;
            stav = StavKolekce.NEPRAZDNY;
        }

        return odeberPrvek;

    }

    @Override
    public void vloz(E element) throws KolekceException {

        if (stav == StavKolekce.PLNY || stav == StavKolekce.PRETECENI) {
            stav = StavKolekce.PRETECENI;
            throw new KolekceException();
        }

        pole[pocetPrvku] = element;
        pocetPrvku++;

        if (pocetPrvku == kapacita) {
            stav = StavKolekce.PLNY;
        } else {
            stav = StavKolekce.NEPRAZDNY;
        }

    }

    @Override
    public void zrus() {
        pole = null;
        pocetPrvku = 0;
        stav = StavKolekce.PRAZDNY;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int pom = 0;

            @Override
            public boolean hasNext() {
                return pom < pocetPrvku;

            }

            @Override
            public E next() {

                if (pom == pocetPrvku) {
                    throw new ArrayIndexOutOfBoundsException();
                } else {
                    return pole[pom++];
                }

            }

            @Override
            public void remove() {
                
            }
        };
    }

    @Override
    public StavKolekce getStav() {
        return stav;
    }

}
