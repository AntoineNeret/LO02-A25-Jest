package Vue;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controleur.ControleurDemarrer;
import Modele.Carte;
import Modele.Joueur;
import Modele.Paquet;
import Modele.Partie;
import Modele.Regle;
import Modele.RegleSpecial01;
import Modele.RegleSpecial02;
import Modele.RegleStandard;

/**
 * The type Mon interface that represent the window.
 */
public class MonInterface {

	private JFrame frame;
	private JTextField textFieldNom;
	private ArrayList<Joueur> tabJoueur = new ArrayList<>();
	private JLabel lblRegles;
	private JLabel lblNom;
	private JButton btnNom;
	private JButton btnCommencer;
	private JButton btnCharger;
	private JButton btnReglesStandard;
	private JButton btnReglesVar1;
	private JButton btnReglesVar2;
	private Regle reglesPartie;
	

	private Partie moteurJeu = new Partie();
	private JLabel lblStrat;
	private JButton btnPhysique;
	private JButton btnSpectateur;
	private JButton btnStrategieBase;
	private JButton btnStrategieAvancee;
	private JLabel lblNbBots;
	private JButton btn2Bots;
	private JButton btn3Bots;
	
	private JPanel panelTrophee;
	private JPanel panelJest;
	
	private JPanel panelOffres;
	private static Carte carteCliquee = null;
	
	private JPanel panelChoixOffre;
	private static Carte carteFaceCachee = null;
	
	private static Carte carteChoisieOffre = null;

    /**
     * Launch the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonInterface window = new MonInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


    /**
     * Instantiates a new Mon interface.
     */
    public MonInterface() {
		initialize();
		

		this.moteurJeu.setGui(this); 

		new Thread(() -> {
			moteurJeu.main(null);
		}).start();
	}


