package modele;

import java.util.concurrent.Future;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface RechercheAsynchroneDeclenchantRechercheSynchrone extends Bibliotheque {

	static ExecutorService executeur = Executors.newCachedThreadPool();

	// Version asynchrone

	default public Future<HyperLien<LivreRessource>> chercherAsynchrone(final Livre l, final AsyncResponse ar) {
		Outils.afficherInfoTache("recherche aynchrone");
		HyperLien<LivreRessource> h = this.chercher(l);
		if (h == null) {
			Response rep = Response.status(Response.Status.NOT_FOUND).build();
			ar.resume(rep);
		} else {
			ar.resume(h);
		}
		return null;
	}
}
