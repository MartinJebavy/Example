package souborSeznam;

import kolekce.IKolekce;
import kolekce.ISeznam;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SouborSeznam {

    public static <T extends Comparable> void uloz(String jmenoSouboru, ISeznam<T> seznam) throws IOException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
            vystup.writeInt(seznam.getPocetPrvku());
            for (T data : seznam) {
                vystup.writeObject(data);
            }
            vystup.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    public static <T extends Comparable> ISeznam<T> nacti(String jmenoSouboru, ISeznam<T> seznam) throws IOException {
        try {
            Objects.requireNonNull(jmenoSouboru);
            Objects.requireNonNull(seznam);
            ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(jmenoSouboru));
            seznam.zrus();
            int pocet = vstup.readInt();
            for (int i = 0; i < pocet; i++) {
                seznam.vloz((T) vstup.readObject());
            }
            vstup.close();

        } catch (IOException | IKolekce.KolekceException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return seznam;
    }

}
