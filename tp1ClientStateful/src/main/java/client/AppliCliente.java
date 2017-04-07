package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import rest.Automate;
import rest.Session;
import rest.jaxb.FournisseurTraduction;

import java.util.Arrays;

public class AppliCliente {

	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		return ClientBuilder.newClient(config);
	}

	public static void main(String[] args) {
		
		String adresse = "http://localhost:8081/tp1-server-stateful/";

		System.out.println("*************");

		WebTarget cible = clientJAXRS().target(adresse);

		Automate automate = WebResourceFactory.newResource(Automate.class, cible);

		test(automate);

		System.out.println("*************");
		
	}

	private static void test(Automate automate) {
		Character[] mot = { 'a', 'b', 'a', 'a', 'a', 'b' };

		Session session = automate.initier();

		boolean isValid = Arrays.stream(mot).allMatch(letter -> automate.accepter(letter, session).isValide());

		String log = isValid ? "Mot Valide" : "Mot Pas valide";

        System.out.println(log);

        automate.clore(session);
    }

}
