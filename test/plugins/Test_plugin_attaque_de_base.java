package plugins;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import gui.Grille;
import main.Robot;

public class Test_plugin_attaque_de_base {

	Attaque_de_Base attaque;
	Robot robotAttaquant;
	Robot robotVictimeFaible;
	Robot robotVictime;
	Grille grille;

	@Before
	public void test() {
		attaque = new Attaque_de_Base();
		robotAttaquant = new Robot();
		robotVictimeFaible = new Robot();
		robotVictime = new Robot();
		grille = new Grille(10, 10);
	}

	@Test
	public void test_lorsque_le_robot_est_juste_en_dehors_de_la_portée() {

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(2);
		grille.deplacerRobot(robotAttaquant, new Point(0, 0));

		// Initialisation du robot se faisant attaquer
		robotVictime.setPortee(2);
		grille.deplacerRobot(robotVictime, new Point(3, 3));

		// Le robot étant à la limite de la portée, il n'est pas pris en compte
		assertTrue(attaque.choisirCible(grille, robotAttaquant) == null);
	}

	@Test
	public void test_lorsque_le_robot_est_juste_à_la_limite_de_la_portée() {

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(2);
		grille.deplacerRobot(robotAttaquant, new Point(0, 0));

		// Initialisation du robot se faisant attaquer
		robotVictime.setPortee(2);
		grille.deplacerRobot(robotVictime, new Point(2, 2));

		// Le robot étant le seul à être à portée est attaqué
		assertTrue(attaque.choisirCible(grille, robotAttaquant).equals(robotVictime));
	}

	@Test
	public void test_que_le_bon_robot_est_choisi_lorsque_il_y_a_plusieurs_robots() {

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(4);
		grille.deplacerRobot(robotAttaquant, new Point(0, 0));

		// Initialisation du robot se faisant attaquer ayant le plus de vie
		robotVictime.setVie(100);
		grille.deplacerRobot(robotVictime, new Point(2, 2));

		// Initialisation du robot se faisant attaquer ayant le moins de vie
		robotVictimeFaible.setVie(90);
		grille.deplacerRobot(robotVictimeFaible, new Point(3, 3));

		// Le robot étant le seul à être à portée est attaqué
		assertTrue(attaque.choisirCible(grille, robotAttaquant).equals(robotVictimeFaible));
	}
}
