package simulateurs;
import modeles.environnements.Environnement;
import modeles.environnements.elements.agents.Agent;
import modeles.environnements.elements.agents.Bille;
import modeles.environnements.elements.agents.Direction;
import modeles.environnements.elements.agents.EtreVivant;
import modeles.environnements.elements.agents.Poisson;
import modeles.environnements.elements.agents.Requin;
import vues.VueSimulateur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

public class Simulateur extends Observable {
	private LinkedList<Agent> agents;
	private Environnement environnement;
	private int pauseMS;
	private boolean continuer;
	
	private int cptPoisson = 0;
	private int cptRequin = 0;
	
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
			
			agents.addAll(agentsAAjouter);
			agentsAAjouter.clear();
			agents.removeAll(agentsASupprimer);
			agentsASupprimer.clear();
			
			setChanged();
			notifyObservers();
			
			Collections.shuffle(agents);
			itAgents = agents.listIterator();
			while (itAgents.hasNext()) {
				agent = itAgents.next();
				if (agent instanceof EtreVivant) {
					EtreVivant ev = (EtreVivant) agent;
					if (ev.estEnVie()) {
						ev.interagir();
						if(agent instanceof Poisson){
							cptPoisson++;
						}
						if (agent instanceof Requin) {
							cptRequin++;
						}
					}
				} else {
					agent.interagir();
				}
			}
			if (pauseMS > 0) {
				try {
					Thread.sleep(pauseMS);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			System.out.println(cptPoisson+";"+cptRequin);
			cptRequin = 0;
			cptPoisson = 0;
			itAgents = null;
		}
	}
	
	public void ajouterAgent(Agent agent) {
		agentsAAjouter.add(agent);
		environnement.mettreElementEnvironnement(agent);
	}
	
	public void supprimerAgent(Agent agent) {
		agentsASupprimer.add(agent);
		environnement.enleverElementEnvironnement(agent);
	}
	
	public boolean arreterSimulation(){
		return continuer = false;
	}
	public static void main(String args[]) {
		Simulateur s = new Simulateur(200,100,6,50,true);
		for (int i=0; i < 100; i++ ){
			s.ajouterAgent(
					new Poisson(
							s, // simulateur
							20, // délais maturité
							8 // délais entre deux naissances
					)
			);
		}
		for (int i=0; i < 20; i++){
			s.ajouterAgent(
					new Requin(
							s, // simulateur
							50, // délais maturité
							15, // délais entre deux naissances
							30 // délais mort sans manger
					)
			);
		}
		s.lancerSimulation();
	}
}
