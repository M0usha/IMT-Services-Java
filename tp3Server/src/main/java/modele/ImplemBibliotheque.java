package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import configuration.JAXRS;

@Path("bibliotheque")
@Singleton
public class ImplemBibliotheque implements BibliothequeArchive {

	private ConcurrentMap<IdentifiantLivre, Livre> catalogue;
	private int compteur; // dernier identifiant utilisé (-1 : non utilisé)

	public ImplemBibliotheque() {
		System.out.println("Déploiement de " + this + " : " +this.getClass());
		catalogue = new ConcurrentHashMap<IdentifiantLivre, Livre>();
		compteur = -1;
	}

	@Override
	public HyperLien<LivreRessource> ajouter(Livre l) {
		IdentifiantLivre id = null;
		compteur++;
		id = new ImplemIdentifiantLivre(Integer.toString(compteur));
		catalogue.put(id, l);
		final URI adresse = URI.create("bibliotheque/" + id.getId());
		return new HyperLien<LivreRessource>(Response.created(adresse).build().getLocation());
	}

	@Override
	public LivreRessource sousRessource(IdentifiantLivre id) {
		Livre l = catalogue.get(id);
		return (l == null) ? null : new ImplemLivreRessource(l);
	}

	@Override
	public HyperLien<LivreRessource> chercher(Livre l) {
		// renvoie null si non trouvé
		for (ConcurrentMap.Entry<IdentifiantLivre, Livre> couple : catalogue
				.entrySet()) {
			if (couple.getValue().equals(l)) {
				IdentifiantLivre id = couple.getKey();
				URI adresse = URI.create("bibliotheque/" + id.getId());
				return new HyperLien<LivreRessource>(Response.created(adresse).build()
						.getLocation());
			}
		}
		return null;
	}
	
	@Override
	public HyperLiens<LivreRessource> repertorier() {
		Set<IdentifiantLivre> ids = catalogue.keySet();
		List<HyperLien<LivreRessource>> catalogueRef = new LinkedList<HyperLien<LivreRessource>>();
		for (IdentifiantLivre id : ids) {
			URI adresse = URI.create("bibliotheque/" + id.getId());
			catalogueRef.add(new HyperLien<LivreRessource>(Response
					.created(adresse).build().getLocation()));
		}
		return new HyperLiens<LivreRessource>(catalogueRef);
	}

}