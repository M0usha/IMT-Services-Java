package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;

@Singleton
@Path(JAXRS.CHEMIN_AUTOMATE)
public class A_B_point_Etoile implements Automate {

    public A_B_point_Etoile() {
        System.out.println("Initialisation de la ressource de type "
                + this.getClass());
    }

    @Override
    public HyperLien<EtatAutomate> initier() {
        return new HyperLien<EtatAutomate>(UriBuilder.fromPath(JAXRS.PROJET).path(EtatUn.class).build());
    }

}
