package vues.environnements;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueEnvironnement extends JPanel implements Observer {
	private JPanel JPANEL_BILLE;
	private JPanel JPANEL_VIERGE;
	
	private static final long serialVersionUID = 1L;

	public VueEnvironnement(int longueur, int hauteur, int tailleCellule) {
		this.setSize(tailleCellule*longueur,tailleCellule*hauteur);
		this.setLayout(new GridLayout(longueur,hauteur));
		JPANEL_VIERGE = new JPanel();
		JPANEL_VIERGE.setSize(tailleCellule,tailleCellule);
		
		JPANEL_BILLE = new JPanel();
		JPANEL_BILLE.setBackground(Color.BLACK);
		JPANEL_BILLE.setSize(tailleCellule,tailleCellule);
		for (int i = 0;i<longueur*hauteur; i++) {
			this.add(JPANEL_VIERGE);
		}
		this.setVisible(true);
	}
	
	public void update(Observable o, Object arg) {
		
	}
}
