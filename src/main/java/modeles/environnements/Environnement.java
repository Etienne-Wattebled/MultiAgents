package modeles.environnements;

import java.util.Observable;

import vues.environnements.Commande;
import modeles.agents.Agent;

public class Environnement extends Observable{
	private int longueur;
	private int hauteur;
	private boolean torique;
	
	private Agent[][] grille;

	public Environnement(int longueur, int hauteur) {
		this.hauteur = hauteur;
		this.longueur = longueur;
		this.grille = new Agent[longueur][hauteur];
		this.torique = false;
	}
	
	// Convertir torique => coordonnées tableau
	private int getX(int xTorique) {
		if (xTorique >= 0) {  return xTorique%longueur; }
		else { return longueur - Math.abs(xTorique); }
	}
	private int getY(int yTorique) {
		if (yTorique >= 0) { return yTorique%hauteur; }
		else { return hauteur - Math.abs(yTorique); }
	}
	
	public int getLongueur() { return longueur; }
	public int getHauteur() { return hauteur; }
	public Agent[][] getGrille() { return grille; }
	
	/**
	 * 
	 * @param x coordonnée x
	 * @param y coordonnée y
	 * @return vrai s'il existe une case aux coordonnées x et y
	 */
	public boolean existeCase(int x, int y) { return torique || (x >= 0 && y >= 0 && x < longueur && y < hauteur); }
	public boolean estTorique() { return torique; }
	
	/**
	 * 
	 * @param x coordonnée x
	 * @param y coordonnée y
	 * @return vrai si la case aux coordonnées x,y est un obstacle (ou si elle n'existe pas)
	 */
	public boolean aUnObstacle(int x, int y) {
		if (torique) {
			x = getX(x);
			y = getY(y);
		}
		return !existeCase(x,y) || grille[x][y] != null;
	}
	
	/**
	 * Ne pas utiliser cette méthode. 
	 * Elle est utilisée automatiquement par l'Agent lorsqu'il se déplace ou lors de la valorisation de l'Environnement.
	 * Risque : problèmes de cohérence de données.
	 * CF: se déplacer dans l'Agent
	 * @param agent
	 */
	@Deprecated
	public void mettreAgent(Agent agent) {
		if (torique) {
			agent.setPosX(getX(agent.getPosX()));
			agent.setPosY(getY(agent.getPosY()));
		}
		if (existeCase(agent.getPosX(),agent.getPosY())) {	
			grille[agent.getPosX()][agent.getPosY()] = agent;
			notifyObservers(new Object[] { Commande.METTRE_AGENT,agent });
		}
	}
	
	/**
	 * Ne pas utiliser cette méthode. 
	 * Elle est utilisée automatiquement par l'Agent lorsqu'il se déplace.
	 * Risque : problèmes de cohérence de données.
	 * CF: se déplacer dans l'Agent
	 */
	@Deprecated
	public void enleverAgent(Agent agent) {
		if (existeCase(agent.getPosX(),agent.getPosY())) {
			grille[agent.getPosX()][agent.getPosY()] = null;
			notifyObservers(new Object[] { Commande.ENLEVER_AGENT, agent });
		}
	}
}
