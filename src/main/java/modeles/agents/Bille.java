package modeles.agents;

import modeles.environnements.Environnement;

public class Bille extends Agent {
	public Bille(Environnement environnement, int posX, int posY, Direction direction) {
		super(posX,posY,direction, environnement);
	}
	public Bille(Environnement environnement, Direction direction) {
		this(environnement,(int)(environnement.getLongueur()*Math.random()),(int)(environnement.getHauteur()*Math.random()),direction);
	}
	public Bille(Environnement environnement) {
		super(environnement);
	}
	
	public void doIt() {
		seDeplacer();
	}
}
