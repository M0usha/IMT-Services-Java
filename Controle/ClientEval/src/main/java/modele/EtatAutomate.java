package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

public interface EtatAutomate {
    @GET
    @Path(JAXRS.SOUS_CHEMIN_SUIVANT)
    @Produces(JAXRS.TYPE_MEDIA)
    HyperLien<EtatAutomate> accepter(@QueryParam(value = JAXRS.CLE_ETIQUETTE) char x);
}
