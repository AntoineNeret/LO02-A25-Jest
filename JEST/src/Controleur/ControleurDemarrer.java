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
import Modele.Pioche;
import Modele.Regle;
import Modele.RegleSpecial01;
import Modele.RegleSpecial02;
import Modele.RegleStandard;
import Modele.VisitorScore;

public class ControleurDemarrer {
	
	private int nbBots;
	private String difficulte;
	private Paquet paquet;

	
	public ControleurDemarrer (JLabel lblTitre ,JButton btnCommencer, JButton btnCharger ,JLabel lblNom ,JTextField textFieldNom,JButton btnNom,JLabel lblNbBots ,JButton btn3Bots, JButton btn2Bots ,JLabel lblStrat ,JButton btnStrategieBase, JButton btnStrategieAvancee ,JLabel lblRegles ,JButton btnReglesStandard, JButton btnReglesVar1,JButton btnReglesVar2 ,ArrayList<Joueur> tabJoueur,Regle reglesPartie ,JFrame frame) {
		

	btnCommencer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			//On supprime les éléments qui composent la fenêtre
			frame.getContentPane().remove(btnCommencer);
			frame.getContentPane().remove(btnCharger);
			frame.getContentPane().remove(lblTitre);
			
			frame.getContentPane().add(lblNom);
			frame.getContentPane().add(textFieldNom);
			textFieldNom.setColumns(10);
			frame.getContentPane().add(btnNom);
			
			// On actualise la fenêtre
			frame.getContentPane().repaint();
		}
	});
	
	btnNom.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(textFieldNom.getText() != "") {
				tabJoueur.add(new JoueurPhysique(textFieldNom.getText()));
				
				frame.getContentPane().remove(lblNom);
				frame.getContentPane().remove(textFieldNom);
				frame.getContentPane().remove(btnNom);
				
				frame.getContentPane().add(lblNbBots);
				frame.getContentPane().add(btn3Bots);
				frame.getContentPane().add(btn2Bots);
				
				frame.getContentPane().repaint();
			}
		}
	});
	
	btn2Bots.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nbBots = 2;
			
			frame.getContentPane().remove(lblNbBots);
			frame.getContentPane().remove(btn3Bots);
			frame.getContentPane().remove(btn2Bots);
			
			frame.getContentPane().add(btnStrategieBase);
			frame.getContentPane().add(lblStrat);
			frame.getContentPane().add(btnStrategieAvancee);
			
			frame.getContentPane().repaint();
		}
	});
	
	btn3Bots.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nbBots = 3;

			frame.getContentPane().remove(lblNbBots);
			frame.getContentPane().remove(btn3Bots);
			frame.getContentPane().remove(btn2Bots);
			
			frame.getContentPane().add(btnStrategieBase);
			frame.getContentPane().add(lblStrat);
			frame.getContentPane().add(btnStrategieAvancee);
			
			frame.getContentPane().repaint();
		}
	});
	
	btnStrategieBase.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			difficulte = "simple";
			paquet = new Pioche(difficulte);
			ajouterBots(nbBots,tabJoueur,difficulte);
			
			frame.getContentPane().remove(btnStrategieBase);
			frame.getContentPane().remove(lblStrat);
			frame.getContentPane().remove(btnStrategieAvancee);
			
			frame.getContentPane().add(lblRegles);
			frame.getContentPane().add(btnReglesStandard);
			frame.getContentPane().add(btnReglesVar1);
			frame.getContentPane().add(btnReglesVar2);
			
			frame.getContentPane().repaint();
		}
	});
	
	btnStrategieAvancee.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			difficulte = "avancee";
			paquet = new Pioche(difficulte);
			ajouterBots(nbBots,tabJoueur,difficulte);
			
			frame.getContentPane().remove(btnStrategieBase);
			frame.getContentPane().remove(lblStrat);
			frame.getContentPane().remove(btnStrategieAvancee);
			
			frame.getContentPane().add(lblRegles);
			frame.getContentPane().add(btnReglesStandard);
			frame.getContentPane().add(btnReglesVar1);
			frame.getContentPane().add(btnReglesVar2);
			
			frame.getContentPane().repaint();
		}
	});
	
	
}
	
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
	
	public void ajouterBots(int nbBots, ArrayList<Joueur> tabJoueur,String strategie) {
		for(int i =1; i < nbBots; i++) {
			tabJoueur.add(new JoueurVirtuel("IA-"+i, strategie));
		}
	}
	
}


