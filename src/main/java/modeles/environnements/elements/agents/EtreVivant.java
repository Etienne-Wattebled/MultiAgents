package modeles.environnements.elements.agents;

import modeles.environnements.Environnement;
import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.simulateurs.Simulateur;

public abstract class EtreVivant extends Agent{
	protected int nbToursEntreDeuxNaissances;
	protected int nbToursAvantMaturite;
	protected int nbToursAvantMortFaim;
	
	protected int nbToursConserverDirection;

	protected boolean enVie;
	
	protected int nbToursEntreDeuxNaissancesInitial;
	protected int nbToursAvantMaturiteInitial;
	protected int nbToursAvantMortFaimInitial;
	
	public EtreVivant(Simulateur simulateur, int posX, int posY, int nbToursAvantMortFaim, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances){
		super(simulateur,posX,posY);
		initialiser(nbToursAvantMortFaim,nbToursAvantMaturite,nbToursEntreDeuxNaissances);
		
	}
	public EtreVivant(Simulateur simulateur, int nbToursAvantMortFaim, int nbToursAvantMaturite,int nbToursEntreDeuxNaissances) {
		super(simulateur);
		initialiser(nbToursAvantMortFaim,nbToursAvantMaturite,nbToursEntreDeuxNaissances);
	}
	
	public void initialiser(int nbToursAvantMortFaim, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances) {
		this.nbToursAvantMortFaim = nbToursAvantMortFaim;
		this.nbToursAvantMortFaimInitial = nbToursAvantMortFaim;
		
		this.nbToursAvantMaturite = nbToursAvantMaturite;
		this.nbToursAvantMaturiteInitial = nbToursAvantMaturite;
		
		this.nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances;
		this.nbToursEntreDeuxNaissancesInitial = nbToursEntreDeuxNaissances;
		
		this.nbToursConserverDirection = 0;
		
		this.enVie = true;
	}
	
	/**
	 * Retourne vrai si l'être vivant peut se reproduire (tient compte de l'environnement)
	 * @return true si l'être vivant peut se reproduire
	 */
	public boolean peutSeReproduire() {
		if ((nbToursAvantMaturite != 0) || (nbToursEntreDeuxNaissances != 0)) {
			return false;
		}
		Environnement environnement = null;
		if ((simulateur == null) || ((environnement = simulateur.getEnvironnement()) == null)) {
			return true;
		}
		int tab[] = environnement.getCaseLibreAuxAlentours(posX,posY);
		return (tab != null);
	}
	
	public void mettreAJourLesNbTours() {
		if ((nbToursAvantMaturite <= 0) && (nbToursEntreDeuxNaissances > 0)) {
			nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances - 1;
		}
		
		if (nbToursConserverDirection <= 0) {
			this.direction = Direction.getRandomDirection();
			this.nbToursConserverDirection = 15 + (int)(Math.random()*7);
		}
		if (nbToursConserverDirection > 0) {
			nbToursConserverDirection = nbToursConserverDirection - 1;
		}
		
		if (nbToursAvantMaturite > 0) {
			nbToursAvantMaturite = nbToursAvantMaturite - 1;
		}
	
		if (nbToursAvantMortFaim > 0) {
			nbToursAvantMortFaim = nbToursAvantMortFaim -1;
		}
	}
	
	public boolean estMature() {
		return nbToursAvantMaturite <= 0;
	}
	
	public void resetNbToursEntreDeuxNaissances() {
		nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissancesInitial;
	}
	public void resetNbToursAvantMortFaim() {
		nbToursAvantMortFaim = nbToursAvantMortFaimInitial;
	}
	public void resetNbToursAvantMaturite() {
		nbToursAvantMaturite = nbToursAvantMaturiteInitial;
	}
	
	public boolean estEnVie() {
		return enVie;
	}
	public void setEnVie(boolean a) {
		this.enVie = a;
	}
}