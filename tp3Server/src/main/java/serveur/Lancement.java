package serveur;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;

import configuration.JAXRS;
import infrastructure.Service;

public class Lancement {

	public static void main(String[] args) {
		
//		ResourceConfig config = new Service();
//		URI baseUri1 = UriBuilder.fromUri(JAXRS.ADRESSE_BIBLIO1).port(8080).build();
//		URI baseUri2 = UriBuilder.fromUri(JAXRS.ADRESSE_BIBLIO2).port(8081).build();
//
//	    HttpServer serveur1 = GrizzlyHttpServerFactory.createHttpServer(baseUri1, config);
//	    HttpServer serveur2 = GrizzlyHttpServerFactory.createHttpServer(baseUri2, config);
//
//		System.out.println("Serveur 1 " + serveur1 + " de la classe " + serveur1.getClass());
//		System.out.println("Serveur Grizzly démarré : " + serveur1.isStarted());
//
//		System.out.println("Serveur 2 " + serveur2 + " de la classe " + serveur2.getClass());
//		System.out.println("Serveur Grizzly démarré : " + serveur2.isStarted());

	}

}
