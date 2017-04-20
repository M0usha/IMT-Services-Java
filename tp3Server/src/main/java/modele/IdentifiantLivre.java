package modele;

import infrastructure.jaxrs.ConversionStringIdentifiantLivre;

public interface IdentifiantLivre {
	String getId();

	public static IdentifiantLivre fromString(String x) {
		return ConversionStringIdentifiantLivre.fromString(x);
	}
}
