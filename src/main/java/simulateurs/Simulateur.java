package simulateurs;
import modeles.agents.Agent;
import modeles.agents.Bille;
import modeles.agents.Direction;
import modeles.agents.EtreVivant;
import modeles.agents.Poisson;
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
	
	private LinkedList<Agent> agentsAAjouter;
	private LinkedList<Agent> agentsASupprimer;
	
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique) {
		this.environnement = new Environnement(nbColonnes,nbLignes,torique);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = pauseMS;
		this.continuer = true;
		
		this.agentsAAjouter = new LinkedList<Agent>();
		this.agentsASupprimer = new LinkedList<Agent>();
		
		new VueSimulateur(this, tailleCellule);
	}
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule) {
		this(nbColonnes,nbLignes,500, tailleCellule,false);
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
			agents.removeAll(agentsASupprimer);
			agentsASupprimer.clear();
			agents.addAll(agentsAAjouter);
			agentsAAjouter.clear();
			
			Collections.shuffle(agents);
			itAgents = agents.listIterator();
			while (itAgents.hasNext()) {
				agent = itAgents.next();
				if (agent instanceof EtreVivant) {
					EtreVivant ev = (EtreVivant) agent;
					if (ev.estEnVie()) {
						ev.doIt();
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
	
	public void ajouterAgent(Agent agent) {
		agentsAAjouter.add(agent);
		environnement.mettreAgent(agent);
	}
	
	public void supprimerAgent(Agent agent) {
		agentsASupprimer.add(agent);
		environnement.enleverAgent(agent);
	}
	
	public boolean arreterSimulation(){
		return continuer = false;
	}
	public static void main(String args[]) {
		Simulateur s = new Simulateur(100,100,6,100,false);
		for (int i=0; i < 10; i++ ){
			s.ajouterAgent(
					new Poisson(
							s, // simulateur
							5, // délais maturité
							5 // délais entre deux naissances
					)
			);
		}
		for (int i=0; i < 10; i++ ){
			s.ajouterAgent(
					new Requin(
							s, // simulateur
							5, // délais maturité
							3, // délais entre deux naissances
							10 // délais mort sans manger
					)
			);
		}
		s.lancerSimulation();
	}
}
