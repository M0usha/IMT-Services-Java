package modele;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import infrastructure.jaxrs.annotations.ReponsesNull404GET;

public interface Archive {
	@Path("{id}")
	public LivreRessource sousRessource(@PathParam("id") IdentifiantLivre id) ; // Une sous-ressource ne peut-être une cible de requête
}
