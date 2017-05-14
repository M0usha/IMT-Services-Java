package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created by anael on 14/05/2017.
 */
public class RechercheAsynchroneStreamParallele extends RechercheAsynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche async stream 8";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return bibliotheques
                .parallelStream()
                .map(e -> rechercheAsync(e, l, client))
                .map(Outils::remplirPromesse)
                .filter(livreRessourceHyperLien -> !isNull(livreRessourceHyperLien))
                .findFirst()
                .orElse(null);
    }
}
