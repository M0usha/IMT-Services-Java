package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface Automate {

    @POST
	@Path("etat/initial")
    @Produces(MediaType.APPLICATION_JSON)
	Session initier();

    @GET
    @Path("etat/suivant/{lettre}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Resultat accepter(@PathParam("lettre") char x, @QueryParam("id") Session id);

}

