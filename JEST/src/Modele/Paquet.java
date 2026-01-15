package Modele;
import java.util.*;

/**
 * The type Paquet that represent a card array.
 */
public class Paquet {

    /**
     * The array which will contain cards
     */
    public List<Carte> cartes = new ArrayList<>();

    /**
     * Method to add a card to array
     *
     * @param c the card to add
     */
    public void ajouterCarte(Carte c) {
		this.cartes.add(c);
	}

    /**
     * Method to remove a card from array
     *
     * @param c the card to remove
     */
    public void retirerCarte(Carte c) {
		
	}

    /**
     * Get cartes list.
     *
     * @return the list
     */
    public List<Carte> getCartes(){
		return cartes;
	}

}
