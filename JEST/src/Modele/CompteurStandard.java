package Modele;

/**
 * The type Compteur standard responsible for counting player's score.
 */
public class CompteurStandard implements VisitorScore {

	@Override
	public int visiter(Joueur j) {
		// TODO Auto-generated method stub
		int value = 0;
		//Determine la presence de Joker
		int countJoke = 0;
		for( Carte c : j.jest.cartes) {
			if(c.getValue() == 0) {
				countJoke = 1;
			}
		}
		//Determine le nombre de ♥
		int heartCount = 0;
		for(int u =0; u < j.jest.cartes.size() ; u++) {
			if(j.jest.cartes.get(u).getColor() == "♥") {
				heartCount++;
			} 
		}
		//On verifie d'abord les paires
		int paireCount = 0;
		for(int i = 0; i < j.jest.cartes.size()-1; i++) {
			for(int u = 1; u < j.jest.cartes.size(); u++) {
				if(j.jest.cartes.get(i).getValue() == j.jest.cartes.get(u).getValue()) {
					if((j.jest.cartes.get(i).getColor() == "♠") && (j.jest.cartes.get(u).getColor() == "♣")) {
						paireCount++;
					}
				}
			}
		}
		value = paireCount *2;
		
		//On verifie la valeur du Ace
		String symbol = "";
		int Acevalue = 0;
		int countSymbol = 0;
		
		//Somme des valeurs
		for(int i = 0; i < j.jest.cartes.size(); i++){
			if(j.jest.cartes.get(i).getColor() == "♠" || j.jest.cartes.get(i).getColor() == "♣") {
				if(j.jest.cartes.get(i).getValue() == 1) {
					symbol = j.jest.cartes.get(i).getColor();
					for(int y = 0; y < j.jest.cartes.size(); y++) {
						if(y == i) {
							continue; 
						}else {
							if(symbol == j.jest.cartes.get(y).getColor()) {
								countSymbol++;
							}
						}
					}
					if(countSymbol == 0) {
						value += 5;
					}else {
						value += 1;
					}
				}else {
					value += j.jest.cartes.get(i).getValue();
				}
			}else if(j.jest.cartes.get(i).getColor() == "♦"){
				if(j.jest.cartes.get(i).getValue() == 1) {	
					symbol = j.jest.cartes.get(i).getColor();
					for(int y = 0; y < j.jest.cartes.size(); y++) {
						if(y == i) {
							continue;
						}else {
							if(symbol == j.jest.cartes.get(y).getColor()) {
								countSymbol++;
							}
						}
					}
					if(countSymbol == 0) {
						value -= 5;
					}else {
						value -= 1;
					}
				}else {
					value -= j.jest.cartes.get(i).getValue();
				}
			}else if(j.jest.cartes.get(i).getColor() == "♥") {
				if(countJoke != 0) {
					if(heartCount > 3) {
						value += j.jest.cartes.get(i).getValue();
					}else if(heartCount > 0 && heartCount <=3) {
						value -=  j.jest.cartes.get(i).getValue(); 
					}
				}
			}
	
		}
		if(heartCount == 0 && countJoke != 0) {value += 4;}
		return value;
	}

}
