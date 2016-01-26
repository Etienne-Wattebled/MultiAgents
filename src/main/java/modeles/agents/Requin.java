package modeles.agents;

import java.util.List;
import java.util.ListIterator;

import simulateurs.Simulateur;
import modeles.environnements.Environnement;

public class Requin extends EtreVivant {
	protected int nbToursAvantMortFaim;
	protected int nbToursAvant;
	
	protected int nbToursAvantMortFaimInitial;

	public Requin(Simulateur simulateur,int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim) {
		super(simulateur, nbToursAvantMaturite,nbToursEntreDeuxNaissances);
		this.nbToursAvantMortFaim = nbToursAvantMortFaim;
	}
	public Requin(Simulateur simulateur, int posX, int posY, int nbToursAvantMaturite, int nbToursEntreDeuxNaissances, int nbToursAvantMortFaim){
		super(simulateur, posX, posY,nbToursAvantMaturite, nbToursAvantMaturite);
		this.nbToursAvantMortFaim = nbToursAvantMortFaim;
		this.nbToursAvantMortFaimInitial = nbToursAvantMortFaim;
	}
	
	public void doIt(){
		super.doIt();
		seDeplacer();	
		// On essaie de manger un poisson
		List<Agent> agentsAuxAlentours = simulateur.getEnvironnement().getAgentsAuxAlentours(posX,posY);
		ListIterator<Agent> itAgentsAuxAlentours = agentsAuxAlentours.listIterator();
		boolean aMange = false;
		Agent a = null;
		
		int nbToursAvantMortFaimMin =3;
		int nbToursAvantMortFaimMax =10;
		while (itAgentsAuxAlentours.hasNext() && !aMange) {
			a = itAgentsAuxAlentours.next();
			if (a instanceof Poisson) {
				Poisson p = (Poisson) a;
				aMange = true;
				p.setEnVie(false);
				this.nbToursAvantMortFaim = this.nbToursAvantMortFaim+ nbToursAvantMortFaimMin + (int)((nbToursAvantMortFaimMax- nbToursAvantMortFaimMin)*Math.random());
			}
		}
		
		// Si le requin est en âge de se reproduire et qu'il y a de la place à côté de lui
		int tab[] = simulateur.getEnvironnement().getCaseLibreAuxAlentours(posX,posY);
		if(estMature() && (nbToursEntreDeuxNaissances == 0) && (tab != null)){
			// Nouveau requin
			simulateur.ajouterAgent(new Requin(simulateur, tab[0], tab[1],nbToursAvantMaturiteInitial,nbToursEntreDeuxNaissancesInitial,nbToursAvantMortFaimInitial));
		}
		
		this.nbToursAvantMortFaim = nbToursAvantMortFaim -1;
		// On vérifie la vie du requin
		if(nbToursAvantMortFaim == 0) {
			enVie = false;
			simulateur.supprimerAgent(this);
		}
	}
}
