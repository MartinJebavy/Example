/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abt;

import java.util.Iterator;

/**
 *
 * @author J3BY
 * @param <T>
 */
public interface IAbstrBinTree<T>{

    public void zrus();

    public boolean jePrazdny();

    public int mohutnost();

    public void vlozKoren(T data);

    public void vlozLevyList(T data);

    public void vlozPravyList(T data);

    public T odeberKoren();

    public T odeberLevyList();

    public T odeberPravyList();

    public T zpristupniKoren();
    
    public T zpristupniAktualni();

    public T zpristupniLevehoSyna();

    public T zpristupniPravehoSyna();

    public T zpristupniOtce();

    public Iterator<T> iterator(eTypProhl typ);

}
