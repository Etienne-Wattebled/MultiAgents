package modeles.environnements;

import java.util.Observable;

import vues.environnements.Commande;
import modeles.agents.Agent;

public class Environnement extends Observable{
	private int longueur;
	private int hauteur;
	
	private Agent[][] grille;

	public Environnement(int hauteur, int longueur) {
		this.hauteur = hauteur;
		this.longueur = longueur;
		this.grille = new Agent[longueur][hauteur];
	}
	
	public boolean estVide(int x, int y) {
		return (grille[x][y] == null);
	}

	public Agent getAgent(int x, int y) {
		return grille[x][y];
	}
	
	public int getLongueur() {
		return longueur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Agent[][] getGrille() {
		return grille;
	}
	/**
	 * Ne pas utiliser cette méthode. 
	 * Elle est utilisée automatiquement par l'Agent lorsqu'il se déplace ou lors de la valorisation de l'Environnement.
	 * Risque : problèmes de cohérence de données.
	 * @param agent
	 */
	@Deprecated
	public void mettreAgent(Agent agent) {
		grille[agent.getPosX()][agent.getPosY()] = agent;
		notifyObservers(new Object[] { Commande.METTRE_AGENT,agent });
	}
	
	/**
	 * Ne pas utiliser cette méthode. 
	 * Elle est utilisée automatiquement par l'Agent lorsqu'il se déplace.
	 * Risque : problèmes de cohérence de données.
	 */
	@Deprecated
	public void enleverAgent(Agent agent) {
		grille[agent.getPosX()][agent.getPosY()] = null;
		notifyObservers(new Object[] { Commande.ENLEVER_AGENT, agent });
	}
	
	public boolean xEstValide(int x) {
		return x >= 0 && x < longueur;
	}
	public boolean yEstValide(int y) {
		return y >= 0 && y < hauteur;
	}
	public boolean estValide(int x, int y) {
		return xEstValide(x) && yEstValide(y);
	}
}
