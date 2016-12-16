package adl;

import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author J3BY
 * @param <T>
 */
public interface IAbstrDoubleList<T> extends Iterable<T> {

    void zrus();

    boolean jePrazdny();

    void vlozPrvni(T prvek);

    void vlozPosledni(T prvek);

    void vlozNaslednika(T prvek);

    void vlozPredchudce(T prvek);

    T zpristupniAktualni();

    T zpristupniPrvni();

    T zpristupniPosledni();

    T zpristupniNaslednika();

    T zpristupniPredchudce();

    T odeberAktualni();

    T odeberPrvni();

    T odeberPosledni();

    T odeberNaslednika();

    T odeberPredchudce();
    
    public int getPocetPolozek();    

    @Override
    Iterator<T> iterator();
}
