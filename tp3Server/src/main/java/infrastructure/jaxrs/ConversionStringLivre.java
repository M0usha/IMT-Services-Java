package infrastructure.jaxrs;

import modele.ImplemLivre;
import modele.Livre;


public class ConversionStringLivre {
    public static Livre fromString(String x) {
        return new ImplemLivre(x);
    }
}
