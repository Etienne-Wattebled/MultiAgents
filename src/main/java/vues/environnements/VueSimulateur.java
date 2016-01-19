package vues.environnements;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulateurs.Simulateur;

public class VueSimulateur extends JFrame {
	private JPanel vueEnvironnement;
	
	public VueSimulateur(int longueur, int hauteur, int tailleCellule, Simulateur simulateur) {
		this.vueEnvironnement = new VueEnvironnement(longueur, hauteur, tailleCellule,simulateur.getEnvironnement());
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		
		this.add(vueEnvironnement);
		this.setSize(new Dimension(tailleCellule*longueur, tailleCellule*hauteur));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
