package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;

import Controleur.ControleurDemarrer;
import Modele.Joueur;
import Modele.JoueurPhysique;
import Modele.Partie;
import Modele.Pioche;
import Modele.Regle;
import Modele.RegleSpecial01;
import Modele.RegleSpecial02;
import Modele.RegleStandard;
import Modele.VisitorScore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

public class InterfaceDebut {

	private JFrame frame;
	private JTextField textFieldNom;
	private ArrayList<Joueur> tabJoueur = new ArrayList<>();
	private JLabel lblRegles;
	private Regle reglesPartie;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDebut window = new InterfaceDebut();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceDebut() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Partie partie = new Partie();
		
		frame = new JFrame();
		frame.setBackground(new Color(240, 240, 240));
		frame.getContentPane().setBackground(new Color(255, 128, 0));
		frame.setBounds(100, 100, 897, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitre = new JLabel("JEST");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblTitre.setBounds(22, 23, 132, 54);
		
		JButton btnCharger = new JButton("Charger une partie");
		btnCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCharger.setForeground(Color.BLACK);
		btnCharger.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCharger.setBackground(new Color(255, 255, 255));
		btnCharger.setBounds(22, 180, 181, 36);
		
		JLabel lblNbBots = new JLabel("Combien de bots ?");
		lblNbBots.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNbBots.setBounds(295, 77, 231, 67);
		
		JButton btn2Bots = new JButton("2");
		btn2Bots.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btn2Bots.setBounds(295, 218, 60, 54);
		
		JButton btn3Bots = new JButton("3");
		btn3Bots.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btn3Bots.setBounds(444, 218, 60, 54);
		
		JButton btnCommencer = new JButton("Commencer une partie");
		btnCommencer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCommencer.setBackground(new Color(255, 255, 255));
		btnCommencer.setForeground(new Color(0, 0, 0));
		btnCommencer.setBounds(22, 98, 181, 36);
		
		JLabel lblNom = new JLabel("Quel est votre nom ?");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNom.setBounds(282, 92, 241, 36);
		
		textFieldNom = new JTextField();
		textFieldNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNom.setBounds(257, 180, 289, 42);

		JButton btnStrategieBase = new JButton("Stratégie de base");
		btnStrategieBase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStrategieBase.setBounds(480, 210, 200, 57);
		

		JButton btnStrategieAvancee = new JButton("Stratégie avancée");
		btnStrategieAvancee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStrategieAvancee.setBounds(197, 210, 205, 57);
		
		JButton btnNom = new JButton("Valider");
		btnNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNom.setBounds(336, 273, 104, 27);
		
		JLabel lblStrat = new JLabel("Quelle stratégie voulez-vous que les bots utilisent ?");
		lblStrat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStrat.setBounds(148, 89, 597, 42);
		
		lblRegles = new JLabel("Avec quelles règles voulez-vous jouer ?");
		lblRegles.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRegles.setBounds(213, 79, 446, 63);
		
		
		JButton btnReglesStandard = new JButton("Règles standard");
		btnReglesStandard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReglesStandard.setBounds(191, 227, 181, 54);
		
		
		JButton btnReglesVar1 = new JButton("1ère variante de règles");
		btnReglesVar1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReglesVar1.setBounds(288, 318, 278, 54);
		
		
		JButton btnReglesVar2 = new JButton("2ème variante de règles");
		btnReglesVar2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReglesVar2.setBounds(497, 225, 260, 54);

		frame.getContentPane().add(lblTitre);
		
		frame.getContentPane().add(btnCommencer);
		
		frame.getContentPane().add(btnCharger);
		
		
		
		ControleurDemarrer controleur = new ControleurDemarrer(lblTitre, btnCommencer, btnCharger , lblNom, textFieldNom,btnNom,lblNbBots,btn3Bots,btn2Bots,lblStrat,btnStrategieBase,btnStrategieAvancee,lblRegles,btnReglesStandard,btnReglesVar1,btnReglesVar2,tabJoueur,reglesPartie ,frame);
		

		
		btnReglesVar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(lblRegles);
				frame.getContentPane().remove(btnReglesStandard);
				frame.getContentPane().remove(btnReglesVar1);
				frame.getContentPane().remove(btnReglesVar2);
				controleur.tours(reglesPartie = new RegleSpecial02(),tabJoueur,frame);

			}
		});
		
		btnReglesVar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(lblRegles);
				frame.getContentPane().remove(btnReglesStandard);
				frame.getContentPane().remove(btnReglesVar1);
				frame.getContentPane().remove(btnReglesVar2);
				controleur.tours(reglesPartie = new RegleSpecial01(),tabJoueur,frame);

			}
		});
		
		btnReglesStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(lblRegles);
				frame.getContentPane().remove(btnReglesStandard);
				frame.getContentPane().remove(btnReglesVar1);
				frame.getContentPane().remove(btnReglesVar2);
				controleur.tours(reglesPartie = new RegleStandard(),tabJoueur,frame);

			}
		});
		
	}
	
}
