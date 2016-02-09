package modeles.environnements.elements.agents;

import modeles.simulateurs.Simulateur;

public class Bille extends Agent {
	public Bille(Simulateur simulateur) {
		super(simulateur);
	}
	public Bille(Simulateur simulateur, int ralentissement) {
		super(simulateur,ralentissement);
	}
	
	public void interagir() {
		if (peutInteragir()) {
			seDeplacer();
		}
	}	
}
