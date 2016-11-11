package plugins.deplacement;

import java.awt.Point;
import java.util.ArrayList;

import graphics.Grille;
import main.Robot;

public class Deplacement_Intelligent {

	/**
	 * Cherchera le robot le plus proche de sa position et renverra une position
	 * permettant à notre robot de s'en approcher le plus possible
	 * 
	 * @param grille
	 * @param robot
	 * @return Point
	 */
	public Point choisirDeplacement(Grille grille, Robot robot) {

		// List des deplacements possibles
		ArrayList<Point> listDeplacements = getListeDeplacementsPossibles(robot, grille);

		// Position du robot vers lequel on se dirige
		Point positionRobotPlusProche = chercherLeRobotLePlusProche(grille, robot);

		// Position du déplacement choisi
		Point positionChoisie = choixDeLaCaseDeDéplacement(listDeplacements, positionRobotPlusProche);

		return positionChoisie;

	}

	/**
	 * Méthode choisit la case où se déplacer
	 * 
	 * @param grille
	 * @param robot
	 * @return
	 */
	private Point choixDeLaCaseDeDéplacement(ArrayList<Point> listePoint, Point positionRobot) {

		// On retient le point retenu
		Point pointRetenu = null;

		// On retient la distance entre les deux
		int distancePointEtRobot = 10000;

		// On parcours la liste des points
		for (Point p : listePoint) {
			int x;

			// Pour chaque point de la liste, on vérifie la distance avec le
			// point du robot, si la distance est inférieur à la précédente, on
			// l'enregistre

			if ((x = distanceNoDiagonal(p.x, positionRobot.x, p.y, positionRobot.y)) < distancePointEtRobot) {
				distancePointEtRobot = x;
				pointRetenu = p;
			}
		}

		return pointRetenu;
	}

	/**
	 * Renvoi la position du robot le plus proche
	 * 
	 * @param grille
	 * @param robot
	 * @return
	 */
	private Point chercherLeRobotLePlusProche(Grille grille, Robot robot) {

		// Point que l'on retourne
		Point pointRobot = null;

		// Initialisation de la distance au max
		int distanceSauvegarder = grille.getNbcolonnesmax() * 10;

		// On parcours la grille
		for (int x = 0; x < grille.getNblignesmax(); x++) {
			for (int y = 0; y < grille.getNbcolonnesmax(); y++) {
				Robot r;

				// On récupère le robot dans le point (x,y)
				if ((r = grille.getRobotFromPoint(new Point(x, y))) != null) {

					// Si ce n'est pas le même robot
					if (!r.equals(robot)) {
						int distance;

						// Si la distance est inférieur à celle prédédemment
						// enregistré
						if ((distance = distanceNoDiagonal(robot.getPosition().x, x, robot.getPosition().y,
								y)) < distanceSauvegarder) {
							distanceSauvegarder = distance;
							pointRobot = r.getPosition();
						}
					}
				}
			}
		}
		return pointRobot;
	}

	/**
	 * Retourne la liste des déplacements possibles du robot <br>
	 * 
	 * On laisse la possibilité au robot de ne pas se déplacer (de se déplacer
	 * sur sa position actuelle)
	 *
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Point> getListeDeplacementsPossibles(Robot robot, Grille grille) {

		// Position actuelle du robot
		Point posActuelle = robot.getPosition();
		int x = posActuelle.x;
		int y = posActuelle.y;

		// Nombre de points de mouvement du robot
		int pm = robot.getPtMouvement();

		// Informations concernant la taille de la grille
		int nbColonneMax = grille.getNbcolonnesmax();
		int nbLigneMax = grille.getNblignesmax();

		// Liste des différents points (déplacement) possible
		ArrayList<Point> listPoints = new ArrayList<>();

		// Calcul de toutes les positions possibles :
		// - en foncton de la pos de départ
		// - et du nombre de pm
		for (int row = x - pm; row <= x + pm; row++) {

			for (int col = y - pm; col <= y + pm; col++) {

				// Cas où le robot se trouve dans un coin
				if (row < 0 || col < 0 || row >= nbLigneMax || col >= nbColonneMax)
					continue;

				// Si on n'est pas sur le point où se trouve le robot
				// initialement
				if (!(row == x && col == y)) {
					// On regarde s'il n'y a pas déja un robot sur cette case
					if (grille.getRobotFromPoint(new Point(row, col)) != null)
						continue;
				}

				if (isAtReachNoDiagonal(row, x, col, y, pm)) {
					// Un déplacement possible
					Point casePossible = new Point(row, col);

					// On l'ajoute à la liste
					listPoints.add(casePossible);
				}
			}
		}
		return listPoints;
	}

	/**
	 * Définie si un robot est à portée d'une case
	 * 
	 * @param x1
	 *            - Position X de la case à testé
	 * @param x2
	 *            - Position X du robot
	 * @param y1
	 *            - Position Y de la case à testé
	 * @param y2
	 *            - Position Y du robot
	 * @param pm
	 *            - Correspond aux points de mouvement du robot
	 * 
	 * @return boolean - true si à portée
	 */
	private boolean isAtReachNoDiagonal(int x1, int x2, int y1, int y2, int portée) {

		// Si l'addition des deux différence (X,Y) est inférieur ou égal à la
		// portée cela signifie que le robot est à portée de mouvement
		if (distanceNoDiagonal(x1, x2, y1, y2) <= portée)
			return true;
		else
			return false;
	}

	/**
	 * Renvoi sous forme d'entier la distance entre 2 cases
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @return int
	 */
	private int distanceNoDiagonal(int x1, int x2, int y1, int y2) {

		int differenceX = 0;
		int differenceY = 0;

		// On récupère la différence entre les X
		if (x1 < x2)
			differenceX = x2 - x1;
		else
			differenceX = x1 - x2;

		// On récupère la différence entre les Y
		if (y1 < y2)
			differenceY = y2 - y1;
		else
			differenceY = y1 - y2;

		return differenceX + differenceY;
	}

}
