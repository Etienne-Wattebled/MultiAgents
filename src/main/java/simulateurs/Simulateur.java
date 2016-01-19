package simulateurs;
import modeles.agents.Agent;
import modeles.environnements.Environnement;

import java.util.Collections;
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
		while (continuer && agents.size() > 0) {
			Collections.shuffle(agents);
			for (Agent a : agents) {
				a.doIt();
			}
			try {
				Thread.sleep(pauseMS);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public boolean arreterSimulation(){
		return continuer = false;
	}
}
