package modeles.simulateurs;
import modeles.environnements.Environnement;
import modeles.environnements.EnvironnementAvatarChasseurs;
import modeles.environnements.elements.agents.Agent;
import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.Bille;
import modeles.environnements.elements.agents.Direction;
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
	private boolean dejaAvatar;
	
	private int cptPoisson = 0;
	private int cptRequin = 0;
	private int cptTour = 1;
	
	private LinkedList<Agent> agentsAAjouter;
	private LinkedList<Agent> agentsASupprimer;
	
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique, int nbBlocs) {
		this.environnement = new Environnement(nbColonnes,nbLignes,torique,nbBlocs);
		this.agents = new LinkedList<Agent>();
		this.pauseMS = pauseMS;
		this.continuer = true;
		
		this.agentsAAjouter = new LinkedList<Agent>();
		this.agentsASupprimer = new LinkedList<Agent>();
		this.dejaAvatar = false;
		
		new VueSimulateur(this, tailleCellule);
	}
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule, int pauseMS, boolean torique) {
		this(nbColonnes,nbLignes,tailleCellule,pauseMS,torique,0);
	}
	public Simulateur(int nbColonnes, int nbLignes, int tailleCellule) {
		this(nbColonnes,nbLignes,500, tailleCellule,false,0);
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
		
		EnvironnementAvatarChasseurs eac = null;
		if (environnement instanceof EnvironnementAvatarChasseurs) {
			eac = (EnvironnementAvatarChasseurs) environnement;
		}
		
		while (continuer) {
			
			agents.addAll(agentsAAjouter);
			agentsAAjouter.clear();
			agents.removeAll(agentsASupprimer);
			agentsASupprimer.clear();
			
			setChanged();
			notifyObservers();
			
			if (eac != null) {
				eac.mettreAJour();
			}
			
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
			System.out.println(cptTour+";"+cptPoisson+";"+cptRequin);
			cptTour++;
			cptRequin = 0;
			cptPoisson = 0;
			itAgents = null;
		}
	}
	
	public void ajouterAgent(Agent agent) {
		if (agent instanceof Avatar) {
			if(this.dejaAvatar) {
				return ;
			} else {
				this.dejaAvatar = true;
			}
		}
		agentsAAjouter.add(agent);
		environnement.mettreElementEnvironnement(agent);
	}
	
	public void supprimerAgent(Agent agent) {
		if (agent instanceof Avatar) {
			this.dejaAvatar = false;
		}
		agentsASupprimer.add(agent);
		environnement.enleverElementEnvironnement(agent);
	}
	
	public boolean arreterSimulation(){
		return continuer = false;
	}
}
