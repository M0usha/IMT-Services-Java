package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.annotations.ReponsesNull404GET;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


public interface Automate {
    @ReponsesNull404GET
    @GET
    @Path(JAXRS.SOUS_CHEMIN_INITIAL)
    @Produces(JAXRS.TYPE_MEDIA)
    HyperLien<EtatAutomate> initier();
}
