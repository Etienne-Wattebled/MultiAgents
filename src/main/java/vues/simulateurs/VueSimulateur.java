package vues.simulateurs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.simulateurs.Simulateur;
import vues.environnements.VueEnvironnement;
import vues.environnements.VueMenuEnvironnement;

public class VueSimulateur extends JFrame implements KeyListener{
	private VueEnvironnement vueEnvironnement;
	private VueMenuEnvironnement vueMenuEnvironnement;
	
	public String boite = "";
	
	public VueSimulateur(Simulateur simulateur, int tailleCellule) {
		this.vueEnvironnement = new VueEnvironnement(simulateur.getEnvironnement(),tailleCellule);
		this.vueMenuEnvironnement = new VueMenuEnvironnement(simulateur,tailleCellule);
		
		this.setLayout(new BorderLayout());
		
		simulateur.addObserver(vueEnvironnement);
		
		this.add(vueEnvironnement,BorderLayout.CENTER);
		//this.add(vueMenuEnvironnement,BorderLayout.SOUTH);
		
		this.setSize(
				vueEnvironnement.getLongueur()+vueMenuEnvironnement.getLongueur(),
				vueEnvironnement.getHauteur()+vueMenuEnvironnement.getHauteur()
		);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent k){
		int keyCode = k.getKeyCode();
		if(keyCode == KeyEvent.VK_NUMPAD4){
			this.boite = "gauche";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD6){
			this.boite = "droite";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD8){
			this.boite = "haut";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD2){
			this.boite = "bas";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD1){
			this.boite = "basGauche";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD3){
			this.boite = "basDroite";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD7){
			this.boite = "hautGauche";
		}
		else if(keyCode == KeyEvent.VK_NUMPAD9){
			this.boite = "hautDroite";
		}
		System.out.println("boite = " + this.boite);
	}
	
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
}
