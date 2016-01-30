package modeles.environnements;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.Direction;

public class EnvironnementAvatarChasseurs extends Environnement {
	protected Avatar avatar;
	protected int grilleDistances[][];
	
	public EnvironnementAvatarChasseurs(int nbColonnes, int nbLignes, boolean torique, int nbBlocs, Avatar avatar) {
		super(nbColonnes, nbLignes, torique, nbBlocs);
		this.grilleDistances = new int[nbColonnes][nbLignes];
		this.avatar = avatar;
		resetGrilleDistances();
	}
	
	private void resetGrilleDistances() {
		for (int j =0; j<grilleDistances[0].length;j++) {
			for (int i = 0;i<grilleDistances.length;i++) {
				grilleDistances[i][j] = 0;
			}
		}
	}
	
	public int[][] getGrilleDistances() {
		return grilleDistances;
	}
	
	public void mettreAJour() {
		resetGrilleDistances();
		int x = avatar.getPosX();
		int y = avatar.getPosY();
		
		grilleDistances[x][y] = Integer.MAX_VALUE;
		List<Case> cases = getCasesLibresNonNumeroteesAuxAlentoursEtNumeroteVoisins(x,y,1);
		List<Case> cases2 = new LinkedList<Case>();
		boolean continuer = true;
		
		ListIterator<Case> it = null;
		Case c = null;
		
		while (continuer) {
			it = cases.listIterator();
			while (it.hasNext()) {
				c = it.next();
				it.remove();
				cases2.addAll(getCasesLibresNonNumeroteesAuxAlentoursEtNumeroteVoisins(c.getX(),c.getY(),c.getValeur()+1));
			}
			cases.addAll(cases2);
			cases2.clear();
			continuer = !cases.isEmpty();
		}
	}
	
	private List<Case> getCasesLibresNonNumeroteesAuxAlentoursEtNumeroteVoisins(int x, int y, int valeurVoisins) {
		LinkedList<Case> list = new LinkedList<Case>();
		int xp, yp;
		for (int j=-1;j<=1;j++) {
			for (int i = -1;i<=1;i++) {
				xp = x+i;
				yp = y+j;
				if (torique) {
					xp = getX(xp);
					yp = getY(yp);
				}
				if ((existeCase(xp, yp) && (grilleDistances[xp][yp] == 0))) {
					if (aUnObstacle(xp, yp)) {
						// Un obstable donc il faut empêcher le Chasseur de passer par là en mettant la distance la plus grande possible.
						grilleDistances[xp][yp] = Integer.MAX_VALUE;
					} else {
						grilleDistances[xp][yp] = valeurVoisins;
						list.add(new Case(xp,yp,valeurVoisins));
					}
				}
			}		
		}
		return list;
	}
	
	public Direction getDirectionVersAvatar(int x, int y) {
		return null;
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
}
