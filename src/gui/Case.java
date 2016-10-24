package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */
@SuppressWarnings("serial")
public class Case extends JLabel {

	/**
	 * Constructeur de la classe {@link Case}
	 */
	public Case() {
		super();
		
		// Opaque pour permettre de mettre un background de couleur
		this.setOpaque(true);

		// On choisit un float random pour le bleu, vert et rouge
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		this.setBackground(randomColor);

	}

	
	/**
	 * Méthode paintComponent de {@link Case}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		// Taille du label
		int xLabel = this.getSize().width;
		int yLabel = this.getSize().height;
		
		// Centre du label
		int xDraw = xLabel / 2 ;
		int yDraw = yLabel / 2 ;
		
		// Dessine au milieu du JLabel
		g.fillOval((xLabel - xDraw)/2, (yLabel - yDraw)/2, xDraw, yDraw);
		
		// Dessiner le background de la barre de vie
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
	    int x1 = xLabel/8;
	    int y1 = yLabel/8;
	    int x2 = (xLabel*7)/8;
	    int y2 = yLabel/8;
		g.drawLine(x1, y1, x2, y2);
		System.out.println("TEST1");
		System.out.println(x1+" "+ y1+" "+ x2+" "+ y2);
		
		// Dessiner la barre de vie
		
		float vie = 0.50f; // A ENLEVER PLUS TARD: on prendra la vie du robot
		float tailleLigne = (xLabel - (xLabel/4)) * vie;
		float positionFinLigneVerte = (xLabel/8) + tailleLigne;
		
		g.setColor(Color.green);
	    g2.setStroke(new BasicStroke(3));
	    x1 = xLabel/8;
	    y1 = yLabel/8;
	    x2 = (int) positionFinLigneVerte;
	    y2 = yLabel/8;
		g.drawLine(x1, y1, x2, y2);
		System.out.println("TEST2");
		System.out.println(x1+" "+ y1+" "+ x2+" "+ y2);

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
