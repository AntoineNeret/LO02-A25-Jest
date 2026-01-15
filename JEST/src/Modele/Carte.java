package Modele;

/**
 * The type Carte that represent a card.
 */
public class Carte {
	
	private Valeur valeur;
	private Couleur couleur;
	private boolean estJoker;
	private boolean estTrophee;
	private String conditionTrophee;
	private boolean estVisible;
	
	private String[] TrophyTitle = {"Highest ♠", "Highest ♣","Highest ♦","Highest ♥","Lowest ♠", "Lowest ♣","Lowest ♦","Lowest ♥", "Majority 3", "Majority 2", "Majority 4", "Joker", "Best Jest", "Best Jest No Joke"};

    /**
     * Compare cards.
     *
     * @param c the c
     * @return the boolean
     */
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

    /**
     * Instantiates a new Carte.
     *
     * @param v    the v
     * @param c    the c
     * @param type the type
     */
    public Carte(Valeur v, Couleur c, String type) {
		valeur = v;
		couleur = c;
		estJoker = false;
		estVisible = false;
		conditionTrophee = null;
		estTrophee = false;
		setCondition(type);
			
	}

    /**
     * Instantiates a new Carte.
     */
    public Carte() {
		valeur = null;
		couleur = null;
		estJoker = true;
		estVisible = false;
		conditionTrophee = "Best Jest";
		estTrophee = false;
	}

    /**
     * Sets condition for the card.
     *
     * @param type the type
     */
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

    /**
     * Sets visibility.
     *
     * @param value the value
     */
    public void setVisibility(boolean value) {
		this.estVisible = value;
	}

    /**
     * Gets visibility.
     *
     * @return the visibility
     */
    public boolean getVisibility() {
		return this.estVisible;
	}

    /**
     * Gets condition.
     *
     * @return the condition
     */
    public String getCondition() {
		return this.conditionTrophee;
	}

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
		if (valeur == null) {
			return 0;
		}
		return valeur.getValue();
	}

    /**
     * Gets color.
     *
     * @return the color
     */
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
