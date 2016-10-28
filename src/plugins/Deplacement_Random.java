package plugins;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import gui.Grille;
import main.Robot;

// TODO changer le nom
public class Deplacement_Random {

	/**
	 * Choisit un déplacement aléatoire
	 * 
	 * @param grille
	 * @param robot
	 * @return
	 */
	public Point choisirDeplacement(Grille grille, Robot robot) {
		// Position actuelle du robot
		Point posActuelle = robot.getPoint();
		int x = posActuelle.x;
		int y = posActuelle.y;

		// Nombre de points de mouvement du robot
		int pm = robot.getPtMouvement();

		// List des deplacements possibles
		ArrayList<Point> listDeplacements = getListeDeplacementsPossibles(x, y, pm, grille);

		// Choix du déplacement aléatoirement
		Random rand = new Random();
		Point positionChoisie = listDeplacements.get(rand.nextInt(listDeplacements.size()));

		return positionChoisie;

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
	public ArrayList<Point> getListeDeplacementsPossibles(int x, int y, int pm, Grille grille) {

		// Informations concernant la taille de la grille
		int nbColonneMax = Grille.getNbcolonnesmax();
		int nbLigneMax = Grille.getNblignesmax();

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
					if (grille.getElementsGrille()[row][col].getRobot() != null)
						continue;
				}
				// Un déplacement possible
				Point casePossible = new Point(row, col);

				// On l'ajoute à la liste
				listPoints.add(casePossible);
			}
		}
		return listPoints;
	}

}
