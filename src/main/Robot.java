package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import gui.Case;
import gui.Grille;
import plugins.Attaque_de_Base;
import plugins.Deplacement_Random;
import plugins.Graphisme_de_Base;

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
	/** Indice du robot */
	private int indice;

	/** Classe qui va choisir les déplacements du robots */
	Class<?> plugin_deplacement;
	/** Instance de la classe qui va choisir les déplacements du robot */
	Deplacement_Random instanceDeplacement;

	/** Classe qui va choisir le robot à attaquer */
	Class<?> plugin_attaque;
	/** Instance de la classe qui va choisir quel robot à prendre pour cible */
	Attaque_de_Base instanceAttaque;

	/** Classe qui va choisir l'apparence d'un robot */
	Class<?> plugin_apparence;
	/** Instance de la classe qui va dessiner le robot */
	Graphisme_de_Base instanceApparence;

	/**
	 * Constructeur de la classe {@link Robot}
	 */
	public Robot() {
		try {
			// Chargement du plugin "Deplacement"
			plugin_deplacement = Class.forName("plugins.Deplacement_Random");
			instanceDeplacement = (Deplacement_Random) plugin_deplacement.newInstance();

			// Chargement du plugin "Attaque"
			plugin_attaque = Class.forName("plugins.Attaque_de_Base");
			instanceAttaque = (Attaque_de_Base) plugin_attaque.newInstance();

			// Chargement du plugin "Apparence"
			plugin_apparence = Class.forName("plugins.Test_Dynamic_Loading");
			instanceApparence = (Graphisme_de_Base) plugin_apparence.newInstance();

			// La méthode du plugin qui permet de choisir la couleur du robot
			Method m = plugin_apparence.getMethod("getCouleur");
			Color couleur = (Color) m.invoke(instanceApparence);
			this.setCouleur(couleur);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
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

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	/**
	 * Méthode qui permet au robot d'attaquer un autre robot
	 * 
	 * @param grille
	 */
	public Robot attaquer(Grille grille) {
		try {
			// La méthode du plugin qui permet de choisir une cible
			Method m = plugin_attaque.getMethod("choisirCible", Grille.class, Robot.class);

			// Cette méthode retourne un robot "cible"
			Robot robotCible = (Robot) m.invoke(instanceAttaque, grille, this);

			// Si un robot a été choisi
			if (robotCible != null) {
				System.out.println("Le robot " + this + " attaque le robot " + robotCible);
				return robotCible;
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}

		return null;
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
			Point posChoisie = (java.awt.Point) m.invoke(instanceDeplacement, grille, this);

			// On déplace le robot sur la grille
			grille.deplacerRobot(this, posChoisie);

			// On redessine la grille
			grille.repaint();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui permet de dessiner le robot
	 * 
	 * @param g
	 * @param caseRobot
	 */
	public void dessiner(Graphics g, Case caseRobot) {
		try {
			Method m = plugin_apparence.getMethod("dessiner", Case.class, Graphics.class);

			// Méthode qui va décider elle même le robot
			m.invoke(instanceApparence, caseRobot, g);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui permet de savoir si un robot est toujours vivant
	 * 
	 */
	public boolean isVivant() {
		if (this.getVie() > 0)
			return true;
		return false;
	}

	/**
	 * Override du toString pour afficher les valeurs importantes
	 * 
	 */
	public String toString() {
		return "Robot " + this.getIndice() + "( X:" + this.getPoint().getX() + " Y:" + this.getPoint().getY() + ")";
	}

}
