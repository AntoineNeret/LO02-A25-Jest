package Modele;
import java.util.*;

/**
 * The type Joueur physique which represent the player behind the computer.
 */
public class JoueurPhysique extends Joueur{
	
	private static Scanner sc = new Scanner(System.in);

    /**
     * @return offre
     */
	public Paquet getOffre() {
		return offre;
	}


    /**
     * Method to choose which card to take from an offer
     *
     * @param tabJoueur the tab joueur
     * @param nbrOffre  the nbr offre
     * @return int poisition of chosen card
     */
	public int choisirOffre(List<Joueur> tabJoueur, int nbrOffre) {
		//Scanner sc = new Scanner(System.in);
		Paquet jest = new Paquet();
		boolean saisieValide = false;
		int choice = 0;
		
		if (Partie.getGui() != null) {
	        Partie.getGui().afficherOffresADisposition(tabJoueur);
	    }
		
		if(nbrOffre >= 3) {
			int notChoose =0;
			int value =1;
			do {
				int i = 0;
				System.out.println("\n*************** Offres des joueurs ***************");
				for(i =1; i < tabJoueur.size(); i++) {
					if(tabJoueur.get(i).offre.cartes.size() == 2) {
						System.out.println((value) + ". " + tabJoueur.get(i).getName() + " Offre -> " + tabJoueur.get(i).offre.cartes);
						value++;
					}else {
						notChoose = i;
					}
					//System.out.println((i) + ". " + tabJoueur.get(i).getName() + " Offre -> " + tabJoueur.get(i).offre.cartes);
					
				}
				System.out.println("*************** Offres des Joueurs ***************");
				System.out.print("Quelle offre vous interesse ? : ");
				try {
					if (Partie.getGui() != null && Partie.getGui().getCarteChoisieOffre() != null) {
			            Carte cliquee = Partie.getGui().getCarteChoisieOffre();
			            choice = determinerIndexDepuisCarte(tabJoueur, cliquee);
			            saisieValide = true;
			            System.out.println("[GUI] Carte de l'offre sélectionnée !");
			        }

			        try {
			            if (System.in.available() > 0) {
			                if (sc.hasNextInt()) {
			                    choice = sc.nextInt();
			                    
			                    if (choice >= 1 && choice <= tabJoueur.size()) {
			                        saisieValide = true;
			                    }
			                }
			                sc.nextLine(); 
			            }
			        } catch (Exception e) {}

			        
			        if (!saisieValide) {
			            try { Thread.sleep(100); } catch (InterruptedException e) {}
			        }
					saisieValide = true;
					if(choice > i || choice < 1) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
				}catch(InputNotDefined e) {
					System.out.println("Choix non disponible");
					
				}
				sc.nextLine();
				
			}while(!saisieValide);
			saisieValide = false;
			int choosenCard = 0;
			do {
				int i = 0;
				System.out.println("\n*************** Votre choix ***************");
				for(i =0; i < tabJoueur.get(choice).offre.cartes.size(); i++) {
					System.out.println((i+1) + ". " + tabJoueur.get(choice).offre.cartes.get(i));
					
				}
				System.out.println("*************** Votre choix ***************");
				System.out.print("Quelle carte voulez vous prendre ? : ");
				try {
					choosenCard = sc.nextInt();
					saisieValide = true;
					if(choosenCard > i || choosenCard < 1) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
				}catch(InputNotDefined e) {
					System.out.println("Choix non disponible");
					
				}
				sc.nextLine();
				
			}while(!saisieValide);
			super.jest.cartes.add(tabJoueur.get(choice).offre.cartes.remove(choosenCard-1));
			//sc.close();
			/*if(nbrOffre == 4) {
				return choice;
			}else {
				if(notChoose ==1) {
					return choice + 1;
				}else {
					if(choice == 1) return choice;
					return choice+1;
				}
			}*/
			super.setHasTook(true);
			return choice;
		}else if(nbrOffre == 2) {
			//Recuperation des deux index possibles
			int index1 = -1;
			int index2 = -1;
			//Compte les index reels ne nous incluant pas 
			int count = 0 ;
			for(int i = 0; i < tabJoueur.size() ; i++) {
				if(tabJoueur.get(i).offre.cartes.size()==2 && index1 == -1) {
					index1 = i;
				}else {
					index2 = i;
				}
				if(tabJoueur.get(i).offre.cartes.size()==2 && i != 0) {
					count++;
				}
			}
			int notChoose =0;
			
			//Cas particulier pour 4 joueurs
			if(tabJoueur.size() == 4 && count == 1) {
				if(index1 == 0) {
					choice = index2;
				}else {
					choice = index1;
				}
				saisieValide = false;
				int choosenCard = 0;
				do {
					int i = 0;
					System.out.println("\n*************** Votre choix ***************");
					System.out.println("********** /Carte de : " + tabJoueur.get(choice).getName() + " **********");
					for(i =0; i < tabJoueur.get(choice).offre.cartes.size(); i++) {
						System.out.println((i+1) + ". " + tabJoueur.get(choice).offre.cartes.get(i));
						
					}
					System.out.println("*************** Votre choix ***************");
					System.out.print("Quelle carte voulez vous prendre ? : ");
					try {
						choosenCard = sc.nextInt();
						saisieValide = true;
						if(choosenCard > i || choosenCard < 1) {
							InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
							throw ind;
						}
						
					}catch(InputMismatchException e) {
						System.out.println("Erreur de format lors de la recuperation de votre choix");
						sc.nextLine();
					}catch(InputNotDefined e) {
						System.out.println("Choix non disponible");
						
					}
					sc.nextLine();
					
				}while(!saisieValide);
				super.jest.cartes.add(tabJoueur.get(choice).offre.cartes.remove(choosenCard-1));
				//sc.close();
				super.setHasTook(true);
				if(super.jest.cartes.size() == tabJoueur.get(index1).jest.cartes.size()) {
					return index2;
				}else{
					return index1;
				}
				
			}
			
			int value =1;
			do {
				int i = 0;
				System.out.println("\n*************** Offres des joeurs ***************");
				for(i =1; i < tabJoueur.size(); i++) {
					if(tabJoueur.get(i).offre.cartes.size() == 2) {
						System.out.println((value) + ". " + tabJoueur.get(i).getName() + " Offre -> " + tabJoueur.get(i).offre.cartes);
						value++;
					}else {
						notChoose = i;
					}
				}
				System.out.println("*************** Offres des Joueurs ***************");
				System.out.print("Quelle offre vous interesse ? : ");
				try {
					choice = sc.nextInt();
					saisieValide = true;
					if(choice > i || choice < 1) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
				}catch(InputNotDefined e) {
					System.out.println("Choix non disponible");
					
				}
				sc.nextLine();
				
			}while(!saisieValide);
			saisieValide = false;
			if(tabJoueur.size() > 3) {
				if(notChoose ==1) {
					choice += 1;
				}
			}
			int choosenCard = 0;
			do {
				int i = 0;
				System.out.println("\n*************** Votre choix ***************");
				System.out.println("********** Carte de : " + tabJoueur.get(choice).getName() + " **********");
				for(i =0; i < tabJoueur.get(choice).offre.cartes.size(); i++) {
					System.out.println((i+1) + ". " + tabJoueur.get(choice).offre.cartes.get(i));
					
				}
				System.out.println("*************** Votre choix ***************");
				System.out.print("Quelle carte voulez vous prendre ? : ");
				try {
					choosenCard = sc.nextInt();
					saisieValide = true;
					if(choosenCard > i || choosenCard < 1) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
				}catch(InputNotDefined e) {
					System.out.println("Choix non disponible");
					
				}
				sc.nextLine();
				
			}while(!saisieValide);
			super.jest.cartes.add(tabJoueur.get(choice).offre.cartes.remove(choosenCard-1));
			//sc.close();
			super.setHasTook(true);
			if(super.jest.cartes.size() == tabJoueur.get(index1).jest.cartes.size()) {
				return index2;
			}else{
				return index1;
			}
		}else{
			saisieValide = false;
			int choosenCard = 0;
			if(super.offre.cartes.size() != 1) {
				do {
					int i = 0;
					System.out.println("\n*************** Votre choix ***************");
					for(i =0; i < super.offre.cartes.size(); i++) {
						System.out.println((i+1) + ". " + super.offre.cartes.get(i));
						
					}
					System.out.println("*************** Votre choix ***************");
					System.out.print("Quelle carte voulez vous mettre dans votre Jest ? : ");
					try {
						choosenCard = sc.nextInt();
						saisieValide = true;
						if(choosenCard > i || choosenCard < 1) {
							InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
							throw ind;
						}
						
					}catch(InputMismatchException e) {
						System.out.println("Erreur de format lors de la recuperation de votre choix");
						sc.nextLine();
					}catch(InputNotDefined e) {
						System.out.println("Choix non disponible");
						
					}
					sc.nextLine();
					
				}while(!saisieValide);
				super.setHasTook(true);
				super.jest.cartes.add(super.offre.cartes.remove(choosenCard-1));
			}else {
				//Recherchons le dernier joueur avec deux cartes 
				int lastIndex = 0;
				for(int i =0; i < tabJoueur.size(); i++) {
					if(tabJoueur.get(i).offre.cartes.size() == 2) {
						lastIndex = i;
					}
				}
				do {
					int i = 0;
					System.out.println("\n*************** Votre choix ***************");
					for(i =0; i < tabJoueur.get(lastIndex).offre.cartes.size(); i++) {
						System.out.println((i+1) + ". " + tabJoueur.get(lastIndex).offre.cartes.get(i));
						
					}
					System.out.println("*************** Votre choix ***************");
					System.out.print("Quelle carte voulez vous mettre dans votre Jest ? : ");
					try {
						choosenCard = sc.nextInt();
						saisieValide = true;
						if(choosenCard > i || choosenCard < 1) {
							InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
							throw ind;
						}
						
					}catch(InputMismatchException e) {
						System.out.println("Erreur de format lors de la recuperation de votre choix");
						sc.nextLine();
					}catch(InputNotDefined e) {
						System.out.println("Choix non disponible");
						
					}
					sc.nextLine();
					
				}while(!saisieValide);
				super.setHasTook(true);
				super.jest.cartes.add(tabJoueur.get(lastIndex).offre.cartes.remove(choosenCard-1));
			}
			//sc.close();
			return 0;
		}
	}


