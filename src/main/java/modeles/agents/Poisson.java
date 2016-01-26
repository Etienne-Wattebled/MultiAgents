package modeles.agents;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public class Poisson extends EtreVivant{
	public Poisson(Simulateur simulateur,int nbToursAvantMaturite, int nbToursEntreDeuxNaissance) {
		super(simulateur, nbToursAvantMaturite, nbToursEntreDeuxNaissance);
	}
	
	public Poisson(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances){
		super(simulateur, posX, posY, nbToursAvantMaturite, nbToursEntreDeuxNaissances);
	}
	
	public void doIt(){
		super.doIt();
		seDeplacer();
		
		// Si le poisson est en âge de se reproduire
		// et qu'il y a eu assez de temps entre deux naissances
		// et qu'il y a de la place à côté de lui
		int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
		if(estMature() && (nbToursEntreDeuxNaissances == 0) && (tab != null)){
			// Nouveau poisson
			new Poisson(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial);
		}
	}
}
