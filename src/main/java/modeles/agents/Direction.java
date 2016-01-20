package modeles.agents;

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
	public static Direction getDirectionRebondissement(Direction direction) {
		Direction directionRebondissement=null;
		switch (direction) {
			case NORD:
				directionRebondissement = SUD;
				break;
			case SUD:
				directionRebondissement = NORD;
				break;
			case OUEST:
				directionRebondissement = EST;
				break;
			case EST:
				directionRebondissement = OUEST;
				break;
			case SUD_OUEST:
				directionRebondissement = NORD_OUEST;
				break;
			case SUD_EST:
				directionRebondissement = NORD_EST;
				break;
			case NORD_EST:
				directionRebondissement = SUD_EST;
				break;
			case NORD_OUEST:
				directionRebondissement = SUD_OUEST;
				break;
		}
		return directionRebondissement;
	}
	
	public static Direction getDirectionOpposee(Direction direction) {
		Direction directionOpposee=null;
		switch (direction) {
			case NORD:
				directionOpposee = SUD;
				break;
			case SUD:
				directionOpposee = NORD;
				break;
			case OUEST:
				directionOpposee = EST;
				break;
			case EST:
				directionOpposee = OUEST;
				break;
			case SUD_OUEST:
				directionOpposee = NORD_EST;
				break;
			case SUD_EST:
				directionOpposee = NORD_OUEST;
				break;
			case NORD_EST:
				directionOpposee = SUD_OUEST;
				break;
			case NORD_OUEST:
				directionOpposee = SUD_EST;
				break;
		}
		return directionOpposee;
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
}