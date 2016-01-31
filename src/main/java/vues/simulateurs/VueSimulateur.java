package vues.simulateurs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.environnements.elements.agents.utilitaires.Direction;
import modeles.simulateurs.Simulateur;
import vues.environnements.VueEnvironnement;
import vues.environnements.VueMenuEnvironnement;

public class VueSimulateur extends JFrame implements KeyListener{
	private VueEnvironnement vueEnvironnement;
	private VueMenuEnvironnement vueMenuEnvironnement;
	
	public Direction directionClavier;
	
	public VueSimulateur(Simulateur simulateur, int tailleCellule, boolean menu) {
		this.vueEnvironnement = new VueEnvironnement(simulateur.getEnvironnement(),tailleCellule);
		
		this.setLayout(new BorderLayout());
		
		simulateur.addObserver(vueEnvironnement);
		this.add(vueEnvironnement,BorderLayout.CENTER);
		
		if (menu) {
			this.vueMenuEnvironnement = new VueMenuEnvironnement(simulateur,tailleCellule);
			this.add(vueMenuEnvironnement,BorderLayout.SOUTH);
			this.setSize(
					vueEnvironnement.getLongueur()+vueMenuEnvironnement.getLongueur(),
					vueEnvironnement.getHauteur()+vueMenuEnvironnement.getHauteur()
			);
		} else {
			this.setSize(vueEnvironnement.getLongueur(),vueEnvironnement.getHauteur());
		}
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.directionClavier = Direction.getRandomDirection();
		
		this.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent k){
		int keyCode = k.getKeyCode();
		if(keyCode == KeyEvent.VK_NUMPAD4){
			this.directionClavier = Direction.OUEST;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD6){
			this.directionClavier = Direction.EST;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD8){
			this.directionClavier = Direction.NORD;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD2){
			this.directionClavier = Direction.SUD;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD1){
			this.directionClavier = Direction.SUD_OUEST;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD3){
			this.directionClavier = Direction.SUD_EST;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD7){
			this.directionClavier = Direction.NORD_OUEST;
		}
		else if(keyCode == KeyEvent.VK_NUMPAD9){
			this.directionClavier = Direction.NORD_EST;
		}
	}
	
	public void keyReleased(KeyEvent k) {		
	}
	
	public void keyTyped(KeyEvent k) {		
	}
	public Direction getDirectionClavier() {
		return directionClavier;
	}
}
