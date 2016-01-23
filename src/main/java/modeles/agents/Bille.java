package modeles.agents;

import modeles.environnements.Environnement;

public class Bille extends Agent {
	public Bille(Environnement environnement, int posX, int posY, Direction direction) {
		super(environnement,posX,posY,direction);
	}
	public Bille(Environnement environnement, Direction direction) {
		this(environnement,(int)(environnement.getNbColonnes()*Math.random()),(int)(environnement.getNbLignes()*Math.random()),direction);
	}
	public Bille(Environnement environnement) {
		super(environnement);
	}
	
	public void doIt() {
		seDeplacer();
	}
}
