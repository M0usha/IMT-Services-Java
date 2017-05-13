package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.Outils;

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



@Singleton
@Path(JAXRS.CHEMIN_BIBLIO)
public class ImplemBibliotheque implements RechercheAsynchroneDeclenchantRechercheSynchrone, BibliothequeArchive {

	private ConcurrentMap<IdentifiantLivre, Livre> catalogue;
	private int compteur; // dernier identifiant utilisé (-1 : non utilisé)
	private Lock verrou;

	public ImplemBibliotheque() {
		System.out.println("Déploiement de " + this + " : " +this.getClass());
		catalogue = new ConcurrentHashMap<IdentifiantLivre, Livre>();
		compteur = -1;
		verrou = new ReentrantLock();
	}

	@Override
	public HyperLien<LivreRessource> ajouter(Livre l) {
		IdentifiantLivre id = null;
		verrou.lock();
		try {
			compteur++;
			id = new ImplemIdentifiantLivre(Integer.toString(compteur));
		} finally {
			verrou.unlock();
		}
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
		Outils.afficherInfoTache("recherche synchrone");
		for (ConcurrentMap.Entry<IdentifiantLivre, Livre> couple : catalogue
				.entrySet()) {
			if (couple.getValue().equals(l)) {
				IdentifiantLivre id = couple.getKey();
				URI adresse = URI.create("bibliotheque/" + id.getId());
				return new HyperLien<LivreRessource>(Response.created(adresse).build()
						.getLocation());
			}
		}
		
		Outils.patienter(15);
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
