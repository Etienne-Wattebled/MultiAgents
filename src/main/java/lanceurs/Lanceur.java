package lanceurs;

import java.util.regex.Pattern;

import modeles.environnements.elements.agents.Avatar;
import modeles.environnements.elements.agents.Bille;
import modeles.environnements.elements.agents.Chasseur;
import modeles.environnements.elements.agents.Poisson;
import modeles.environnements.elements.agents.Requin;
import modeles.environnements.elements.agents.utilitaires.TableauDistancesAgent;
import modeles.simulateurs.Simulateur;

public class Lanceur {
	public static boolean estUnNombre(String s) {
		return Pattern.matches("^\\d$",s);
	}
	public static void main(String args[]) {
		Avatar a = null;
		TableauDistancesAgent tda = null;
		try {
			int i = 0;
			// args[0] correspond à 'environnement' donc on ignore.
			Simulateur s = new Simulateur(
				Integer.parseInt(args[++i]),//nbColonnes
				Integer.parseInt(args[++i]),//nbLignes
				Integer.parseInt(args[++i]),//tailleCellule
				Integer.parseInt(args[++i]),//pauseMS
				"true".equals(args[++i]),
				Integer.parseInt(args[++i]),//nbBlocs
				false //menu
			);
			int j=0,nb;
			int e1,e2,e3,e4,e5;
			// 7 correspond à 'agents' donc on ignore
			i = i+2;
			while (i < args.length) {
				if ("Bille".equals(args[i])) {
						j = Integer.parseInt(args[++i]);
						e1 = Integer.parseInt(args[++i]);
						for (nb=1;nb<=j;nb++) {
							s.ajouterAgent(new Bille(s,e1));
						}
				}
				else if ("Requin".equals(args[i])) {
					j = Integer.parseInt(args[++i]);
					e1 = Integer.parseInt(args[++i]);
					e2 = Integer.parseInt(args[++i]);
					e3 = Integer.parseInt(args[++i]);
					e4 = Integer.parseInt(args[++i]);
					for (nb=1;nb<=j;nb++) {
						s.ajouterAgent(new Requin(s,e1,e2,e3,e4));
					}
				}
				else if ("Poisson".equals(args[i])) {
					j = Integer.parseInt(args[++i]);
					e1 = Integer.parseInt(args[++i]);
					e2 = Integer.parseInt(args[++i]);
					e3 = Integer.parseInt(args[++i]);
					for (nb=1;nb<=j;nb++) {
						s.ajouterAgent(new Poisson(s,e1,e2,e3));
					}
				}
				else if ("Avatar".equals(args[i])) {
					if (a != null) {
						System.out.println("Interdiction de mettre plus d'un avatar !");
					} else {
						a = new Avatar(s,Integer.parseInt(args[++i]));
						tda = new TableauDistancesAgent(s.getEnvironnement(),a);
						s.ajouterAgent(a);
					}
				}
				else if ("Chasseur".equals(args[i])) {
					j = Integer.parseInt(args[++i]);
					if (tda == null) {
						System.out.println("Interdiction de définir un chasseur avant un avatar");
					} else {
						e1 = Integer.parseInt(args[++i]);
						for (nb=1;nb<=j;nb++) {
							s.ajouterAgent(new Chasseur(s,tda,e1));
						}
					}
				}
				i = i+1;
			}
			s.lancerSimulation();
		} catch (Exception e) {
			System.out.println("Commande doit être sous la forme :");
			System.out.println("environnement <nbColonnes> <nbLignes> <tailleCellule> <pauseMS> <torique=true|false> <nbBlocs> agents <agent> <agent> <agent> ...");
			System.out.println("Avec <agent> qui peut être : ");
			System.out.println("Bille <nombre> <ralentissement>");
			System.out.println("Avatar <ralentissement>");
			System.out.println("Chasseur <nombre> <ralentissement>");
			System.out.println("Poisson <nombre> <nbToursAvantMaturite> <nbToursEntreDeuxNaissance> <ralentissement>");
			System.out.println("Requin <nombre> <nbToursAvantMaturite> <nbToursEntreDeuxNaissances> <nbToursAvantMortFaim> <ralentissement>");
		}
	}
}
