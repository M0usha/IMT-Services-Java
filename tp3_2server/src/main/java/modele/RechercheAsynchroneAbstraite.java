package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;
import java.util.concurrent.Future;

/**
 * Created by anael on 12/05/2017.
 */
public abstract class RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

    protected Future<HyperLien<LivreRessource>> rechercheAsync(
            HyperLien<BibliothequeArchive> h, Livre l, Client client) {

        return client
                .target(h.getUri())
                .queryParam(JAXRS.CLE_TITRE, l)
                .request()
                .async()
                .get(ImplemPortail.typeRetourChercherAsync());
    }

    protected Future<HyperLien<LivreRessource>> rechercheAsyncAvecRappel(
            HyperLien<BibliothequeArchive> h, Livre l, Client client,
            InvocationCallback<HyperLien<LivreRessource>> retour) {
        return client
                .target(h.getUri())
                .queryParam(JAXRS.CLE_TITRE, l)
                .request()
                .async()
                .get(retour);
    }
}
