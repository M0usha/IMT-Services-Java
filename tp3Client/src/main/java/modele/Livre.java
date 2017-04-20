package modele;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

import configuration.JAXRS;
import infrastructure.jaxrs.ConversionStringLivre;

@XmlRootElement(name="livre")
public interface Livre {
	@GET
	@Path(JAXRS.SOUSCHEMIN_TITRE)
	String getTitre();

	public static Livre fromString(String x) {
		return ConversionStringLivre.fromString(x);
	}
}
