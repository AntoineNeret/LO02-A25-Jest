package Modele;
import java.util.List;

/**
 * The type Joueur virtuel that represent a bot.
 */
public class JoueurVirtuel extends Joueur{

	private Strategie strategie;
	
	public Paquet getOffre() {
		return offre;
	}

    /**
     * Instantiates a new Joueur virtuel.
     *
     * @param nom           the nom
     * @param strategieName the strategie name
     */
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

    /**
     * Method to make an offer from player's cards
     *
     * @return the offer
     */
	public Paquet faireOffre() {
		return strategie.faireOffre(super.main.cartes);
	}

    /**
     * Method to choose which card to take from an offer
     *
     * @param tabJoueur the tab joueur
     * @param nbrOffre  the nbr offre
     * @return int poisition of chosen card
     */
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

    /**
     * Method to clear offer and player's hand
     */
    public void vider() {
		super.offre.cartes.clear();
		super.main.cartes.clear();
	}
	
	

}
