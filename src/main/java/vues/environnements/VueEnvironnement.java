package vues.environnements;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modeles.environnements.Environnement;

public class VueEnvironnement extends JPanel implements Observer {
	private JPanel JPANEL_BILLE;
	private JPanel JPANEL_VIERGE;
	private Environnement environnement;
	
	private static final long serialVersionUID = 1L;

	public VueEnvironnement(Environnement environnement, int tailleCellule) {
		this.setSize(tailleCellule*environnement.getLongueur(),tailleCellule*environnement.getHauteur());
		this.setLayout(new GridLayout(environnement.getLongueur(),environnement.getHauteur()));
		JPANEL_VIERGE = new JPanel();
		JPANEL_VIERGE.setSize(tailleCellule,tailleCellule);
		
		JPANEL_BILLE = new JPanel();
		JPANEL_BILLE.setBackground(Color.BLACK);
		JPANEL_BILLE.setSize(tailleCellule,tailleCellule);
		
		this.environnement = environnement;
		
		int nb = environnement.getLongueur()*environnement.getHauteur();
		for (int i = 0;i<nb; i++) {
			this.add(JPANEL_VIERGE);
		}
		this.setVisible(true);
	}
	
	public void update(Observable o, Object arg) {
		this.removeAll();
		int i = environnement.getLongueur();
		int j = environnement.getHauteur();
		for (int y=0; y<j;y++) {
			for (int x =0; x<i;x++) {
				if (environnement.getAgent(x,y) != null) {
					this.add(JPANEL_BILLE);
				} else {
					this.add(JPANEL_VIERGE);
				}
			}
		}
	}
}
