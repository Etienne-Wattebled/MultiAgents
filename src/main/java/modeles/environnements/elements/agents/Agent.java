package modeles.environnements.elements.agents;

import modeles.environnements.Environnement;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.simulateurs.Simulateur;

public abstract class Agent extends ElementEnvironnement {
	protected Direction direction;
	protected Simulateur simulateur;
	
	public Agent(Simulateur simulateur, int posX, int posY, Direction direction) {
		super(posX,posY);
		this.direction = direction;
		this.simulateur = simulateur;
	}
	/**
	 * Crée un agent avec une direction aléatoire
	 * @param posX
	 * @param posY
	 * @param environnement
	 */
	public Agent(Simulateur simulateur, int posX, int posY) {
		this(simulateur,posX, posY, Direction.getRandomDirection());
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
	
	abstract public void interagir();
}
