package modeles.agents;

import simulateurs.Simulateur;

public class Bille extends Agent {
	public Bille(Simulateur simulateur, int posX, int posY, Direction direction) {
		super(simulateur,posX,posY,direction);
	}
	public Bille(Simulateur simulateur, Direction direction) {
		this(simulateur,(int)(simulateur.getEnvironnement().getNbColonnes()*Math.random()),(int)(simulateur.getEnvironnement().getNbLignes()*Math.random()),direction);
	}
	public Bille(Simulateur simulateur) {
		super(simulateur);
	}
	
	public void interagir() {
		seDeplacer();
	}
}
