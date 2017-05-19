package modele;

import configuration.JAXRS;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= JAXRS.SOUSCHEMIN_LIVRE)
public class ImplemLivre implements Livre {

	private String titre;

	public ImplemLivre(String titre) {
		super();
		this.titre = titre;
	}

	public ImplemLivre() {
	}
	
	@Override
	public String getTitre() {
		return this.titre;
	}

	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Livre){
			Livre l = (Livre)obj;
			return this.getTitre().equals(l.getTitre());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getTitre();
	}

	
	
}
