package modeles.agents;

import modeles.environnements.Environnement;

public class Poisson extends EtreVivant{
	public Poisson(Environnement environnement, int posX, int posY, int tourAvantReproduction){
		super(environnement, posX, posY, tourAvantReproduction);
	}
	
	public void doIt(){
		int x = this.getPosX();
		int y = this.getPosY();
		seDeplacer();
		// Si le poisson est en âge de se reproduire et qu'il y a de la place à côté de lui
		if(tourAvantReproductionActuel <= 0 && (this.getPosX() != x || this.getPosY() != y)){
			//nouveau poisson
			Poisson petitPoisson = new Poisson(environnement, x, y, tourAvantReproductionInitial);
			tourAvantReproductionActuel = tourAvantReproductionInitial;
		}
		tourAvantReproductionActuel--;
	}
}
