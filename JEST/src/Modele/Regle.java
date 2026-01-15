package Modele;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface Regle that represent a rule.
 */
public interface Regle {
    /**
     * Method to determine who play
     *
     * @param tabJoueur the tab joueur
     * @param paquet    the paquet
     */
    public void deroulementPartie(List<Joueur> tabJoueur, Paquet paquet);

    /**
     * Method to declare a winner
     *
     * @param tabJoueur the tab joueur
     */
    public void declareGagnant(List<Joueur> tabJoueur);

    /**
     * Method to count player score
     *
     * @return the visitor score
     */
    public VisitorScore Visiteur();

    /**
     * Method to determine which player wins a trophy
     *
     * @param tabJoueur the tab joueur
     * @param trophy    the trophy
     */
    public void gagnantTrophee(List<Joueur> tabJoueur, Paquet trophy);

    /**
     * Method to get the pioche
     *
     * @param pioche the pioche
     */
    public void recupPaquet(Pioche pioche);

    /**
     * Method to shuffle
     *
     * @param tabJoueur  the tab joueur
     * @param tour       the tour
     * @param pioche     the pioche
     * @param trophy     the trophy
     * @param piocheTemp the pioche temp
     */
    public void melanger(ArrayList<Joueur> tabJoueur,int tour,Pioche pioche, Paquet trophy,Pioche piocheTemp);

    /**
     * Method to distribute cards
     *
     * @param tabJoueur  the tab joueur
     * @param tour       the tour
     * @param pioche     the pioche
     * @param piocheTemp the pioche temp
     */
    public void distribution(ArrayList<Joueur> tabJoueur, int tour, Pioche pioche, Pioche piocheTemp);

    /**
     * Method to re-initialize who took an offer
     *
     * @param tabJoueur the tab joueur
     */
    public void reinitAPrisOffre(ArrayList<Joueur> tabJoueur);

    /**
     * Methode to manage offers
     *
     * @param tabJoueur  the tab joueur
     * @param pioche     the pioche
     * @param piocheTemp the pioche temp
     */
    public void gererOffres(ArrayList<Joueur> tabJoueur, Pioche pioche, Pioche piocheTemp);
}
