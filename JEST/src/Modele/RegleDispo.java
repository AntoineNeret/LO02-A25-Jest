package Modele;

/**
 * The enum Regle dispo that represent every rules available.
 */
public enum RegleDispo {
    /**
     * Standard rules
     */
    UN(1, "Règles standard"),
    /**
     * First rules variation
     */
    DEUX(2, "Première variante de règles"),
    /**
     * Second rules variation
     */
    TROIS(3, "Deuxième variante de règles"),
    /**
     * Cancel the game
     */
    OUT(0, "Annuler la partie");
	
	private int val;
	private String title;
	
	private RegleDispo(int val, String title) {
		this.val = val;
		this.title = title;
	}
	
	public String toString() {
		String str = new String(val+". "+ title);
		return str;
	}
}
