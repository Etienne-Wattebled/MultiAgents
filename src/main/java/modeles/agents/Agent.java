package modeles.agents;

import modeles.environnements.Environnement;

public abstract class Agent {
	private int posX;
	private int posY;
	private Direction direction;
	private Environnement environnement;
	
	/**
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param posX
	 * @param posY
	 * @param direction
	 * @param environnement
	 */
	public Agent(int posX, int posY, Direction direction, Environnement environnement) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.environnement = environnement;
		if (environnement != null) {
			environnement.mettreAgent(this);
		}
	}
	/**
	 * Direction aléatoire
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param posX
	 * @param posY
	 * @param environnement
	 */
	public Agent(int posX, int posY, Environnement environnement) {
		this(posX, posY, Direction.getRandomDirection(), environnement);
	}
	
	/**
	 * Direction aléatoire
	 * Position aléatoire
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param environnement
	 */
	public Agent(Environnement environnement) {
		this((int)(environnement.getLongueur()*Math.random()),(int)(environnement.getHauteur()*Math.random()),environnement);		
	}
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Set a random direction.
	 */
	public void setDirection() {
		this.direction = Direction.getRandomDirection();
	}
	
	public Environnement getEnvironnement() {
		return environnement;
	}
	
	public void seDeplacer(int x, int y) {
		if (environnement != null) {
			this.environnement.enleverAgent(this);
		}
		this.posX = x;
		this.posY = y;
		if (environnement != null) {
			this.environnement.mettreAgent(this);
		}
	}
	
	abstract public void doIt();
}
