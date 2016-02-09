package modeles.environnements.elements.agents;

import modeles.environnements.Environnement;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.simulateurs.Simulateur;

public abstract class Agent extends ElementEnvironnement {
	protected Direction direction;
	protected Simulateur simulateur;
	// Un tour sur ralentissement
	protected int ralentissement;
	// Nombre de fois où la méthode peutInteragir a été appelée (normalement = nombre de fois où la méthode interagir a été appelée)
	protected int nbTours;
	
	public Agent(Simulateur simulateur, int posX, int posY, int ralentissement) {
		super(posX,posY);
		this.direction = Direction.getRandomDirection();
		this.simulateur = simulateur;
		this.ralentissement = ralentissement;
		this.nbTours = 0;
	}
	
	/**
	 * Crée un agent avec une direction aléatoire
	 * @param posX
	 * @param posY
	 * @param environnement
	 */
	public Agent(Simulateur simulateur, int posX, int posY) {
		this(simulateur,posX, posY,0);
	}
	
	public Agent(Simulateur simulateur,  int ralentissement) {
		this(simulateur);
		this.ralentissement = ralentissement;
	}
	
	/**
	 * Crée un agent avec une direction aléatoire et une position aléatoire
	 * @param environnement
	 */
	public Agent(Simulateur simulateur) {
		super();
		this.simulateur = simulateur;
		if (simulateur != null) {
			Environnement environnement = simulateur.getEnvironnement();
			if (environnement != null) {
				int tab[] = environnement.getCaseLibreAleatoire();
				if (tab == null) {
					simulateur.supprimerAgent(this);
				} else {
					posX = tab[0];
					posY = tab[1];
				}
			}
		}
		this.direction = Direction.getRandomDirection();
	}
	
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	
	public void setPosX(int x) { this.posX = x; }
	public void setPosY(int y) { this.posY = y; }
	
	public Direction getDirection() { return direction; }
	
	public void setDirection(Direction direction) {	this.direction = direction; }
	public void setDirection() { this.direction = Direction.getRandomDirection(); }
		
	/**
	 * Éviter d'utiliser cette fonction.
	 * N'utiliser qu'en cas de force majeure (pour des IA etc..)
	 * Raison: ne met pas à jour la direction.
	 * @param x
	 * @param y
	 */
	@Deprecated
	public void seDeplacer(int x, int y) {
		Environnement environnement = simulateur.getEnvironnement();
		if (environnement != null) {
			environnement.enleverElementEnvironnement(this);
		}
		this.posX = x;
		this.posY = y;
		if (environnement != null) {
			environnement.mettreElementEnvironnement(this);
		}
	}
	/**
	 * Fonction qui permet de se déplacer dans la direction qui est valorisée en attribut.
	 * Si pas possible, changement de direction automatiquement comme si c'était un rebond de bille.
	 */
	public void seDeplacer() {
		int tab[] = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
		int xF = tab[0], yF = tab[1], i = 0;
		Direction directions[] = Direction.getDirectionsPossiblesApresObstacle(getDirection());
		Environnement environnement = simulateur.getEnvironnement();
		while (environnement.aUnObstacle(xF,yF) && (i < directions.length)) {
			setDirection(directions[i]);
			tab = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
			xF = tab[0];
			yF = tab[1];
			i = i+1;
		}
		if (!environnement.aUnObstacle(xF,yF)) {
			seDeplacer(xF,yF);
		}
	}
	/**
	 * Attention. N'utiliser cette méthode qu'une fois par tour.
	 * Elle permet de ralentir éventuellement les agents.
	 * @return true si l'agent peut interagir (par rapport au ralentissement), false sinon. 
	 */
	public boolean peutInteragir() {
		this.nbTours = this.nbTours +1;
		if (ralentissement <= 0) {
			return true;
		}
		return (nbTours % ralentissement == 0);
	}
	/**
	 * Méthode appelée par les Simulateurs
	 */
	abstract public void interagir();
}
