
public enum RegleDispo {
	UN(1, "Règles standard"),
	DEUX(2, "Première variante de règles"),
    TROIS(3, "Deuxième variante de règles"),
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
