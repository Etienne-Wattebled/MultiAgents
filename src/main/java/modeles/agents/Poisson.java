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
		seDeplacer();
		seReproduire();
		super.doIt();
	}
	public void seReproduire() {
		int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
		// Si c'est possible de se reproduire
		if(peutSeReproduire() && (tab != null)){
			// Nouveau poisson
			simulateur.ajouterAgent(new Poisson(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial));
			resetNbToursEntreDeuxNaissances();
		}
	}
}
