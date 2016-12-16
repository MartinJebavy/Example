package auta;

import java.util.Date;

public interface IAutomobil extends Comparable {

    Date getDatumPoslednihoMyti();

    String getSpz();

    int getStavTachometru();

    public Date getRokVyroby();

    public TypAutomobilu getTyp();

    void setDatumPoslednihoMyti(Date datumPoslednihoMyti);

    void setStavTachometru(int stavTachometru);

}
