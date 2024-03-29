package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import graphics.Grille;
import main.Robot;
import plugins.deplacement.Deplacement_Random;

public class Test_plugin_deplacement_random {

	Deplacement_Random deplacement;
	Robot robot;
	Grille grille;
	private Gestionnaire_Plugins gestionnairePlugins;

	@Before
	public void init_objets() {
		gestionnairePlugins = new Gestionnaire_Plugins();

		// Chargement des plugins GRAPHISME :
		gestionnairePlugins.chargerPlugin("plugins.graphisme.Graphisme_de_Base", TypePlugin.GRAPHISME);
		gestionnairePlugins.chargerPlugin("plugins.graphisme.Barre_de_vie", TypePlugin.GRAPHISME);

		// Chargement du plugin ATTAQUE :
		gestionnairePlugins.chargerPlugin("plugins.attaque.Attaque_de_Base", TypePlugin.ATTAQUE);

		// Chargement du plugin DEPLACEMENT :
		gestionnairePlugins.chargerPlugin("plugins.deplacement.Deplacement_Random", TypePlugin.DEPLACEMENT);

		deplacement = new Deplacement_Random();
		robot = new Robot(gestionnairePlugins);
		grille = new Grille(10, 10);

	}

	@Test
	public void deplacement_al�atoire_dun_robot_avec_un_pm() {
		// Position de d�part du robot
		robot.setPosition(new Point(5, 5));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(5, 5));

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 5, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}
	}

	@Test
	public void deplacement_al�atoire_dun_robot_avec_deux_pm() {
		// Position de d�part du robot
		robot.setPosition(new Point(5, 5));
		robot.setPtMouvement(2);
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(5, 5));

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 13, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}
	}

	@Test
	public void deplacement_al�atoire_dun_robot_qui_se_trouve_dans_un_coin() {
		// Position de d�part du robot
		robot.setPosition(new Point(0, 9));
		robot.setPtMouvement(1);
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(0, 9));

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 3, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_al�atoire_dun_robot_qui_se_trouve_dans_un_coin_et_a_deux_pm() {
		// Position de d�part du robot
		robot.setPosition(new Point(0, 9));
		robot.setPtMouvement(2);
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(0, 9));

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 6, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_al�atoire_dun_robot_avec_un_autre_robot_sur_la_grille() {
		// Position de d�part du robot
		robot.setPosition(new Point(0, 0));
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(0, 0));

		// On cr�e un deuxieme robot � cot� du premier
		Robot r2 = new Robot(gestionnairePlugins);
		// On le place sur la grille
		grille.deplacerRobot(r2, new Point(0, 1));

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 2, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertTrue("La nouvelle position est correcte", listDeplacementsPossibles.contains(nouvellePosition));
		}

	}

	@Test
	public void deplacement_al�atoire_dun_robot_entoure_par_dautres_robots() {
		// Position de d�part du robot
		robot.setPosition(new Point(0, 0));
		assertEquals("Position initiale du robot", robot.getPosition(), new Point(0, 0));

		// On cr�e un deuxieme robot que je place tout autour du robot 1
		Robot r2 = new Robot(gestionnairePlugins);
		r2.setPosition(new Point(0, 1));
		// On le place sur la grille
		grille.getElementsGrille()[0][1].setRobot(r2);
		grille.getElementsGrille()[1][1].setRobot(r2);
		grille.getElementsGrille()[1][0].setRobot(r2);

		// Liste des d�placements possibles du robot
		ArrayList<Point> listDeplacementsPossibles = deplacement.getListeDeplacementsPossibles(robot, grille);

		assertEquals("Nombre de d�placements possibles", 1, listDeplacementsPossibles.size());

		for (int i = 0; i < 10; i++) {
			Point nouvellePosition = deplacement.choisirDeplacement(grille, robot);
			assertEquals("Nouvelle position", nouvellePosition, new Point(0, 0));
		}

	}

}
