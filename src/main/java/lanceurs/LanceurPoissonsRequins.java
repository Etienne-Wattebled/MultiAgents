package lanceurs;

import modeles.environnements.elements.agents.Poisson;
import modeles.environnements.elements.agents.Requin;
import modeles.simulateurs.Simulateur;

public class LanceurPoissonsRequins {
	public static void main(String args[]) {
		Simulateur s = new Simulateur(200,100,6,50,true);
		for (int i=0; i < 100; i++ ){
			s.ajouterAgent(
					new Poisson(
							s, // simulateur
							20, // délais maturité
							8, // délais entre deux naissances
							0 // ralentissement
					)
			);
		}
		for (int i=0; i < 20; i++){
			s.ajouterAgent(
					new Requin(
							s, // simulateur
							50, // délais maturité
							15, // délais entre deux naissances
							30, // délais mort sans manger
							0 // ralentissement
					)
			);
		}
		s.lancerSimulation();
	}
}
