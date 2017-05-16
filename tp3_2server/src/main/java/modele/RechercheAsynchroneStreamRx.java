package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.ws.rs.client.Client;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created by anael on 14/05/2017.
 */
public class RechercheAsynchroneStreamRx extends RechercheAsynchroneAbstraite {
    @Override
    public String getNomAlgorithme() {
        return "recherche async stream rx";
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client) {
        Observable<HyperLien<LivreRessource>> observable = Observable
                .from(bibliotheques)
                .flatMap(bibliotheque -> Observable
                        .from(rechercheAsync(bibliotheque, l, client))
                        .subscribeOn(Schedulers.io())
                )
                .filter(hyperLienObservable -> !isNull(hyperLienObservable))
                .firstOrDefault(null);

        return observable.toBlocking().single();
    }
}
