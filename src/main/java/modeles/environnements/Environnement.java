package modeles.environnements;

import java.util.LinkedList;
import java.util.List;

import modeles.environnements.elements.ElementEnvironnement;
import modeles.environnements.elements.agents.Agent;
import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.blocs.Bloc;

public class Environnement {
	protected int nbColonnes;
	protected int nbLignes;
	protected boolean torique;
	protected int nbBlocs;
	
	protected Avatar avatar;
	
	private ElementEnvironnement[][] grille;

	public Environnement(int nbColonnes, int nbLignes, boolean torique, int nbBlocs) {
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		this.torique = torique;
		this.nbBlocs = nbBlocs;
		this.grille = new ElementEnvironnement[nbColonnes][nbLignes];
		genererBlocs();
	}
	
	public Environnement(int nbColonnes, int nbLignes) {
		this(nbColonnes,nbLignes,false,0);
	}
	public Environnement(int nbColonnes, int nbLignes, boolean torique) {
		this(nbColonnes,nbLignes,torique,0);
	}
	
	protected void genererBlocs() {
		int tab[] = null;
		for (int i=1;i<=nbBlocs;i++) {
			tab = getCaseLibreAleatoire();
			if (tab != null) {
				grille[tab[0]][tab[1]] = new Bloc(tab[0],tab[1]);
			}
		}
	}
	
	// Convertir torique => coordonnées tableau
	public int getX(int xTorique) {
		if (xTorique >= 0) {  return xTorique%nbColonnes; }
		else { return nbColonnes - Math.abs(xTorique); }
	}
	public int getY(int yTorique) {
		if (yTorique >= 0) { return yTorique%nbLignes; }
		else { return nbLignes - Math.abs(yTorique); }
	}
	
	public ElementEnvironnement getElementEnvironnement(int x, int y) {
		if (torique) {
			x = getX(x);
			y = getY(y);
		}
		if (existeCase(x,y)) { return grille[x][y]; }
		return null;
	}
	
	public int getNbColonnes() { return nbColonnes; }
	public int getNbLignes() { return nbLignes; }
	public ElementEnvironnement[][] getGrille() { return grille; }
	
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

	public boolean mettreElementEnvironnement(ElementEnvironnement element) {
		if (torique) {
			element.setPosX(getX(element.getPosX()));
			element.setPosY(getY(element.getPosY()));
		}
		if (!aUnObstacle(element.getPosX(), element.getPosY()) && existeCase(element.getPosX(),element.getPosY())) {	
			if (element instanceof Avatar) {
				if (this.avatar != null) {
					enleverElementEnvironnement(this.avatar);
				}
				this.avatar = (Avatar) element;
			}
			grille[element.getPosX()][element.getPosY()] = element;
			return true;
		}
		return false;
	}
	
	public boolean enleverElementEnvironnement(ElementEnvironnement element) {
		if (existeCase(element.getPosX(),element.getPosY()) && aUnObstacle(element.getPosX(), element.getPosY())) {
			grille[element.getPosX()][element.getPosY()] = null;
			if (element instanceof Avatar) {
				this.avatar = null;
			}
			return true;
		}
		return false;
	}
	/**
	 * Retourne les coordonnées d'une seule case libre si elle existe
	 * @param x coordonnée x du point dont on doit chercher autour
	 * @param y coordonnée y du point dont on doit chercher autour
	 * @return
	 */
	public int[] getCaseLibreAuxAlentours(int x,int y) {
		int xp,yp; // périphérie
		for (int i = -1; i<=1 ;i++) {
			for (int j = -1; j<=1;j++) {
				xp = x+i;
				yp = y+j;
				if (torique) {
					xp = getX(xp);
					yp = getY(yp);
				}
				if (!aUnObstacle(xp, yp)) {
					return new int[] {xp,yp};
				}
			}
		}
		return null;
	}
	public List<ElementEnvironnement> getElementsEnvironnementAuxAlentours(int x, int y) {
		LinkedList<ElementEnvironnement> elements = new LinkedList<ElementEnvironnement>();
		int xp,yp; // périphérie
		for (int i = -1; i<=1;i++) {
			for (int j =-1;j<=1;j++) {
				xp = x+i;
				yp = y+j;
				if (torique) {
					xp = getX(xp);
					yp = getY(yp);
				}
				if ((aUnObstacle(xp,yp)) && (existeCase(xp,yp)) && (xp!=x) && (yp != y)) {
					elements.add(grille[xp][yp]);
				}
			}
		}
		return elements;
	}
	/** 
	 * Retourne une case libre aléatoirement, null si non trouvée.
	 * Fait nbCases tentatives dans le pire des cas.
	 * @return les coordonnées d'une case libre aléatoirement, null si ne parvient pas à trouver.
	 */
	public int[] getCaseLibreAleatoire() {
		int xa=(int)(Math.random()*nbColonnes), ya=(int)(Math.random()*nbLignes);
		int nbTentatives = nbColonnes*nbLignes;
		while (aUnObstacle(xa, ya) && (nbTentatives > 0)) {
			xa = (int)(Math.random()*nbColonnes);
			ya = (int)(Math.random()*nbLignes);
			nbTentatives = nbTentatives -1;
		}
		if (aUnObstacle(xa, ya)) {
			return null;
		} else {
			return new int[] {xa,ya};
		}
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
}
