import java.util.List;

public interface Regle {
	public void deroulementPartie(List<Joueur> tabJoueur, Paquet paquet);
	public void declareGagnant(List<Joueur> tabJoueur);
	public VisitorScore Visiteur();
	public void gagnantTrophee(List<Joueur> tabJoueur, Paquet trophy);
}
