package Modele;
import java.util.*;

public interface Strategie {
	public Paquet faireOffre(List<Carte> main);
	public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre, int monIndex);
}
