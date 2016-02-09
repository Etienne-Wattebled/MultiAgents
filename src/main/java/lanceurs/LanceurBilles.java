package lanceurs;

import modeles.environnements.elements.agents.Bille;
import modeles.simulateurs.Simulateur;

public class LanceurBilles {
	public static void main(String args[]) {
		Simulateur s = new Simulateur(200,100,6,70,false, 0,true);
		for (int i=0; i < 100; i++ ){
			s.ajouterAgent(
					new Bille(s)
			);
		}
		s.lancerSimulation();
	}
}
