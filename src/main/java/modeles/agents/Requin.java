package modeles.agents;

import java.util.List;
import java.util.ListIterator;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public class Requin extends EtreVivant {
	
	public Requin(Simulateur simulateur, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim){
		super(simulateur,nbToursAvantMortFaim, nbToursAvantMaturite, nbToursEntreDeuxNaissances);
	}
	
	public Requin(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim){
		super(simulateur,posX, posY,nbToursAvantMortFaim, nbToursAvantMaturite, nbToursEntreDeuxNaissances);
	}
	
	public void interagir(){
		seDeplacer();
		manger();
		seReproduire();
		verifierVie();
		mettreAJourLesNbTours();
	}
	public void manger() {
		List<Agent> agentsAuxAlentours = simulateur.getEnvironnement().getAgentsAuxAlentours(posX,posY);
		ListIterator<Agent> itAgentsAuxAlentours = agentsAuxAlentours.listIterator();
		boolean aMange = false;
		Agent a = null;
		
		while (itAgentsAuxAlentours.hasNext() && !aMange) {
			a = itAgentsAuxAlentours.next();
			if (a instanceof Poisson) {
				Poisson p = (Poisson) a;
				aMange = true;
				p.setEnVie(false);
				simulateur.supprimerAgent(p);
				resetNbToursAvantMortFaim();
			}
		}
	}
	public void seReproduire() {
		if (peutSeReproduire()) {
			int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
			simulateur.ajouterAgent(new Requin(simulateur, tab[0],tab[1], nbToursAvantMaturiteInitial, nbToursEntreDeuxNaissancesInitial, nbToursAvantMortFaimInitial));
			resetNbToursEntreDeuxNaissances();
		}
	}
	public void verifierVie() {
		if(nbToursAvantMortFaim <= 0) {
			enVie = false;
			simulateur.supprimerAgent(this);
		}
	}
}
