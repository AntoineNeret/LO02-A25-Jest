
public enum Couleur {
	TREFLE("♣"), CARREAU("♦"), COEUR("♥"), PIQUE("♠");
	
	private String title;
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(title);
		return str.toString();
	}
	
	public String getColor() {
		return this.title;
	}
	
	private Couleur(String title) {
		this.title = title;
	}
	
	
}
