package modele;

import configuration.JAXRS;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

public interface AdminAlgo {
	@PUT
	@Path(JAXRS.SOUSCHEMIN_ALGO_RECHERCHE)
	@Consumes(JAXRS.TYPE_MEDIA)
	void algorithmeRecherche(AlgorithmeNomme algo);
}
