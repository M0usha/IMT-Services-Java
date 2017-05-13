package modele;

import configuration.Initialisation;
import configuration.JAXRS;
import configuration.Orchestrateur;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;
import infrastructure.jaxrs.Outils;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Singleton
@Path(JAXRS.CHEMIN_PORTAIL)
public class ImplemPortail implements RechercheAsynchroneDeclenchantRechercheSynchrone, Portail {

    private ConcurrentMap<AlgorithmeNomme, AlgorithmeRecherche> tableAlgos;
    private Client client;
    private AlgorithmeRecherche algo;
    private List<HyperLien<BibliothequeArchive>> bibliotheques;
    private static GenericType<HyperLien<LivreRessource>>  typeRetourChercherAsync;

    static {
        Method m = null;
        try {
            m = ImplemPortail.class.getDeclaredMethod("chercher", Livre.class);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        typeRetourChercherAsync = new GenericType<HyperLien<LivreRessource>>(m.getGenericReturnType());
    }

    public ImplemPortail() {
        System.out.println("Déploiement de " + this + " : " + this.getClass());
        tableAlgos = new ConcurrentHashMap<AlgorithmeNomme, AlgorithmeRecherche>();
        client = Orchestrateur.clientJAXRS();
        algo = null;
        bibliotheques = Initialisation.bibliotheques();

        //Ajout des algos
        AlgorithmeRecherche rechercheSynchoneSequentielle = new RechercheSynchroneSequentielle();
        tableAlgos.put(rechercheSynchoneSequentielle.nom(), rechercheSynchoneSequentielle);
    }

    @Override
    public void algorithmeRecherche(AlgorithmeNomme algo) {
        this.algo = tableAlgos.getOrDefault(algo, this.algo);
    }

    @Override
    public HyperLien<LivreRessource> chercher(Livre l) {
        return algo.chercher(l, bibliotheques, client);
    }

    @Override
    public HyperLiens<LivreRessource> repertorier() {
        return bibliotheques.stream().map(
                e -> {
                    BibliothequeArchive a = LienVersRessource.proxy(client, e, BibliothequeArchive.class);
                    return a.repertorier();
                }
        ).reduce(new HyperLiens<LivreRessource>(), (sum, element) -> Outils.sommeHyperLiens(sum, element));
    }

    public static GenericType<HyperLien<LivreRessource>> typeRetourChercherAsync(){
        return typeRetourChercherAsync;
    }
}
