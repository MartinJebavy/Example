package auta;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SouborUlozNactiStav {

    public static void uloz(String jmenoSouboru, SeznamAut seznam) throws IOException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
            vystup.writeInt(seznam.getPocetAutSeznamu());
            for (IAutomobil data : seznam) {
                vystup.writeObject(data);
            }
            vystup.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    public static SeznamAut nacti(String jmenoSouboru, SeznamAut seznam) throws IOException, ISeznamAutomobilu.AutomobilSeznamException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(jmenoSouboru));
            seznam.zrusCelySeznamAut();
            int pocet = vstup.readInt();
            for (int i = 0; i < pocet; i++) {
                seznam.vlozAuto((IAutomobil) vstup.readObject());
            }
            vstup.close();

        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return seznam;
    }

}
