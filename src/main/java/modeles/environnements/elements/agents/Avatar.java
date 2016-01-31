package modeles.environnements.elements.agents;

import vues.simulateurs.VueSimulateur;
import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.simulateurs.Simulateur;

public class Avatar extends Agent{
	protected boolean aEteAttrape;
	
	public Avatar(Simulateur simulateur, int posX, int posY, Direction direction) {
		super(simulateur,posX,posY,direction);
		this.aEteAttrape = false;
	}
	public Avatar(Simulateur s) {
		super(s);
		this.aEteAttrape = false;
	}
	
	public void interagir(){
		VueSimulateur vueSimulateur = simulateur.getVueSimulateur();
		if (vueSimulateur == null) {
			direction = Direction.getRandomDirection();
		} else {
			direction = vueSimulateur.getDirectionClavier();
		}
		seDeplacer();
	}
	public boolean getAEteAttrape() {
		return aEteAttrape;
	}
	public void setAEteAttrape(boolean aEteAttrape) {
		this.aEteAttrape = aEteAttrape;
	}
}
