package modele;

import infrastructure.jaxrs.HyperLien;

import javax.ws.rs.client.Client;
import java.util.List;

/**
 * Created by anael on 12/05/2017.
 */
public abstract class RechercheSynchroneAbstraite implements AlgorithmeRecherche {
    protected HyperLien<LivreRessource> rechercheSync(Bibliotheque bib, Livre l) {
        return bib.chercher(l);
    }
}
