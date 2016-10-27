package plugins;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import gui.Grille;
import gui.Robot;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */

public class Attaque_de_Base {

	/**
	 * Choisit une cible
	 * 
	 * @param grille
	 * @param robot
	 * @return Robot
	 */
	public Robot choisirCible(Grille grille, Robot robot) {

		// On récupere la liste des robots attaquable
		ArrayList<Robot> listRobotAttaquable = getListeAttaquesPossibles(robot.getPoint().x, robot.getPoint().y,
				robot.getPortee(), grille);
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

	public ArrayList<Robot> getListeAttaquesPossibles(int x, int y, int portée, Grille grille) {

		// Informations concernant la taille de la grille
		int nbColonneMax = Grille.getNbcolonnesmax();
		int nbLigneMax = Grille.getNblignesmax();

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
				if (grille.getElementsGrille()[row][col].getRobot() != null)
					listRobot.add(grille.getElementsGrille()[row][col].getRobot());
			}
		}
		return listRobot;
	}
}
