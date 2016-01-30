package modeles.environnements.elements.agents;

import modeles.environnements.Environnement;
import modeles.simulateurs.Simulateur;

public class Poisson extends EtreVivant{
	public Poisson(Simulateur simulateur,int nbToursAvantMaturite, int nbToursEntreDeuxNaissance) {
		super(simulateur,1, nbToursAvantMaturite, nbToursEntreDeuxNaissance);
	}
	
	public Poisson(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances){
		super(simulateur,posX, posY,1,nbToursAvantMaturite, nbToursEntreDeuxNaissances);
	}
	
	public void interagir(){
		resetNbToursAvantMortFaim();
		seDeplacer();
		seReproduire();
		mettreAJourLesNbTours();
	}
	public void seReproduire() {
		if(peutSeReproduire()) {
			int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
			simulateur.ajouterAgent(new Poisson(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial));
			resetNbToursEntreDeuxNaissances();
		}
	}
}
