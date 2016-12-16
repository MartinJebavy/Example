package kolekce;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rozhraní spojového lineárního seznamu
 *
 * @param <E> typový parametr rozhraní
 */
public interface ISeznam<E extends Comparable> extends IKolekce<E>, Iterable<E> {

    /**
     * Metoda odebere se seznamu prvek podle kliče
     *
     * @param <K> generický typový parametr klíče metody
     * @param klic hodnota klíče podle kterého se vybere odebíraný prvek ze
     * seznamu
     * @return návratová hodnota reference odebraných dat
     * @throws Kolekce.ISeznam.SeznamException výjimka, která se vystaví při
     * @throws NullPointerException výjimka, která se vystaví při null v
     * paremetru klíči
     *
     */
    public <K> E odeber(K klic) throws SeznamException, NullPointerException;

    /**
     * Metoda najde v seznamu data podle hodnoty klíče
     *
     * @param <K> generický typový parametr metody
     * @param klic hodnota klíče podle kterého se vyhledá prvek v seznamu
     * @return reference vyhledaných dat v seznamu
     * @throws kolekce.ISeznam.SeznamException
     * @throws kolekce.IKolekce.KolekceException
     */
    public <K> E najdi(K klic) throws SeznamException, NullPointerException;

    /**
     * Metoda vytvoří z lineárního spojového seznamu pole referencí na instance
     * dat vložených do seznamu přesně o velikosti počtu vložených prvků.
     *
     * @param <T> typový parametr metody
     * @param a instance pole pro data ze seznamu. Pokud toto pole bude menší,
     * bude nahrazeno polem o nové velikosti.
     *
     * @return pole s referencemi na data v seznamu
     */
    public <T> T[] toArray(T[] a);

    /**
     * Tato čast rozhraní je pro verzi Java 1.8. Pro nizší verze Java je ji
     * nutno zrusit.
     *
     */
    /**
     * Metoda nemá pro seznam význam.
     *
     * @return vrací trvale hodnotu -1 jako indikaci, že počet prvků v seznamu
     * je neomezen
     */
    @Override
    public default int getKapacita() {
        return -1;
    }

    /**
     * Metoda nemá pro seznam žádný význam
     *
     * @return vrací trvale false
     */
    @Override
    public default boolean jePlny() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public default E odeber() {
        try {
            throw new SeznamException("Metoda odeber neni podporovana.");
        } catch (SeznamException ex) {
            Logger.getLogger(ISeznam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Deklarace vlastní kontrolované výjimky, která se bude vystavovat při
     * překročení rozsahu kolekce
     */
    class SeznamException extends Exception {

        /**
         * Bezparametrický konstruktor kontrolované výjimky.
         */
        public SeznamException() {
        }

        /**
         * Konstruktor s jedním parametrem, kteý specifikuje důvod vystavení
         * výjimky.
         *
         * @param message reference na textový řetězec s popisem důvodu
         * vystavení výjimky.
         */
        public SeznamException(String message) {
            super(message);
        }

    }

}
