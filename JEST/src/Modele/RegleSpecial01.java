import java.util.*;

public class RegleSpecial01 implements Regle{

    private static Scanner sc = new Scanner(System.in);
    private VisitorScore compteur;

    public void deroulementPartie(List<Joueur> tabJoueur, Paquet paquet) {
        //Initialisation du compteur de jeu
        compteur = this.Visiteur();

        //Recuperation du paquet
        Pioche pioche = (Pioche) paquet;
        for(Carte c : pioche.cartes) {
            c.setVisibility(false);
        }
        //Compteur de tour
        int tour = 1;
        //Declaration des trophees
        Paquet trophy = new Paquet();

        Pioche piocheTemp = new Pioche("temporaire");
        do {
            System.out.println("************ Debut du tour " + tour + " ************");
            //On s'assure de la coherence de chaque carte
            for(Carte c : piocheTemp.cartes) {
                c.setVisibility(false);
            }

            //melange des cartes

            if(tour == 1) {
                Collections.shuffle(pioche.cartes);
                int nbrTrophy = (tabJoueur.size()%2)+3;
                for(int i = 1;i <=nbrTrophy ; i++) {
                    trophy.cartes.add(pioche.cartes.remove(0));
                    trophy.cartes.get(i-1).setVisibility(true);
                }
            }else {
                Collections.shuffle(piocheTemp.cartes);
            }


            System.out.print("\n** Trophee de jeu ** : ");
            for(Carte c : trophy.cartes) {
                System.out.print(c + "  ");
            }


            //Distribution des cartes a chaque joueur
            if(tour == 1) {
                pioche.distribuer(tabJoueur, 4);
            }else {
                piocheTemp.distribuer(tabJoueur, 4);
            }

            //Aucun joueur n'a choisi une offre
            for(Joueur j : tabJoueur) {
                j.setHasTook(false);
            }

            //Procede par tour de jeu
            //En ce qui concerne le joueur
            tabJoueur.get(0).offre = tabJoueur.get(0).faireOffre();
            for(int i = 1; i < tabJoueur.size(); i++) {
                tabJoueur.get(i).offre = tabJoueur.get(i).faireOffre();
            }

            int starter;
            int nextOne;

            //Comparaison des cartes des offres
            starter = leStarter(tabJoueur);

            //Choix de l'offre
            nextOne = tabJoueur.get(starter).choisirOffre(tabJoueur, offreDispo(tabJoueur));
            for(int i = 0; i<tabJoueur.size()-1; i++) {
                //On s'assure du suivant legitime
                if(tabJoueur.get(nextOne).getHasTook()) {
                    if (nextStarter(tabJoueur, nextOne, starter) >= 0) {
                        nextOne = nextStarter(tabJoueur, nextOne, starter);
                    }
                }
                if (offreDispo(tabJoueur) != 0) {
                    nextOne = tabJoueur.get(nextOne).choisirOffre(tabJoueur, offreDispo(tabJoueur));
                }
            }

            if(!pioche.cartes.isEmpty()) {
                piocheTemp.recuperer(tabJoueur);
                for(int i = 0; i < tabJoueur.size(); i++) {
                    if (!pioche.cartes.isEmpty()) {
                        piocheTemp.ajouterCarte(pioche.cartes.removeFirst());
                    }
                }
            }else {
                for(int i = 0; i < tabJoueur.size(); i++) {
                    tabJoueur.get(i).jest.cartes.add(tabJoueur.get(i).offre.cartes.remove(0));
                }
            }
            System.out.println("************** Fin du tour " + tour + " *************");

            //prompt des resultat du tour
            printResult(tabJoueur,"tour");

            if(piocheTemp.cartes.size() != 0) {
                System.out.println("************* Recuperation des cartes *************");
            }
            //Demande la suite a l'utilisateur
            if(piocheTemp.cartes.size() != 0) {
                int choice = MenuOption();

                switch(choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 0:
                        return;
                }
            }

            tour++;
        }while(piocheTemp.cartes.size() != 0);

        System.out.println("************ Fin de partie ************");
        System.out.println();
        System.out.println("\\\\ Conditions de gains des trophees //");
        for(Carte c: trophy.cartes) {
            System.out.println(c + " --> " + c.getCondition() );
        }
        System.out.println();
        System.out.println("\\\\ Scores de jest avant trophee //");
        printScores(tabJoueur);
        System.out.println();
        System.out.println("\\\\ Identification des gagnants trophee //");
        gagnantTrophee(tabJoueur, trophy);
        lastPrintResult(tabJoueur,"partie");
        System.out.println();
        System.out.println("\\\\ Comptabilisation des scores //");
        System.out.println();
        System.out.println("\\\\ Scores de jest avec trophee //");
        printScores(tabJoueur);
        System.out.println();
        System.out.println("****** Par ordre de merite les gagnants sont ******");
        declareGagnant(tabJoueur);
        System.out.println("****** ------------------------------------- ******");





        //Vidage de la memoire occupe
        pioche = null;
        piocheTemp = null;
        tabJoueur = null;
    }

