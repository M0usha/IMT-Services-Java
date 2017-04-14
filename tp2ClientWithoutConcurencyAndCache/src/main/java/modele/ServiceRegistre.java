package modele;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("optimiste")
public interface ServiceRegistre {
	@PUT
	@Path("ressource")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Ressource set(Ressource n);

    @GET
    @Path("ressource")
    @Produces(MediaType.APPLICATION_XML)
	public Ressource get();
}
