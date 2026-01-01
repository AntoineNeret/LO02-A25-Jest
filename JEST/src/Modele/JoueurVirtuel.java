package Modele;
import java.util.List;

public class JoueurVirtuel extends Joueur{

	private Strategie strategie;
	
	public Paquet getOffre() {
		return offre;
	}
	
	public JoueurVirtuel(String nom,String strategieName){
		super(nom);
		switch(strategieName) {
			case "simple":
				strategie = new StrategieSimple();
				break;
			case "avancee":
				strategie = new StrategieAvancee();
				break;
		}
		
	}
	
	public Paquet faireOffre() {
		return strategie.faireOffre(super.main.cartes);
	}
	
	public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre) {
		// TODO Auto-generated method stub
		int index = 0 ;
		for(int i = 0; i < tabJoueur.size(); i++) {
			if(tabJoueur.get(i).offre.cartes.equals(super.offre.cartes)){
				index = i;
			}
		}
		super.setHasTook(true);
		return strategie.choisirOffre(tabJoueur, nbrOffre, index);
	}
	
	public void vider() {
		super.offre.cartes.clear();
		super.main.cartes.clear();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
