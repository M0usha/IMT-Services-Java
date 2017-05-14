package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by anael on 13/05/2017.
 */
public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite {

    @Override
    public String getNomAlgorithme() {
        return "recherche sync multi";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        CountDownLatch end = new CountDownLatch(bibliotheques.size());

        for (HyperLien<BibliothequeArchive> bibliothequeArchiveHyperLien : bibliotheques) {
            Executors.newCachedThreadPool().execute(() -> {
                BibliothequeArchive a = LienVersRessource.proxy(client, bibliothequeArchiveHyperLien, BibliothequeArchive.class);
                rechercheSync(a, l);
            });
        }

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

        //TODO Make it work

    }
}