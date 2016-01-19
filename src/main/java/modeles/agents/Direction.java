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
				directionOpposee = NORD_OUEST;
				break;
			case SUD_EST:
				directionOpposee = NORD_EST;
				break;
			case NORD_EST:
				directionOpposee = SUD_EST;
				break;
			case NORD_OUEST:
				directionOpposee = SUD_OUEST;
				break;
		}
		return directionOpposee;
	}
}