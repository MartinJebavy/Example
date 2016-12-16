/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auta;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author J3BY
 */
public abstract class Auto implements IAuto, Serializable, Comparable<IAuto>, Comparator<IAuto>{

    private String spz;
    private int pocetKilometru, pocetVypujceni;

    public Auto(String spz, int pocetKilometru, int pocetVypujceni) {
        this.spz = spz;
        this.pocetKilometru = pocetKilometru;
        this.pocetVypujceni = pocetVypujceni;
    }

    @Override
    public String getSpz() {
        return spz;
    }

    @Override
    public void setSpz(String spz) {
        this.spz = spz;
    }

    @Override
    public int getPocetKilometru() {
        return pocetKilometru;
    }

    @Override
    public void setPocetKilometru(int pocetKilometru) {
        this.pocetKilometru = pocetKilometru;
    }

    @Override
    public int getPocetVypujceni() {
        return pocetVypujceni;
    }

    @Override
    public void setPocetVypujceni(int pocetVypujceni) {
        this.pocetVypujceni = pocetVypujceni;
    }

    @Override
    public int compareTo(IAuto object){
   
            return this.spz.compareTo(object.getSpz());        
    }
    
    @Override
    public String toString() {
        return "     SPZ: " + spz + "\tPočet najetých kilometrů: " + pocetKilometru + "\t\tPočet Vypujčení: " + pocetVypujceni + "\n";
    }

}
