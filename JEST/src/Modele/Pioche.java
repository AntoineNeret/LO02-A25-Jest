package Modele;
import java.util.*;

/**
 * The type Pioche that represent the main cards pack.
 */
public class Pioche extends Paquet{

    /**
     * Instantiates a new Pioche by putting in every possible combination of colors and values.
     *
     * @param rule the rule
     */
    public Pioche(String rule) {
		switch (rule) {
			case "simple", "avancee":
				for(Couleur c : Couleur.values()) {
					for(Valeur v : Valeur.values()) {
						cartes.add(new Carte(v,c, rule));
					}
				}
				cartes.add(new Carte());
				break;
            case "temporaire":
				break;
		}
		
	}

    /**
     * Method to distribute cards
     *
     * @param tabJoueur  the tab joueur
     * @param nbrDistrib the nbr distrib
     */
    public void distribuer(List<Joueur> tabJoueur, int nbrDistrib){
		int tour = 0;
		int nombreJoueur = tabJoueur.size();
		for(tour = 0; tour < nbrDistrib; tour++ ) {
			
			for (int i = 0; i < nombreJoueur; i++) {
				if(super.cartes.isEmpty()) {
					System.out.println("La pioche est vide");
					return;
				}
				Paquet mainJoueur = tabJoueur.get(i).getmain();
				mainJoueur.cartes.add(super.cartes.removeFirst());
			}
		}
		
		
	}

    /**
     * Method to add remaining offer cards to player's hand
     *
     * @param tabJoueur the tab joueur
     */
    public void recuperer(List<Joueur> tabJoueur) {
		for(Joueur j : tabJoueur) {
			if(!j.offre.cartes.isEmpty()) {
				this.cartes.addAll(j.offre.cartes);
				if(j instanceof JoueurVirtuel) {
					((JoueurVirtuel) j).vider();
				}
			}
		}
		
	}

}
