
public enum Valeur {
	QUATRE(4),
	TROIS(3),
	DEUX(2),
	AS(1);
	
	private int val;
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(val);
		return str.toString();
	}
	
	public int getValue() {
		return this.val;
	}
	
	private Valeur(int val) {
		this.val = val;
	}
}
