package Modele;

/**
 * The enum Niveau that represent every strategy available for bots.
 */
public enum Niveau {
    /**
     * Simple strategy
     */
    UN(1, "Strategie Simple"),
    /**
     * Advanced strategy
     */
    DEUX(2, "Strategie Avancee"),
    /**
     * Cancel the game
     */
    OUT(0, "Annuler la partie");
	
	private int val;
	private String title;

    /**
     * Instantiates a new Niveau.
     *
     * @param val
     * @param title
     */
	private Niveau(int val, String title) {
		this.val = val;
		this.title = title;
	}
	
	public String toString() {
		String str = new String(val+". "+ title);
		return str;
	}
}
