package infrastructure.jaxrs;

import modele.ImplemLivre;
import modele.Livre;

/**
 * Created by anael on 20/04/2017.
 */
public class ConversionStringLivre {
    public static Livre fromString(String x) {
        return new ImplemLivre(x);
    }
}
