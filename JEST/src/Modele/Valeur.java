package Modele;

/**
 * The enum Valeur that represent the available values for cards.
 */
public enum Valeur {
    /**
     * Quatre value.
     */
    QUATRE(4),
    /**
     * Trois valeur.
     */
    TROIS(3),
    /**
     * Deux valeur.
     */
    DEUX(2),
    /**
     * As valeur.
     */
    AS(1);
	
	private int val;
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(val);
		return str.toString();
	}

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
		return this.val;
	}
	
	private Valeur(int val) {
		this.val = val;
	}
}
