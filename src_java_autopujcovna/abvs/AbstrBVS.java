/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abvs;

import abt.AbstrBinTree;
import abt.IAbstrBinTree;
import abt.eTypProhl;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author J3BY
 * @param <T>
 */
public class AbstrBVS<T extends Comparable> implements IAbstrBVS<T>, Serializable {

    private IAbstrBinTree<T> binarniStrom;

    public AbstrBVS() {
        this.binarniStrom = new AbstrBinTree();
    }

    @Override
    public void zrus() {
        binarniStrom.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return binarniStrom.jePrazdny();
    }

    @Override
    public <K extends T> T najdi(K data) {
        if (data != null && binarniStrom.zpristupniKoren() != null) {
            T dataVeStrome = binarniStrom.zpristupniKoren();
            while (data.compareTo(dataVeStrome) != 0) {
                if (data.compareTo(dataVeStrome) > 0) {
                  
                    dataVeStrome = binarniStrom.zpristupniPravehoSyna();
                } else {
                    dataVeStrome = binarniStrom.zpristupniLevehoSyna();
                }
                if (dataVeStrome == null) {
                    return null;
                }
            }
            return dataVeStrome;
        }
        return null;

    }

    @Override
    public <K extends T> void vloz(K data) {
        if (binarniStrom.jePrazdny() == false && data != null) {
            binarniStrom.zpristupniKoren();
            while (true) {
                if (data.compareTo(binarniStrom.zpristupniAktualni()) > 0) {
                    if (binarniStrom.zpristupniPravehoSyna() == null) {
                        binarniStrom.vlozPravyList(data);
                        break;
                    }
                } else {
                    if (binarniStrom.zpristupniLevehoSyna() == null) {
                        binarniStrom.vlozLevyList(data);
                        break;

                    }

                }
            }
        } else if (data != null) {
            binarniStrom.vlozKoren(data);
        }

    }

    @Override
    public <K extends T> T odeber(K data) {
        if (najdi(data) != null) {
            T odebiranyPrvek = binarniStrom.zpristupniAktualni();
            Iterator<T> prohlidkaProOdebrani = iterator(eTypProhl.HLOUBKA);
            prohlidkaProOdebrani.remove();
            return odebiranyPrvek;
        }

        return null;

    }

    @Override
    public Iterator<T> iterator(eTypProhl typ) {
        return binarniStrom.iterator(typ);
    }

    @Override
    public int getMohutnost() {
        return binarniStrom.mohutnost();
    }

}
