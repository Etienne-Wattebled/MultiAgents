package modeles.environnements.elements.agents;

import modeles.simulateurs.Simulateur;

public class Avatar extends Agent{
	protected boolean estAttrape = false;
	
	public Avatar(Simulateur simulateur, int posX, int posY) {
		super(simulateur,posX,posY);
	}
	
	public Avatar(Simulateur simulateur) {
		super(simulateur);
	}
	
	public void interagir(){
		if(simulateur.getEnvironnement().getBoite() == "gauche"){
			seDeplacer(posX-1, posY);
		}
		else if(simulateur.getEnvironnement().getBoite() == "droite"){
			seDeplacer(posX+1, posY);
		}
		else if(simulateur.getEnvironnement().getBoite() == "haut"){
			seDeplacer(posX, posY-1);
		}
		else if(simulateur.getEnvironnement().getBoite() == "bas"){
			seDeplacer(posX, posY+1);
		}
		else if(simulateur.getEnvironnement().getBoite() == "basGauche"){
			seDeplacer(posX-1, posY+1);
		}
		else if(simulateur.getEnvironnement().getBoite() == "basDroite"){
			seDeplacer(posX+1, posY+1);
		}
		else if(simulateur.getEnvironnement().getBoite() == "hautGauche"){
			seDeplacer(posX-1, posY-1);
		}
		else if(simulateur.getEnvironnement().getBoite() == "hautDroite"){
			seDeplacer(posX+1, posY-1);
		}
	}
}
