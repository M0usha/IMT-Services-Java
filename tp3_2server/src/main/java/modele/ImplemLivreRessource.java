package modele;

public class ImplemLivreRessource implements LivreRessource {

	private Livre l;
	
	public ImplemLivreRessource(Livre l) {
		this.l = l;
	}
	
	@Override
	public Livre getRepresentation(){
		return this.l; 
	}
	
	@Override
	public String getTitre() {
		return this.l.getTitre();
	}

}
