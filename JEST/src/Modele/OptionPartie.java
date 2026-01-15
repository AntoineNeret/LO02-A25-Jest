package Modele;

/**
 * The enum Option partie that represent the options available between every turns.
 */
public enum OptionPartie {

    /**
     * Continue
     */
    UN(1, "Continuer"),
    /**
     * Save the game
     */
    DEUX(2, "Sauvegarder la partie"),
    /**
     * Cancel the game
     */
    OUT(0, "Annuler la partie");
		
		private int val;
		private String title;

    /**
     * Instantiates a new Option Partie.
     *
     * @param val
     * @param title
     */
		private OptionPartie(int val, String title) {
			this.val = val;
			this.title = title;
		}
		
		public String toString() {
			String str = new String(val+". "+ title);
			return str;
		}
}
