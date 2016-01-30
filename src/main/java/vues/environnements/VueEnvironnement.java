package vues.environnements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import modeles.environnements.Environnement;
import modeles.environnements.elements.ElementEnvironnement;
import modeles.environnements.elements.agents.Agent;
import modeles.environnements.elements.agents.Bille;
import modeles.environnements.elements.agents.Poisson;
import modeles.environnements.elements.agents.Requin;
import modeles.environnements.elements.blocs.Bloc;

public class VueEnvironnement extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Environnement environnement;
	private int tailleCellule;
	
	private int hauteur;
	private int longueur;
	
	public int getHauteur() {
		return hauteur;
	}
	public int getLongueur() {
		return longueur;
	}
	public VueEnvironnement(Environnement environnement, int tailleCellule) {
		this.environnement = environnement;
		this.tailleCellule = tailleCellule;
		this.longueur = environnement.getNbColonnes()*tailleCellule;
		this.hauteur = environnement.getNbLignes()*tailleCellule;
		this.setSize(longueur,hauteur);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		ElementEnvironnement grille[][] = environnement.getGrille();
		int xDeb = (int)(tailleCellule/2);
		int yDeb = (int)(tailleCellule/2);
		int x = 0;
		int y = 0;
		int longueurMax =grille.length*tailleCellule;
		int hauteurMax=grille[0].length*tailleCellule;
		for (int j = yDeb; j<hauteurMax;j=j+tailleCellule) {
			x = 0;
			for (int i = xDeb; i<longueurMax;i=i+tailleCellule) {
				if (grille[x][y] != null) {
					if (grille[x][y] instanceof Bille) {
						g.setColor(Color.BLACK);
						g.fillOval(i, j, tailleCellule,tailleCellule);
					}
					else if (grille[x][y] instanceof Requin) {
						g.setColor(Color.GRAY);
						g.fillOval(i, j, tailleCellule, tailleCellule);
					}
					else if (grille[x][y] instanceof Poisson) {
						g.setColor(Color.RED);
						g.fillOval(i, j, tailleCellule, tailleCellule);
					}
					else if (grille[x][y] instanceof Bloc) {
						g.setColor(Color.orange);
						g.fillOval(i, j, tailleCellule, tailleCellule);
					}
				}
				x = x+1;
			}
			y = y+1;
		}
	}

	public void update(Observable o, Object arg) {
		this.repaint();
	}
}
