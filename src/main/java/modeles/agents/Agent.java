package modeles.agents;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public abstract class Agent {
	protected int posX;
	protected int posY;
	protected Direction direction;
	protected Simulateur simulateur;
	
	/**
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param posX
	 * @param posY
	 * @param direction
	 * @param environnement
	 */
	public Agent(Simulateur simulateur, int posX, int posY, Direction direction) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.simulateur = simulateur;
	}
	/**
	 * Direction aléatoire
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param posX
	 * @param posY
	 * @param environnement
	 */
	public Agent(Simulateur simulateur, int posX, int posY) {
		this(simulateur,posX, posY, Direction.getRandomDirection());
	}
	
	/**
	 * Direction aléatoire
	 * Position aléatoire
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param environnement
	 */
	public Agent(Simulateur simulateur) {
		this(simulateur,(int)(simulateur.getEnvironnement().getNbColonnes()*Math.random()),(int)(simulateur.getEnvironnement().getNbLignes()*Math.random()));		
	}
	
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	public Direction getDirection() { return direction; }
	
	public void setDirection(Direction direction) {	this.direction = direction; }
	public void setDirection() { this.direction = Direction.getRandomDirection(); }
		
	public void seDeplacer(int x, int y) {
		Environnement environnement = simulateur.getEnvironnement();
		if (environnement != null) {
			environnement.enleverAgent(this);
		}
		this.posX = x;
		this.posY = y;
		if (environnement != null) {
			environnement.mettreAgent(this);
		}
	}
	
	public void setPosX(int x) { this.posX = x; }
	public void setPosY(int y) { this.posY = y; }
	
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
	
	abstract public void doIt();
}
