package configuration;

import javax.ws.rs.core.MediaType;

public class JAXRS {
	// Adresses
	public static final String ADRESSE_BIBLIO1 = "http://localhost:8080/ServeurBibliotheque1"; // TODO
	public static final String ADRESSE_BIBLIO2 = "http://localhost:8080/ServeurBibliotheque2"; // TODO 
	// Chemins et requêtes
	public static final String CHEMIN_BIBLIO = "bibliotheque";
	public static final String CLE_TITRE = "titre";
	public static final String SOUSCHEMIN_CATALOGUE = "catalogue";
	public static final String SOUSCHEMIN_ASYNC = "async";
	public static final String SOUSCHEMIN_LIVRE = "livre";
	public static final String SOUSCHEMIN_TITRE = "titre";
	public static final String SOUSCHEMIN_REPLIVRE = "";

	// Types
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
}
