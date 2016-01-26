package modeles.agents;

import modeles.environnements.Environnement;

public abstract class EtreVivant extends Agent{
	protected int tourAvantReproductionInitial;
	protected int tourAvantReproductionActuel;
	
	public EtreVivant(Environnement environnement, int posX, int posY, int tourAvantReproduction){
		super(environnement,posX,posY);
		this.tourAvantReproductionInitial = tourAvantReproduction;
		this.tourAvantReproductionActuel = tourAvantReproduction;
	}
}
