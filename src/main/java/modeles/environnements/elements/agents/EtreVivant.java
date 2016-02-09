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
		this(simulateur,posX,posY,nbToursAvantMortFaim,nbToursAvantMaturite,nbToursEntreDeuxNaissances,0);
	}
	public EtreVivant(Simulateur simulateur, int posX, int posY, int nbToursAvantMortFaim, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances,int ralentissement){
		super(simulateur,posX ,posY,ralentissement);
		initialiser(nbToursAvantMortFaim,nbToursAvantMaturite,nbToursEntreDeuxNaissances);
	}
	
	public EtreVivant(Simulateur simulateur, int nbToursAvantMortFaim, int nbToursAvantMaturite,int nbToursEntreDeuxNaissances) {
		this(simulateur,nbToursAvantMortFaim,nbToursAvantMaturite,nbToursEntreDeuxNaissances,0);
	}
	
	public EtreVivant(Simulateur simulateur, int nbToursAvantMortFaim, int nbToursAvantMaturite,int nbToursEntreDeuxNaissances, int ralentissement) {
		super(simulateur, ralentissement);
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
	 * Met aussi à jour les variables de reproduction (décrémentation pour les délais)
	 */
	public boolean peutSeReproduire() {
		// Mise à jour des nbTours concernant la reproduction.
		if ((nbToursAvantMaturite <= 0) && (nbToursEntreDeuxNaissances > 0)) {
			nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissances - 1;
		}
		if (nbToursAvantMaturite > 0) {
			nbToursAvantMaturite = nbToursAvantMaturite - 1;
		}
		// Retour du résultat
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
	/**
	 * Se déplace dans une direction aléatoire pendant un certain temps puis changer de direction...
	 * Si un obstacle est détecté, changement de direction.
	 */
	public void seDeplacer() {
		// Mise à jour des nbTours concernant la direction
		if (nbToursConserverDirection <= 0) {
			this.direction = Direction.getRandomDirection();
			this.nbToursConserverDirection = 15 + (int)(Math.random()*7);
		}
		if (nbToursConserverDirection > 0) {
			nbToursConserverDirection = nbToursConserverDirection - 1;
		}
		// Déplacement
		super.seDeplacer();
	}
	
	public boolean peutMourirFaim() {
		if (nbToursAvantMortFaim > 0) {
			nbToursAvantMortFaim = nbToursAvantMortFaim -1;
		}
		return (nbToursAvantMortFaim <= 0);
	}
	
	public boolean estMature() {
		return nbToursAvantMaturite <= 0;
	}
	
	public void sePreparerPourAutreNaissance() {
		nbToursEntreDeuxNaissances = nbToursEntreDeuxNaissancesInitial;
	}
	public void manger(EtreVivant etreVivant) {
		etreVivant.mourir();
		nbToursAvantMortFaim = nbToursAvantMortFaimInitial;
	}
	
	public boolean estEnVie() {
		return enVie;
	}
	public void mourir() {
		this.enVie = false;
		if (simulateur != null) {
			simulateur.supprimerAgent(this);
		}
	}
}