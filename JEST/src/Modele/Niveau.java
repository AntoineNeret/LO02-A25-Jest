package Modele;

public enum Niveau {
	UN(1, "Strategie Simple"),
	DEUX(2, "Strategie Avancee"),
	OUT(0, "Annuler la partie");
	
	private int val;
	private String title;
	
	private Niveau(int val, String title) {
		this.val = val;
		this.title = title;
	}
	
	public String toString() {
		String str = new String(val+". "+ title);
		return str;
	}
}
