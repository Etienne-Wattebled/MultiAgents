package modeles.agents;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public abstract class EtreVivant extends Agent{
	protected int nbToursEntreDeuxNaissances;
	protected int nbToursAvantMaturite;
	protected int nbToursConserverDirection;
	
	protected boolean enVie;
	
	protected int nbToursEntreDeuxNaissancesInitial;
	protected int nbToursAvantMaturiteInitial;
	
	public EtreVivant(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances){
		super(simulateur,posX,posY);
		this.nbToursAvantMaturite = nbToursAvantMaturite;
		this.nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances;
		this.nbToursConserverDirection = 0;
		
		this.nbToursAvantMaturiteInitial = nbToursAvantMaturite;
		this.nbToursEntreDeuxNaissancesInitial = nbToursEntreDeuxNaissances;
		
		this.enVie = true;
	}
	
	public EtreVivant(Simulateur simulateur,int nbToursAvantMaturite, int nbToursEntreDeuxNaissances) {
		this(simulateur,(int)(simulateur.getEnvironnement().getNbColonnes()*Math.random()),(int)(simulateur.getEnvironnement().getNbLignes()*Math.random()),nbToursAvantMaturite,nbToursEntreDeuxNaissances);
	}
	
	public boolean estMature() {
		return nbToursAvantMaturite == 0;
	}
	/**
	 * Retourne vrai si l'être vivant peut se reproduire mais ne prend pas en compte la place dans l'environnement.
	 * @return true si l'être vivant peut se reproduire
	 */
	public boolean peutSeReproduire() {
		return estMature() && (nbToursEntreDeuxNaissances == 0);
	}
	
	public void doIt() {
		if (estMature() && nbToursEntreDeuxNaissances > 0) {
			nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances - 1;
		}
		if (nbToursConserverDirection == 0) {
			this.direction = Direction.getRandomDirection();
			this.nbToursConserverDirection = 2 + (int)(Math.random()*3);
		}
		if (nbToursAvantMaturite > 0) {
			nbToursAvantMaturite = nbToursAvantMaturite - 1;
		}
		if (nbToursConserverDirection > 0) {
			nbToursConserverDirection = nbToursConserverDirection - 1;
		}
	}
	
	public void resetNbToursEntreDeuxNaissances() {
		nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissancesInitial;
	}
	
	public boolean estEnVie() {
		return enVie;
	}
	public void setEnVie(boolean a) {
		this.enVie = a;
	}
}
