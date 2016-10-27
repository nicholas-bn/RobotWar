package plugins;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import gui.Grille;
import gui.Robot;

public class Test_plugin_attaque_de_base {

	Attaque_de_Base attaque;
	Robot robotAttaquant;
	Robot robotVictime;
	Grille grille;
	
	@Before
	public void test() {
		attaque = new Attaque_de_Base();
		robotAttaquant = new Robot();
		robotVictime = new Robot();
		grille = new Grille();
	}

	@Test
	public void test_lorsque_le_robot_est_juste_en_dehors_de_la_portée(){
		
		// Initialisation du robot attaquant
		robotAttaquant.setPortee(2);
		robotAttaquant.setPoint(new Point(0, 0));
		grille.getElementsGrille()[0][0].setRobot(robotAttaquant);
		
		// Initialisation du robot se faisant attaquer
		robotVictime.setPortee(2);
		robotVictime.setPoint(new Point(3, 3));
		grille.getElementsGrille()[3][3].setRobot(robotVictime);
		
		// Le robot étant à la limite de la portée, il n'est pas pris en compte
		assertTrue(attaque.choisirCible(grille, robotAttaquant).equals(null));
	}
	
	@Test
	public void test_lorsque_le_robot_est_juste_à_la_limite_de_la_portée(){

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(2);
		robotAttaquant.setPoint(new Point(0, 0));
		grille.getElementsGrille()[0][0].setRobot(robotAttaquant);

		// Initialisation du robot se faisant attaquer
		robotVictime.setPortee(2);
		robotVictime.setPoint(new Point(2, 2));
		grille.getElementsGrille()[2][2].setRobot(robotVictime);
		
		// Le robot étant le seul à être à portée est attaqué
		assertTrue(attaque.choisirCible(grille, robotAttaquant).equals(robotVictime));
	}
	
}
