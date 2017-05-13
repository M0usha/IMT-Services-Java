package modele;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;

public interface AlgorithmeRecherche extends AlgorithmeNomme{

	@GET
	@Path(JAXRS.SOUSCHEMIN_ALGO_RECHERCHE)
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client);

	default AlgorithmeNomme nom(){
		return new ImplemAlgorithmeNomme(this.getNomAlgorithme());
	}
}
