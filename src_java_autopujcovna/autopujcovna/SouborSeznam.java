package autopujcovna;

import adl.AbstrDoubleList;
import adl.IAbstrDoubleList;
import auta.IAuto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Objects;
import pobocka.EnumPozice;
import pobocka.IPobocka;

public class SouborSeznam {

    public static <T extends Comparable> void uloz(String jmenoSouboru, IAutopujcovna seznam, eTyp typ) throws IOException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
            switch (typ) {
                case POBOCKA:
                    vystup.writeInt(seznam.getPocetPobocek());
                    Iterator projdiPobocky = seznam.iterator(eTyp.POBOCKA, null);
                    while (projdiPobocky.hasNext()) {
                        IPobocka pobocka = (IPobocka) projdiPobocky.next();
                        vystup.writeObject(pobocka);
                    }

                    break;
                case VYP_AUTA:
                    vystup.writeInt(seznam.getPocetVypujcek());
                    Iterator projdiVyjpucky = seznam.iterator(eTyp.VYP_AUTA, null);
                    while (projdiVyjpucky.hasNext()) {
                        IAuto auto = (IAuto) projdiVyjpucky.next();
                        vystup.writeObject(auto);
                    }
                    break;
            }

            vystup.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    public static IAutopujcovna nacti(String jmenoSouboru, IAutopujcovna seznam, eTyp typ) throws IOException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(jmenoSouboru));
            int pocet;
            switch (typ) {
                case POBOCKA:
                    seznam.zrus();
                    pocet = vstup.readInt();
                    for (int i = 0; i < pocet; i++) {
                        seznam.vlozPobocku((IPobocka) vstup.readObject(), EnumPozice.POSLEDNI);
                    }
                    break;
                case VYP_AUTA:
                    IAbstrDoubleList vyjpujcky = new AbstrDoubleList();
                    pocet = vstup.readInt();
                    for (int i = 0; i < pocet; i++) {
                        vyjpujcky.vlozPosledni(vstup.readObject());
                    }
                    seznam.setVyjpujcky(vyjpujcky);
                    break;

            }

            vstup.close();

        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return seznam;
    }

}
