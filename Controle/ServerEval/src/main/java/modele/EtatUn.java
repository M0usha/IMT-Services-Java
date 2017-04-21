package modele;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;

@Singleton
@Path(JAXRS.CHEMIN_UN)
public class EtatUn implements EtatAutomate {

    public EtatUn() {
        System.out.println("Initialisation de la ressource de type "
                + this.getClass());
    }

    @Override
    public HyperLien<EtatAutomate> accepter(char x) {
        if (x == 'a') {
            return new HyperLien<EtatAutomate>(UriBuilder.fromPath(JAXRS.PROJET).path(EtatDeux.class).build());
        } else {
            return null;
        }
    }

}
