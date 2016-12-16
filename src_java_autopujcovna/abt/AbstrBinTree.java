/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author J3BY
 * @param <T>
 */
public class AbstrBinTree<T> implements IAbstrBinTree<T>, Serializable {

    private Prvek<T> koren;
    private Prvek<T> aktualni;
    private int pocetPrvkuStromu;
    private Fifo fronta;
    private Lifo zasobnik;

    public AbstrBinTree() {
        koren = null;
        aktualni = null;
        pocetPrvkuStromu = 0;
        fronta = new Fifo();
        zasobnik = new Lifo();

    }

    private class Prvek<T> implements Serializable {

        private T data;
        private Prvek<T> levySyn;
        private Prvek<T> pravySyn;
        private Prvek<T> otec;

        public Prvek(T data) {
            if (data != null) {
                this.data = data;
                levySyn = null;
                pravySyn = null;
                otec = null;
            }
        }
    }

    @Override
    public void zrus() {
        koren = null;
        aktualni = null;
        pocetPrvkuStromu = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocetPrvkuStromu == 0;
    }

    @Override
    public int mohutnost() {
        return pocetPrvkuStromu;
    }

    @Override
    public void vlozKoren(T data) {
        Objects.requireNonNull(data);
        Prvek novyKoren = new Prvek(data);
        this.koren = novyKoren;
        this.aktualni = novyKoren;
        this.pocetPrvkuStromu++;
    }

    @Override
    public void vlozLevyList(T data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(aktualni);
        Objects.requireNonNull(koren);

        if (aktualni.levySyn == null) {
            Prvek novyLevyList = new Prvek(data);
            this.aktualni.levySyn = novyLevyList;
            this.aktualni.levySyn.otec = aktualni;
            this.pocetPrvkuStromu++;
        }
    }

    @Override
    public void vlozPravyList(T data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(aktualni);
        Objects.requireNonNull(koren);

        if (aktualni.pravySyn == null) {
            Prvek novyPravyList = new Prvek(data);
            this.aktualni.pravySyn = novyPravyList;
            this.aktualni.pravySyn.otec = aktualni;
            this.pocetPrvkuStromu++;
        }
    }

    @Override
    public T odeberKoren() {
        Objects.requireNonNull(koren);
        if (koren.levySyn == null && koren.pravySyn == null) {
            T odebiranyKoren = koren.data;
            this.koren = null;
            this.aktualni = null;
            this.pocetPrvkuStromu--;
            return odebiranyKoren;
        }
        return null;
    }

    @Override
    public T odeberLevyList() {
        Objects.requireNonNull(aktualni);
        if (aktualni.levySyn.levySyn == null && aktualni.levySyn.pravySyn == null) {
            T odeberLevyList = aktualni.levySyn.data;
            this.aktualni.levySyn = null;
            this.pocetPrvkuStromu--;
            return odeberLevyList;
        }
        return null;
    }

    @Override
    public T odeberPravyList() {
        Objects.requireNonNull(aktualni);
        if (aktualni.pravySyn.levySyn == null && aktualni.pravySyn.pravySyn == null) {
            T odeberPravyList = aktualni.pravySyn.data;
            this.aktualni.pravySyn = null;
            this.pocetPrvkuStromu--;
            return odeberPravyList;
        }
        return null;
    }

    @Override
    public T zpristupniKoren() {
        if (koren == null) {
            return null;
        } else {
            this.aktualni = koren;
            return aktualni.data;
        }
    }

    @Override
    public T zpristupniAktualni() {
        if (aktualni == null) {
            return null;
        } else {
            return aktualni.data;
        }
    }

    @Override
    public T zpristupniLevehoSyna() {
        if (aktualni.levySyn == null) {
            return null;
        } else {
            this.aktualni = aktualni.levySyn;
            return aktualni.data;
        }
    }

    @Override
    public T zpristupniPravehoSyna() {
        if (aktualni.pravySyn == null) {
            return null;
        } else {
            this.aktualni = aktualni.pravySyn;
            return aktualni.data;
        }
    }

    @Override
    public T zpristupniOtce() {
        this.aktualni = aktualni.otec;
        return aktualni.data;
    }

    @Override
    public Iterator<T> iterator(eTypProhl typ) {
        switch (typ) {
            case HLOUBKA:
                return hloubka();
            case SIRKA:
                return sirka();

        }
        return null;
    }

