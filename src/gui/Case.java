package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

import plugins.Test_Dynamic_Loading;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */
@SuppressWarnings("serial")
public class Case extends JLabel {

	private Robot robot;

	/**
	 * Constructeur de la classe {@link Case}
	 */
	public Case() {
		super();
		this.setRobot(null);
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	/**
	 * Méthode paint de {@link Case}
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (this.getRobot() != null){
			try {
				Class c = Class.forName("plugins.Test_Dynamic_Loading");

				Test_Dynamic_Loading instance = (Test_Dynamic_Loading) c.newInstance();

				Method m = c.getMethod("dessiner", Case.class, Graphics.class);
				m.invoke(instance, this, g);
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
	
	

	// g.setColor(Color.black);
	//
	// // Taille du label
	// int xLabel = this.getSize().width;
	// int yLabel = this.getSize().height;
	//
	// // Centre du label
	// int xDraw = xLabel / 2 ;
	// int yDraw = yLabel / 2 ;
	//
	// // Dessine au milieu du JLabel
	// g.fillOval((xLabel - xDraw)/2, (yLabel - yDraw)/2, xDraw, yDraw);
	//
	// // Dessiner le background de la barre de vie
	// g.setColor(Color.white);
	// Graphics2D g2 = (Graphics2D) g;
	// g2.setStroke(new BasicStroke(3));
	// int x1 = xLabel/8;
	// int y1 = yLabel/8;
	// int x2 = (xLabel*7)/8;
	// int y2 = yLabel/8;
	// g.drawLine(x1, y1, x2, y2);
	//
	// // Dessiner la barre de vie
	//
	// float vie = 0.50f; // A ENLEVER PLUS TARD: on prendra la vie du robot
	// float tailleLigne = (xLabel - (xLabel/4)) * vie;
	// float positionFinLigneVerte = (xLabel/8) + tailleLigne;
	//
	// g.setColor(Color.green);
	// g2.setStroke(new BasicStroke(3));
	// x1 = xLabel/8;
	// y1 = yLabel/8;
	// x2 = (int) positionFinLigneVerte;
	// y2 = yLabel/8;
	// g.drawLine(x1, y1, x2, y2);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(100, 100);
		jf.setLocation(100, 100);

		Case cg = new Case();

		jf.add(cg);
		jf.setVisible(true);
	}

}
