package vues.environnements;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulateurs.Simulateur;

public class VueSimulateur extends JFrame {
	private VueEnvironnement vueEnvironnement;
	private VueMenuEnvironnement vueMenuEnvironnement;
	
	public VueSimulateur(Simulateur simulateur, int tailleCellule) {
		this.vueEnvironnement = new VueEnvironnement(simulateur.getEnvironnement(),tailleCellule);
		this.vueMenuEnvironnement = new VueMenuEnvironnement(simulateur);
		
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		
		this.add(vueEnvironnement);
		this.add(vueMenuEnvironnement);
		this.setSize(new Dimension(tailleCellule*simulateur.getLongueur(), tailleCellule*simulateur.getHauteur()));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
