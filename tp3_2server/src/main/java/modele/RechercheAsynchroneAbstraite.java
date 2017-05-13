package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.Future;

/**
 * Created by anael on 12/05/2017.
 */
public abstract class RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

    protected Future<HyperLien<LivreRessource>> rechercheAsync(
            HyperLien<BibliothequeArchive> h, Livre l, Client client) {
        //TODO DON'T UNDERSTAND THIS

        BibliothequeArchive a = LienVersRessource.proxy(client, h, BibliothequeArchive.class);

//        return a.chercherAsynchrone(l);
        return null;
    }

    protected Future<HyperLien<LivreRessource>> rechercheAsyncAvecRappel(
            HyperLien<BibliothequeArchive> h, Livre l, Client client,
            InvocationCallback<HyperLien<LivreRessource>> retour) {
        //TODO DON'T UNDERSTAND THIS
        BibliothequeArchive a = LienVersRessource.proxy(client, h, BibliothequeArchive.class);

//        a.chercherAsynchrone(l, new A)
        return null;
    }
}
