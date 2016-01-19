package simulateurs;
import modeles.agents.Agent;
import modeles.environnements.Environnement;
import vues.environnements.VueSimulateur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;

public class Simulateur extends Observable {
	private LinkedList<Agent> agents;
	private Environnement environnement;
	private int pauseMS;
	private boolean continuer;
	
	public Simulateur(int longueur, int hauteur, int tailleCellule, int pauseMS) {
		this.environnement = new Environnement(longueur,hauteur);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = pauseMS;
		this.continuer = true;
		new VueSimulateur(longueur, hauteur,tailleCellule, this);
	}
	public Simulateur(int longueur, int hauteur, int tailleCellule) {
		this(longueur,hauteur,500, tailleCellule);
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
			notifyObservers();
		}
	}
	public void ajouterAgent(Agent agent) {
		agents.add(agent);
	}
	public boolean arreterSimulation(){
		return continuer = false;
	}
}
