package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by anael on 13/05/2017.
 */
public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite {

    @Override
    public String getNomAlgorithme() {
        return "recherche sync seq";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        HyperLiens<LivreRessource> livres = bibliotheques.stream().map(e -> {
            BibliothequeArchive a = LienVersRessource.proxy(client, e, BibliothequeArchive.class);
            return a.repertorier();
        }).reduce(new HyperLiens<LivreRessource>(), (sum, element) -> Outils.sommeHyperLiens(sum, element));

        Stream<HyperLien<LivreRessource>> resources = livres
                .getLiens()
                .stream()
                .filter(e -> l.getTitre().equals(LienVersRessource.proxy(client, e, LivreRessource.class).getTitre()));

        if (resources.findFirst().isPresent()) {
            return resources.findFirst().get();
        }

        return null;
    }
}
