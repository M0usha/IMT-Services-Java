package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
                .map(future -> { // on démarre la seconde itération
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                    }
                    return null;
                })
                .filter(livreRessourceHyperLien -> !isNull(livreRessourceHyperLien))
                .findFirst()
                .orElse(null);
    }
}
