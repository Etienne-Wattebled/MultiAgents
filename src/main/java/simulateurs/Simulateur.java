package simulateurs;
import modeles.agents.Agent;
import modeles.agents.Bille;
import modeles.agents.Direction;
import modeles.agents.EtreVivant;
import modeles.agents.Requin;
import modeles.environnements.Environnement;
import vues.environnements.VueSimulateur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
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
		ListIterator<Agent> itAgents = null;
		Agent agent = null;
		while (continuer) {
			Collections.shuffle(agents);
			itAgents = agents.listIterator();
			while (itAgents.hasNext()) {
				agent = itAgents.next();
				if (agent instanceof EtreVivant) {
					EtreVivant ev = (EtreVivant) agent;
					if (ev.estEnVie()) {
						ev.doIt();
					} else {
						itAgents.remove();
						environnement.enleverAgent(ev);
					}
				} else {
					agent.doIt();
				}
				
			}	
			if (pauseMS > 0) {
				try {
					Thread.sleep(pauseMS);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			itAgents = null;
			setChanged();
			notifyObservers();
		}
	}
	@Deprecated
	/**
	 * Appelée automatiquement lorsqu'on passe le simulateur à l'agent.
	 * @param agent
	 */
	public void ajouterAgent(Agent agent) {
		agents.add(agent);
		environnement.mettreAgent(agent);
	}
	/**
	 * Appelée automatiquement lorsqu'on passe le simulateur à l'agent.
	 * @param agent
	 */
	@Deprecated
	public void supprimerAgent(Agent agent) {
		agents.remove(agent);
		environnement.enleverAgent(agent);
	}
	
	public boolean arreterSimulation(){
		return continuer = false;
	}
	public static void main(String args[]) {
		Simulateur s = new Simulateur(200,200,4,10,false);
		/*
		for (int i=0; i < 10; i++ ){
			new Bille(s);
		}
		*/
		for (int i=0; i < 100; i++ ){
			new Requin(s,5,3,10);
		}
		s.lancerSimulation();
	}
}
