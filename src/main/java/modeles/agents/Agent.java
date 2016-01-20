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
	public Agent(Environnement environnement, int posX, int posY, Direction direction) {
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
	public Agent(Environnement environnement, int posX, int posY) {
		this(environnement,posX, posY, Direction.getRandomDirection());
	}
	
	/**
	 * Direction aléatoire
	 * Position aléatoire
	 * Mise en place de l'agent automatiquement dans l'environnement
	 * @param environnement
	 */
	public Agent(Environnement environnement) {
		this(environnement,(int)(environnement.getLongueur()*Math.random()),(int)(environnement.getHauteur()*Math.random()));		
	}
	
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	public Direction getDirection() { return direction; }
	public Environnement getEnvironnement() { return environnement; }
	
	public void setDirection(Direction direction) {	this.direction = direction; }
	public void setDirection() { this.direction = Direction.getRandomDirection(); }
		
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
	
	@Deprecated
	/**
	 * Méthode utilisée par l'environnement lorsqu'elle sait ce qu'elle fait.
	 * Ne pas utiliser cette méthode.
	 * CF: se déplacer
	 */
	public void setPosX(int x) { this.posX = x; }
	
	@Deprecated
	/**
	 * Méthode utilisée par l'environnement lorsqu'elle sait ce qu'elle fait.
	 * Ne pas utiliser cette méthode.
	 * CF: se déplacer
	 * @param y
	 */
	public void setPosY(int y) { this.posY = y; }
	
	public void seDeplacer() {
		int tab[] = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
		int xF = tab[0], yF = tab[1];
		
		if (!getEnvironnement().aUnObstacle(xF,yF)) {
			seDeplacer(xF,yF);
		} else {
			setDirection(Direction.getDirectionRebondissement(getDirection()));
			tab = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
			xF = tab[0];
			yF = tab[1];
			if (!getEnvironnement().aUnObstacle(xF, yF)) {
				seDeplacer(xF,yF);
			}
			else{
				setDirection(Direction.getDirectionOpposee(getDirection()));
				tab = Direction.calculerNouvellesCoordonnees(getDirection(),getPosX(),getPosY());
				xF = tab[0];
				yF = tab[1];
				if (!getEnvironnement().aUnObstacle(xF, yF)) {
					seDeplacer(xF,yF);
				}
			}
		}
	}
	
	abstract public void doIt();
}
