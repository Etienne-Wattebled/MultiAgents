package modeles.simulateurs;
import modeles.environnements.Environnement;
import modeles.environnements.elements.agents.Agent;
import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.EtreVivant;
import modeles.environnements.elements.agents.Poisson;
import modeles.environnements.elements.agents.Requin;
import vues.simulateurs.VueSimulateur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

public class Simulateur extends Observable {
	private LinkedList<Agent> agents;
	private Environnement environnement;
	
	private int pauseMS;
	private boolean continuer;
	private VueSimulateur vueSimulateur;
	
	private int cptPoisson = 0;
	private int cptRequin = 0;
	private int cptTour = 1;
	
	private LinkedList<Agent> agentsAAjouter;
	private LinkedList<Agent> agentsASupprimer;
	
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique, int nbBlocs, boolean menu) {
		this.environnement = new Environnement(nbColonnes,nbLignes,torique,nbBlocs);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = pauseMS;
		this.continuer = true;
		
		this.agentsAAjouter = new LinkedList<Agent>();
		this.agentsASupprimer = new LinkedList<Agent>();
		this.vueSimulateur = new VueSimulateur(this, tailleCellule,menu);
	}
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique) {
		this(nbColonnes,nbLignes,tailleCellule,pauseMS,torique,0,true);
	}
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule) {
		this(nbColonnes,nbLignes,500, tailleCellule,false,0,true);
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
				// Etre Vivant
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
				
				// Avatar
				} else if (agent instanceof Avatar) {
					Avatar avatar = (Avatar) agent;
					if(!avatar.getAEteAttrape()) {
						agent.interagir();
					}
					
				// Autres...
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
			System.out.println(cptTour+";"+cptPoisson+";"+cptRequin);
			cptTour++;
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
	
	public VueSimulateur getVueSimulateur() {
		return vueSimulateur;
	}
	
	public int getCptTour() {
		return cptTour;
	}
}
