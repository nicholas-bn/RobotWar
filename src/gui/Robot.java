package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import plugins.Test_Deplacement_Random;

public class Robot {

	/** Portée de l'arme */
	private static int Portee;
	/** Nombre de vie */
	private static int Vie;
	/** Nombre de points d'actions diponibles */
	private static int ptAction;
	/** Nombre de points de mouvements diponibles */
	private static int ptMouvement = 1;
	/** Coordonnées */
	private Point Point;

	public static int getPortee() {
		return Portee;
	}

	public static void setPortee(int portee) {
		Portee = portee;
	}

	public static int getVie() {
		return Vie;
	}

	public static void setVie(int vie) {
		Vie = vie;
	}

	public static int getPtAction() {
		return ptAction;
	}

	public static void setPtAction(int ptAction) {
		Robot.ptAction = ptAction;
	}

	public static int getPtMouvement() {
		return ptMouvement;
	}

	public static void setPtMouvement(int ptMouvement) {
		Robot.ptMouvement = ptMouvement;
	}

	public Point getPoint() {
		return Point;
	}

	public void setPoint(Point point) {
		this.Point = point;
	}

	public void seDeplacer(Grille grille) {
		try {
			Class c = Class.forName("plugins.Test_Deplacement_Random");

			Test_Deplacement_Random instance = (Test_Deplacement_Random) c.newInstance();

			Method m = c.getMethod("choisirDeplacement", Grille.class, Robot.class);

			Point p = (java.awt.Point) m.invoke(instance, grille, this);

			System.out.println(p);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
