package Modele;
import java.util.*;

import Vue.MonInterface;

/**
 * The type Partie that represent the game.
 */
public class Partie {
	
	private List<Joueur> tabJoueur;
	private Paquet paquet;
	private Regle reglePartie;
	private static MonInterface gui;
	
	private static int niveauChoisiGUI = -1;
	
	private static int nbBotsChoisisGUI = -1;
	
	private static int regleChoisieGUI = -1; 
	
	private static boolean interfaceAAppuyeSurCommencer = false;
	
	private static int modeJeuChoisiGUI = -1;

    public static int type;

    /**
     * Sets mode jeu choisi gui.
     *
     * @param mode the mode
     */
    public void setModeJeuChoisiGUI(int mode) {
	    modeJeuChoisiGUI = mode;
	}

    /**
     * Notifier commencer.
     */
    public void notifierCommencer() {
	    this.interfaceAAppuyeSurCommencer = true;
	    //System.out.println(this.interfaceAAppuyeSurCommencer);
	}
	
	private static String nomDepuisInterface = null;


    /**
     * Sets nom depuis interface.
     *
     * @param nom the nom
     */
    public static void setNomDepuisInterface(String nom) {
	    nomDepuisInterface = nom;
	}

    /**
     * Sets regle choisie gui.
     *
     * @param choix the choix
     */
    public void setRegleChoisieGUI(int choix) {
	    Partie.regleChoisieGUI = choix; 
	}


    /**
     * Sets nb bots choisis gui.
     *
     * @param nb the nb
     */
    public void setNbBotsChoisisGUI(int nb) {
	    Partie.nbBotsChoisisGUI = nb;
	}

    /**
     * Sets niveau choisi gui.
     *
     * @param niveau the niveau
     */
    public void setNiveauChoisiGUI(int niveau) {
	    Partie.niveauChoisiGUI = niveau; 
	}

    /**
     * Sets gui.
     *
     * @param g the g
     */
    public void setGui(MonInterface g) {
		this.gui = g;
	}

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public static MonInterface getGui() {
        return gui;
    }

    /**
     * Method to start a game
     *
     * @param rulenumber the rulenumber
     * @param nom        the nom
     * @param difficulte the difficulte
     * @param nbrJoueur  the nbr joueur
     * @param modeJeu    the mode jeu
     */
    public void demarrerPartie(int rulenumber, String nom, String difficulte, int nbrJoueur, int modeJeu) {
		tabJoueur = new ArrayList<>();
		paquet = new Pioche(difficulte);

		
		if (modeJeu == 1) {
			
			tabJoueur.add(new JoueurPhysique(nom));
			
			for (int i = 1; i < nbrJoueur; i++) {
				tabJoueur.add(new JoueurVirtuel("IA-" + i, difficulte));
			}
		} else {
			
			for (int i = 0; i < nbrJoueur; i++) {
				tabJoueur.add(new JoueurVirtuel("IA-" + (i + 1), difficulte));
			}
		}

		switch (rulenumber) {
			case 1:
				reglePartie = new RegleStandard();
				break;
			case 2:
				reglePartie = new RegleSpecial01();
				break;
			case 3:
				reglePartie = new RegleSpecial02();
				break;
			default:
				System.out.println("Erreur de sélection de règle.");
				return;
		}

		if (reglePartie != null) {
			reglePartie.deroulementPartie(tabJoueur, paquet);
		}
	}

    /**
     * Method to load a game
     */
    public void chargerPartie() {
		
	}

    /**
     * Method to save a game
     */
    public void sauvegarderPartie() {
		
	}

    /**
     * Method to pause a game
     */
    public void pausePartie() {
		
	}

