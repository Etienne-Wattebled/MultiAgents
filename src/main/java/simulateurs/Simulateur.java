package simulateurs;
import modeles.agents.Agent;
import modeles.environnements.Environnement;
import java.util.LinkedList;

public class Simulateur {
	private LinkedList<Agent> agents;
	private Environnement environnement;
	private int pauseMS;
	private boolean continuer;
	
	public Simulateur(int longueur, int hauteur) {
		this.environnement = new Environnement(longueur,hauteur);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = 500;
		this.continuer = true;
	}
	public void lancerSimulation() {
		while (continuer) {
			
		}
	}
}
