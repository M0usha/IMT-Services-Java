package configuration;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import infrastructure.jaxrs.AdapterReponsesCreatedPOST;
import infrastructure.jaxrs.AdapterReponsesNull404GET;
import modele.ImplemBibliotheque;
import modele.ImplemLivreRessource;

public class ServiceBibliotheque extends ResourceConfig {
	public ServiceBibliotheque(){
		System.out.println("* Chargement de " + this.getClass() + " ...");
		System.out.println("** Configuration");
		this.register(LoggingFeature.class); 
		this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
		this.register(JacksonFeature.class);
		
		// Adaptateurs JAXB
		this.register(infrastructure.jaxb.FournisseurTraduction.class);
		

		// Ressource
		this.register(ImplemBibliotheque.class);
		// Sous-ressources
		this.register(ImplemLivreRessource.class);
		
	
		System.out.println("** Enregistrement des filtres ");
		// Enregistrement des filtres (alternative possible via providers)
		this.register(AdapterReponsesCreatedPOST.class);
		this.register(AdapterReponsesNull404GET.class);
		System.out.println("* Fin du chargement de " + this.getClass());
	}
}

