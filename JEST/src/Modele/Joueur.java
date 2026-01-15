package Modele;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Joueur that represent any player.
 */
public abstract class Joueur {

    /**
     * The name.
     */
    protected String nom;
    /**
     * The Score.
     */
    protected int score;
    /**
     * The Jest.
     */
//protected int scoreNojoke;
	public Paquet jest;
    /**
     * The player's hand.
     */
    protected Paquet main;
    /**
     * The Has took.
     */
    protected boolean hasTook;
    /**
     * The Offer.
     */
    public Paquet offre;

    /**
     * Method to choose an offer
     *
     * @param tabJoueur the tab joueur
     * @param nbrOffre  the nbr offre
     * @return the int
     */
    public abstract int choisirOffre(List<Joueur> tabJoueur, int nbrOffre);

    /**
     * Method to make an offer
     *
     * @return the paquet
     */
    public abstract Paquet faireOffre();

    /**
     * Gets offre.
     *
     * @return the offre
     */
    public Paquet getOffre() {
		return offre;
	}

    /**
     * Method to accept an offer
     *
     * @param compteur the compteur
     */
    public void accepter(VisitorScore compteur) {
		this.score = compteur.visiter(this);
	}

    /**
     * Gets hand.
     *
     * @return the
     */
    public Paquet getmain() {
		return this.main;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return this.nom;
	}

    /**
     * Instantiates a new Joueur.
     *
     * @param name the name
     */
    public Joueur(String name) {
		this.nom = name;
		this.hasTook = false;
		main = new Paquet();
		offre = new Paquet();
		jest = new Paquet();
	}

    /**
     * Sets has took.
     *
     * @param value the value
     */
    public void setHasTook(boolean value) {
		this.hasTook = value;
	}

    /**
     * Gets has took.
     *
     * @return the has took
     */
    public boolean getHasTook() {
		return hasTook;
	}
}
