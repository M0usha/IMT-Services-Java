package modele;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "algo")
public interface AlgorithmeNomme {
	@XmlAttribute(name = "nom")
	String getNomAlgorithme();
}
