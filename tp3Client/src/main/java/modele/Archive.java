package modele;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface Archive {
	@GET
	@Path("{id}")
	public LivreRessource sousRessource(@PathParam("id") IdentifiantLivre id) ;// Une sous-ressource ne peut-être une cible de requête.
		
}
