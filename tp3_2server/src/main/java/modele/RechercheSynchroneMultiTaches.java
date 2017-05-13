package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.ws.rs.client.Client;
import java.util.List;

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
        HyperLiens<LivreRessource> livres = bibliotheques.stream().map(e -> {
            BibliothequeArchive a = LienVersRessource.proxy(client, e, BibliothequeArchive.class);
            return a.repertorier();
        }).reduce(new HyperLiens<LivreRessource>(), (sum, element) -> Outils.sommeHyperLiens(sum, element));


    }
}
