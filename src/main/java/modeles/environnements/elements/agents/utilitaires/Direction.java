package modeles.environnements.elements.agents.utilitaires;

public enum Direction {
	NORD,
	SUD,
	EST,
	OUEST,
	NORD_OUEST,
	NORD_EST,
	SUD_EST,
	SUD_OUEST;
	
	public static Direction getRandomDirection() {
		Direction directions[] = Direction.values();
		int nbDirections = directions.length;
		return directions[(int) (nbDirections*Math.random())];
	}
	public static Direction[] getDirectionsPossiblesApresObstacle(Direction direction) {
		Direction directionRebondissement[] = new Direction[3];
		switch (direction) {
			case NORD:
				directionRebondissement[0] = SUD;
				directionRebondissement[1] = SUD_OUEST;
				directionRebondissement[2] = SUD_EST;
				break;
				
			case SUD:
				directionRebondissement[0] = NORD;
				directionRebondissement[1] = NORD_OUEST;
				directionRebondissement[2] = NORD_EST;

				break;
			case OUEST:
				directionRebondissement[0] = EST;
				directionRebondissement[1] = NORD_EST;
				directionRebondissement[2] = SUD_EST;

				break;
			case EST:
				directionRebondissement[0] = OUEST;
				directionRebondissement[1] = NORD_OUEST;
				directionRebondissement[2] = SUD_OUEST;

				break;
			case SUD_OUEST:
				directionRebondissement[0] = NORD_OUEST;
				directionRebondissement[1] = SUD_EST;
				directionRebondissement[2] = NORD_EST;
				
				break;
			case SUD_EST:
				directionRebondissement[0] = NORD_EST;
				directionRebondissement[1] = SUD_OUEST;
				directionRebondissement[2] = NORD_OUEST;
				break;
			case NORD_EST:
				directionRebondissement[0] = SUD_EST;
				directionRebondissement[1] = NORD_OUEST;
				directionRebondissement[2] = SUD_OUEST;
				break;
			case NORD_OUEST:
				directionRebondissement[0] = SUD_OUEST;
				directionRebondissement[1] = NORD_EST;
				directionRebondissement[2] = SUD_EST;
				break;
		}
		return directionRebondissement;
	}
	
	// [x,y]
	public static int[] calculerNouvellesCoordonnees(Direction direction, int x, int y) {
		int tab[] = new int[2];
		tab[0] = x;
		tab[1] = y;
		// NORD
		if ((direction == NORD) || (direction == NORD_EST) || (direction == NORD_OUEST)) {
			tab[1] = tab[1]-1;
		}
		// SUD
		if ((direction == SUD) || (direction == SUD_OUEST) || (direction == SUD_EST)) {
			tab[1] = tab[1]+1;
		}
		// OUEST
		if ((direction == OUEST) || (direction == NORD_OUEST) || (direction == SUD_OUEST)) {
			tab[0] = tab[0]-1;
		}
		// EST
		if ((direction == EST) || (direction == NORD_EST) || (direction == SUD_EST)) {
			tab[0] = tab[0]+1;
		}
		return tab;
	}
	
	public static Direction getDirection(int xd, int yd, int xf, int yf) {
		Direction direction = null;
		if (xf < xd) {
			if (yf < yd) {
				direction = Direction.NORD_OUEST;
			}
			else if (yf > yd) {
				direction = Direction.SUD_OUEST;
			} else {
				direction = Direction.OUEST;
			}
		} 
		else if (xf > xd) {
			if (yf < yd) {
				direction = Direction.NORD_EST;
			} else if (yf > yd) {
				direction = Direction.SUD_EST;
			} else {
				direction = Direction.EST;
			}
		} else {
			if (yf < yd) {
				direction = Direction.NORD;
			} else if (yf > yd) {
				direction = Direction.SUD;
			}
		}
		return direction;
	}
}