package lanceurs;

import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.Chasseur;
import modeles.environnements.elements.agents.utilitaires.TableauDistancesAgent;
import modeles.simulateurs.Simulateur;

public class LanceurPacman {
	public static void main(String args[]) {
		Simulateur s = new Simulateur(100,100,6,70,true,2,false);
		Avatar a = new Avatar(s);
		s.ajouterAgent(a);
		TableauDistancesAgent tda = new TableauDistancesAgent(s.getEnvironnement(),a); 
		s.ajouterAgent(new Chasseur(s,tda));
		s.ajouterAgent(new Chasseur(s,tda));
		s.ajouterAgent(new Chasseur(s,tda));
		s.lancerSimulation();
	}
}
