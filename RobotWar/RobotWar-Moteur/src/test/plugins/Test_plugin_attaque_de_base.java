package plugins;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import graphics.Grille;
import main.Robot;
import plugins.attaque.Attaque_de_Base;

public class Test_plugin_attaque_de_base {

	Attaque_de_Base attaque;
	Robot robotAttaquant;
	Robot robotVictimeFaible;
	Robot robotVictime;
	Grille grille;

	@Before
	public void test() {
		Gestionnaire_Plugins gestionnairePlugins = new Gestionnaire_Plugins();

		// Chargement des plugins GRAPHISME :
		gestionnairePlugins.chargerPlugin("plugins.graphisme.Graphisme_de_Base", TypePlugin.GRAPHISME);
		gestionnairePlugins.chargerPlugin("plugins.graphisme.Barre_de_vie", TypePlugin.GRAPHISME);

		// Chargement du plugin ATTAQUE :
		gestionnairePlugins.chargerPlugin("plugins.attaque.Attaque_de_Base", TypePlugin.ATTAQUE);

		// Chargement du plugin DEPLACEMENT :
		gestionnairePlugins.chargerPlugin("plugins.deplacement.Deplacement_Random", TypePlugin.DEPLACEMENT);
		
		attaque = new Attaque_de_Base();
		robotAttaquant = new Robot(gestionnairePlugins);
		robotVictimeFaible = new Robot(gestionnairePlugins);
		robotVictime = new Robot(gestionnairePlugins);
		grille = new Grille(10, 10);
	}

	@Test
	public void test_lorsque_le_robot_est_juste_en_dehors_de_la_port�e() {

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(2);
		grille.deplacerRobot(robotAttaquant, new Point(0, 0));

		// Initialisation du robot se faisant attaquer
		grille.deplacerRobot(robotVictime, new Point(3, 3));

		// Le robot �tant � la limite de la port�e, il n'est pas pris en compte
		assertTrue(attaque.choisirCible(grille, robotAttaquant) == null);
	}

	@Test
	public void test_lorsque_le_robot_est_juste_�_la_limite_de_la_port�e() {

		// Initialisation du robot attaquant
		robotAttaquant.setPortee(4);
		grille.deplacerRobot(robotAttaquant, new Point(0, 0));

		// Initialisation du robot se faisant attaquer
		grille.deplacerRobot(robotVictime, new Point(2, 2));

		// Le robot �tant le seul � �tre � port�e est attaqu�
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
		grille.deplacerRobot(robotVictimeFaible, new Point(1, 1));

		// Le robot �tant le seul � �tre � port�e est attaqu�
		assertTrue(attaque.choisirCible(grille, robotAttaquant).equals(robotVictimeFaible));
	}
}
