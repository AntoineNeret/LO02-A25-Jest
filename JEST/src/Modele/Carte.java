package Modele;

public class Carte {
	
	private Valeur valeur;
	private Couleur couleur;
	private boolean estJoker;
	private boolean estTrophee;
	private String conditionTrophee;
	private boolean estVisible;
	
	private String[] TrophyTitle = {"Highest ♠", "Highest ♣","Highest ♦","Highest ♥","Lowest ♠", "Lowest ♣","Lowest ♦","Lowest ♥", "Majority 3", "Majority 2", "Majority 4", "Joker", "Best Jest", "Best Jest No Joke"};
	
	public boolean compareCarte(Carte c) {
		if(c.getValue() > this.getValue()) {
			return false;
		}else if(c.getValue() < this.getValue()) {
			return true;
		}else {
			if((this.getColor() == "♠")|| (this.getColor() == "♣" && (c.getColor() == "♥" || c.getColor() == "♦")) || (this.getColor()=="♦" && c.getColor() == "♥")) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public Carte(Valeur v, Couleur c, String type) {
		valeur = v;
		couleur = c;
		estJoker = false;
		estVisible = false;
		conditionTrophee = null;
		estTrophee = false;
		setCondition(type);
			
	}
	
	public Carte() {
		valeur = null;
		couleur = null;
		estJoker = true;
		estVisible = false;
		conditionTrophee = "Best Jest";
		estTrophee = false;
	}
	
	public void setCondition(String type) {
		if(type == "simple") {
			switch(valeur.getValue()) {
			case 1:
				if(couleur.getColor() == "♠"){
					conditionTrophee = "Highest ♣";
				}
				if(couleur.getColor() == "♣"){
					conditionTrophee = "Highest ♠";
				}
				if(couleur.getColor() == "♦"){
					conditionTrophee = "Majority 4";
				}
				if(couleur.getColor() == "♥"){
					conditionTrophee = "Joker";
				}
				break;
			case 2:
				if(couleur.getColor() == "♠"){
					conditionTrophee = "Majority 3";
				}
				if(couleur.getColor() == "♣"){
					conditionTrophee = "Lowest ♥";
				}
				if(couleur.getColor() == "♦"){
					conditionTrophee = "Highest ♦";
				}
				if(couleur.getColor() == "♥"){
					conditionTrophee = "Joker";
				}
				break;
			case 3:
				if(couleur.getColor() == "♠"){
					conditionTrophee = "Majority 2";
				}
				if(couleur.getColor() == "♣"){
					conditionTrophee = "Highest ♥";
				}
				if(couleur.getColor() == "♦"){
					conditionTrophee = "Lowest ♦";
				}
				if(couleur.getColor() == "♥"){
					conditionTrophee = "Joker";
				}
				break;
			case 4:
				if(couleur.getColor() == "♠"){
					conditionTrophee = "Lowest ♣";
				}
				if(couleur.getColor() == "♣"){
					conditionTrophee = "Lowest ♠";
				}
				if(couleur.getColor() == "♦"){
					conditionTrophee = "Best Jest No Joke"; 
				}
				if(couleur.getColor() == "♥"){
					conditionTrophee = "Joker";
				}
				break;
		}
		}
	}
	
	public void setVisibility(boolean value) {
		this.estVisible = value;
	}
	
	public boolean getVisibility() {
		return this.estVisible;
	}
	
	public String getCondition() {
		return this.conditionTrophee;
	}
	
	public int getValue() {
		if (valeur == null) {
			return 0;
		}
		return valeur.getValue();
	}
	
	public String getColor() {
		if(valeur == null) {
			return "Joker";
		}
		return couleur.getColor();
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		if(this.estVisible == false) {
			str.append("***");
		}else if(this.estVisible == true && this.estJoker == true)  {
			str.append("Joker");
		}else {
			if(this.getValue() == 1){
				str.append("AS");
			}else {
				str.append(valeur);
			}
			str.append(" ");
			str.append(couleur);
		}
		
	
		
		return str.toString();
	}
}
