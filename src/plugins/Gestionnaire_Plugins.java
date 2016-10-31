package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import gui.Case;
import gui.Grille;
import main.Robot;
import plugins.attaque.Attaque_de_Base;
import plugins.deplacement.Deplacement_Random;
import plugins.graphisme.Graphisme_de_Base;

/**
 * La classe qui permet de charger et d'utiliser les différents plugins
 * 
 * @author Karl
 *
 */
public class Gestionnaire_Plugins {

	/** L'instance du plugin d'attaque */
	Attaque_de_Base pluginAttaque;
	/** L'instance du plugin de déplacement */
	Deplacement_Random pluginDeplacement;
	/** La liste des instances des plugins graphismes */
	ArrayList<Graphisme_de_Base> listPluginsGraphisme;

	/**
	 * Constructeur de la classe {@link Gestionnaire_Plugins}
	 */
	public Gestionnaire_Plugins() {
		listPluginsGraphisme = new ArrayList<Graphisme_de_Base>();
	}

	/**
	 * Méthode qui charge un plugin
	 * 
	 * @param nom
	 *            nom du plugin
	 * @param typePlugin
	 *            type du plugin
	 */
	public void chargerPlugin(String nom, TypePlugin typePlugin) {
		try {
			// On charge la classe
			Class<?> pluginClass = Class.forName(nom);

			// Si c'est un plugin d'attaque :
			if (typePlugin == TypePlugin.ATTAQUE) {
				// On stocke le plugin d'attaque
				pluginAttaque = (Attaque_de_Base) pluginClass.newInstance();
			}

			// Si c'est un plugin de déplacement :
			if (typePlugin == TypePlugin.DEPLACEMENT) {
				// On stocke le plugin de déplacement
				pluginDeplacement = (Deplacement_Random) pluginClass.newInstance();
			}

			// Si c'est un plugin de graphisme :
			if (typePlugin == TypePlugin.GRAPHISME) {
				// On stocke le plugin Graphisme avec les autres
				Graphisme_de_Base plugin = (Graphisme_de_Base) pluginClass.newInstance();

				// On l'ajoute dans la liste des plugins Graphisme
				listPluginsGraphisme.add(plugin);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui retourne une couleur
	 * 
	 * @return
	 */
	public Color getCouleurRobot() {
		// La méthode du plugin qui permet de choisir la couleur du robot
		Method m;
		try {
			// On demande à un des plugins graphisme la couleur
			m = listPluginsGraphisme.get(0).getClass().getMethod("getCouleur");
			Color couleur = (Color) m.invoke(listPluginsGraphisme.get(0));
			return couleur;

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return Color.black;

	}

	/**
	 * Méthode qui permet au robot d'attaquer un autre robot
	 * 
	 * @param grille
	 */
	public Robot attaquer(Grille grille, Robot robot) {
		try {
			// La méthode du plugin qui permet de choisir une cible
			Method m = pluginAttaque.getClass().getMethod("choisirCible", Grille.class, Robot.class);

			// Cette méthode retourne un robot "cible"
			Robot robotCible = (Robot) m.invoke(pluginAttaque, grille, robot);

			// On retourne le robot choisit (null si aucun)
			return robotCible;

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		// Le robot n'attaque pas
		return null;
	}

	/**
	 * Méthode qui permet au robot de se déplacer sur la grille
	 * 
	 * @param grille
	 */
	public Point seDeplacer(Grille grille, Robot robot) {
		try {
			// La méthode du plugin qui permet de choisir un déplacement
			Method m = pluginDeplacement.getClass().getMethod("choisirDeplacement", Grille.class, Robot.class);

			// Point choisie par le plugin
			Point posChoisie = (java.awt.Point) m.invoke(pluginDeplacement, grille, robot);

			return posChoisie;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		// On retourne sa position initiale
		return robot.getPosition();
	}

	/**
	 * Méthode qui permet de dessiner le robot
	 * 
	 * @param g
	 * @param caseRobot
	 */
	public void dessiner(Graphics g, Case caseRobot) {
		try {
			for (Graphisme_de_Base plugin : listPluginsGraphisme) {
				Method m = plugin.getClass().getMethod("dessiner", Case.class, Graphics.class);

				// Méthode qui va décider elle même le robot
				m.invoke(plugin, caseRobot, g);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
