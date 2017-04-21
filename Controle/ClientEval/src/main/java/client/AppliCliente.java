package client;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import modele.Automate;
import modele.EtatAutomate;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class AppliCliente {

    private static Client clientJAXRS;

    static {
        ClientConfig config = new ClientConfig();

        config.register(LoggingFeature.class);
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
        config.register(JacksonFeature.class);

        clientJAXRS = ClientBuilder.newClient(config);
    }

    private static EtatAutomate proxyEtat(HyperLien<EtatAutomate> h) {
        return LienVersRessource.proxy(clientJAXRS, h, EtatAutomate.class);
    }

    public static void main(String[] args) {

        System.out.println("*************");
        WebTarget cible = clientJAXRS.target(JAXRS.ADRESSE_SERVEUR_AUTOMATE);

        Automate automate = WebResourceFactory.newResource(Automate.class, cible);

        tester(automate, 'a', 'b', 'a', 'b', 'a', 'b');
        tester(automate, 'a', 'b', 'a', 'b', 'a', 'a');

//		System.out.println("C'est bon");
        System.exit(0);

    }

    private static void tester(Automate automate, char... mot) {
        HyperLien<EtatAutomate> hyperlien = automate.initier();
        EtatAutomate etat = proxyEtat(hyperlien);

        try {
            for (char lettre : mot) {
                hyperlien = etat.accepter(lettre);
                etat = proxyEtat(hyperlien);
            }

            System.out.println("Mot reconnu : " + String.valueOf(mot));
        } catch (WebApplicationException e) {
            System.out.println("Mot non reconnu : " + String.valueOf(mot));
        }
    }

}
