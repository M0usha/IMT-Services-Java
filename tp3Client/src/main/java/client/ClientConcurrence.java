package client;


import javax.ws.rs.client.WebTarget;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemLivre;
import modele.Livre;
import modele.LivreRessource;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

public class ClientConcurrence {
	private static final String ADRESSE = "http://localhost:9091/tp3-server/bibliotheque"; // TODO

	public static void main(String[] args) {
		System.out.println("*************");
		
		WebTarget cible = AppliCliente.clientJAXRS().target(ADRESSE);
		Bibliotheque biblio  = WebResourceFactory.newResource(Bibliotheque.class, cible);

		System.out.println("*** 1. Ajouter des livres ***");
		
		Livre l1 = new ImplemLivre("Restful Java with JAX-RS");
		
		HyperLien<LivreRessource> r1 = null;
		
		for (int i = 0; i < 100; i++) {
			r1 = biblio.ajouter(l1); 
		}
		System.out.println("URI : " + r1.getUri());
		System.exit(0);
	}

}
