package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Modele.Carte;
import Modele.Joueur;
import Modele.JoueurPhysique;
import Modele.JoueurVirtuel;
import Modele.Paquet;
import Modele.Partie;
import Modele.Pioche;
import Modele.Regle;
import Modele.RegleSpecial01;
import Modele.RegleSpecial02;
import Modele.RegleStandard;
import Modele.VisitorScore;
import Vue.MonInterface;

/**
 * The type Controleur demarrer.
 */
public class ControleurDemarrer {
	
	private int nbBots;
	private String difficulte;
	private Paquet paquet;
	private Partie moteur;
	private MonInterface gui;


    /**
     * Instantiates a new Controleur demarrer with every window component's listener.
     *
     * @param gui                 the gui
     * @param moteur              the moteur
     * @param lblTitre            the lbl titre
     * @param btnCommencer        the btn commencer
     * @param btnCharger          the btn charger
     * @param lblNom              the lbl nom
     * @param textFieldNom        the text field nom
     * @param btnNom              the btn nom
     * @param lblNbBots           the lbl nb bots
     * @param btn3Bots            the btn 3 bots
     * @param btn2Bots            the btn 2 bots
     * @param lblStrat            the lbl strat
     * @param btnStrategieBase    the btn strategie base
     * @param btnStrategieAvancee the btn strategie avancee
     * @param lblRegles           the lbl regles
     * @param btnReglesStandard   the btn regles standard
     * @param btnReglesVar1       the btn regles var 1
     * @param btnReglesVar2       the btn regles var 2
     * @param tabJoueur           the tab joueur
     * @param reglesPartie        the regles partie
     * @param frame               the frame
     */
    public ControleurDemarrer (MonInterface gui, Partie moteur, JLabel lblTitre ,JButton btnCommencer, JButton btnCharger ,JLabel lblNom ,JTextField textFieldNom,JButton btnNom,JLabel lblNbBots ,JButton btn3Bots, JButton btn2Bots ,JLabel lblStrat ,JButton btnStrategieBase, JButton btnStrategieAvancee ,JLabel lblRegles ,JButton btnReglesStandard, JButton btnReglesVar1,JButton btnReglesVar2 ,ArrayList<Joueur> tabJoueur,Regle reglesPartie ,JFrame frame) {
		this.moteur = moteur;
		this.gui = gui;
		
		btnStrategieBase.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        moteur.setNiveauChoisiGUI(1);
		    }
		});

		btnStrategieAvancee.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        moteur.setNiveauChoisiGUI(2);
		    }
		});
		
	btnCommencer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			//On supprime les éléments qui composent la fenêtre
			frame.getContentPane().remove(btnCommencer);
			frame.getContentPane().remove(btnCharger);
			frame.getContentPane().remove(lblTitre);
			
			
			// On actualise la fenêtre
			frame.getContentPane().repaint();
		}
	});
	
	btn2Bots.addActionListener(e -> {
	    moteur.setNbBotsChoisisGUI(2); // Libère la boucle dans Partie.java
	});

	btn3Bots.addActionListener(e -> {
	    moteur.setNbBotsChoisisGUI(3); // Libère la boucle dans Partie.java
	});
	
	
	
	btnCommencer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        moteur.notifierCommencer(); 
	        gui.afficherEcranModeJeu();
	    }
	});
	
	btnReglesStandard.addActionListener(e -> {
	    moteur.setRegleChoisieGUI(1);
	});

	btnReglesVar1.addActionListener(e -> {
	    moteur.setRegleChoisieGUI(2);
	});

	btnReglesVar2.addActionListener(e -> {
	    moteur.setRegleChoisieGUI(3);
	});
	
	btnStrategieBase.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        moteur.setNiveauChoisiGUI(1);
	    }
	});

	btnStrategieAvancee.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        moteur.setNiveauChoisiGUI(2);
	    }
	});
	
	
}

    /**
     * Method that manage the player's turn
     *
     * @param reglesPartie the regles partie
     * @param tabJoueur    the tab joueur
     * @param frame        the frame
     */
    public void tours(Regle reglesPartie,ArrayList<Joueur> tabJoueur, JFrame frame) {
		
		VisitorScore compteur = reglesPartie.Visiteur();
		
		Pioche pioche = (Pioche) paquet;
        reglesPartie.recupPaquet(pioche);
        
		//Compteur de tour
		int tour = 1;
		//Declaration des trophees
		Paquet trophy = new Paquet();
		
		Pioche piocheTemp = new Pioche("temporaire");
		do {
			
			for(Carte c : piocheTemp.getCartes()) {
				c.setVisibility(false);
			}
			
            reglesPartie.melanger(tabJoueur,tour,pioche,trophy,piocheTemp);
			
    		JLabel lbltrophees = new JLabel("Les trophées sont les suivants");
    		lbltrophees.setFont(new Font("Tahoma", Font.PLAIN, 25));
    		lbltrophees.setBounds(240, 56, 343, 72);
    		frame.getContentPane().add(lbltrophees);
    		
    		String trophees = "";
			for(Carte c : trophy.getCartes()) {
				trophees = trophees+c+"      ";
			}
    		JLabel lblAfficherTrophees = new JLabel(trophees);
    		lblAfficherTrophees.setBounds(106, 145, 692, 295);
    		frame.getContentPane().add(lblAfficherTrophees);
    		lblAfficherTrophees.setFont(new Font("Tahoma", Font.PLAIN, 25));
			frame.getContentPane().repaint();
			
            
			reglesPartie.distribution(tabJoueur,tour,pioche,piocheTemp);
			
			reglesPartie.reinitAPrisOffre(tabJoueur);
			
			tabJoueur.get(0).offre = tabJoueur.get(0).faireOffre();
			for(int i = 1; i < tabJoueur.size(); i++) {
				tabJoueur.get(i).offre = tabJoueur.get(i).faireOffre();
			}
			
			
			
            reglesPartie.gererOffres(tabJoueur,pioche,piocheTemp);
			
			tour++;
		}while(piocheTemp.getCartes().size() != 0);
	}

    /**
     * Method that add bots to the game
     *
     * @param nbBots    the nb bots
     * @param tabJoueur the tab joueur
     * @param strategie the strategie
     */
    public void ajouterBots(int nbBots, ArrayList<Joueur> tabJoueur,String strategie) {
		for(int i =1; i < nbBots; i++) {
			tabJoueur.add(new JoueurVirtuel("IA-"+i, strategie));
		}
	}
	
}


