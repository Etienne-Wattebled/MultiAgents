package modeles.environnements.elements.agents;

import java.util.List;

import modeles.environnements.Environnement;
import modeles.environnements.EnvironnementAvatarChasseurs;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.simulateurs.Simulateur;

public class Chasseur extends Agent{
	public Chasseur(Simulateur simulateur, int posX, int posY, Direction direction) {
		super(simulateur,posX,posY,direction);
	}
	
	public void interagir(){
		Environnement environnement = simulateur.getEnvironnement();
		if ((environnement != null) && (environnement instanceof EnvironnementAvatarChasseurs)) {
			List<ElementEnvironnement> list = environnement.getElementsEnvironnementAuxAlentours(posX,posY);
			for (ElementEnvironnement e : list) {
				if (e instanceof Avatar) {
					simulateur.supprimerAgent((Avatar)e);
					simulateur.arreterSimulation();
					return;
				}
			}
			
			EnvironnementAvatarChasseurs eac = (EnvironnementAvatarChasseurs) environnement;
			direction = eac.getDirectionVersAvatar(posX,posY);
		}
	}
}
