package gui;

import java.awt.Point;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

/**
 * Moteur du jeu
 * 
 * @author Karl
 *
 */
public class Moteur {

	Robot r;
	Robot r2;
	Grille grille;

	public Moteur() {

		// Instantiation de la grille de jeu
		creationDeLaGrilleDeJeu();

		// Création du premier robot
		r = new Robot();
		int randomx = (int) (Math.random() * (Grille.getNbcolonnesmax() - 0));
		int randomy = (int) (Math.random() * (Grille.getNblignesmax() - 0));
		Point point = new Point(randomx, randomy);
		r.setPoint(point);
		r.setPtMouvement(1);
		grille.getElementsGrille()[randomx][randomy].setRobot(r);

		// Création du deuxième robot
		r2 = new Robot();
		randomx = (int) (Math.random() * (Grille.getNbcolonnesmax() - 0));
		randomy = (int) (Math.random() * (Grille.getNblignesmax() - 0));
		point = new Point(randomx, randomy);
		r2.setPoint(point);
		r2.setPtMouvement(1);
		grille.getElementsGrille()[randomx][randomy].setRobot(r2);

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
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// On demande au robot de se déplacer
			r.seDeplacer(grille);

			r2.seDeplacer(grille);

		}

	}

	public static void main(String[] args) {

		Moteur m = new Moteur();

	}
}
