package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphics.PanelChoixRepertoire;
import graphics.PanelSelectionPlugins;
import main.Moteur;

@SuppressWarnings("serial")
public class VueMenuDuJeu extends JFrame {

	/** Titre de la fenetre */
	private static final String titreFenetre = "Menu du jeu";

	/** Panel principal */
	private JPanel panelPrincipal;

	/** Panel pour choisir le répertoire des plugins */
	private PanelChoixRepertoire panelChoixRep;

	/** Panel pour choisir les plugins à utiliser */
	private PanelSelectionPlugins panelSelectPlugins;

	/**
	 * Constructeur de la classe {@link VueMenuDuJeu}
	 */
	public VueMenuDuJeu() {
		super(titreFenetre);

		initialisationDuPanelPrincipal();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);

	}

	/**
	 * Méthode qui initialise le panelPrincipal et ses éléments
	 */
	private void initialisationDuPanelPrincipal() {
		// Création du panel principal
		panelPrincipal = new JPanel(new BorderLayout());

		// Ajout de ce panel à la JFrame
		setContentPane(panelPrincipal);

		initialisationDuPanelChoixRepertoire();

		initialisationDuPanelSelectionDesPlugins();

	}

	/**
	 * Méthode qui initialise le panel pour choisir un répertoire
	 */
	private void initialisationDuPanelChoixRepertoire() {
		panelChoixRep = new PanelChoixRepertoire(this);

		panelPrincipal.add(panelChoixRep, BorderLayout.NORTH);

	}

	/**
	 * Méthode qui initialise le panel pour choisir les plugins à utiliser
	 */
	private void initialisationDuPanelSelectionDesPlugins() {
		panelSelectPlugins = new PanelSelectionPlugins(this);

		panelPrincipal.add(panelSelectPlugins, BorderLayout.SOUTH);

	}

	/**
	 * Méthode qui modifie le panel de sélection des plugins quand l'utilisateur
	 * à choisi un répertoire
	 * 
	 * @param repertoireChoisi
	 */
	public void modifierSelectionPlugins(String repertoireChoisi) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode qui lance la partie
	 */
	public void lancerLaPartie() {

		// On ferme cette fenetre
		dispose();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// On lance le moteur de jeu
				new Moteur(5, 10, 10, false);

			}
		}).start();
		;

	}

}
