package modeles.environnements;

import java.util.LinkedList;
import java.util.List;

import modeles.agents.Agent;

public class Environnement {
	private int nbColonnes;
	private int nbLignes;
	private boolean torique;
	
	private Agent[][] grille;

	public Environnement(int nbColonnes, int nbLignes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.grille = new Agent[nbColonnes][nbLignes];
		this.torique = false;
	}
	public Environnement(int longueur, int hauteur, boolean torique) {
		this(longueur,hauteur);
		this.torique = torique;
	}
	
	// Convertir torique => coordonnées tableau
	private int getX(int xTorique) {
		if (xTorique >= 0) {  return xTorique%nbColonnes; }
		else { return nbColonnes - Math.abs(xTorique); }
	}
	private int getY(int yTorique) {
		if (yTorique >= 0) { return yTorique%nbLignes; }
		else { return nbLignes - Math.abs(yTorique); }
	}
	
	public Agent getAgent(int x, int y) { 
		if (existeCase(x,y)) { return grille[x][y]; }
		return null;
	}
	
	public int getNbColonnes() { return nbColonnes; }
	public int getNbLignes() { return nbLignes; }
	public Agent[][] getGrille() { return grille; }
	
	/**
	 * 
	 * @param x coordonnée x
	 * @param y coordonnée y
	 * @return vrai s'il existe une case aux coordonnées x et y
	 */
	public boolean existeCase(int x, int y) { return torique || (x >= 0 && y >= 0 && x < nbColonnes && y < nbLignes); }
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

	public void mettreAgent(Agent agent) {
		if (torique) {
			agent.setPosX(getX(agent.getPosX()));
			agent.setPosY(getY(agent.getPosY()));
		}
		if (existeCase(agent.getPosX(),agent.getPosY())) {	
			grille[agent.getPosX()][agent.getPosY()] = agent;
		}
	}
	
	public void enleverAgent(Agent agent) {
		if (existeCase(agent.getPosX(),agent.getPosY())) {
			grille[agent.getPosX()][agent.getPosY()] = null;
		}
	}
	
	public int[] getCaseLibreAuxAlentours(int x,int y) {
		int xp,yp; // périphérie
		for (int i = -1; i<=1 ;i++) {
			for (int j = -1; j<=1;j++) {
				xp = x+i;
				yp = y+j;
				if (torique) {
					xp = getX(x);
					yp = getY(y);
				}
				if (!aUnObstacle(xp, yp)) {
					return new int[] {xp,yp};
				}
			}
		}
		return null;
	}
	public List<Agent> getAgentsAuxAlentours(int x, int y) {
		LinkedList<Agent> agents = new LinkedList<Agent>();
		int xp,yp; // périphérie
		for (int i = -1; i<=1;i++) {
			for (int j =-1;j<=1;j++) {
				xp = x+i;
				yp = y+j;
				if (torique) {
					xp = getX(x);
					yp = getY(y);
				}
				if ((aUnObstacle(xp,yp)) && (existeCase(xp,yp)) && (xp!=x) && (yp != y) && (grille[xp][yp] instanceof Agent)) {
					agents.add(grille[xp][yp]);
				}
			}
		}
		return agents;
	}
}
