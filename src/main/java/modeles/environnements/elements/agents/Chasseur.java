package modeles.environnements.elements.agents;

import java.util.List;

import modeles.environnements.Environnement;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.environnements.elements.agents.utilitaires.TableauDistancesAgent;
import modeles.simulateurs.Simulateur;

public class Chasseur extends Agent{
	
	// Direction non utilisée
	
	protected TableauDistancesAgent tableauDistancesAvatar;
	
	public Chasseur(Simulateur simulateur, int posX, int posY, Direction direction, TableauDistancesAgent tableauDistancesAvatar) {
		super(simulateur,posX,posY);
		this.tableauDistancesAvatar = tableauDistancesAvatar;
	}
	public Chasseur(Simulateur simulateur, TableauDistancesAgent tableauDistancesAvatar) {
		super(simulateur);
		this.tableauDistancesAvatar = tableauDistancesAvatar;
	}
	public Chasseur(Simulateur simulateur, TableauDistancesAgent tableauDistancesAvatar, int ralentissement) {
		super(simulateur,ralentissement);
		this.tableauDistancesAvatar = tableauDistancesAvatar;
	}
	
	
	public void interagir(){
		if (peutInteragir()) {
			Environnement environnement = simulateur.getEnvironnement();
			if (environnement != null) {
				List<ElementEnvironnement> list = environnement.getElementsEnvironnementAuxAlentours(posX,posY);
				for (ElementEnvironnement e : list) {
					if (e instanceof Avatar) {
						((Avatar) e).setAEteAttrape(true);
						simulateur.supprimerAgent((Avatar)e);
						simulateur.arreterSimulation();
						return;
					}
				}
				tableauDistancesAvatar.mettreAJour();
				int tab[] = tableauDistancesAvatar.getProchainesCoordonneesVersAgent(posX,posY);
				seDeplacer(tab[0],tab[1]);
			}
		}
	}
}
