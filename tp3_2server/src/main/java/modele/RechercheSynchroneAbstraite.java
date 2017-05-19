package modele;

import infrastructure.jaxrs.HyperLien;

/**
 * Created by anael on 12/05/2017.
 */
public abstract class RechercheSynchroneAbstraite implements AlgorithmeRecherche {
    protected HyperLien<LivreRessource> rechercheSync(Bibliotheque bib, Livre l) {
        return bib.chercher(l);
    }
}
