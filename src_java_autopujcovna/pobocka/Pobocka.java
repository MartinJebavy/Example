/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pobocka;

import abt.eTypProhl;
import abvs.AbstrBVS;
import abvs.IAbstrBVS;
import auta.IAuto;
import auta.Osobni;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author J3BY
 */
public class Pobocka implements IPobocka, Serializable {

    private String jmenoPobocky;
    private IAbstrBVS<IAuto> stromAut;

    public Pobocka(String jmenoPobocky) {
        this.jmenoPobocky = jmenoPobocky;
        stromAut = new AbstrBVS();
    }

    @Override
    public String getJmenoPobocky() {
        return jmenoPobocky;
    }

    @Override
    public void setJmenoPobocky(String jmenoPobocky) {
        this.jmenoPobocky = jmenoPobocky;
    }

    @Override
    public int getPocetAutNaPobocce() {
        return stromAut.getMohutnost();
    }

    @Override
    public void vlozAuto(IAuto auto) {
        stromAut.vloz(auto);

    }

    @Override
    public IAuto odeberAuto(String spz) {
        IAuto odebirane = stromAut.odeber(new Osobni(null, spz, 0, 0));
        return odebirane;
    }

    @Override
    public IAuto najdiAuto(String spz) {
        IAuto hledaneAuto = stromAut.najdi(new Osobni(null, spz, 0, 0));
        return hledaneAuto;
       
    }

    @Override
    public void zrus() {
        stromAut.zrus();
    }

    @Override
    public Iterator iterator(eTypProhl typ) {
        return stromAut.iterator(typ);
    }

}