    private Iterator<T> hloubka() {
        Objects.requireNonNull(koren);
        return new Iterator<T>() {
            Prvek<T> pomAktualni = koren;
            int prochazejiciCislo = 0;

            @Override
            public boolean hasNext() {
                return pocetPrvkuStromu > prochazejiciCislo;
            }

            @Override
            public T next() {
                while (pomAktualni != null) {
                    zasobnik.vlozPrvni(pomAktualni);
                    pomAktualni = pomAktualni.levySyn;
                }
                pomAktualni = (Prvek) zasobnik.odeberPrvni();
                T vracejiciData = pomAktualni.data;
                pomAktualni = pomAktualni.pravySyn;
                prochazejiciCislo++;
                return vracejiciData;
            }

            @Override
            public void remove() {
                if (aktualni != null) { //pokud se koren rovna aktualni, odebere koren v elsu
                    if (aktualni != koren) {
                        Prvek<T> odebiranyPrvek = aktualni;
                        aktualni = aktualni.otec;

                        //Odebirany prvek se nachazi nalevo od otce
                        if (odebiranyPrvek == aktualni.levySyn) {
                            if (odeberLevyList() == null) {
                                if (odebiranyPrvek.pravySyn != null) {
                                    aktualni.levySyn = odebiranyPrvek.pravySyn;
                                    odebiranyPrvek.pravySyn.otec = aktualni;

                                    aktualni = odebiranyPrvek.pravySyn;
                                    if (odebiranyPrvek.levySyn != null) {
                                        while (aktualni.levySyn != null) {
                                            aktualni = aktualni.levySyn;
                                        }
                                        aktualni.levySyn = odebiranyPrvek.levySyn;
                                        odebiranyPrvek.levySyn.otec = aktualni;
                                    }

                                } else {
                                    aktualni.levySyn = odebiranyPrvek.levySyn;
                                    odebiranyPrvek.levySyn.otec = aktualni;
                                }
                                pocetPrvkuStromu--;

                            }
                            //Odebirany prvek se nachazi napravo od otce
                        } else {
                            if (odeberPravyList() == null) {
                                if (odebiranyPrvek.pravySyn != null) {
                                    aktualni.pravySyn = odebiranyPrvek.pravySyn;
                                    odebiranyPrvek.pravySyn.otec = aktualni;

                                    aktualni = odebiranyPrvek.pravySyn;
                                    if (odebiranyPrvek.levySyn != null) {
                                        while (aktualni.levySyn != null) {
                                            aktualni = aktualni.levySyn;
                                        }
                                        aktualni.levySyn = odebiranyPrvek.levySyn;
                                        odebiranyPrvek.levySyn.otec = aktualni;
                                    }

                                } else {
                                    aktualni.pravySyn = odebiranyPrvek.levySyn;
                                    odebiranyPrvek.levySyn.otec = aktualni;
                                }
                                pocetPrvkuStromu--;
                            }

                        }
                    } else {
                        // Odebrani korene,  vytvoreni noveho korene, zretezeni
                        if (odeberKoren() == null) {
                            if (aktualni.levySyn != null && aktualni.pravySyn != null) {
                                Prvek<T> levaVetev = aktualni.levySyn;
                                aktualni = aktualni.pravySyn;
                                aktualni.otec = null;
                                koren = aktualni;

                                while (aktualni.levySyn != null) {
                                    aktualni = aktualni.levySyn;
                                }
                                aktualni.levySyn = levaVetev;
                                levaVetev.otec = aktualni;
                            } else if (aktualni.levySyn == null) {
                                aktualni = aktualni.pravySyn;
                                aktualni.otec = null;
                                koren = aktualni;
                            } else {
                                aktualni = aktualni.levySyn;
                                aktualni.otec = null;
                                koren = aktualni;
                            }
                            pocetPrvkuStromu--;

                        }
                    }

                }
            }

        };
    }

    private Iterator<T> sirka() {
        Objects.requireNonNull(koren);
        fronta.vlozPrvni(koren);
        return new Iterator<T>() {
            Prvek<T> pomAktualni = koren;

            int prochazejiciCislo = 0;

            @Override
            public boolean hasNext() {
                return pocetPrvkuStromu > prochazejiciCislo;
            }

            @Override
            public T next() {
                pomAktualni = (Prvek) fronta.odeberPosledni();

                if (pomAktualni.levySyn != null) {
                    fronta.vlozPrvni(pomAktualni.levySyn);
                }
                if (pomAktualni.pravySyn != null) {
                    fronta.vlozPrvni(pomAktualni.pravySyn);
                }
                prochazejiciCislo++;
                return pomAktualni.data;

            }

        };
    }

}
