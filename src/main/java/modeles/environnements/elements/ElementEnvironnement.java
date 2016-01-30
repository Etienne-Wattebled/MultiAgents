package modeles.environnements.elements;

public abstract class ElementEnvironnement {
	protected int posX;
	protected int posY;
	
	public ElementEnvironnement() {
		this.posX = 0;
		this.posY = 0;
	}
	public ElementEnvironnement(int x, int y) {
		this.posX = x;
		this.posY = y;
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
}
