package environnements;

import java.util.Observable;

public class Environnement extends Observable{
	protected int width, height;

	public Environnement(int height, int width) {
		this.height = height;
		this.width = width;
		this.init();
	}

	private void init() {				
	}

	public boolean emptyBox(int x, int y) {
		return true;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
