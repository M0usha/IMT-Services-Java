package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface Automate {
    @GET
    @Path(JAXRS.SOUS_CHEMIN_INITIAL)
    @Produces(JAXRS.TYPE_MEDIA)
    HyperLien<EtatAutomate> initier();
}
