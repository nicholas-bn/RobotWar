package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import gui.Grille;
import gui.Robot;

public class Test_plugin_deplacement_random {

	@Test
	public void deplacement_aléatoire_dun_robot() {
		Deplacement_Random deplacement = new Deplacement_Random();

		// Instantiation d'un robot
		Robot robot = new Robot();
		robot.setPoint(new Point(1, 1));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(1, 1));

		Grille grille = new Grille();

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y);

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			System.out.println("Nouvelle position du robot : " + nouvellePosition);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

}
