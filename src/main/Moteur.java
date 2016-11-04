package main;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import gui.Grille;
import plugins.Gestionnaire_Plugins;
import plugins.TypePlugin;

/**
 * Moteur du jeu
 * 
 * @author Karl,Leonard
 *
 */
public class Moteur {

	/** Liste des robots */
	ArrayList<Robot> listeRobots = new ArrayList<>();

	/** Grille de jeu */
	Grille grille;

	/** Fenêtre de jeu */
	JFrame frame;

	/** Chemin vers le fichier pour stocker l'état des plugins */
	private final String PATH_TO_FILE = "src/ressources/test.txt";

	/** Gestionnaire des plugins */
	private Gestionnaire_Plugins gestionnairePlugins;

	/**
	 * Constructeur de la classe Moteur
	 * 
	 * @param nbRobots
	 *            nombre de robots à placer
	 */
	public Moteur(int nbRobots, int xGrille, int yGrille) {

		// - PREPARATION DU JEU :

		// 1) Initialisation des plugins
		initialisationDesPlugins();

		// 2) Instantiation de la grille de jeu
		creationDeLaGrilleDeJeu(xGrille, yGrille);

		// 3) Placement des robots
		placementDesRobots(nbRobots);

		// - DEBUT DU JEU :

		// Lancement du jeu
		gestionDesTours();
	}

	/**
	 * Méthode qui initialise tous les différents plugins du jeu
	 */
	private void initialisationDesPlugins() {
		// On instancie le gestionnaire de plugins
		gestionnairePlugins = new Gestionnaire_Plugins();

		// On appelle lectureFichier qui permet de renvoyer le
		// fichier sous forme d'arraylist de String
		ArrayList<String> resultatFichier = lectureFichier();

		//On instancie les plugins, et on a en retour les plugins activés
		ArrayList<String> pluginActiver = parserLigneFichier(resultatFichier);
		
		// Sauvegarde des plugins qui ont bien été instanciés
		sauvegardeEtatPlugin(pluginActiver);

	}

	/**
	 * Instancie les plugins et retourne une liste dans le meme format que le 
	 * fichier de persistance avec uniquement les plugins s'étant bien instanciés
	 * 
	 * @return ArrayList<String>
	 */
	private ArrayList<String> parserLigneFichier(ArrayList<String> resultatFichier) {
		// On créé l'arraylist des plugins qui sont bien instanciés
		ArrayList<String> pluginActivated = new ArrayList<String>();
		
		// On parcours ligne par ligne pour en suite parser
		for (String ligne : resultatFichier) {
			ArrayList<String> splitLigne = new ArrayList<String>(Arrays.asList(ligne.split(" ")));

			// Si le plugin a été sauvegardé à "true" on l'active
			if (Boolean.parseBoolean(splitLigne.get(2))) {
				// On récupère le type de plugin
				String type = splitLigne.get(1);
				TypePlugin typePlugin;
				if (type.equals("GRAPHISME")) {
					typePlugin = TypePlugin.GRAPHISME;
				} else if (type.equals("ATTAQUE")) {
					typePlugin = TypePlugin.ATTAQUE;
				} else if (type.equals("DEPLACEMENT")) {
					typePlugin = TypePlugin.DEPLACEMENT;
				} else {
					continue;
				}

				// On charge le plugin suivant son type choisi précédemment
				if (gestionnairePlugins.chargerPlugin(splitLigne.get(0), typePlugin)) {
					pluginActivated.add(ligne);
				}
			}
		}
		return pluginActivated;
	}

	/**
	 * Permet de retourner sous forme d'arraylist de string le contenu du
	 * fichier
	 * 
	 * @return ArrayList<String>
	 */
	private ArrayList<String> lectureFichier() {

		ArrayList<String> resultatFichier = new ArrayList<String>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATH_TO_FILE));
			String line = null;
			// Tant qu'il y a de nouvelles lignes on continue
			while ((line = br.readLine()) != null) {
				System.out.println("#" + line);
				resultatFichier.add(line);
			}

			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultatFichier;
	}

	/**
	 * Méthode qui crée un grille de jeu
	 */
	private void creationDeLaGrilleDeJeu(int xGrille, int yGrille) {
		grille = new Grille(xGrille, yGrille);

		// TEST : On place la grille dans une JFrame
		frame = new JFrame("RobotWar");
		frame.setContentPane(grille);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				// sauvegardeEtatPlugin();
				System.exit(0);
			}
		});

	}

	/**
	 * Méthode qui place les robots aléatoirement sur la grille
	 * 
	 * @param nbRobot
	 */
	private void placementDesRobots(int nbRobot) {
		// Liste des toutes les positions possibles :
		ArrayList<Point> listePositions = new ArrayList<>();

		// Remplissage de cette liste
		for (int row = 0; row < grille.getNblignesmax(); row++) {
			for (int col = 0; col < grille.getNbcolonnesmax(); col++) {
				listePositions.add(new Point(row, col));
			}
		}

		// Création des robots :
		for (int i = 1; i <= nbRobot; i++) {
			// Création d'un robot
			Robot robot = new Robot(gestionnairePlugins);
			robot.setIndice(i);

			// Choix de sa position de départ
			Random rand = new Random();
			// Un random dans la liste des positions
			Point positionChoisie = listePositions.get(rand.nextInt(listePositions.size()));

			// On ajoute le robot à la grille
			grille.deplacerRobot(robot, positionChoisie);

			// Ajout du robot à la liste des robots
			listeRobots.add(robot);

			// On enlève la position de la liste des positions possibles
			listePositions.remove(positionChoisie);
		}

	}

	/**
	 * Méthode qui s'occupe de la gestion des tours
	 */
	public void gestionDesTours() {

		boolean finDePartie = false;
		int compteur = 0;

		// Boucle pour chaque tour de jeu :
		while (finDePartie != true) {
			compteur++;
			frame.setTitle("RobotWar - Tours : " + compteur);
			// Pour chaque robots :
			for (Robot robot : listeRobots) {
				// Temps entre chaque tour
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Il faut que le robot soit vivant pour jouer
				if (!robot.isVivant()) {
					continue;
				}

				// On demande au robot de se déplacer
				robot.seDeplacer(grille);

				// On demande au robot d'attaquer une cible
				Robot cible = robot.attaquer(grille);

				// On regarde si le robot est mort
				gestionMortRobot(cible);
			}

			frame.repaint();

			finDePartie = VerifFinDePartie();
		}
		frame.repaint();
	}

	private boolean VerifFinDePartie() {
		int nbRobotVivant = 0;
		for (Robot robot : listeRobots) {
			if (robot.isVivant()) {
				nbRobotVivant += 1;
			}
		}

		if (nbRobotVivant == 1) {
			frame.setTitle("RobotWar - Fin de partie");
			return true;
		}

		return false;
	}

	private void gestionMortRobot(Robot cible) {
		if (cible != null) {
			// Si le robot est mort
			if (!cible.isVivant()) {
				// On le retire de la grille
				grille.retirerRobot(cible);
			}
		}
	}

	private void sauvegardeEtatPlugin(ArrayList<String> toWrite) {
		File f = new File(PATH_TO_FILE);
		PrintWriter writer;
		try {
			writer = new PrintWriter(f, "UTF-8");
			
			// On écrit ligne par ligne les plugins dans le fichier
			for(String ligne: toWrite){
				writer.println(ligne);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Moteur(2, 4, 4);
	}
}
