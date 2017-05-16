package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Objects.isNull;

/**
 * Created by anael on 13/05/2017.
 */
public class RechercheAsynchroneMultiTaches extends RechercheAsynchroneAbstraite {

    static ExecutorService executeur = Executors.newCachedThreadPool();


    @Override
    public String getNomAlgorithme() {
        return "recherche async multi";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        CountDownLatch end = new CountDownLatch(bibliotheques.size());



        for (HyperLien<BibliothequeArchive> bibliothequeArchiveHyperLien : bibliotheques) {
            executeur.submit(() -> {
                BibliothequeArchive a = LienVersRessource.proxy(client, bibliothequeArchiveHyperLien, BibliothequeArchive.class);
                HyperLien<LivreRessource> ressource = rechercheAsync(a, l);

                if (isNull(ressoure)) {
                    end.countDown();
                } else {
                    return;
                }
            });
        }

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
