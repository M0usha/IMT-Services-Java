package modele;

import infrastructure.jaxrs.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface ServiceRegistre {

	@PUT
	@Path("ressource")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
    @StatRequetes
    @StatReponses
    @AtomiciteRequeteReponseServeur
    @CacheClient
    @EcritureOptimiste
    @VersionReponses
	public Ressource set(Ressource n);

	@GET
	@Path("ressource")
	@Produces(MediaType.APPLICATION_XML)
    @StatRequetes
    @StatReponses
    @AtomiciteRequeteReponseServeur
    @CacheClient
    @EcritureOptimiste
    @VersionReponses
	public Ressource get();
}



