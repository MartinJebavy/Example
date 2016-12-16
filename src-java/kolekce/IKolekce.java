package kolekce;

import java.util.Iterator;

/**
 * RozhranĂ­ je urÄŤeno pro implementaci jednoduchĂ˝ch kolekcĂ­ jako je fronta nebo
 * zĂˇsobnĂ­k. MĹŻĹľe bĂ˝t tĂ©Ĺľ pouĹľito jako rodiÄŤovskĂ© rozhranĂ­ pro dalĹˇĂ­ rozhranĂ­.
 * RozhranĂ­ kromÄ› poĹľadovanĂ˝ch metod dĂˇle obsahuje vĂ˝ÄŤet moĹľnĂ˝ch stavĹŻ kolekce a
 * deklaraci kontrolovanĂ© vĂ˝jimky.
 *
 * @param <E> parametr rozhranĂ­, kterĂ˝ zastupuje jakĂ˝koliv budoucĂ­ typ
 * vklĂˇdanĂ˝ch dat
 */
public interface IKolekce<E> extends Iterable<E> {

    /**
     * Metoda vracĂ­ aktuĂˇlnĂ­ poÄŤet prvkĹŻ v kolekci.
     *
     * @return poÄŤet prvkĹŻ
     */
    int getPocetPrvku();

    /**
     * Metoda vracĂ­ maximĂˇlnĂ­ poÄŤet prvkĹŻ, kterĂ˝ se dĂˇ vloĹľit do kolekce.
     *
     * @return poÄŤet prvkĹŻ, kdyĹľ je 0 nenĂ­ maximĂˇlnĂ­ poÄŤet stanoven, potom mĹŻĹľe
     * bĂ˝t poÄŤet prvkĹŻ v kolekci libovolnĂ˝
     */
    int getKapacita();

    /**
     * Metoda vracĂ­ pĹ™Ă­znak, zda je kolekce plnĂˇ.
     *
     * @return vracĂ­ true, kdyĹľ je kolekce plnĂˇ
     */
    boolean jePlny();

    /**
     * Metoda vracĂ­ pĹ™Ă­znak, zda kolekce je prĂˇzdnĂˇ
     *
     * @return vracĂ­ true, kdyĹľ je kolekce prĂˇzdnĂˇ
     */
    boolean jePrazdny();

    /**
     * Metoda odebĂ­rĂˇ z kolekce data. PoĹ™adĂ­ odebrĂˇnĂ­ je dĂˇno implementacĂ­.
     *
     * @return vraci referenci na data odebĂ­ranĂ©ho prvku.
     * @throws kolekce.IKolekce.KolekceException VĂ˝jimka se vystavĂ­, kdyĹľ se
     * odebĂ­rĂˇ z prĂˇzdnĂ© kolekce nebo z pĹ™eteÄŤenĂ© kolekce
     *
     */
    E odeber() throws KolekceException;

    /**
     * Metoda vklĂˇdĂˇ do kolekce data. PoĹ™adĂ­ vklĂˇdĂˇnĂ­ je dĂˇno implementacĂ­.
     *
     *
     * @param element reference na vklĂˇdanĂˇ data
     * @throws kolekce.IKolekce.KolekceException VĂ˝jimka se vystavĂ­, kdyĹľ se
     * vloĹľĂ­ data do plnĂ© kolekce nebo pĹ™eplnÄ›nĂ©
     *
     */
    void vloz(E element) throws KolekceException;

    /**
     * Metoda pĹ™Ă­padnÄ› zruĹˇĂ­ reference na vĹˇechny vloĹľenĂ© prvky a ze vĹˇech stavĹŻ
     * kolekce pĹ™ejde do prĂˇzdnĂ©ho stavu.
     */
    void zrus();

    /**
     * Metoda vraci instanci iterĂˇtoru.
     *
     * PoznĂˇmka: Metoda je zde uvedeno jenom zdĹŻvodu pĹ™ehlednosti, prtoĹľe je jiĹľ
     * deklarovĂˇna v rozhranĂ­ Iterable
     *
     * @return reference na instanci iterĂˇtoru
     */
    @Override
    Iterator<E> iterator();

    /**
     * Metoda poskytne aktuĂˇlnĂ­ stav kolekce
     *
     * @return stav kolekce
     */
    StavKolekce getStav();

    /**
     * Deklarace moĹľnĂ˝ch stavĹŻ kolekce
     */
    enum StavKolekce {

        /**
         * V tomto stavu kolekce neobsahuje ĹľĂˇdnĂ˝ prvek.
         */
        PRAZDNY,
        /**
         * V tomoto stavu se kolekce nachĂˇzĂ­, kdyĹľ obsahuje alespoĹ jeden prvek
         * a nenĂ­ plnĂˇ, pĹ™eteÄŤenĂˇ a anĂ­ podteÄŤenĂˇ.
         */
        NEPRAZDNY,
        /**
         * V tomto stavu se kolekce nachĂˇzĂ­, kdyĹľ se dosĂˇhlo plnĂ© kapacity
         * kolekce.
         */
        PLNY,
        /**
         * V tomto stavu se kolekce nachĂˇzĂ­, kdyĹľ byl uÄŤinÄ›n pokus vloĹľenĂ­ prvku
         * plnĂ© kolekce
         */
        PRETECENI,
        /**
         * V tomto stavu se kolekce nachĂˇzĂ­, kdyĹľ byl uÄŤinÄ›n pokus od odebrĂˇnĂ­
         * prvku prĂˇzdnĂ© kolekce
         */
        PODTECENI;
    }

    /**
     * Deklarace vlastnĂ­ kontrolovanĂ© vĂ˝jimky, kterĂˇ se bude vystavovat pĹ™i
     * pĹ™ekroÄŤenĂ­ rozsahu kolekce
     */
    class KolekceException extends Exception {

        /**
         * BezparametrickĂ˝ konstruktor kontrolovanĂ© vĂ˝jimky.
         */
        public KolekceException() {
        }

        /**
         * Konstruktor s jednĂ­m parametrem, kteĂ˝ specifikuje dĹŻvod vystavenĂ­
         * vĂ˝jimky.
         *
         * @param message reference na textovĂ˝ Ĺ™etÄ›zec s popisem dĹŻvodu
         * vystavenĂ­ vĂ˝jimky.
         */
        public KolekceException(String message) {
            super(message);
        }


    }

}