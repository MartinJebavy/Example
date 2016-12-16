/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apq;

import java.util.Iterator;

/**
 *
 * @author J3BY
 */
public interface IAbstrPriorQueue<T>{

    public void zrus();
    
    public boolean jePrazdny();

    public void vloz(T prvek);

    public T odeberMax();
    
    public T zpristupni();
    
    public Iterator<T> vytvorIterator();
    
}
