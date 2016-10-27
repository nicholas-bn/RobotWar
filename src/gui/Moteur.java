package gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

/**
 * Moteur du jeu
 * 
 * @author Karl,Leonard
 *
 */
public class Moteur {

	ArrayList<Robot> list = new ArrayList<>();

	Grille grille;
	Robot robot;

	public Moteur(int i) {

		// Instantiation de la grille de jeu
		creationDeLaGrilleDeJeu();

		// Création des robots
		for (int k = 0; k < i; k++) {
			robot = new Robot();
			robot.setIndice(k);
			int randomX = (int) (Math.random() * (Grille.getNbcolonnesmax() - 0));
			int randomY = (int) (Math.random() * (Grille.getNblignesmax() - 0));
			Point point = new Point(randomX, randomY);
			robot.setPoint(point);
			robot.setPtMouvement(1);
			robot.setPortee(1);
			grille.getElementsGrille()[randomX][randomY].setRobot(robot);
			list.add(robot);
		}

		// Démarrage des tours
		gestionDesTours();
	}

	/**
	 * Méthode qui crée un grille de jeu
	 */
	private void creationDeLaGrilleDeJeu() {
		grille = new Grille();

		// TEST : On place la grille dans une JFrame
		JFrame frame = new JFrame("Test de la classe Grille");
		frame.setContentPane(grille);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Méthode qui s'occupe de la gestion des tours
	 */
	public void gestionDesTours() {

		// Boucle pour chaque tour de jeu :
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Tour : " + (i + 1));
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Robot robot : list) {
				// On demande au robot de se déplacer
				robot.seDeplacer(grille);
				robot.attaquer(grille);
			}
		}

	}

	public static void main(String[] args) {

		Moteur m = new Moteur(5);

	}
}
