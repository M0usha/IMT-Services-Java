package modele;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import configuration.JAXRS;

/*
 * Une ressource Livre "est" un livre et "a" un livre.
 * "Est" : il est possible de récupérer les informations associées au livre.
 * "A" : la ressource peut être la cible d'un requête donnant une description complète du livre. 
 * 
 */
public interface LivreRessource extends Livre {

	@GET 
	@Produces(JAXRS.TYPE_MEDIA)
	Livre getRepresentation();

	// Possibles autres méthodes liées au livre et à la bibliothèque : cote, etc.

}
