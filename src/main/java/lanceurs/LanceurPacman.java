package lanceurs;

import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.Chasseur;
import modeles.environnements.elements.agents.utilitaires.TableauDistancesAgent;
import modeles.simulateurs.Simulateur;

public class LanceurPacman {
	public static void main(String args[]) {
		Simulateur s = new Simulateur(50,50,6,100,true,20,false);
		Avatar a = new Avatar(s);
		s.ajouterAgent(a);
		TableauDistancesAgent tda = new TableauDistancesAgent(s.getEnvironnement(),a); 
		for (int i = 1; i<=5;i++) {
			s.ajouterAgent(new Chasseur(s,tda));
		}
		s.lancerSimulation();
	}
}
