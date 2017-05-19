package configuration;

import infrastructure.jaxrs.AdapterReponsesCreatedPOST;
import infrastructure.jaxrs.AdapterReponsesNull404GET;
import modele.ImplemPortail;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ServicePortail extends ResourceConfig {
	public ServicePortail(){
		System.out.println("* Chargement de " + this.getClass() + " ...");
		System.out.println("** Configuration");
		this.register(LoggingFeature.class);

		this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");

		this.register(JacksonFeature.class);
		this.property("com.sun.jersey.config.feature.Debug", Boolean.TRUE);
		// Adaptateurs JAXB
		this.register(infrastructure.jaxb.FournisseurTraduction.class);


		// Ressource : un portail
		System.out.println("Chargement de l'implem portail");
		this.register(ImplemPortail.class);

		System.out.println("** Enregistrement des filtres ");

		// Enregistrement des filtres (alternative possible via providers)
		this.register(AdapterReponsesCreatedPOST.class);
		this.register(AdapterReponsesNull404GET.class);
		System.out.println("* Fin du chargement de " + this.getClass());
	}
}

