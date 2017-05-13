package configuration;

import javax.ws.rs.core.MediaType;

public class JAXRS {
	public static final String ADRESSE_BIBLIO1 = "http://localhost/bib1"; 
	public static final int PORT_BIBLIO1 = 8087; 
	public static final String ADRESSE_BIBLIO2 = "http://localhost/bib2";
	public static final int PORT_BIBLIO2 = 8088; 
	// Chemins et requêtes
	public static final String CHEMIN_PORTAIL = "portail";
	public static final String CHEMIN_BIBLIO = "bibliotheque";
	public static final String CLE_TITRE = "titre";
	public static final String SOUSCHEMIN_CATALOGUE = "catalogue";
	public static final String SOUSCHEMIN_ASYNC = "async";
	public static final String SOUSCHEMIN_LIVRE = "livre";
	public static final String SOUSCHEMIN_TITRE = "titre";
	public static final String SOUSCHEMIN_REPLIVRE = "";
	public static final String SOUSCHEMIN_ALGO_RECHERCHE = "admin/recherche";

	// Types
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
}