    /**
     * Method to make an offer from player's cards
     *
     * @return the offer
     */
	public Paquet faireOffre() {
		//Scanner sc = new Scanner(System.in);
		Paquet offre = new Paquet();
		boolean saisieValide = false;
		if (Partie.getGui() != null) {
            Partie.getGui().demanderFaireOffre(this.main.cartes);
        }
		int choice = 0;
		do {
			int i = 0;
			System.out.println("\n****************** Main ******************");
			for(Carte c : super.main.cartes) {
				super.main.cartes.get(i).setVisibility(true);
				System.out.println((i+1) + ". " + c);
				super.main.cartes.get(i).setVisibility(false);
				i++;
			}
			System.out.println("****************** Main ******************");
			System.out.print("Quelle carte doit etre visible ? : ");
			while (!saisieValide) {
		        
		        if (Partie.getGui() != null && Partie.getGui().getCarteFaceCachee() != null) {
		            Carte cliquee = Partie.getGui().getCarteFaceCachee();
		            
		            choice = main.cartes.indexOf(cliquee) + 1;
		            saisieValide = true;
		            //System.out.println("\n[Interface] Choix enregistré : " + cliquee);
		        }

		         try {
		            if (System.in.available() > 0) {
		                if (sc.hasNextInt()) {
		                    choice = sc.nextInt();
		                    saisieValide = true;
		                    //System.out.println("[Console] Choix enregistré : " + choice);
		                }
		                sc.nextLine(); // Vide le tampon
		            }
		        } catch (Exception e) {}

		        
		        try { Thread.sleep(50); } catch (InterruptedException e) {}
		    }
			try {
				saisieValide = true;
				//System.out.println(main.cartes.get(choice));
				
				if(choice > i || choice < 1) {
					InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
					throw ind;
				}
				
			}catch(InputMismatchException e) {
				System.out.println("Erreur de format lors de la recuperation de votre choix");
				sc.nextLine();
			}catch(InputNotDefined e) {
				System.out.println("Choix non disponible");
				saisieValide = false;
				
			}
			//sc.nextLine();
			
		}while(!saisieValide);
		choice--;
		super.main.cartes.get(choice).setVisibility(true);
		offre.cartes.addAll(super.main.cartes);
		super.main.cartes.clear();
		//sc.close();
		if (Partie.getGui() != null) {
		    Partie.getGui().viderPanelOffre();
		}
		return offre;
		
	}

    /**
     * Instantiates a new Joueur physique.
     *
     * @param nom the nom
     */
    public JoueurPhysique(String nom) {
		super(nom);
	}


    /**
     * Determine the position of a player in the array from the card that's in their offer
     *
     * @param tab player array
     * @param c card
     * @return the position of a player from the card
     */
	private int determinerIndexDepuisCarte(List<Joueur> tab, Carte c) {
	    int indexVisuel = 1;
	    for (int i = 1; i < tab.size(); i++) {
	        if (tab.get(i).offre.cartes.contains(c)) {
	            return indexVisuel;
	        }
	        
	        if (tab.get(i).offre.cartes.size() == 2) {
	            indexVisuel++;
	        }
	    }
	    return 1; 
	}

}
