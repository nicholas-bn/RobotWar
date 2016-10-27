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
	Class c;
	Deplacement_Random instance;

	public Robot() {

		try {
			c = Class.forName("plugins.Deplacement_Random");
			instance = (Deplacement_Random) c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
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

	public void seDeplacer(Grille grille) {
		try {

			Method m = c.getMethod("choisirDeplacement", Grille.class, Robot.class);

			Point p = (java.awt.Point) m.invoke(instance, grille, this);
			
			Point pointDepart = getPoint();
			
			System.out.println("Depart :" + pointDepart);
			grille.getElementsGrille()[pointDepart.x][pointDepart.y].setRobot(null);

			setPoint(p);

			System.out.println("Deplacement :" + p);

			grille.getElementsGrille()[p.x][p.y].setRobot(this);
			grille.repaint();

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
