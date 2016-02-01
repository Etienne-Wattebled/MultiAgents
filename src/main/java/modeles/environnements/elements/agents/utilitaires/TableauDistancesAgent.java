package modeles.environnements.elements.agents.utilitaires;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import modeles.environnements.Environnement;
import modeles.environnements.elements.agents.Agent;

public class TableauDistancesAgent {
	
	protected Environnement environnement;
	protected Case grilleDistances[][];
	protected Agent agent;
	/**
	 * Tableau des distances d'un élément pour aller à l'agent
	 * @param environnement
	 * @param agent
	 * @param x
	 * @param y
	 */
	public TableauDistancesAgent(Environnement environnement, Agent agent) {
		this.grilleDistances = new Case[environnement.getNbColonnes()][environnement.getNbLignes()];
		this.environnement = environnement;
		this.agent = agent;
		resetGrilleDistances();
	}
	
	private void resetGrilleDistances() {
		for (int j =0; j<grilleDistances[0].length;j++) {
			for (int i = 0;i<grilleDistances.length;i++) {
				grilleDistances[i][j] = null;
			}
		}
	}
	
	public void mettreAJour() {
		resetGrilleDistances();
		if ((environnement != null) && (agent != null)) {
			int x = agent.getPosX();
			int y = agent.getPosY();
			
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
					if (environnement.existeCase(xp, yp)) {
						if (grilleDistances[xp][yp] == null) {
							if (!environnement.aUnObstacle(xp, yp)) {
								grilleDistances[xp][yp] = new Case(xp,yp,valeurVoisins);
								list.add(grilleDistances[xp][yp]);
							}
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
			int i,j;
			Case vMin = new Case(-1,-1,Integer.MAX_VALUE);
			int xp=0, yp=0;
			for (j = -1;j<=1;j++) {
				for (i =-1;i<=1;i++) {
					xp = x+i;
					yp = y+j;
					if (environnement.estTorique()) {
						xp = environnement.getX(xp);
						yp = environnement.getY(yp);
					}
					if ((environnement.existeCase(xp, yp) && (grilleDistances[xp][yp] != null) && grilleDistances[xp][yp].getValeur() < vMin.getValeur())) {
						vMin = grilleDistances[xp][yp];
						xf = xp;
						yf = yp;
					}
				}
			}
		}
		// Portée de 1 donc chemin le plus court.
		return Direction.getDirection(x,y,xf,yf);
	}
	
	private class Case {
		protected int x;
		protected int y;
		protected int valeur;
		
		public Case(int x, int y, int valeur) {
			this.x = x;
			this.y = y;
			this.valeur = valeur;
		}
		public int getX() { return x; }
		public int getY() {	return y;}
		public int getValeur() { return valeur; }
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = x;
		}
		public void setValeur(int valeur) {
			this.valeur = valeur;
		}
	}
}
