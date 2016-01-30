package modeles.environnements.elements.agents;

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
		
	}
	public boolean getAEteAttrape() {
		return aEteAttrape;
	}
	public void setAEteAttrape(boolean aEteAttrape) {
		this.aEteAttrape = aEteAttrape;
	}
}
