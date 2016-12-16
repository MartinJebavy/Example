/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pobocka;

import abt.eTypProhl;
import auta.IAuto;
import java.util.Iterator;

/**
 *
 * @author J3BY
 */
public interface IPobocka {

    void vlozAuto(IAuto auto);

    IAuto odeberAuto(String spz);

    IAuto najdiAuto(String spz);

    void zrus();

    Iterator iterator(eTypProhl typ);

    public int getPocetAutNaPobocce();

    public void setJmenoPobocky(String jmenoAutopujcovny);

    public String getJmenoPobocky();

}
