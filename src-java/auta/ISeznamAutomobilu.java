package auta;

import java.util.Iterator;


public interface ISeznamAutomobilu extends Iterable<IAutomobil> {

    int getPocetAutSeznamu();

    Iterator<IAutomobil> getProhlidka();

    IAutomobil najdiAuto(String spz) throws AutomobilSeznamException;

    IAutomobil vyradAuto(String spz) throws AutomobilSeznamException;

    void vlozAuto(IAutomobil element) throws AutomobilSeznamException;

    void zrusCelySeznamAut();
    
    IAutomobil[] getPoleAutomobilu();
    
     IAutomobil[] getPoleAutomobilu(TypAutomobilu typ);
    
    /**
     * Deklarace vlastní kontrolované výjimky, která se bude vystavovat 
     * chybě práce se seznamem automobilů
     */
    class AutomobilSeznamException extends Exception {

        /**
         * Bezparametrický konstruktor kontrolované výjimky.
         */
        public AutomobilSeznamException() {
        }

        /**
         * Konstruktor s jedním parametrem, kteý specifikuje důvod vystavení
         * výjimky.
         *
         * @param message reference na textový řetězec s popisem důvodu
         * vystavení výjimky.
         */
        public AutomobilSeznamException(String message) {
            super(message);
        }

    }


}
