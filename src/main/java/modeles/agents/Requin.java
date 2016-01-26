package modeles.agents;

import java.util.List;
import java.util.ListIterator;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public class Requin extends EtreVivant {
	protected int nbToursAvantMortFaim;
	protected int nbToursAvant;
	
	protected int nbToursAvantMortFaimInitial;

	public Requin(Simulateur simulateur, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim){
		this(
				simulateur,
				(int)(simulateur.getEnvironnement().getNbColonnes()*Math.random()),
				(int)(simulateur.getEnvironnement().getNbLignes()*Math.random()),
				nbToursAvantMaturite,
				nbToursEntreDeuxNaissances,
				nbToursAvantMortFaim
		);
	}
	
	public Requin(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim){
		super(simulateur,posX, posY, nbToursAvantMaturite, nbToursEntreDeuxNaissances);
		this.nbToursAvantMortFaim = nbToursAvantMortFaim;
		this.nbToursAvantMortFaimInitial = nbToursAvantMortFaim;
	}
	
	public void doIt(){
		seDeplacer();
		manger();
		seReproduire();
		verifierVie();
		super.doIt();
	}
	public void manger() {
		List<Agent> agentsAuxAlentours = simulateur.getEnvironnement().getAgentsAuxAlentours(posX,posY);
		ListIterator<Agent> itAgentsAuxAlentours = agentsAuxAlentours.listIterator();
		boolean aMange = false;
		Agent a = null;
		
		while (itAgentsAuxAlentours.hasNext() && !aMange) {
			a = itAgentsAuxAlentours.next();
			if (a instanceof Poisson) {
				Poisson p = (Poisson) a;
				aMange = true;
				p.setEnVie(false);
				simulateur.supprimerAgent(p);
				this.nbToursAvantMortFaim = this.nbToursAvantMortFaim+ 3 + (int)(2*Math.random());
			}
		}
	}
	public void seReproduire() {
		int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
		if(peutSeReproduire() && (tab != null)){
			// Nouveau requin
			simulateur.ajouterAgent(new Requin(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial,nbToursAvantMortFaimInitial));
			resetNbToursEntreDeuxNaissances();
		}
	}
	public void verifierVie() {
		if(nbToursAvantMortFaim == 0) {
			enVie = false;
			simulateur.supprimerAgent(this);
		}
		this.nbToursAvantMortFaim = nbToursAvantMortFaim -1;
	}
}
