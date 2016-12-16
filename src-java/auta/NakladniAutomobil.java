
package auta;

import java.util.Date;


public class NakladniAutomobil extends Automobil {

    private final int delka;

    public NakladniAutomobil(int delka, Date rokVyroby, String spz, TypAutomobilu typ) {
        super(rokVyroby, spz, typ);
        if (delka > 0) {
            this.delka = delka;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getDelka() {
        return delka;
    }

    @Override
    public String toString() {
        String text = "";
        text += super.toString();
        text += "\t\t" + delka + "\t-"+ "\n";
        return text;
    }

}
