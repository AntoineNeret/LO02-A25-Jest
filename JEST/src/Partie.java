import java.util.*;

public class Partie{
	
	private List <Joueur> tabJoueur;
	private Paquet paquet;
	private Regle reglePartie;
	private String regleDisponible;
	private MenuPrincipale menu;

	public void demarrerPartie(int rulenumber, String nom, String difficulte, int nbrJoueur) {
		tabJoueur = new ArrayList<>();
		paquet = new Pioche(difficulte);
		switch(rulenumber) {
			case 1:
				tabJoueur.add(new JoueurPhysique(nom));
				for(int i =1; i < nbrJoueur; i++) {
					tabJoueur.add(new JoueurVirtuel("IA-"+i, difficulte));
				}
				reglePartie = new RegleStandard();
				reglePartie.deroulementPartie(tabJoueur, paquet);
				break;
		}
		
	}

	public void chargerPartie() {
		
	}

	public void sauvegarderPartie() {
		
	}

	public void pausePartie() {
		
	}
	

	public static int choisirRegle(Scanner sc) {
		boolean saisieValide = false;
		int choice = -1;
			do {
				System.out.println("\\\\\\*************** Regle de jeu ***************///");
				for(RegleDispo r : RegleDispo.values()) {
					System.out.println(r);
				}
				System.out.println("\\\\\\*************** ***** ** *** ***************///");
				saisieValide = false;
				try {
					System.out.print("Quel est la regle de jeu ? : ");
					choice = sc.nextInt();
					saisieValide = true;
					if(choice > 2 || choice < 0) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
					//saisieValide = true;
				}catch (InputNotDefined e) {
					System.out.println("Choix non disponible");
					saisieValide = false;
				}
				
			}while(!saisieValide);
		return choice;
		
	}
	
	public static int choisirNbrJoueur(Scanner sc) {
		boolean saisieValide = false;
		int choice = -1;
			do {
				System.out.println("\\\\\\************** Nombre de joueur ************///");
				saisieValide = false;
				try {
					System.out.print("Quel est le nombre de bot (2 ou 3) : ");
					choice = sc.nextInt();
					saisieValide = true;
					if(choice > 3 || choice < 2) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
					//saisieValide = true;
				}catch (InputNotDefined e) {
					System.out.println("Choix non disponible");
					saisieValide = false; 
				}
				
			}while(!saisieValide);
		return choice + 1;
	}
	
	public static String choisirNiveau(Scanner sc) {
		boolean saisieValide = false;
		int choice = -1;
			do {
				System.out.println("\\\\\\************** Niveau de jeu ***************///");
				for(Niveau r : Niveau.values()) {
					System.out.println(r);
				}
				System.out.println("\\\\\\************** ****** ** *** ***************///");
				saisieValide = false;
				try {
					System.out.print("Quel niveau pour les bots ? : ");
					choice = sc.nextInt();
					saisieValide = true;
					if(choice > 2 || choice < 0) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					sc.nextLine();
					//saisieValide = true;
				}catch (InputNotDefined e) {
					System.out.println("Choix non disponible");
					saisieValide = false;
				}
				
			}while(!saisieValide);
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
		return niveau;
		
	}

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
				saisieValide = false;
				try {
					System.out.print("Que souhaitez vous faire ? : ");
					choice = sc.nextInt();
					saisieValide = true;
					if(choice > 4 || choice < 1) {
						InputNotDefined ind = new InputNotDefined("Ce choix n'est pas disponible");
						throw ind;
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Erreur de format lors de la recuperation de votre choix");
					//sc.nextLine();
					saisieValide = false;
				}catch (InputNotDefined e) {
					System.out.println("Choix non disponible");
				}
				sc.nextLine();
				
			}while(!saisieValide);
			switch(choice) {
				case 1:
					String answer = "";
					saisieValide = false;
					do {
						System.out.print("\n Quel est votre nom ? : ");
						answer = sc.nextLine();
						saisieValide = true;
					}while(!saisieValide);
					int rulechoice = choisirRegle(sc);
					if(rulechoice == 0) {
						break;
					}
					String levelchoice = choisirNiveau(sc);
					if(levelchoice ==  null) {
						break;
					}
					int nbrJoueur = choisirNbrJoueur(sc);
					if(rulechoice != 0 && levelchoice != null ) {
						jeu.demarrerPartie(rulechoice, answer, levelchoice, nbrJoueur);
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
		}
		
		sc.close();
	}
}
