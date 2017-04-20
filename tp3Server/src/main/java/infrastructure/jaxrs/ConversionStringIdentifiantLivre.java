package infrastructure.jaxrs;

import modele.IdentifiantLivre;
import modele.ImplemIdentifiantLivre;

public class ConversionStringIdentifiantLivre {
    public static IdentifiantLivre fromString(String x) {
        return new ImplemIdentifiantLivre(x);
    }
}
