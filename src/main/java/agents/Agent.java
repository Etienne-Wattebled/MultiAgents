package agents;

import environnements.Environnement;

public abstract class Agent {
	private int posX;
	private int posY;
	private Direction direction;
	private Environnement environnement;
	
	public Agent(int posX, int posY, Direction direction, Environnement environnement) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.environnement = environnement;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Environnement getEnvironnement() {
		return environnement;
	}
	
	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}
	
	abstract public void doIt();
}
