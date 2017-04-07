package client;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import rest.Automate;
import rest.Session;

import javax.ws.rs.client.WebTarget;
import java.util.stream.Stream;

/**
 * Created by anael on 07/04/2017.
 */
public class TestConcurrence {
    public static void main(String[] args) {
        String adresse = "http://localhost:8081/tp1-server-stateful/";

        System.out.println("*************");

        WebTarget cible = AppliCliente.clientJAXRS().target(adresse);

        Automate automate = WebResourceFactory.newResource(Automate.class, cible);

        int MAX = 10000;

        Session[] sessions = new Session[MAX];

        Stream.iterate(0, i -> i + 1)
                .limit(MAX)
                .unordered()
                .parallel()
                .forEach( i -> sessions[i] = automate.initier());
    }
}
