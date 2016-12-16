/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopujcovna;

import abt.eTypProhl;
import adl.AbstrDoubleList;
import adl.IAbstrDoubleList;
import auta.IAuto;
import java.util.Iterator;
import pobocka.EnumPozice;
import pobocka.IPobocka;

/**
 *
 * @author J3BY
 */
public class Autopujcovna implements IAutopujcovna {

    private String jmenoAutopujcovny;
    private final IAbstrDoubleList<IPobocka> seznamPobocek;
    private IAbstrDoubleList<IAuto> seznamVyjpujcek;

    public Autopujcovna(String jmenoAutopujcovny) {
        this.jmenoAutopujcovny = jmenoAutopujcovny;
        seznamPobocek = new AbstrDoubleList();
        seznamVyjpujcek = new AbstrDoubleList();
    }

    @Override
    public String getJmenoAutopujcovny() {
        return jmenoAutopujcovny;
    }

    @Override
    public void setJmenoAutopujcovny(String jmenoAutopujcovny) {
        this.jmenoAutopujcovny = jmenoAutopujcovny;
    }

    @Override
    public int getPocetPobocek() {
        return seznamPobocek.getPocetPolozek();
    }

    @Override
    public void vlozPobocku(IPobocka pobocka, EnumPozice pozice) {
        switch (pozice) {
            case PRVNI:
                seznamPobocek.vlozPrvni(pobocka);
                break;
            case POSLEDNI:
                seznamPobocek.vlozPosledni(pobocka);
                break;
            case NASLEDNIK:
                seznamPobocek.vlozNaslednika(pobocka);
                break;
            case PREDCHUDCE:
                seznamPobocek.vlozPredchudce(pobocka);
                break;

        }
    }

    @Override
    public IPobocka zpristupniPobocku(EnumPozice pozice) {
        switch (pozice) {
            case PRVNI:
                return seznamPobocek.zpristupniPrvni();
            case POSLEDNI:
                return seznamPobocek.zpristupniPosledni();
            case NASLEDNIK:
                return seznamPobocek.zpristupniNaslednika();
            case PREDCHUDCE:
                return seznamPobocek.zpristupniPredchudce();
            case AKTUALNI:
                return seznamPobocek.zpristupniAktualni();
        }
        return null;
    }

    @Override
    public IPobocka odeberPobocku(EnumPozice pozice) {
        switch (pozice) {
            case PRVNI:
                return seznamPobocek.odeberPrvni();
            case POSLEDNI:
                return seznamPobocek.odeberPosledni();
            case NASLEDNIK:
                return seznamPobocek.odeberNaslednika();
            case PREDCHUDCE:
                return seznamPobocek.odeberPredchudce();
            case AKTUALNI:
                return seznamPobocek.odeberAktualni();
        }
        return null;
    }

    @Override
    public void vlozAuto(IAuto auto) {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        aktualniPobocka.vlozAuto(auto);
    }

    @Override
    public IAuto odeberAuto(String spz) {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        return aktualniPobocka.odeberAuto(spz);
    }

    @Override
    public IAuto najdiAuto(String spz) {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        return aktualniPobocka.najdiAuto(spz);
    }

    @Override
    public IAuto vypujciAuto(IAuto vypujcene) {
        if (vypujcene != null) {
            Iterator<IPobocka> iteratorPobocky = seznamPobocek.iterator();
            while (iteratorPobocky.hasNext()) {
                IPobocka aktualniPobocka = iteratorPobocky.next();
                if (aktualniPobocka.odeberAuto(vypujcene.getSpz()) != null) {                   
                    vypujcene.setPocetVypujceni(vypujcene.getPocetVypujceni() + 1);
                    seznamVyjpujcek.vlozPrvni(vypujcene);
                    break;
                }

            }
        }
        return vypujcene;
    }

    @Override
    public IAuto vratAuto(EnumPozice pozice) {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        IAuto vracene = null;
        switch (pozice) {
            case PRVNI:
                vracene = seznamVyjpujcek.odeberPrvni();
                break;
            case POSLEDNI:
                vracene = seznamVyjpujcek.odeberPosledni();
                break;
            case NASLEDNIK:
                vracene = seznamVyjpujcek.odeberNaslednika();
                break;
            case PREDCHUDCE:
                vracene = seznamVyjpujcek.odeberPredchudce();
                break;
            case AKTUALNI:
                vracene = seznamVyjpujcek.odeberAktualni();
                break;
        }
        int novyStavTachometru = (int) (50 + (5051 - 50) * Math.random());
        vracene.setPocetKilometru(vracene.getPocetKilometru() + novyStavTachometru);
        aktualniPobocka.vlozAuto(vracene);
        return vracene;
    }

    @Override
    public Iterator iterator(eTyp typ, eTypProhl typProhlidky) {
        switch (typ) {
            case AUTA:
                IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
                switch (typProhlidky) {
                    case HLOUBKA:
                        return aktualniPobocka.iterator(eTypProhl.HLOUBKA);
                    case SIRKA:
                        return aktualniPobocka.iterator(eTypProhl.SIRKA);
                }

            case POBOCKA:
                return seznamPobocek.iterator();

            case VYP_AUTA:
                return seznamVyjpujcek.iterator();
        }
        return null;

    }

    @Override
    public void zrusPobocku() {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        aktualniPobocka.zrus();
    }

    @Override
    public void zrus() {
        seznamPobocek.zrus();
        seznamVyjpujcek.zrus();

    }

    @Override
    public IAuto zpristupniVypujceneAuto(EnumPozice pozice) {
        switch (pozice) {
            case PRVNI:
                return seznamVyjpujcek.zpristupniPrvni();
            case POSLEDNI:
                return seznamVyjpujcek.zpristupniPosledni();
            case NASLEDNIK:
                return seznamVyjpujcek.zpristupniNaslednika();
            case PREDCHUDCE:
                return seznamVyjpujcek.zpristupniPredchudce();
            case AKTUALNI:
                return seznamVyjpujcek.zpristupniAktualni();
        }
        return null;
    }

    @Override
    public int getPocetAutNaPobocce() {
        IPobocka aktualniPobocka = seznamPobocek.zpristupniAktualni();
        return aktualniPobocka.getPocetAutNaPobocce();
    }

    @Override
    public int getPocetVypujcek() {
        return seznamVyjpujcek.getPocetPolozek();
    }

//    @Override
//    public IAbstrDoubleList getPobocky() {
//        return seznamPobocek;
//    }
    @Override
    public void setVyjpujcky(IAbstrDoubleList vyjpujcky) {
        seznamVyjpujcek = vyjpujcky;
    }

}
