package configuration;

import infrastructure.jaxrs.AdapterReponsesNull404GET;
import modele.A_B_point_Etoile;
import modele.EtatDeux;
import modele.EtatUn;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Service extends ResourceConfig {
    public Service() {
        System.out.println("Chargement de " + this.getClass());
        this.register(LoggingFeature.class);
        this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
        this.register(JacksonFeature.class);

        this.register(A_B_point_Etoile.class);
        this.register(EtatUn.class);
        this.register(EtatDeux.class);

        this.register(AdapterReponsesNull404GET.class);
    }
}
