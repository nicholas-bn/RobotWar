package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import gui.Grille;
import main.Robot;
import plugins.deplacement.Deplacement_Random;

public class Test_plugin_deplacement_random {

	Deplacement_Random deplacement;
	Robot robot;
	Grille grille;

	@Before
	public void init_objets() {
		deplacement = new Deplacement_Random();
		robot = new Robot();
		grille = new Grille(10, 10);

	}

	@Test
	public void deplacement_aléatoire_dun_robot() {
		// Position de départ du robot
		robot.setPoint(new Point(1, 1));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(1, 1));

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement(), grille);

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
				robot.getPoint().y, robot.getPtMouvement(), grille);

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
				robot.getPoint().y, robot.getPtMouvement(), grille);

		assertEquals("Nombre de déplacements possibles", 9, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_aléatoire_dun_robot_avec_un_autre_robot_sur_la_grille() {
		// Position de départ du robot
		robot.setPoint(new Point(0, 0));
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(0, 0));

		// On crée un deuxieme robot à coté du premier
		Robot r2 = new Robot();
		// On le place sur la grille
		grille.deplacerRobot(r2, new Point(0, 1));

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement(), grille);

		assertEquals("Nombre de déplacements possibles", 3, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_aléatoire_dun_robot_entoure_par_dautres_robots() {
		// Position de départ du robot
		robot.setPoint(new Point(0, 0));
		assertEquals("Position initiale du robot", robot.getPoint(), new Point(0, 0));

		// On crée un deuxieme robot que je place tout autour du robot 1
		Robot r2 = new Robot();
		r2.setPoint(new Point(0, 1));
		// On le place sur la grille
		grille.getElementsGrille()[0][1].setRobot(r2);
		grille.getElementsGrille()[1][1].setRobot(r2);
		grille.getElementsGrille()[1][0].setRobot(r2);

		// Liste des déplacements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot.getPoint().x,
				robot.getPoint().y, robot.getPtMouvement(), grille);

		assertEquals("Nombre de déplacements possibles", 1, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertEquals("Nouvelle position", nouvellePosition, new Point(0, 0));
		}

	}

}
