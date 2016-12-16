
package auta;

import java.util.Date;

public class OsobniAutomobil extends Automobil {

    private Barva barva;

    public OsobniAutomobil(Barva barva, Date rokVyroby, String spz, TypAutomobilu typ) {
        super(rokVyroby, spz, typ);
        if (barva != null) {
            this.barva = barva;
        } else {
            throw new NullPointerException();
        }
    }
    

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }
    
    
    
    @Override
    public String toString() {
        String text = "";
        text += super.toString();
        text += "\t\t-\t" + barva+ "\n";
        return text;
    }

}
