
package auta;


public enum TypAutomobilu {

    OSOBNI_AUTOMOBIL,
    NAKLADNI_AUTOMOBIL,
    NEZNAMY;
    @Override
    public String toString() {

        switch (this) {
            case OSOBNI_AUTOMOBIL:
                return "osobní automobil";
            case NAKLADNI_AUTOMOBIL:
                return "nákladní automobil";
            default:
                return "Neznámý";
        }
    }
}
