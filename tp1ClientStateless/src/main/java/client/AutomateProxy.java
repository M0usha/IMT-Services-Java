package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import rest.*;

public class AutomateProxy implements Automate {
	private WebTarget cibleInitier;
	private WebTarget cibleAccepter;
	private MediaType typeContenu;

	
	public AutomateProxy(String uriBase, MediaType typeContenu){
		WebTarget cible = AppliCliente.clientJAXRS().target(uriBase);
		cibleInitier = cible.path("etat/initial");
		cibleAccepter = cible.path("etat/suivant");
		this.typeContenu = typeContenu;
        System.out.println("CONSTRUCTEUR PROXY");
    }
	
	@Override
	public Session initier() {
	    Session session = new ImplemSession();
        cibleInitier.request().post(Entity.entity(session, typeContenu));

        return session;
	}

	@Override
	public Resultat accepter(char x, Session id) {
	    return cibleAccepter
                .path(String.valueOf(x))
                .queryParam("id", id.getEtatExecution() + "-" + id.getNumero())
                .request().get(Resultat.class);
	}

}