    public void declareGagnant(List<Joueur> tabJoueur) {
        int rank = 1;
        do {
            int i = 0;
            int max =0;
            for(Joueur j : tabJoueur) {
                if(max <= j.score) {
                    max  = j.score;
                    i = tabJoueur.indexOf(j);
                }
            }
            System.out.println(rank + ". " + tabJoueur.get(i).getName() );
            tabJoueur.remove(i);
            rank++;
        }while(!tabJoueur.isEmpty());
    }

    public void printScores(List<Joueur> tabJoueur) {
        int i = 0;
        for(Joueur j : tabJoueur) {
            j.accepter(compteur);
            System.out.println( j.getName() +" Scores --> " + j.score);
            i++;
        }
    }

    public static int leStarter(List<Joueur> tabJoueur) {
        int indexPlusGrandeOffre = 0;
        Carte[] cartesVisible = new Carte[tabJoueur.size()];
        for(int i = 0; i < tabJoueur.size(); i++) {
            for(int j = 0; j < tabJoueur.get(i).offre.cartes.size(); j++) {
                if(tabJoueur.get(i).offre.cartes.get(j).getVisibility() == true) {
                    cartesVisible[i] = tabJoueur.get(i).offre.cartes.get(j);
                }
            }
        }

        for(int i = 1; i < tabJoueur.size(); i++) {
            if(cartesVisible[indexPlusGrandeOffre].compareCarte(cartesVisible[i])) {
                continue;
            }else {
                indexPlusGrandeOffre = i;
            }
        }
        return indexPlusGrandeOffre;
    }

    public static int nextStarter(List<Joueur> tabJoueur, int outIndex, int starter) {
        Carte[] cartesVisible = new Carte[tabJoueur.size()];
        for(int i = 0; i < tabJoueur.size(); i++) {
            for(int j = 0; j < tabJoueur.get(i).offre.cartes.size(); j++) {
                if(tabJoueur.get(i).offre.cartes.get(j).getVisibility() == true && !tabJoueur.get(i).getHasTook()) {
                    if(i == outIndex || i == starter) {
                        cartesVisible[i] = null;
                    }else {
                        cartesVisible[i] = tabJoueur.get(i).offre.cartes.get(j);
                    }
                }
            }
        }
        int i1 = -1;
        int i2 = -1;

        if(tabJoueur.size() ==3) {
            return i1;
        }

        for(int i = 0; i < tabJoueur.size(); i++) {
            if(cartesVisible[i] == null) {
                continue;
            }else {
                if(i1 == -1) {
                    i1 = i;
                }else if(i2 == -1) {
                    i2 = i;
                }
            }
        }


        if(cartesVisible[i1].compareCarte(cartesVisible[i2])) {
            return i1;
        }else {
            return i2;
        }
    }

    public  static int offreDispo(List<Joueur> tabJoueur) {
        int nbr = 0;
        for(int i = 0; i < tabJoueur.size(); i++) {
            if(tabJoueur.get(i).offre.cartes.size() == 2) {
                nbr++;
            }
        }
        return nbr;
    }

    public VisitorScore Visiteur() {
        return new CompteurStandard();
    }

    public void gagnantTrophee(List<Joueur> tabJoueur, Paquet trophy) {
        //On recupere chaque carte verifie sa condition puis remet au gagnant
        int winnerIndex = 0;
        for(Carte c : trophy.cartes) {
            winnerIndex = executeCondition(tabJoueur, c.getCondition());
            if(winnerIndex == -1) {
                System.out.println(c + " -> Egalite personne ne gagne ce trophee !");
            }else if(winnerIndex == -2) {
                System.out.println(c + "-> Personne ne respecte la condition etablie !");
            }else {
                System.out.println(c +" est pour  " + tabJoueur.get(winnerIndex).getName());
                tabJoueur.get(winnerIndex).jest.cartes.add(c);
            }
        }



    }

    public static void printResult(List<Joueur> tabJoueur, String name) {
        System.out.println("************** Resultat de "+name+" *************");
        for(int i = 0; i < tabJoueur.size(); i++) {
            for(int j = 0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                if(i == 0) {
                    tabJoueur.get(i).jest.cartes.get(j).setVisibility(true);
                }else {
                    tabJoueur.get(i).jest.cartes.get(j).setVisibility(false);
                }
            }
        }
        for(Joueur j : tabJoueur) {
            System.out.println(j.getName() + " Jest --> " + j.jest.cartes );
        }
        System.out.println("************** ******** ** **** *************");
    }