    /**
     * Method that initialize every components and every listeners attached to them
     */
	private void initialize() {
		
		frame = new JFrame();
	    frame.setBounds(100, 100, 1000, 800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);

	    
	    frame = new JFrame();
	    frame.getContentPane().setBackground(new Color(255, 128, 0)); // Orange
	    frame.setBounds(100, 100, 1000, 800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null); 


	    panelTrophee = new JPanel();
	    panelTrophee.setBounds(100, 50, 800, 200); 
	    panelTrophee.setOpaque(false);
	    panelTrophee.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
	    frame.getContentPane().add(panelTrophee); 
	    panelChoixOffre = new JPanel();
		panelChoixOffre.setBounds(100, 300, 800, 400); 
		panelChoixOffre.setOpaque(false);
		panelChoixOffre.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10)); 
		frame.getContentPane().add(panelChoixOffre);
	    
	    panelJest = new JPanel();
	    panelJest.setBounds(100, 550, 800, 200);
	    panelJest.setOpaque(false);
	    panelJest.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
	    
	    panelOffres = new JPanel();
	    panelOffres.setBounds(50, 400, 900, 250); 
	    panelOffres.setOpaque(false);
	    panelOffres.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
	    frame.getContentPane().add(panelOffres);
	    
	    panelChoixOffre = new JPanel();
	    panelChoixOffre.setBounds(100, 300, 800, 300);
	    panelChoixOffre.setOpaque(false);
	    panelChoixOffre.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
	    frame.getContentPane().add(panelChoixOffre);
		
		lblRegles = new JLabel("Quelles règles ?");
	    lblRegles.setBounds(213, 79, 446, 63);
	    lblRegles.setFont(new Font("Tahoma", Font.PLAIN, 25));

	    btnReglesStandard = new JButton("Standard");
	    btnReglesStandard.setBounds(191, 227, 181, 54);
	    btnReglesStandard.addActionListener(e -> {
			
		    moteurJeu.setRegleChoisieGUI(1);
		});

	    btnReglesVar1 = new JButton("Variante 1");
	    btnReglesVar1.setBounds(288, 318, 278, 54);
	    btnReglesVar1.addActionListener(e -> {
		    moteurJeu.setRegleChoisieGUI(2);
		});

	    btnReglesVar2 = new JButton("Variante 2");
	    btnReglesVar2.setBounds(497, 225, 260, 54);
	    btnReglesVar2.addActionListener(e -> {
		    moteurJeu.setRegleChoisieGUI(3);
		});
	    
	    lblStrat = new JLabel("Quelle stratégie ?"); 
	    lblStrat.setBounds(148, 89, 597, 42);

	    btnStrategieBase = new JButton("Base");
	    btnStrategieBase.setBounds(480, 210, 200, 57);

	    btnStrategieAvancee = new JButton("Avancée");
	    btnStrategieAvancee.setBounds(197, 210, 205, 57);
	   
	    
	    lblNbBots = new JLabel("Combien de bots ?");
		lblNbBots.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNbBots.setBounds(295, 77, 231, 67);
		
		if(moteurJeu.type == 1) {
			btn2Bots = new JButton("2");
		}else {
			btn2Bots = new JButton("3");
		}
		btn2Bots.setBounds(295, 218, 60, 54);
		
		if(moteurJeu.type == 1) {
			btn3Bots = new JButton("3");
		}else {
			btn3Bots = new JButton("4");
		}
		btn3Bots.setBounds(444, 218, 60, 54);
		
		btn2Bots.addActionListener(e -> {
		    System.out.println("Signal : 2 bots choisis");
		    moteurJeu.setNbBotsChoisisGUI(2); 
		});

		btn3Bots.addActionListener(e -> {
		    System.out.println("Signal : 3 bots choisis");
		    moteurJeu.setNbBotsChoisisGUI(3); 
		});
		
		btnPhysique = new JButton("Joueur vs IA");
		btnPhysique.setBounds(191, 227, 181, 54); 

		btnSpectateur = new JButton("IA vs IA");
		btnSpectateur.setBounds(497, 225, 260, 54);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 0));
		frame.setBounds(100, 100, 897, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTitre = new JLabel("JEST");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblTitre.setBounds(22, 23, 132, 54);
		frame.getContentPane().add(lblTitre);


		btnCommencer = new JButton("Commencer une partie");
		btnCommencer.setBounds(22, 98, 181, 36);
		frame.getContentPane().add(btnCommencer);

		btnCharger = new JButton("Charger une partie");
		btnCharger.setBounds(22, 180, 181, 36);
		frame.getContentPane().add(btnCharger);


		lblNom = new JLabel("Quel est votre nom ?");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNom.setBounds(282, 92, 241, 36);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(257, 180, 289, 42);

		btnNom = new JButton("Valider");
		btnNom.setBounds(336, 273, 104, 27);


		JLabel lblNbBots = new JLabel("Combien de bots ?");
		lblNbBots.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNbBots.setBounds(295, 77, 231, 67);
		
		
		if(moteurJeu.type == 1) {
			JButton btn2Bots = new JButton("2");
			JButton btn3Bots = new JButton("3");
		}else {
			JButton btn2Bots = new JButton("3");
			JButton btn3Bots = new JButton("4");
		}
		btn2Bots.setBounds(295, 218, 60, 54);
		
		
		btn3Bots.setBounds(444, 218, 60, 54);

		JLabel lblStrat = new JLabel("Quelle stratégie ?");
		lblStrat.setBounds(148, 89, 597, 42);

		lblRegles = new JLabel("Quelles règles ?");
		lblRegles.setBounds(213, 79, 446, 63);
		
		JButton btnReglesStandard = new JButton("Standard");
		btnReglesStandard.setBounds(191, 227, 181, 54);
		
		JButton btnReglesVar1 = new JButton("Variante 1");
		btnReglesVar1.setBounds(288, 318, 278, 54);
		
		JButton btnReglesVar2 = new JButton("Variante 2");
		btnReglesVar2.setBounds(497, 225, 260, 54);
		
		ControleurDemarrer controleur = new ControleurDemarrer(this, moteurJeu,lblTitre, btnCommencer, btnCharger, lblNom, textFieldNom, btnNom, lblNbBots, btn3Bots, btn2Bots, lblStrat, btnStrategieBase, btnStrategieAvancee, lblRegles, btnReglesStandard, btnReglesVar1, btnReglesVar2, tabJoueur, reglesPartie, frame);


		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moteurJeu.notifierCommencer(); 
				afficherEcranModeJeu(); 
			}
		});

		btnPhysique.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		       moteurJeu.setModeJeuChoisiGUI(1);
		        
		        afficherEcranNom(); 
		    }
		});

		btnSpectateur.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        moteurJeu.setModeJeuChoisiGUI(2);
		        System.out.println("[GUI] Mode Spectateur sélectionné");
		        
		        afficherEcranRegles();
		    }
		});
		
		btnNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String nomSaisi = textFieldNom.getText();
		        if (!nomSaisi.trim().isEmpty()) {
		            
		            Partie.setNomDepuisInterface(nomSaisi);
		        }
		    }
		});
		
	}

    /**
     * Method to display the available game modes
     */
    public void afficherEcranModeJeu() {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	       
	        frame.getContentPane().add(btnPhysique);
	        frame.getContentPane().add(btnSpectateur);
	        
	        frame.revalidate();
	        frame.repaint();
	    });
	}

    /**
     * Method to display the game's title screen
     */
    public void afficherEcranNom() {
		SwingUtilities.invokeLater(() -> {
			frame.getContentPane().removeAll();
			frame.getContentPane().remove(btnCommencer);
			frame.getContentPane().remove(btnCharger);
			frame.getContentPane().add(lblNom);
			frame.getContentPane().add(textFieldNom);
			frame.getContentPane().add(btnNom);
			frame.revalidate();
			frame.repaint();
		});
	}

    /**
     * Method to display rules
     */
    public void afficherEcranRegles() {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	        
	        
	        if (lblRegles != null && btnReglesStandard != null) {
	            frame.getContentPane().add(lblRegles);
	            frame.getContentPane().add(btnReglesStandard);
	            frame.getContentPane().add(btnReglesVar1);
	            frame.getContentPane().add(btnReglesVar2);
	        } else {
	            System.out.println("Erreur : Les boutons de règles ne sont pas initialisés !");
	        }
	        
	        frame.revalidate();
	        frame.repaint();
	    });
	}

    /**
     * Method to display strategies
     */
    public void afficherEcranNiveau() {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	        
	        frame.getContentPane().add(lblStrat);
	        frame.getContentPane().add(btnStrategieBase);
	        frame.getContentPane().add(btnStrategieAvancee);
	        frame.revalidate();
	        frame.repaint();
	    });
	}

    /**
     * Method to display the bots number
     */
    public void afficherEcranBots() {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	        
	        frame.getContentPane().add(lblNbBots);
	        frame.getContentPane().add(btn2Bots);
	        frame.getContentPane().add(btn3Bots);
	        frame.revalidate();
	        frame.repaint();
	    });
	}

    /**
     * Method to display the trophies
     *
     * @param cartesTrophy the cartes trophy
     */
    public void afficherTrophees(List<Carte> cartesTrophy) {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	        
	        frame.getContentPane().add(panelTrophee);
	        frame.getContentPane().add(panelJest);
	        
	        panelTrophee.removeAll();
	        for (Carte c : cartesTrophy) {
	            String nomImg = c.getValue() + "_" + c.getColor() + ".jpg"; 
	            panelTrophee.add(creerLabelImage(nomImg, 120, 180));
	        }
	        
	        frame.revalidate();
	        frame.repaint();
	    });
	}

	private JLabel creerLabelImage(String nomFichier, int width, int height) {
	    try {
	        ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + nomFichier));
	        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        return new JLabel(new ImageIcon(img));
	    } catch (Exception e) {
	        return new JLabel("[Image manquante]");
	    }
	}

    /**
     * Method to create the cards icons
     *
     * @param c the card
     * @param w the width
     * @param h the height
     * @return the image icon
     */
    public ImageIcon creerIcone(Carte c, int w, int h) {
	    String nom = c.getVisibility() ? (c.getValue() + "_" + c.getColor() + ".jpg") : "back.png";
	    try {
	        java.net.URL url = getClass().getResource("/images/" + nom);
	        if (url != null) {
	            Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
	            return new ImageIcon(img);
	        }
	    } catch (Exception e) {}
	    return null;
	}

    /**
     * Method to display player's hand to make him create an offer
     *
     * @param mainJoueur the main joueur
     */
    public void demanderFaireOffre(List<Carte> mainJoueur) {
	    SwingUtilities.invokeLater(() -> {
	        frame.getContentPane().removeAll();
	        
	        
	        frame.getContentPane().add(panelTrophee);
	        frame.getContentPane().add(panelChoixOffre);

	        panelChoixOffre.removeAll();
	        carteFaceCachee = null;
	        
	        
	        //JLabel consigne = new JLabel("Cliquez sur la carte à mettre FACE CACHÉE");
	        //consigne.setFont(new Font("Tahoma", Font.BOLD, 18));
	        //consigne.setForeground(Color.WHITE);
	        //panelChoixOffre.add(consigne);

	        
	        for (Carte c : mainJoueur) {
	            c.setVisibility(true); 
	            
	            ImageIcon icone = creerIcone(c, 150, 220);
	            JButton btn = new JButton(icone);
	            
	            if (icone == null) {
	                btn.setText(c.toString()); 
	            }

	            btn.addActionListener(e -> {
	                carteFaceCachee = c;
	               
	            });
	            panelChoixOffre.add(btn);
	        }
	        
	        frame.revalidate();
	        frame.repaint();
	    });
	}

    /**
     * Method to remove offers from the screen
     */
    public void viderPanelOffre() {
	    SwingUtilities.invokeLater(() -> {
	        panelChoixOffre.removeAll();
	        panelChoixOffre.revalidate();
	        panelChoixOffre.repaint();
	    });
	}

    /**
     * Method to display every offers available
     *
     * @param listeJoueurs the liste joueurs
     */
    public void afficherOffresADisposition(List<Joueur> listeJoueurs) {
	    SwingUtilities.invokeLater(() -> {
	        panelChoixOffre.removeAll(); 
	        carteChoisieOffre = null;

	        for (Joueur j : listeJoueurs) {
	            
	            if (j.offre.cartes.size() == 2) {
	                for (Carte c : j.offre.cartes) {
	                    
	                    JButton btn = new JButton(creerIcone(c, 110, 160));
	                    
	                    btn.addActionListener(e -> {
	                        carteChoisieOffre = c;
	                        System.out.println("[GUI] Vous avez choisi la carte de " + j.getName());
	                    });
	                    panelChoixOffre.add(btn);
	                }
	            }
	        }
	        panelChoixOffre.revalidate();
	        panelChoixOffre.repaint();
	    });
	}

    /**
     * Gets carte choisie offre.
     *
     * @return the carte choisie offre
     */
    public Carte getCarteChoisieOffre() { return carteChoisieOffre; }

    /**
     * Sets carte choisie offre.
     *
     * @param c the c
     */
    public void setCarteChoisieOffre(Carte c) { carteChoisieOffre = c; }


    /**
     * Gets carte face cachee.
     *
     * @return the carte face cachee
     */
    public Carte getCarteFaceCachee() { return carteFaceCachee; }
}


