package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.annotations.ReponsesCreatedPOST;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

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
