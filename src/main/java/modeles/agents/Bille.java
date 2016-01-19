package modeles.agents;

import modeles.environnements.Environnement;

public class Bille extends Agent {
	public Bille(int posX, int posY, Direction direction, Environnement environnement) {
		super(posX,posY,direction, environnement);
	}
	public void seDeplacer() {
		int tab[] = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
		int xF = tab[0], yF = tab[1];
		
		if (!getEnvironnement().aUnObstacle(xF,yF)) {
			seDeplacer(xF,yF);
		} else {
			setDirection(Direction.getDirectionRebondissement(getDirection()));
			tab = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
			xF = tab[0];
			yF = tab[1];
			if (!getEnvironnement().aUnObstacle(xF, yF)) {
				seDeplacer(xF,yF);
			}
		}
	}
	public void doIt() {
		
	}
}
