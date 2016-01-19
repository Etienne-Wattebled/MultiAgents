package vues.environnements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulateurs.Simulateur;

public class VueMenuEnvironnement extends JPanel {
	private Simulateur simulateur;
	
	public VueMenuEnvironnement(final Simulateur simulateur){
		super();
		this.simulateur = simulateur;
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		final JTextField jtf = new JTextField();
		this.add(jtf);
		JButton ok = new JButton("OK");
		this.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jtf.getText() != null) {
					simulateur.setPauseMS(Integer.parseInt(jtf.getText()));
				}
				
			}
		});
		JButton jb = new JButton("STOP");
		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulateur.arreterSimulation();
			}
		});
		this.setVisible(true);
	}
}
