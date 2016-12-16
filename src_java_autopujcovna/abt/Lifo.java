/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abt;

import adl.AbstrDoubleList;
import adl.IAbstrDoubleList;
import java.io.Serializable;

/**
 *
 * @author J3BY
 */
public class Lifo<T> implements Serializable{
    
    private IAbstrDoubleList<T> zasobnik;

    public Lifo() {
        
        zasobnik = new AbstrDoubleList();
    }
    
    public void vlozPrvni(T data){
        zasobnik.vlozPrvni(data);    
    }
    
    public T odeberPrvni() {
        return zasobnik.odeberPrvni();
    }
    
}
