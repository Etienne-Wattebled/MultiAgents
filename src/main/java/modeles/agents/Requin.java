package modeles.agents;

import modeles.environnements.Environnement;

public class Requin extends EtreVivant{
	private int tourAvantMortActuel;
	private int tourAvantMortInitial;
	private int nombreTourApresManger;
	
	public Requin(Environnement environnement, int posX, int posY, int tourAvantReproduction, int tourAvantMort, int nombreTourApresManger){
		super(environnement, posX, posY, tourAvantReproduction);
		this.tourAvantMortInitial = tourAvantMort;
		this.tourAvantMortActuel = tourAvantMort;
		this.nombreTourApresManger = nombreTourApresManger;
	}
	
	public void doIt(){
		int x = this.getPosX();
		int y = this.getPosY();
		
		// On v�rifie la vie du requin
		if(tourAvantMortActuel == 0){
			// On supprime le requin
		}
		
		// On essaie de manger un poisson
		
		// Si le requin est en �ge de se reproduire et qu'il y a de la place � c�t� de lui
		if(tourAvantReproductionActuel <= 0 && (this.getPosX() != x || this.getPosY() != y)){
			//nouveau requin
			Requin petitRequin = new Requin(environnement, x, y, tourAvantReproductionInitial);
			tourAvantReproductionActuel = tourAvantReproductionInitial;
		}
		
		tourAvantReproductionActuel--;
		tourAvantMortActuel--;
	}
}
