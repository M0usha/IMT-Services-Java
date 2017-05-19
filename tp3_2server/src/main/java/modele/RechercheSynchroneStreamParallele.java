package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Objects;

/**
 * Created by anael on 14/05/2017.
 */
public class RechercheSynchroneStreamParallele extends RechercheSynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche sync stream 8";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        return bibliotheques
                .parallelStream()
                .map(e -> LienVersRessource.proxy(client, e, BibliothequeArchive.class))
                .map(e -> rechercheSync(e, l))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
