package vues.environnements;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulateurs.Simulateur;

public class VueMenuEnvironnement extends JPanel {
	public VueMenuEnvironnement(Simulateur simulateur){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(new JTextField());
		this.add(new JButton("START"));
	}
}
