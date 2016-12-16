/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abvs;

import abt.eTypProhl;
import java.util.Iterator;

/**
 *
 * @author J3BY
 * @param <T>
 */
public interface IAbstrBVS<T> {

    public void zrus();

    public boolean jePrazdny();

    public <K extends T> T najdi(K data);

    public <K extends T> void vloz(K data);

    public <K extends T> T odeber(K data);

    public Iterator<T> iterator(eTypProhl typ);

    public int getMohutnost();
    
    
}
