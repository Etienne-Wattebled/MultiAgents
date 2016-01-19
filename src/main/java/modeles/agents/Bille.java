package modeles.agents;

import modeles.environnements.Environnement;

public class Bille extends Agent {
	public Bille(int posX, int posY, Direction direction, Environnement environnement) {
		super(posX,posY,direction, environnement);
	}
	public void seDeplacer() {
		int xF = getPosX();
		int yF = getPosY();
		switch (getDirection()) {
			
		}
	}
	public void doIt() {
		
	}
}
