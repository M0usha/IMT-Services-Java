package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.Objects.isNull;

/**
 * Created by anael on 13/05/2017.
 */
public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite {

    static ExecutorService executeur = Executors.newCachedThreadPool();

    @Override
    public String getNomAlgorithme() {
        return "recherche sync multi";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        CountDownLatch end = new CountDownLatch(bibliotheques.size());

        HyperLien<LivreRessource> ressource = new HyperLien();
        HyperLien<LivreRessource> emptyRessource = new HyperLien<>();

        for (HyperLien<BibliothequeArchive> bibliothequeArchiveHyperLien : bibliotheques) {
            executeur.submit(() -> {
                BibliothequeArchive a = LienVersRessource.proxy(client, bibliothequeArchiveHyperLien, BibliothequeArchive.class);
                HyperLien<LivreRessource> result = rechercheSync(a, l);

                if (isNull(result)) {
                    end.countDown();
                } else {
                    ressource.setUri(result.getUri());

                    while (end.getCount() > 0) {
                        end.countDown();
                    }
                }
            });
        }

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0 == ressource.getUri().compareTo(emptyRessource.getUri()) ? null : ressource;
    }
}
