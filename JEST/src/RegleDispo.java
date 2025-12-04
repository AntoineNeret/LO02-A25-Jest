
public enum RegleDispo {
	UN(1, "Regle standard"),
	DEUX(2, "Regle ameliore"),
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