    /**
     * Method to choose the number of players
     *
     * @param sc   the scanner
     * @param type the type
     * @return the int
     */
    public static int choisirNbrJoueur(Scanner sc, int type) {
	    if (gui != null) gui.afficherEcranBots(); 

	    boolean saisieValide = false;
	    int choice = -1;
	    Partie.nbBotsChoisisGUI = -1;

	    System.out.println("\\\\\\************** Nombre de joueur ************///");
	    if(type == 1) {
	    	System.out.print("Combien de bots ? (2 ou 3) : ");
	    }else {
	    	System.out.print("Combien de bots ? (3 ou 4) : ");
	    }
	    

	    do {
	        while (choice == -1 && Partie.nbBotsChoisisGUI == -1) {
	            try {
	                if (System.in.available() > 0) {
	                    if (sc.hasNextInt()) {
	                        choice = sc.nextInt();
	                    } else { sc.next(); }
	                }
	            } catch (Exception e) {}
	            if (Partie.nbBotsChoisisGUI != -1) break;
	            try { Thread.sleep(100); } catch (Exception e) {}
	        }

	        if (Partie.nbBotsChoisisGUI != -1) return Partie.nbBotsChoisisGUI + 1;

	        saisieValide = true;
	        if(type == 1) {
	        	if(choice < 2 || choice > 3) {
		            System.out.println("Veuillez choisir 2 ou 3.");
		            saisieValide = false;
		            choice = -1;
		        }
	        }else {
	        	if(choice < 3 || choice > 4) {
		            System.out.println("Veuillez choisir 3 ou 4.");
		            saisieValide = false;
		            choice = -1;
		        }
	        }
	    } while(!saisieValide);

	    return choice + 1;
	}

    /**
     * Method to choose a game mode
     *
     * @param sc the sc
     * @return the int
     */
    public static int choisirModeJeu(Scanner sc) {
		boolean saisieValide = false;
		int choice = -1;
		do {
			System.out.println("\\\\\\************** Mode de Jeu ***************///");
			System.out.println("1. Joueur vs IA");
			System.out.println("2. IA vs IA (Spectateur)");
			System.out.println("\\\\\\************** **** ** *** ***************///");
			saisieValide = false;
			while (!saisieValide) {
		        
		        if (modeJeuChoisiGUI != -1) {
		            choice = modeJeuChoisiGUI;
		            saisieValide = true;
		            
		        }

		        
		        try {
		            if (System.in.available() > 0) {
		                if (sc.hasNextInt()) {
		                    choice = sc.nextInt();
		                    if (choice == 1 || choice == 2) {
		                        saisieValide = true;
		                    } else {
		                        System.out.println("Choix invalide (1 ou 2 uniquement).");
		                    }
		                }
		                sc.nextLine(); 
		            }
		        } catch (Exception e) {}

		        // Petite pause pour laisser Swing respirer
		        if (!saisieValide) {
		            try { Thread.sleep(100); } catch (InterruptedException e) {}
		        }
		    }
		} while (!saisieValide);
		return choice;
	}

    /**
     * Method to choose the rules
     *
     * @param sc the sc
     * @return the int
     */
    public static int choisirRegle(Scanner sc) {
	    if (gui != null) {
	        gui.afficherEcranRegles(); 
	    }
	    
	    int choice = -1;
	    regleChoisieGUI = -1; 
	    System.out.println("\\\\\\*************** Regle de jeu ***************///");
    	for(RegleDispo m : RegleDispo.values()) {
			System.out.println(m);
		}
		System.out.println("\\\\\\************** **** ** *** ***************///");
		System.out.print("Quelle  regle souhaitez vous ? : ");
	    while (choice == -1) {
	        if (regleChoisieGUI != -1) {
	            System.out.println("[Console] Choix reçu via l'interface.");
	            return regleChoisieGUI; 
	        }

	        
	        try {
	            if (System.in.available() > 0) {
	                if (sc.hasNextInt()) {
	                    choice = sc.nextInt();
	                    sc.nextLine();
	                    if(choice < 1 || choice > 3) {
	    		            System.out.println("Choix non disponible.");
	    		            System.out.print("Quelle  regle souhaitez vous ? : ");
	    		            //saisieValide = false;
	    		            choice = -1;
	    		        }
	                    //return choice;
	                }
	            }
	        } catch (Exception e) {}

	        try { Thread.sleep(100); } catch (InterruptedException e) {}
	    }
	    return choice;
	}

