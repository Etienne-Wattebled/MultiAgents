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
}