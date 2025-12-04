import java.util.ArrayList;
import java.util.List;

public abstract class Joueur {
	
	protected String nom;
	protected int score;
	protected int scoreNojoke;
	protected Paquet jest;
	protected Paquet main;
	protected boolean hasTook;
	protected Paquet offre;
	
	public abstract int choisirOffre(List<Joueur> tabJoueur, int nbrOffre);
	
	public abstract Paquet faireOffre();
	
	public void accepter(VisitorScore compteur) {
		this.score = compteur.visiter(this);
	}
	
	public Paquet getmain() {
		return this.main;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public Joueur(String name) {
		this.nom = name;
		this.hasTook = false;
		main = new Paquet();
		offre = new Paquet();
		jest = new Paquet();
	}
	
	public void setHasTook(boolean value) {
		this.hasTook = value;
	}
	 
	public boolean getHasTook() {
		return hasTook;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
