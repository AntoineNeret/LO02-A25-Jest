import java.util.*;
public class Pioche extends Paquet{
	
	public Pioche(String rule) {
		switch (rule) {
			case "simple", "avancee":
				for(Couleur c : Couleur.values()) {
					for(Valeur v : Valeur.values()) {
						cartes.add(new Carte(v,c, rule));
					}
				}
				cartes.add(new Carte());
				break;
            case "temporaire":
				break;
		}
		
	}
	
	public void distribuer(List<Joueur> tabJoueur, int nbrDistrib){
		int tour = 0;
		int nombreJoueur = tabJoueur.size();
		for(tour = 0; tour < nbrDistrib; tour++ ) {
			
			for (int i = 0; i < nombreJoueur; i++) {
				if(super.cartes.isEmpty()) {
					System.out.println("La pioche est vide");
					return;
				}
				Paquet mainJoueur = tabJoueur.get(i).getmain();
				mainJoueur.cartes.add(super.cartes.removeFirst());
			}
		}
		
		
	}
	
	public void recuperer(List<Joueur> tabJoueur) {
		for(Joueur j : tabJoueur) {
			if(!j.offre.cartes.isEmpty()) {
				this.cartes.addAll(j.offre.cartes);
				if(j instanceof JoueurVirtuel) {
					((JoueurVirtuel) j).vider();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
