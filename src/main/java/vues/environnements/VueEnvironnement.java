package vues.environnements;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import modeles.environnements.Environnement;

public class VueEnvironnement extends JPanel implements Observer {

	private Environnement environnement;
	
	private static final long serialVersionUID = 1L;

	public VueEnvironnement(Environnement environnement, int tailleCellule) {
		JPanel bille;
		JPanel vierge;
		
		this.setLayout(new GridLayout(environnement.getHauteur(),environnement.getLongueur()));
		this.setSize(tailleCellule*environnement.getLongueur(),tailleCellule*environnement.getHauteur());
		
		this.environnement = environnement;
		
		int nb = environnement.getLongueur()*environnement.getHauteur();
		for (int i = 0;i<nb; i++) {
			vierge = new JPanel();
			vierge.setSize(tailleCellule,tailleCellule);
			this.add(vierge);
		}
		this.setVisible(true);
	}

	public void update(Observable obs, Object obj) {
		JPanel bille;
		JPanel vierge;
		this.removeAll();
		int i = environnement.getLongueur();
		int j = environnement.getHauteur();
		for (int y=0; y<j;y++) {
			for (int x =0; x<i;x++) {
				if (environnement.getAgent(x,y) != null) {
					bille = new JPanel();
					bille.setBackground(Color.BLACK);
					this.add(bille);
				} else {
					vierge = new JPanel();
					this.add(vierge);
				}
			}
		}
		revalidate();
		repaint();
	}
}
