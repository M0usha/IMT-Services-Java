package client;

import java.lang.reflect.Proxy;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import infrastructure.jaxrs.AdapterReponsesCreatedPOST;

import infrastructure.jaxrs.ClientRessource;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import modele.BibliothequeArchive;
import modele.ImplemLivre;
import modele.Livre;
import modele.LivreRessource;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

public class AppliCliente {

	private static final String ADRESSE = "http://localhost:9091/tp3-server/bibliotheque"; // TODO
	
	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(infrastructure.jaxb.FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		config.register(AdapterReponsesCreatedPOST.class);
		
		return ClientBuilder.newClient(config);
	}


	public static void main(String[] args) {

		System.out.println("*************");
		
		WebTarget cible = clientJAXRS().target(ADRESSE);
		BibliothequeArchive biblio  = WebResourceFactory.newResource(BibliothequeArchive.class, cible);

		System.out.println("Biblio (proxy) : "
				+ Proxy.isProxyClass(biblio.getClass()));

		System.out.println("*** 1. Ajouter des livres ***");
		Livre l1 = new ImplemLivre("Restful Java with JAX-RS");
		Livre l2 = new ImplemLivre("Restful Java with JAX-RS 2.0");

		HyperLien<LivreRessource> r1 = null;
		HyperLien<LivreRessource> r2 = null;

		for (int i = 0; i < 1; i++) {
			r1 = biblio.ajouter(l1); // POST 1
			System.out.println("POST 1 : " + r1.getClass());
			r2 = biblio.ajouter(l2); // POST 2
			System.out.println("POST 2 : " + r2.getClass());
		}

		System.out.println("POST 1 - uri : " + r1.getUri());
		System.out.println("POST 2 - uri : " + r2.getUri());

		System.out.println("*** 2. Récupérer un livre à partir de l'hyperlien ***");

		LivreRessource l3 = ClientRessource.proxy(r2, LivreRessource.class); 
		System.out.println("Proxy (true) ? "
				+ Proxy.isProxyClass(l3.getClass()));
		System.out.println("Classe du proxy : " + l3.getClass()); 
		System.out.println("Classe parente du proxy : "
				+ l3.getClass().getSuperclass());
		String titre3 = l3.getTitre(); 
		System.out.println("GET 3 (titre : 2.0) : " + titre3);
		Livre l3b = l3.getRepresentation(); 
		System.out.println("GET 4 (ImplemLivre) : " + l3b.getClass()); 
		System.out.println("livre (2.0) : " + l3b);

		LivreRessource l4 = ClientRessource.proxy(r2, LivreRessource.class); 
		Livre l4b = l4.getRepresentation(); // 
		System.out.println("GET 5 (ImplemLivre) : " + l4b.getClass()); 
		System.out.println("livre (2.0) : " + l4b); 

		l3 = ClientRessource.proxy(new HyperLien<LivreRessource>(ADRESSE + "/123"), LivreRessource.class); 
		
		try {
			System.out
					.println("GET 6 - livre : " + l3.getRepresentation()); 
		} catch (WebApplicationException e) {
			System.out.println("GET 6 - exception - "
					+ Outils.messageErreur(e.getResponse()));
		}
		
		System.out.println("*** 3. Chercher un livre ***");

		try {
			System.out
					.println("GET 7 - uri : " + biblio.chercher(l3b)); 
		} catch (WebApplicationException e) {
			System.out.println("GET 7 - exception - "
					+ Outils.messageErreur(e.getResponse()));
		}
		try {
			System.out.println("GET 8 - uri livre null : "
					+ biblio.chercher(new ImplemLivre("absent")));
		} catch (WebApplicationException e) {
			System.out.println("GET 8 - exception - "
					+ Outils.messageErreur(e.getResponse()));
		}
		
		System.out.println("*** 4. Répertorier les livres ***");

		System.out.println("GET 9 - catalogue : " + biblio.repertorier()); 

		System.exit(0);
	}

}
