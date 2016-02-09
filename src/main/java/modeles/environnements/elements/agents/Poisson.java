package modeles.environnements.elements.agents;

import modeles.simulateurs.Simulateur;

public class Poisson extends EtreVivant{
	public Poisson(Simulateur simulateur,int nbToursAvantMaturite, int nbToursEntreDeuxNaissance, int ralentissement) {
		super(simulateur,1, nbToursAvantMaturite, nbToursEntreDeuxNaissance,ralentissement);
	}
	
	public Poisson(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int ralentissement){
		super(simulateur,posX, posY,1,nbToursAvantMaturite, nbToursEntreDeuxNaissances,ralentissement);
	}
	
	public void interagir(){
		if (peutInteragir()) {
			seDeplacer();
			seReproduire();
		}
	}
	
	public void seReproduire() {
		if(peutSeReproduire()) {
			int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
			simulateur.ajouterAgent(new Poisson(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial,ralentissement));
			sePreparerPourAutreNaissance();
		}
	}
}
