/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopujcovna;

import abt.eTypProhl;
import adl.IAbstrDoubleList;
import auta.IAuto;
import java.util.Iterator;
import pobocka.EnumPozice;
import pobocka.IPobocka;

/**
 *
 * @author J3BY
 */
public interface IAutopujcovna {

    void vlozPobocku(IPobocka Pobocka, EnumPozice pozice);

    IPobocka zpristupniPobocku(EnumPozice pozice);

    IPobocka odeberPobocku(EnumPozice pozice);

    void vlozAuto(IAuto auto);

    IAuto odeberAuto(String spz);

    IAuto najdiAuto(String spz);

    IAuto vypujciAuto(IAuto vypujcene);

    IAuto vratAuto(EnumPozice pozice);

    IAuto zpristupniVypujceneAuto(EnumPozice pozice);

    Iterator iterator(eTyp typ, eTypProhl typProhlidky);

    void zrusPobocku();

    void zrus();

    public int getPocetPobocek();

    public void setJmenoAutopujcovny(String jmenoAutopujcovny);

    public String getJmenoAutopujcovny();

    public int getPocetAutNaPobocce();

    public int getPocetVypujcek();

//    public IAbstrDoubleList getPobocky();
//
       public void setVyjpujcky(IAbstrDoubleList vyjpujcky);
}
