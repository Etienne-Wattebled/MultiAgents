package modeles.environnements.elements.agents;

import vues.simulateurs.VueSimulateur;
import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.simulateurs.Simulateur;

public class Avatar extends Agent{
	protected boolean aEteAttrape;
	
	public Avatar(Simulateur simulateur, int posX, int posY, int ralentissement) {
		super(simulateur, posX, posY,ralentissement);
		this.aEteAttrape = false;
	}
	public Avatar(Simulateur simulateur, int ralentissement) {
		super(simulateur, ralentissement);
		this.aEteAttrape = false;
	}
	
	public Avatar(Simulateur s) {
		super(s);
		this.aEteAttrape = false;
	}
	
	public void interagir(){
		if (peutInteragir()) {
			VueSimulateur vueSimulateur = simulateur.getVueSimulateur();
			if (vueSimulateur == null) {
				direction = Direction.getRandomDirection();
			} else {
				direction = vueSimulateur.getDirectionClavier();
			}
			seDeplacer();
		}
	}
	public boolean getAEteAttrape() {
		return aEteAttrape;
	}
	public void setAEteAttrape(boolean aEteAttrape) {
		this.aEteAttrape = aEteAttrape;
	}
}
