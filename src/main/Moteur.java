package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import gui.Grille;

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

	/**
	 * Constructeur de la classe Moteur
	 * 
	 * @param nbRobots
	 *            nombre de robots à placer
	 */
	public Moteur(int nbRobots, int xGrille, int yGrille) {

		// ----------------------------------
		// 1) Phase de mise en route du jeu :
		// ----------------------------------

		// Instantiation de la grille de jeu
		creationDeLaGrilleDeJeu(xGrille, yGrille);

		// Le moteur place les robots aléatoirement sur la grille
		placementDesRobots(nbRobots);

		// --------------------
		// 2) Le jeu commence :
		// --------------------

		// Démarrage des tours
		gestionDesTours();
	}

	/**
	 * Méthode qui crée un grille de jeu
	 */
	private void creationDeLaGrilleDeJeu(int xGrille, int yGrille) {
		grille = new Grille(xGrille, yGrille);

		// TEST : On place la grille dans une JFrame
		JFrame frame = new JFrame("Test de la classe Grille");
		frame.setContentPane(grille);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		for (int row = 0; row < Grille.getNblignesmax(); row++) {
			for (int col = 0; col < Grille.getNbcolonnesmax(); col++) {
				listePositions.add(new Point(row, col));
			}
		}

		// Création des robots :
		for (int i = 1; i <= nbRobot; i++) {
			// Création d'un robot
			Robot robot = new Robot();
			robot.setIndice(i);
			robot.setPtMouvement(1);
			robot.setPortee(1);
			robot.setVie(100);

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

		// Boucle pour chaque tour de jeu :
		while (finDePartie != true) {
			// System.out.println("Tour : " + (i + 1));

			// Temps entre chaque tour
			// try {
			// TimeUnit.SECONDS.sleep(1);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
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


			finDePartie = VerifFinDePartie();
		}

	}
	
	private boolean VerifFinDePartie(){
		int nbRobotVivant = 0;
		for (Robot robot : listeRobots) {
			if (robot.isVivant()) {
				nbRobotVivant += 1;
			}
		}

		if (nbRobotVivant == 1) {
			System.out.println("Fin de la partie");
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

	public static void main(String[] args) {
		new Moteur(2, 10, 10);
	}
}
