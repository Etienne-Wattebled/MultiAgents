package vues.simulateurs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.simulateurs.Simulateur;
import vues.environnements.VueEnvironnement;
import vues.environnements.VueMenuEnvironnement;

public class VueSimulateur extends JFrame {
	private VueEnvironnement vueEnvironnement;
	private VueMenuEnvironnement vueMenuEnvironnement;
	
	public VueSimulateur(Simulateur simulateur, int tailleCellule) {
		this.vueEnvironnement = new VueEnvironnement(simulateur.getEnvironnement(),tailleCellule);
		this.vueMenuEnvironnement = new VueMenuEnvironnement(simulateur,tailleCellule);
		
		this.setLayout(new BorderLayout());
		
		simulateur.addObserver(vueEnvironnement);
		
		this.add(vueEnvironnement,BorderLayout.CENTER);
		this.add(vueMenuEnvironnement,BorderLayout.SOUTH);
		
		this.setSize(
				vueEnvironnement.getLongueur()+vueMenuEnvironnement.getLongueur(),
				vueEnvironnement.getHauteur()+vueMenuEnvironnement.getHauteur()
		);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
