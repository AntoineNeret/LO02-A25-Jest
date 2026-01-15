package Modele;
import java.util.*;

/**
 * The interface Strategie that represent any strategy.
 */
public interface Strategie {
    /**
     * Method to make an offer
     *
     * @param main the main
     * @return the paquet
     */
    public Paquet faireOffre(List<Carte> main);

    /**
     * Method to choose an offer
     *
     * @param tabJoueur the tab joueur
     * @param nbrOffre  the nbr offre
     * @param monIndex  the mon index
     * @return the int
     */
    public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre, int monIndex);
}
