package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.Future;

public interface Bibliotheque {

	@GET
	@ReponsesNull404GET
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<LivreRessource> chercher(@QueryParam(JAXRS.CLE_TITRE) final Livre l);

	@GET
	@ReponsesNull404GET
	@Path(JAXRS.SOUSCHEMIN_ASYNC)
	@Produces(JAXRS.TYPE_MEDIA)
	public Future<HyperLien<LivreRessource>> chercherAsynchrone(@QueryParam(JAXRS.CLE_TITRE) final Livre l, @Suspended final AsyncResponse ar);

	@GET
	@Path(JAXRS.SOUSCHEMIN_CATALOGUE)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLiens<LivreRessource> repertorier();

}
