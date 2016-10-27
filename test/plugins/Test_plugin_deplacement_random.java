package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import gui.Grille;
import gui.Robot;

public class Test_plugin_deplacement_random {

	Deplacement_Random deplacement;
	Robot robot;
	Grille grille;

	@Before
	public void init_objets() {
		deplacement = new Deplacement_Random();
		robot = new Robot();
		grille = new Grille();

	}

	@Test
	public void deplacement_aléatoire_dun_robot() {
		// Position de départ du robot
		robot.setPoint(new Point(1, 1));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(1, 1));

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement());

		assertEquals("Nombre de déplacements possibles", 9, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}
	}

	@Test
	public void deplacement_aléatoire_dun_robot_qui_se_trouve_dans_un_coin() {
		// Position de départ du robot
		robot.setPoint(new Point(0, 9));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(0, 9));

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement());

		assertEquals("Nombre de déplacements possibles", 4, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_aléatoire_dun_robot_qui_se_trouve_dans_un_coin_et_a_deux_pm() {
		// Position de départ du robot
		robot.setPoint(new Point(0, 9));
		robot.setPtMouvement(2);
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(0, 9));

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement());

		assertEquals("Nombre de déplacements possibles", 9, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

}
