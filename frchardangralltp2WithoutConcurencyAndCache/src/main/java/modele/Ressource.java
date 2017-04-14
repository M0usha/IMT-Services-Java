package modele;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ressource")
@Singleton
public class Ressource {
	public final static Ressource SINGLETON;
	static {
		SINGLETON = new Ressource();
		System.out.println("Initialisation de la ressource " + SINGLETON + " : " + SINGLETON.getClass());
		System.out.println("- Valeur initiale : 0");

	}
	private int i;

	public Ressource(){
		this.i = 0;
	}

	public Ressource(int j) {
		this.i = j;
	}

	@XmlElement(name = "x")
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
}
