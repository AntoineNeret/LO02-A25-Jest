package Modele;

/**
 * The enum Menu principale that represent every option in the menu.
 */
public enum MenuPrincipale {

    /**
     * Start the game
     */
    UN(1, "Commencer une partie"),
    /**
     * Load a game
     */
    DEUX(2, "Charger une partie"),
    /**
     * Add cards
     */
    TROIS(3, "Ajouter des cartes"),
    /**
     * Quit the game
     */
    QUATRE(4, "Quitter le JEST");
	
	private int val;
	private String title;


    /**
     * Instantiates a new Menu principale.
     *
     * @param val
     * @param title
     */
	private MenuPrincipale(int val, String title) {
		this.val = val;
		this.title = title;
	}
	
	public String toString() {
		String str = new String(val+". "+ title);
		return str;
	}
}
