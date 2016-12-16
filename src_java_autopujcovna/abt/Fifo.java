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
public class Fifo<T> implements Serializable{
    
    private IAbstrDoubleList<T> fronta;

    public Fifo() {
        fronta = new AbstrDoubleList();
    }
    public void vlozPrvni(T data){
        fronta.vlozPrvni(data);    
    }
    
    public T odeberPosledni() {
        return fronta.odeberPosledni();
    }
    
}
