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
public class Uzitkova extends Auto {

    private int nosnost;

    public Uzitkova(int nosnost, String spz, int pocetKilometru, int pocetVypujceni) {
        super(spz, pocetKilometru, pocetVypujceni);
        this.nosnost = nosnost;
    }

    @Override
    public int getNosnost() {
        return nosnost;
    }

    @Override
    public void setNosnost(int nosnost) {
        this.nosnost = nosnost;
    }

    @Override
    public String toString() {
        return "Užitkový automobil\t" + "Nosnost: " + nosnost + "\n" + super.toString();
    }

    @Override
    public Barva getBarva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBarva(Barva barva) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(IAuto o1, IAuto o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