    public static void lastPrintResult(List<Joueur> tabJoueur, String name) {
        System.out.println("************** Resultat de "+name+" *************");
        for(int i = 0; i < tabJoueur.size(); i++) {
            for(int j = 0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                tabJoueur.get(i).jest.cartes.get(j).setVisibility(true);
            }
        }
        for(Joueur j : tabJoueur) {
            System.out.println(j.getName() + " Jest --> " + j.jest.cartes );
        }
        System.out.println("************** ******** ** **** *************");
    }

    public static int MenuOption() {

        boolean saisieValide = false;
        int choice = 0;
        do {
            System.out.println("************** Voulez vous continuer ? *************");
            for(OptionPartie op : OptionPartie.values()) {
                System.out.println(op);
            }
            System.out.println("************** ****** **** ********* *************");
            saisieValide = false;
            try {
                System.out.print("Que voulez vous faire : ");
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


    public static int executeCondition(List<Joueur> tabJoueur, String condit) {
        //recupere la codition et son execution
        //index du gagnant a retourne
        int index = 0;
        //reservoir des valeurs
        int maxFace = 0;
        int minFace = Integer.MAX_VALUE;
        //valeurs de decompte
        int n = 0;
        int count = 0;
        //Valeur Best Jest
        int max = 0;
        int countEgal = 0;
        switch(condit) {
            case "Highest ♠":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♠") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() > maxFace) {
                                maxFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Highest ♣":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♣") {
                            if(maxFace < tabJoueur.get(i).jest.cartes.get(j).getValue()) {
                                maxFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Highest ♦":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♦") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() > maxFace) {
                                maxFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Highest ♥":
                System.out.print("4-");
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♥") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() > maxFace) {
                                maxFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Lowest ♠":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♠") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() < minFace) {
                                minFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Lowest ♣":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♣") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() < minFace) {
                                minFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Lowest ♦":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♦") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() < minFace) {
                                minFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Lowest ♥":
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getColor() == "♥") {
                            if(tabJoueur.get(i).jest.cartes.get(j).getValue() < minFace) {
                                minFace = tabJoueur.get(i).jest.cartes.get(j).getValue();
                                index = i;
                            }
                        }
                    }
                }
                break;
            case "Majority 4":
                for(int i=0; i<tabJoueur.size(); i++) {
                    count = 0;
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getValue() == 4) {
                            count++;
                        }
                    }
                    if(count > n) {
                        n = count;
                        index =i;
                    }else if(count == n) {
                        if(count == 2) {
                            index = -1;
                            break;
                        }
                    }
                }
                break;
            case "Majority 3":
                for(int i=0; i<tabJoueur.size(); i++) {
                    count = 0;
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getValue() == 3) {
                            count++;
                        }
                    }
                    if(count > n) {
                        n = count;
                        index =i;
                    }else if(count == n) {
                        if(count == 2) {
                            index = -1;
                            break;
                        }
                    }
                }
                break;
            case "Majority 2":
                for(int i=0; i<tabJoueur.size(); i++) {
                    count = 0;
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getValue() == 2) {
                            count++;
                        }
                    }
                    if(count > n) {
                        n = count;
                        index =i;
                    }else if(count == n) {
                        if(count == 2) {
                            index = -1;
                            break;
                        }
                    }
                }
                break;
            case "Joker":
                index = -2;
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j < tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getValue() == 0) {
                            index = i;
                        }
                    }
                }
                break;
            case "Best Jest":
                max = tabJoueur.get(0).score;
                countEgal = 0;
                for(int i=1; i<tabJoueur.size(); i++) {
                    if(max < tabJoueur.get(i).score) {
                        max = tabJoueur.get(i).score;
                        index = i;
                    }else if(max == tabJoueur.get(i).score ) {
                        countEgal++;
                    }
                }
                if(countEgal != 0) {
                    index = -1;
                }
                break;
            case "Best Jest No Joke":
                max = 0;
                countEgal = 0;
                int indexJoker =-1;
                for(int i=0; i<tabJoueur.size(); i++) {
                    for(int j=0; j<tabJoueur.get(i).jest.cartes.size(); j++) {
                        if(tabJoueur.get(i).jest.cartes.get(j).getValue() == 0) {
                            indexJoker = i;
                        }
                    }
                }
                for(int i=0; i<tabJoueur.size(); i++) {
                    if(i == indexJoker) {
                        continue;
                    }
                    if(max < tabJoueur.get(i).score) {
                        max = tabJoueur.get(i).score;
                        index = i;
                    }else if(max == tabJoueur.get(i).score ) {
                        countEgal++;
                    }
                }
                if(countEgal != 0) {
                    index = -1;
                }
                break;
            default:
                index =-2;
        }

        return index;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }


}

