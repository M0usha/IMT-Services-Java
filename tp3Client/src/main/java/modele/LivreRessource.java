package modele;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import configuration.JAXRS;

public interface LivreRessource extends Livre {
	@GET 
	@Path(JAXRS.SOUSCHEMIN_REPLIVRE)
	@Produces(JAXRS.TYPE_MEDIA)
	Livre getRepresentation();


}
