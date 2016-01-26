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
		super(simulateur,(int)(simulateur.getEnvironnement().getNbColonnes()*Math.random()),(int)(simulateur.getEnvironnement().getNbLignes()*Math.random()));
		this.enVie = true;
	}
	
	public boolean estMature() {
		return nbToursAvantMaturite == 0;
	}
	
	public void seDeplacer() {
		int nbToursConserverDirectionMin = 2;
		int nbToursConserverDirectionMax = 4;
		
		if (nbToursConserverDirection == 0) {
			this.direction = Direction.getRandomDirection();
			this.nbToursConserverDirection = nbToursConserverDirectionMin + (int)(Math.random()*(nbToursConserverDirectionMax- nbToursConserverDirectionMin));
		}
		Direction d = direction;
		super.seDeplacer();
		if (d != direction) {
			this.nbToursConserverDirection = nbToursConserverDirectionMin + (int)(Math.random()*(nbToursConserverDirectionMax- nbToursConserverDirectionMin));
		}
	}
	
	public void doIt() {
		if (nbToursAvantMaturite > 0) {
			nbToursAvantMaturite = nbToursAvantMaturite - 1;
		}
		if (nbToursConserverDirection > 0) {
			nbToursConserverDirection = nbToursConserverDirection - 1;
		}
		if (nbToursEntreDeuxNaissances > 0) {
			nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances - 1;
		}
	}
	public boolean estEnVie() {
		return enVie;
	}
	public void setEnVie(boolean a) {
		this.enVie = a;
	}
}
