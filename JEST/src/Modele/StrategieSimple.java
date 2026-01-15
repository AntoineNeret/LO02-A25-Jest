package Modele;
import java.util.*;

/**
 * The type Strategie simple that represent the most basic strategy.
 */
public class StrategieSimple implements Strategie {

    /**
     * Method to make an offer
     *
     * @param main the main
     * @return offer
     */
	public Paquet faireOffre(List<Carte> main){
		Paquet offreFaite = new Paquet();
		offreFaite.cartes.addAll(main);
		int max = offreFaite.cartes.get(0).getValue();
		int highValueIndex = 0; 
		for(int i = 0; i < main.size(); i++) {
			if(max < main.get(i).getValue()) {
				max = main.get(i).getValue();
				highValueIndex = i;
			}
		}
		offreFaite.cartes.get(highValueIndex).setVisibility(true);
		return offreFaite;
	}

    /**
     * Method to choose an offer
     *
     * @param tabJoueur the tab joueur
     * @param nbrOffre  the nbr offre
     * @param monIndex  the mon index
     * @return index of chosen offer
     */
	public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre, int monIndex){
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Paquet jest = new Paquet();
		boolean saisieValide = false;
		int rPlayerChoice;
		//valeur random de choix
		do{
			rPlayerChoice = getRandom(0,2);
		}while(rPlayerChoice == monIndex);
		int rCardChoice;
		//valeur random de choix de carte
		do{
			rCardChoice = getRandom(0,2);
		}while(rCardChoice == 2);
		if(nbrOffre >= 3) {
			if(nbrOffre >3) {
				do{
					rPlayerChoice = getRandom(0,3);
				}while(rPlayerChoice == monIndex);
			}
			tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(rPlayerChoice).offre.cartes.remove(rCardChoice));
			return rPlayerChoice;
			
		}else if(nbrOffre == 2) {
			//Recuperation des deux index possibles
			int index1 = -1;
			int index2 = -1;
			//int index3 = -1;
			for(int i = 0; i < tabJoueur.size(); i++) {
				if(tabJoueur.get(i).offre.cartes.size() == 2 && index1 == -1) {
					index1 = i;
				}else if (tabJoueur.get(i).offre.cartes.size() == 2 && index2 == -1) {
					index2 = i;
				}
			}
			do{
				if(tabJoueur.size() > 3) {
					do{
						rPlayerChoice = getRandom(index1,index2);
					}while(tabJoueur.get(rPlayerChoice).getHasTook());
				}else {
					rPlayerChoice = getRandom(0,2);
				}
			}while(rPlayerChoice == monIndex);
			do{
					rPlayerChoice = getRandom(index1,index2);
			}while(tabJoueur.get(rPlayerChoice).getHasTook());
			if(tabJoueur.size() == 4 && tabJoueur.get(rPlayerChoice).getHasTook()) {
				if(tabJoueur.get(rPlayerChoice).offre.cartes.size() == 2) {
					tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(rPlayerChoice).offre.cartes.remove(rCardChoice));
					if(rPlayerChoice == index1) {
						return index2;
					}else {
						return index1;
					}
				}
				return rPlayerChoice;
			}else if(tabJoueur.size() == 4 && !tabJoueur.get(rPlayerChoice).getHasTook()) {
				tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(rPlayerChoice).offre.cartes.remove(rCardChoice));
				return rPlayerChoice;
			}
			
			if(tabJoueur.size() == 3) {
				tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(rPlayerChoice).offre.cartes.remove(rCardChoice));
				
			}
			return rPlayerChoice;
			/*System.out.println("Index 1 : "+ index1 + " Index 2 : " + index2);
			System.out.println("rPlayerChoice : "+ rPlayerChoice + " rCardChoice : " + rCardChoice + " player card : " + tabJoueur.get(rPlayerChoice).offre.cartes);
			tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(rPlayerChoice).offre.cartes.remove(rCardChoice));
			if(tabJoueur.get(index1).getHasTook() && tabJoueur.get(index2).getHasTook()) {
				return index2;
			}else if(tabJoueur.get(index2).getHasTook() && tabJoueur.get(index3).getHasTook()){
				return index1;
			}else {
				return index3;
			}*/
			
			
		}else {
			//Recherche de la derniere offre
			int index = 0;
			for(int i = 0; i < tabJoueur.size() ; i++) {
				if(tabJoueur.get(i).offre.cartes.size()==2) {
					index = i;
				}
			}
			tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(index).offre.cartes.remove(rCardChoice));
			return 0;
		}
		
	}

    /**
     * Gets a random integer used for the strategy.
     *
     * @param min the min
     * @param max the max
     * @return the random
     */
    public static int getRandom(int min, int max) {
		int range = (max-min)+1;
		int random = (int)(range*Math.random())+min;
		return random;
	}
}
