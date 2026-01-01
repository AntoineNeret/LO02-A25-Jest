package Modele;
import java.util.*;

public class StrategieAvancee implements Strategie {

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

    public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre, int monIndex){
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        Paquet jest = new Paquet();
        boolean saisieValide = false;
        int PlayerChoice = 0;
        int CardChoice = 0;
        //valeur random de choix de carte
        if(nbrOffre >= 3) {
            tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(PlayerChoice).offre.cartes.remove(CardChoice));
            return PlayerChoice;

        }else if(nbrOffre == 2) {
            //Recuperation des deux index possibles
            int index1 = -1;
            int index2 = -1;
            for(int i = 0; i < tabJoueur.size(); i++) {
                if(tabJoueur.get(i).offre.cartes.size() == 2 && index1 == -1) {
                    index1 = i;
                }else if (tabJoueur.get(i).offre.cartes.size() == 2 && index2 == -1) {
                    index2 = i;
                }
            }
            if(tabJoueur.size() == 4 && tabJoueur.get(PlayerChoice).getHasTook()) {
                if(tabJoueur.get(PlayerChoice).offre.cartes.size() == 2) {
                    tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(PlayerChoice).offre.cartes.remove(CardChoice));
                    if(PlayerChoice == index1) {
                        return index2;
                    }else {
                        return index1;
                    }
                }
                return PlayerChoice;
            }else if(tabJoueur.size() == 4 && !tabJoueur.get(PlayerChoice).getHasTook()) {
                tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(PlayerChoice).offre.cartes.remove(CardChoice));
                return PlayerChoice;
            }

            if(tabJoueur.size() == 3) {
                tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(PlayerChoice).offre.cartes.remove(CardChoice));

            }
            return PlayerChoice;
			/*System.out.println("Index 1 : "+ index1 + " Index 2 : " + index2);
			System.out.println("PlayerChoice : "+ PlayerChoice + " CardChoice : " + CardChoice + " player card : " + tabJoueur.get(PlayerChoice).offre.cartes);
			tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(PlayerChoice).offre.cartes.remove(CardChoice));
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
            tabJoueur.get(monIndex).jest.cartes.add(tabJoueur.get(index).offre.cartes.remove(CardChoice));
            return 0;
        }

    }

    public static int getRandom(int min, int max) {
        int range = (max-min)+1;
        int random = (int)(range*Math.random())+min;
        return random;
    }
}
