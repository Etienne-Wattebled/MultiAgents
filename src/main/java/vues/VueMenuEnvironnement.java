package vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulateurs.Simulateur;

public class VueMenuEnvironnement extends JPanel {
	private Simulateur simulateur;
	private int tailleCellule;
	
	private int hauteur;
	private int longueur;
	
	public int getHauteur() {
		return hauteur;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public VueMenuEnvironnement(final Simulateur simulateur, int tailleCellule){
		this.tailleCellule = tailleCellule;
		this.simulateur = simulateur;
		
		this.longueur = (simulateur.getEnvironnement().getNbColonnes())*tailleCellule;
		this.hauteur = 100;
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		final JTextField jtf = new JTextField();
		jtf.setSize(15,5);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jtf.getText() != null) {
					simulateur.setPauseMS(Integer.parseInt(jtf.getText()));
				}
				
			}
		});
		JButton stop = new JButton("STOP");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulateur.arreterSimulation();
			}
		});
		this.add(jtf);
		this.add(ok);
		this.add(stop);
		
		this.setSize(simulateur.getNbColonnes()*tailleCellule,100);
	}
}
