package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.annotations.ReponsesCreatedPOST;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.*;

public interface Archive {
	@ReponsesNull404GET
	@Path("{id}")
	public LivreRessource sousRessource(@PathParam("id") IdentifiantLivre id) ; // Une sous-ressource ne peut-être une cible de requête.
	
	@POST
	@ReponsesCreatedPOST
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<LivreRessource> ajouter(Livre l);
		
}
