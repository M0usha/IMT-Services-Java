package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "algo")
public class ImplemAlgorithmeNomme implements AlgorithmeNomme{

	@XmlElement(name = "nom")
	private String nom;

	public ImplemAlgorithmeNomme() {
		this.nom = null;
	}
	
	public ImplemAlgorithmeNomme(String nomAlgorithme) {
		this.nom = nomAlgorithme;
	}

	@Override
	public String getNomAlgorithme() {
		return this.nom;
	}

	public void setNomAlgorithme(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AlgorithmeNomme))
			return false;
		AlgorithmeNomme arg = (AlgorithmeNomme)obj;
		return this.getNomAlgorithme().equals(arg.getNomAlgorithme());
	}
	
	@Override
	public int hashCode() {
		return this.getNomAlgorithme().hashCode();
	}
	
}
