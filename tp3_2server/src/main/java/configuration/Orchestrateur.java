package configuration;

import infrastructure.jaxb.FournisseurTraduction;
import infrastructure.jaxrs.AdapterReponses404Null;
import infrastructure.jaxrs.AdapterReponsesCreatedPOST;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Orchestrateur {
	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		System.out.println("Enregistrement des filtres Orchestrateur");
		config.register(AdapterReponsesCreatedPOST.class);
		config.register(AdapterReponses404Null.class);
				
		return ClientBuilder.newClient(config);
	}
}
