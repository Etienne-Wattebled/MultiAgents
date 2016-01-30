package modeles.environnements.elements.agents;

import java.util.List;
import java.util.ListIterator;

import modeles.environnements.Environnement;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.simulateurs.Simulateur;

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
		List<ElementEnvironnement> elementsAuxAlentours = simulateur.getEnvironnement().getElementsEnvironnementAuxAlentours(posX,posY);
		ListIterator<ElementEnvironnement> itElementsAuxAlentours = elementsAuxAlentours.listIterator();
		boolean aMange = false;
		ElementEnvironnement e = null;
		
		while (itElementsAuxAlentours.hasNext() && !aMange) {
			e = itElementsAuxAlentours.next();
			if (e instanceof Poisson) {
				Poisson p = (Poisson) e;
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
