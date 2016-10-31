package plugins.attaque;

import java.awt.Point;
import java.util.ArrayList;

import gui.Grille;
import main.Robot;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */

public class Attaque_de_Base {

	private final int degats = 50;

	/**
	 * Choisit une cible
	 * 
	 * @param grille
	 * @param robot
	 * @return Robot
	 */
	public Robot choisirCible(Grille grille, Robot robot) {

		// On récupere la liste des robots attaquable
		ArrayList<Robot> listRobotAttaquable = getListeAttaquesPossibles(robot, grille);
		int tailleArray = listRobotAttaquable.size();
		if (tailleArray != 0) {
			// On définit le premier robot comme la cible
			Robot r = listRobotAttaquable.get(0);

			// Si la liste a plus de deux robots on parcours
			if (tailleArray > 1) {
				for (int i = 1; i < tailleArray; i++) {
					if (listRobotAttaquable.get(i).getVie() < r.getVie())
						r = listRobotAttaquable.get(i);
				}
			}
			// Le robot subit des dégats
			r.setVie(r.getVie() - degats);
			return r;
		} else
			return null;
	}

	/**
	 * Retourne la liste des robots dans la portée du robot attaquant <br>
	 * 
	 * @param x
	 * @param y
	 * @param portée
	 * @return ArrayList<Robot>
	 */

	private ArrayList<Robot> getListeAttaquesPossibles(Robot r, Grille grille) {

		// On récupère les valeurs des attributs du Robot
		int x = r.getPosition().x;
		int y = r.getPosition().y;
		int portée = r.getPortee();

		// Informations concernant la taille de la grille
		int nbColonneMax = grille.getNbcolonnesmax();
		int nbLigneMax = grille.getNblignesmax();

		// On instancie la liste de robots à retourner
		ArrayList<Robot> listRobot = new ArrayList<Robot>();

		// On parcours la zone entourant le robot et si il y a un robot on
		// ajoute
		for (int row = x - portée; row <= x + portée; row++) {
			for (int col = y - portée; col <= y + portée; col++) {

				// Cas où le robot se trouve dans un coin
				if (row < 0 || col < 0 || row >= nbLigneMax || col >= nbColonneMax)
					continue;

				if (row == x && col == y)
					continue;

				// On regarde s'il y a un robot sur cette case
				if (grille.getRobotFromPoint(new Point(row, col)) != null) {
					// On vérifie qu'il soit à portée
					if (isAtReachNoDiagonal(row, x, col, y, r.getPortee())) {
						listRobot.add(grille.getRobotFromPoint(new Point(row, col)));
					}
				}
			}
		}
		return listRobot;
	}

	/**
	 * Définie si un robot est portée ou non
	 * 
	 * @param x1
	 *            - Position X du robot visé
	 * @param x2
	 *            - Position X du robot attaquant
	 * @param y1
	 *            - Position Y du robot visé
	 * @param y2
	 *            - Position Y du robot attaquant
	 * @param portée
	 *            - Correspond à la portée du robot attaquant
	 * 
	 * @return boolean - true si à portée
	 */
	private boolean isAtReachNoDiagonal(int x1, int x2, int y1, int y2, int portée) {

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

		// Si l'addition des deux différence (X,Y) est inférieur ou égal à la
		// portée cela signifie que le robot est à portée de tir
		if (differenceX + differenceY <= portée)
			return true;
		else
			return false;
	}
}
