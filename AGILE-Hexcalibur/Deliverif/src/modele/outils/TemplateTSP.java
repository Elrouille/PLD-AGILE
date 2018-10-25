package modele.outils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class TemplateTSP implements TSP {
	
	private Integer[] meilleureSolution;
	private int coutMeilleureSolution = 0;
	private Boolean tempsLimiteAtteint;
        
        
	
	public Boolean getTempsLimiteAtteint(){
            return tempsLimiteAtteint;
	}
	
	public void chercheSolution(int tpsLimite, int nbSommets, int nbLivreur, int[][] cout){
		tempsLimiteAtteint = false;
		coutMeilleureSolution = Integer.MAX_VALUE;
		meilleureSolution = new Integer[nbSommets+nbLivreur-1];
		ArrayList<Integer> nonVus = new ArrayList<Integer>();
		for (int i=1; i<nbSommets; i++) nonVus.add(i);
		ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
		vus.add(0); // le premier sommet visite est 0
		branchAndBound(0, nonVus, vus, 0, cout, System.currentTimeMillis(), tpsLimite);
	}
	
	public Integer getMeilleureSolution(int i){
		if ((meilleureSolution == null) || (i<0) || (i>=meilleureSolution.length))
			return null;
		return meilleureSolution[i];
	}
	
	public int getCoutMeilleureSolution(){
		return coutMeilleureSolution;
	}
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param sommetCourant
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @return une borne inferieure du cout des permutations commencant par sommetCourant, 
	 * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
	 */
	protected abstract int bound(ArrayList<Integer> vus, Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout);
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCrt
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout);
	
	/**
	 * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param coutVus la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param tpsDebut : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */	
        void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, int coutVus, int[][] cout, long tpsDebut, int tpsLimite){
            if (System.currentTimeMillis() - tpsDebut > tpsLimite){
                    tempsLimiteAtteint = true;
                    return;
            }
	    if (nonVus.size() == 0){ // tous les sommets ont ete visites
	    	coutVus += cout[sommetCrt][0];
	    	if (coutVus < coutMeilleureSolution){ // on a trouve une solution meilleure que meilleureSolution
                    vus.toArray(meilleureSolution);
                    coutMeilleureSolution = coutVus;
                }
	    } else if (coutVus + bound(vus, sommetCrt, nonVus, cout) < coutMeilleureSolution){
                Iterator<Integer> it = iterator(sommetCrt, nonVus, cout);
                while (it.hasNext()){
                    Integer prochainSommet = it.next();
                    vus.add(prochainSommet);
                    nonVus.remove(prochainSommet);
                    branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet], cout, tpsDebut, tpsLimite);
                    if (prochainSommet!=0){
                        vus.remove(prochainSommet);
                        nonVus.add(prochainSommet);
                    } else {
                        //distinction obligee en raison de la methode remove des
                        //arraylist. On detecte donc les entrepots virtuels.
                        vus.remove(vus.size()-1);
                    }
                }
	    }
	}
         
}
