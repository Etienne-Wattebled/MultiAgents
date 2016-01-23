package simulateurs;
import modeles.agents.Agent;
import modeles.agents.Bille;
import modeles.agents.Direction;
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
	
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique) {
		this.environnement = new Environnement(nbColonnes,nbLignes,torique);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = pauseMS;
		this.continuer = true;
		new VueSimulateur(this, tailleCellule);
	}
	public Simulateur(int longueur, int hauteur, int tailleCellule) {
		this(longueur,hauteur,500, tailleCellule,false);
	}
	
	public Environnement getEnvironnement() {
		return environnement;
	}
	
	public int getNbColonnes() {
		return environnement.getNbColonnes();
	}
	public int getNbLignes() {
		return environnement.getNbLignes();
	}
	
	public void setPauseMS(int pause) {
		this.pauseMS = pause;
	}
	
	public void lancerSimulation() {
		while (continuer) {
			Collections.shuffle(agents);
			for (Agent a : agents) {
				a.doIt();
			}
			try {
				Thread.sleep(pauseMS);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			setChanged();
			notifyObservers();
		}
	}
	public void ajouterAgent(Agent agent) {
		agents.add(agent);
	}
	public boolean arreterSimulation(){
		return continuer = false;
	}
	public static void main(String args[]) {
		Simulateur s = new Simulateur(400,200,4,10,false);
		for (int i=0; i < 10; i++ ){
			s.ajouterAgent(new Bille(s.getEnvironnement()));
		}
		s.lancerSimulation();
	}
}
