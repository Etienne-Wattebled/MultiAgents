package modeles.environnements.elements.agents.utilitaires;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import modeles.environnements.Environnement;
import modeles.environnements.elements.agents.Agent;

public class TableauDistancesAgent {
	
	protected Environnement environnement;
	protected int grilleDistances[][];
	protected Agent agent;
	protected int numToursDerniereMiseAJour;
	/**
	 * Tableau des distances d'un élément pour aller à l'agent
	 * @param environnement
	 * @param agent
	 * @param x
	 * @param y
	 */
	public TableauDistancesAgent(Environnement environnement, Agent agent) {
		this.grilleDistances = new int[environnement.getNbColonnes()][environnement.getNbLignes()];
		this.environnement = environnement;
		this.agent = agent;
		this.numToursDerniereMiseAJour = 0;
		resetGrilleDistances();
	}
	
	private void resetGrilleDistances() {
		for (int j =0; j<grilleDistances[0].length;j++) {
			for (int i = 0;i<grilleDistances.length;i++) {
				grilleDistances[i][j] = 0;
			}
		}
	}
	
	public void mettreAJour() {
		resetGrilleDistances();
		if ((environnement != null) && (agent != null)) {
			int x = agent.getPosX();
			int y = agent.getPosY();
			
			grilleDistances[x][y] = Integer.MAX_VALUE;
			List<Case> cases = getCasesLibresNonNumeroteesAuxAlentoursEtNumeroterVoisins(x,y,1);
			List<Case> cases2 = new LinkedList<Case>();
			boolean continuer = true;
			
			ListIterator<Case> it = null;
			Case c = null;
			
			while (continuer) {
				it = cases.listIterator();
				while (it.hasNext()) {
					c = it.next();
					it.remove();
					cases2.addAll(getCasesLibresNonNumeroteesAuxAlentoursEtNumeroterVoisins(c.getX(),c.getY(),c.getValeur()+1));
				}
				cases.addAll(cases2);
				cases2.clear();
				continuer = !cases.isEmpty();
			}
		}
	}
	
	private List<Case> getCasesLibresNonNumeroteesAuxAlentoursEtNumeroterVoisins(int x, int y, int valeurVoisins) {
		LinkedList<Case> list = new LinkedList<Case>();
		if (environnement != null) {
			int xp, yp;
			for (int j=-1;j<=1;j++) {
				for (int i = -1;i<=1;i++) {
					xp = x+i;
					yp = y+j;
					if (environnement.estTorique()) {
						xp = environnement.getX(xp);
						yp = environnement.getY(yp);
					}
					if ((environnement.existeCase(xp, yp) && (grilleDistances[xp][yp] == 0))) {
						if (environnement.aUnObstacle(xp, yp)) {
							// Un obstacle donc il faut empêcher le Chasseur de passer par là en mettant la distance la plus grande possible.
							grilleDistances[xp][yp] = Integer.MAX_VALUE;
						} else {
							grilleDistances[xp][yp] = valeurVoisins;
							list.add(new Case(xp,yp,valeurVoisins));
						}
					}
				}	
			}
		}
		return list;
	}
	
	public Direction getDirectionVersAgent(int x, int y) {
		int xf=0, yf=0;
		if (environnement != null) {
			int i,j,vMin = Integer.MAX_VALUE;
			int xp=0, yp=0;
			for (j = -1;j<=1;j++) {
				for (i =-1;i<=1;i++) {
					xp = x+i;
					yp = y+j;
					if (environnement.estTorique()) {
						xp = environnement.getX(xp);
						yp = environnement.getY(yp);
					}
					if ((environnement.existeCase(xp, yp) && grilleDistances[xp][yp] < vMin)) {
						vMin = grilleDistances[xp][yp];
						xf = xp;
						yf = yp;
					}
				}
			}
		}
		return Direction.getDirection(x,y,xf,yf);
	}
	
	private class Case {
		private int x;
		private int y;
		private int valeur;
		
		public Case(int x, int y, int valeur) {
			this.x = x;
			this.y = y;
			this.valeur = valeur;
		}
		public int getX() { return x; }
		public int getY() {	return y;}
		public int getValeur() { return valeur; }
	}
	public int getNumToursDerniereMiseAJour() {
		return numToursDerniereMiseAJour;
	}
	public void setNumToursDerniereMiseAJour(int numTours) {
		this.numToursDerniereMiseAJour = numTours;
	}
}
