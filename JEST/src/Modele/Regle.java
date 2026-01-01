package Modele;
import java.util.ArrayList;
import java.util.List;

public interface Regle {
	public void deroulementPartie(List<Joueur> tabJoueur, Paquet paquet);
	public void declareGagnant(List<Joueur> tabJoueur);
	public VisitorScore Visiteur();
	public void gagnantTrophee(List<Joueur> tabJoueur, Paquet trophy);
	public void recupPaquet(Pioche pioche);
	public void melanger(ArrayList<Joueur> tabJoueur,int tour,Pioche pioche, Paquet trophy,Pioche piocheTemp);
	public void distribution(ArrayList<Joueur> tabJoueur, int tour, Pioche pioche, Pioche piocheTemp);
	public void reinitAPrisOffre(ArrayList<Joueur> tabJoueur);
	public void gererOffres(ArrayList<Joueur> tabJoueur, Pioche pioche, Pioche piocheTemp);
}
