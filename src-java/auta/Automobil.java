package auta;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Automobil implements Serializable, IAutomobil {

    private Date datumPoslednihoMyti = null;
    private Date rokVyroby;
    private final String spz;
    private int stavTachometru = 0;
    private final TypAutomobilu typ;

    public Automobil(Date rokVyroby, String spz, TypAutomobilu typ) {
        this.rokVyroby = rokVyroby;
        this.spz = spz;
        this.typ = typ;
    }

    public Automobil(String spz) {
        this.spz = spz;
        this.typ = TypAutomobilu.NEZNAMY;
    }

    @Override
    public Date getDatumPoslednihoMyti() {
        return datumPoslednihoMyti;
    }

    @Override
    public String getSpz() {
        return spz;
    }

    @Override
    public int getStavTachometru() {
        return stavTachometru;
    }

    @Override
    public Date getRokVyroby() {
        return rokVyroby;
    }

    @Override
    public TypAutomobilu getTyp() {
        return typ;
    }

    @Override
    public void setDatumPoslednihoMyti(Date datumPoslednihoMyti) {
        if (datumPoslednihoMyti == null) {
            throw new NullPointerException();
        }
        this.datumPoslednihoMyti = datumPoslednihoMyti;
    }

    @Override
    public void setStavTachometru(int stavTachometru) {
        if (stavTachometru > 0) {
            this.stavTachometru = stavTachometru;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            if (o instanceof Automobil) {
                return spz.compareTo(((Automobil) o).getSpz());
            } else {
                return 1;
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat datumMyti = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat datumVyroby = new SimpleDateFormat("yyyy");
        String text = "";
        text += typ + "\t" + datumVyroby.format(rokVyroby) + "\t\t" + spz;
        text += "\t" + stavTachometru + "\t\t" + datumMyti.format(datumPoslednihoMyti);
        return text;
    }

}




