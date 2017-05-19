
import configuration.Orchestrateur;
import infrastructure.jaxrs.HyperLien;
import modele.ImplemAlgorithmeNomme;
import modele.ImplemLivre;
import modele.LivreRessource;
import modele.Portail;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.WebTarget;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * @author Anael CHARDAN
 */
public class Client {
    private static final String ADRESSE = "http://localhost:8080/tp3_2.server/portail";
    private static final int NB_BIBLIOTHEQUES = 10;

    public static void main(String[] args) throws URISyntaxException {
        WebTarget target = Orchestrateur.clientJAXRS().target(ADRESSE);
        Portail portail = WebResourceFactory.newResource(Portail.class, target);

        List<Result> results1 = run(portail, true);
        List<Result> results2 = run(portail, false);

        System.out.println(results1);
        System.out.println(results2);
    }

    private static class Result {
        String algo;
        long millis;
        boolean res;

        Result(String algo, long millis, boolean res) {
            this.algo = algo;
            this.millis = millis;
            this.res = res;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d, %s]", algo, millis, (res ? "fructueuse" : "infructueuse"));
        }
    }

    /**
     * Genere un ID en fonction de ce que l'on souhaite
     *
     * @param isSuccessful si l'on veut quelque chose de possible
     *
     * @return le nombre aléatoire
     */
    private static int generateId(boolean isSuccessful) {
        return new Random().nextInt(NB_BIBLIOTHEQUES) + (isSuccessful ? 0 : NB_BIBLIOTHEQUES);
    }

    /**
     * Execute les tests
     *
     * @param portail Le portail a interogger
     *
     * @param isSuccessful
     *
     * @return
     */
    private static List<Result> run(Portail portail, boolean isSuccessful) {
        List<String> typesAlgo = Arrays.asList("seq", "multi", "stream 8", "stream rx");
        List<String> typesSync = Arrays.asList("sync", "async");

        return typesSync
                .stream()
                .flatMap(typeSync -> typesAlgo.stream().map(typeAlgo -> {
                    String nomAlgo = String.format("recherche %s %s", typeSync, typeAlgo);
                    int livreId = generateId(isSuccessful);
                    int bibliothequeId = generateId(isSuccessful);

                    Instant start = Instant.now();

                    ImplemAlgorithmeNomme recherche = new ImplemAlgorithmeNomme(nomAlgo);
                    ImplemLivre livre = new ImplemLivre(String.format("Services%d.%d", bibliothequeId, livreId));

                    portail.algorithmeRecherche(recherche);

                    HyperLien expected = new HyperLien(String.format("http://localhost:809%d/bib%d/bibliotheque/%d", bibliothequeId, bibliothequeId, livreId));
                    HyperLien<LivreRessource> hyperLien = portail.chercher(livre);

                    boolean res = (isSuccessful) ? expected.getUri().equals(hyperLien.getUri()) : nonNull(hyperLien);

                    Instant end = Instant.now();

                    return new Result(nomAlgo, Duration.between(start, end).toMillis(), res);
                }))
                .collect(Collectors.toList());
    }
}