package lanceurs;

import modeles.environnements.elements.agents.Avatar;
import modeles.simulateurs.Simulateur;

public class LanceurPacman {
	public static void main(String args[]) {
		Simulateur s = new Simulateur(200,100,6,50,false, 200);
		s.ajouterAgent(new Avatar(s));
		s.lancerSimulation();
	}
}
