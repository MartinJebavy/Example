/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auta;

/**
 *
 * @author J3BY
 */
public class Osobni extends Auto {

    private Barva barva;

    public Osobni(Barva barva, String spz, int pocetKilometru, int pocetVypujceni) {
        super(spz, pocetKilometru, pocetVypujceni);
        this.barva = barva;
    }

    @Override
    public Barva getBarva() {
        return barva;
    }

    @Override
    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    @Override
    public String toString() {
        return "Osobní automobil\t" + "Barva: " + barva + "\n" + super.toString();
    }

    @Override
    public int getNosnost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNosnost(int nosnost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(IAuto o1, IAuto o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
