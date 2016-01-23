package vues.environnements;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import modeles.environnements.Environnement;

public class VueEnvironnement2 extends JPanel implements Observer {

	private Environnement environnement;
	
	private static final long serialVersionUID = 1L;

	public VueEnvironnement2(Environnement environnement, int tailleCellule) {
		JPanel bille;
		JPanel vierge;
		
		this.setLayout(new GridLayout(environnement.getNbLignes(),environnement.getNbColonnes()));
		this.setSize(tailleCellule*environnement.getNbColonnes(),tailleCellule*environnement.getNbLignes());
		
		this.environnement = environnement;
		
		int nb = environnement.getNbColonnes()*environnement.getNbLignes();
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
		int i = environnement.getNbColonnes();
		int j = environnement.getNbLignes();
		for (int y=0; y<j;y++) {
			for (int x =0; x<i;x++) {
				if (environnement.getAgent(x,y) != null) {
					bille = new JPanel();
					bille.setBackground(Color.BLUE);
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
