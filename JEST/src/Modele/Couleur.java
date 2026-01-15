package Modele;

/**
 * The enum Couleur that represent every colors available for cards.
 */
public enum Couleur {
    /**
     * Trefle.
     */
    TREFLE("♣"),
    /**
     * Carreau.
     */
    CARREAU("♦"),
    /**
     * Coeur.
     */
    COEUR("♥"),
    /**
     * Pique.
     */
    PIQUE("♠");
	
	private String title;
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(title);
		return str.toString();
	}

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
		return this.title;
	}
	
	private Couleur(String title) {
		this.title = title;
	}
	
	
}
