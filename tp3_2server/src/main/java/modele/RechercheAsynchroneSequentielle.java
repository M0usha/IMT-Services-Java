package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by anael on 13/05/2017.
 */
public class RechercheAsynchroneSequentielle extends RechercheAsynchroneAbstraite {

    @Override
    public String getNomAlgorithme() {
        return "recherche async seq";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return bibliotheques.stream()
                .map(e -> rechercheAsync(e, l, client))
                .collect(Collectors.toList()) // Premiere iteration
                .stream()
                .map(Outils::remplirPromesse) // seconde iteration
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