    /**
     * Method to choose the bot's strategy
     *
     * @param sc the sc
     * @return the string
     */
    public static String choisirNiveau(Scanner sc) {
	    if (gui != null) gui.afficherEcranNiveau();

	    int choice = -1;
	    Partie.niveauChoisiGUI = -1; 
	    System.out.println("\\\\\\*************** Niveau de jeu ***************///");
    	for(Niveau m : Niveau.values()) {
			System.out.println(m);
		}
		System.out.println("\\\\\\************** **** ** *** ***************///");
		System.out.print("Quel niveau souhaitez vous ? : ");
	    while (choice == -1) {
	        if (Partie.niveauChoisiGUI != -1) {
	            choice = Partie.niveauChoisiGUI; 
	        }

	        
	        try {
	            if (System.in.available() > 0) {
	                if (sc.hasNextInt()) {
	                    choice = sc.nextInt();
	                    if(choice < 1 || choice > 2) {
	    		            System.out.println("Choix non disponible.");
	    		            System.out.print("Quel  niveau souhaitez vous ? : ");
	    		            //saisieValide = false;
	    		            choice = -1;
	    		        }
	                } else { sc.next(); }
	            }
	        } catch (Exception e) {}

	        
	        try { Thread.sleep(100); } catch (Exception e) {}
	    }

	   
	    String niveau = null;
	    switch(choice) {
	        case 1:
	            niveau = "simple";
	            break;
	        case 2:
	            niveau = "avancee";
	            break;
	        case 0:
	            niveau = null;
	            break;
	    }
	    System.out.println("Niveau retenu : " + niveau);
	    return niveau;
	}


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		Partie jeu = new Partie();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		boolean saisieValide = false;
		while(choice != 4) {
			do {
				System.out.println("****************** JEST ******************");
				for(MenuPrincipale m : MenuPrincipale.values()) {
					System.out.println(m);
				}
				System.out.println("****************** **** ******************");
				System.out.print("Que souhaitez vous faire ? : ");
				saisieValide = false;
		       
		        
		        while (!saisieValide) {
		            
		            if (interfaceAAppuyeSurCommencer == true) {
		                choice = 1;
		                saisieValide = true;
		                gui.afficherEcranModeJeu();
		                //break;
		            }

		            try {
		                
		                if (System.in.available() > 0) {
		                    if (sc.hasNextInt()) {
		                        choice = sc.nextInt();
		                        saisieValide = true;
		                    }
		                    // sc.nextLine(); 
		                }
		            } catch (Exception e) {}

		            
		            if (!saisieValide) {
		                try { Thread.sleep(100); } catch (InterruptedException e) {}
		            }
		        }
				//sc.nextLine();
				
			}while(!saisieValide);
			switch(choice) {
				case 1:
					int modeJeu = choisirModeJeu(sc);
					type = modeJeu;
					if (modeJeu == 0) break;

					String answer = "Spectateur"; 
					
					if (modeJeu == 1) {
						saisieValide = false;
						System.out.print("Entrez votre nom (en console ou via l'interface) : ");

						while (!saisieValide) {
						    
						    if (nomDepuisInterface != null && !nomDepuisInterface.isEmpty()) {
						        answer = nomDepuisInterface;
						        saisieValide = true;
						    }

						   
						    try {
						        if (System.in.available() > 0) {
						            answer = sc.nextLine();
						            if (!answer.isEmpty()) {
						                saisieValide = true;
						            }
						        }
						    } catch (Exception e) {
						       
						    }

						    
						    if (!saisieValide) {
						        try {
						            Thread.sleep(100); 
						        } catch (InterruptedException e) {
						            e.printStackTrace();
						        }
						    }
						}
					}

					
					int rulechoice = choisirRegle(sc);
					if(rulechoice == 0) {
						break;
					}

					
					String levelchoice = choisirNiveau(sc);
					if(levelchoice ==  null) {
						break;
					}

					
					int nbrJoueur = choisirNbrJoueur(sc,modeJeu);
					
             
					
					if(rulechoice != 0 && levelchoice != null ) {
						
						jeu.demarrerPartie(rulechoice, answer, levelchoice, nbrJoueur, modeJeu);
					}
					choice = 0;
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
			}
			choice=0;
		}
		
		sc.close();
	}


}