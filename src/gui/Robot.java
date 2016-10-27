package gui;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import plugins.Deplacement_Random;

public class Robot {

	/** Portée de l'arme */
	private int Portee;
	/** Nombre de vie */
	private int Vie;
	/** Nombre de points d'actions diponibles */
	private int ptAction;
	/** Nombre de points de mouvements diponibles */
	private int ptMouvement;
	/** Coordonnées */
	private Point Point;
	/** Couleur */
	private Color couleur;

	/** Classe qui va choisir les déplacements du robots */
	Class<?> plugin_deplacement;
	/** Instance de la classe qui va choisir les déplacements du robot */
	Deplacement_Random instance;

	/**
	 * Constructeur de la classe {@link Robot}
	 */
	public Robot() {

		// Chargement de la classe qui s'occupe des déplacements du robot
		try {
			plugin_deplacement = Class.forName("plugins.Deplacement_Random");
			instance = (Deplacement_Random) plugin_deplacement.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public int getPortee() {
		return Portee;
	}

	public void setPortee(int portee) {
		Portee = portee;
	}

	public int getVie() {
		return Vie;
	}

	public void setVie(int vie) {
		Vie = vie;
	}

	public int getPtAction() {
		return ptAction;
	}

	public void setPtAction(int ptAction) {
		this.ptAction = ptAction;
	}

	public int getPtMouvement() {
		return ptMouvement;
	}

	public void setPtMouvement(int ptMouvement) {
		this.ptMouvement = ptMouvement;
	}

	public Point getPoint() {
		return Point;
	}

	public void setPoint(Point point) {
		this.Point = point;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Méthode qui permet au robot de se déplacer sur la grille
	 * 
	 * @param grille
	 */
	public void seDeplacer(Grille grille) {

		try {
			// La méthode du plugin qui permet de choisir un déplacement
			Method m = plugin_deplacement.getMethod("choisirDeplacement", Grille.class, Robot.class);

			// Point choisie par le plugin
			Point p = (java.awt.Point) m.invoke(instance, grille, this);

			// Position initiale du robot
			Point pointDepart = getPoint();
			// On l'enlève de sa position initiale
			// TODO faire une méthode
			grille.getElementsGrille()[pointDepart.x][pointDepart.y].setRobot(null);

			// On affecte au robot sa nouvelle position
			setPoint(p);
			// On le place sur la grille avec sa nouvelle position
			grille.getElementsGrille()[p.x][p.y].setRobot(this);

			// On redessine la grille
			grille.repaint();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
