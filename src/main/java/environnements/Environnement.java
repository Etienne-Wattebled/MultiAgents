package environnements;

import java.util.Observable;

import agents.Agent;

public class Environnement extends Observable{
	private int longueur;
	private int hauteur;
	
	private Agent[][] grille;

	public Environnement(int hauteur, int longueur) {
		this.hauteur = hauteur;
		this.longueur = longueur;
		this.grille = new Agent[longueur][hauteur];
		this.initialiser();
	}

	private void initialiser() {
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
		notifyObservers();
	}
	
	/**
	 * Ne pas utiliser cette méthode. 
	 * Elle est utilisée automatiquement par l'Agent lorsqu'il se déplace.
	 * Risque : problèmes de cohérence de données.
	 */
	@Deprecated
	public void supprimerAgent(Agent agent) {
		grille[agent.getPosX()][agent.getPosY()] = null;
		notifyObservers();
	}
}
