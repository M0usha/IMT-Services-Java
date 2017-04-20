package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.annotations.ReponsesCreatedPOST;

public interface Bibliotheque {
	@ReponsesCreatedPOST
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	HyperLien<LivreRessource> ajouter(Livre l);

	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	HyperLien<LivreRessource> chercher(@QueryParam("titre") Livre l);

	@GET
	@Path("catalogue")
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLiens<LivreRessource> repertorier();
	
}
