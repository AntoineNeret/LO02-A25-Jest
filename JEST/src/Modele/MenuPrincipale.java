package Modele;

public enum MenuPrincipale {
	
	UN(1, "Commencer une partie"),
	DEUX(2, "Charger une partie"),
	TROIS(3, "Ajouter des cartes"),
	QUATRE(4, "Quitter le JEST");
	
	private int val;
	private String title;
	
	private MenuPrincipale(int val, String title) {
		this.val = val;
		this.title = title;
	}
	
	public String toString() {
		String str = new String(val+". "+ title);
		return str;
	}
}
