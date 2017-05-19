package modele;

import infrastructure.jaxrs.HyperLien;

import javax.ws.rs.client.Client;
import java.util.List;

public interface AlgorithmeRecherche extends AlgorithmeNomme{
	HyperLien<LivreRessource> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client);

	default AlgorithmeNomme nom(){
		return new ImplemAlgorithmeNomme(this.getNomAlgorithme());
	}
}
