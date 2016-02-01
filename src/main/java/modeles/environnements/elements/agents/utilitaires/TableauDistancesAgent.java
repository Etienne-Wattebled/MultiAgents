package modeles.environnements.elements.agents.utilitaires;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

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
			Queue<Case> cases = new LinkedList<Case>();
			ajouterEtNumeroterVoisins(agent.getPosX(),agent.getPosY(),1,cases);
			
			Case c = null;
			while (!cases.isEmpty()) {
				c = cases.poll();
				ajouterEtNumeroterVoisins(c.getX(),c.getY(),c.getValeur()+1,cases);
			}
		}
	}

	
	private void ajouterEtNumeroterVoisins(int x, int y, int valeurVoisins, Queue<Case> cases) {
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
					if ((environnement.existeCase(xp, yp)) && (grilleDistances[xp][yp] == null) && (!environnement.aUnObstacle(xp, yp))) {
						grilleDistances[xp][yp] = new Case(xp,yp,valeurVoisins);
						cases.add(grilleDistances[xp][yp]);
					}
				}	
			}
		}
	}
	
	public int[] getProchainesCoordonneesVersAgent(int x, int y) {
		Case vMin = new Case(-1,-1,Integer.MAX_VALUE);
		
		if (environnement != null) {
			int i,j;
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
					}
				}
			}
		} 
		return new int[] {vMin.getX(),vMin.getY()};
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
	}
}
