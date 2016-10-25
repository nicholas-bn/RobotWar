package gui;

import java.awt.Point;

public class Robot {

	/** Portée de l'arme */
	private static int Portee;
	/** Nombre de vie */
	private static int Vie;
	/** Nombre de points d'actions diponibles */
	private static int ptAction;
	/** Nombre de points de mouvements diponibles */
	private static int ptMouvement;
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

}
