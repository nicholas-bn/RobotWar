package plugins;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import gui.Case;

public class Test_Dynamic_Loading extends Component {

	public void dessiner(Case c, Graphics g) {

		paint(g, c);
	}

	/**
	 * Méthode paintComponent de {@link Case}
	 * 
	 * @param c
	 */
	public void paint(Graphics g, Case c) {
		super.paint(g);

		c.setOpaque(true);
		c.setBackground(c.getRobot().getCouleur());

		g.setColor(Color.black);

		// Taille du label
		int xLabel = c.getWidth();
		int yLabel = c.getHeight();

		// Centre du label
		int xDraw = xLabel / 2;
		int yDraw = yLabel / 2;

		// Dessine au milieu du JLabel
		g.fillOval((xLabel - xDraw) / 2, (yLabel - yDraw) / 2, xDraw, yDraw);

		// Dessiner le background de la barre de vie
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		int x1 = xLabel / 8;
		int y1 = yLabel / 8;
		int x2 = (xLabel * 7) / 8;
		int y2 = yLabel / 8;
		g.drawLine(x1, y1, x2, y2);

		// Dessiner la barre de vie

		float vie = 0.50f; // A ENLEVER PLUS TARD: on prendra la vie du robot
		float tailleLigne = (xLabel - (xLabel / 4)) * vie;
		float positionFinLigneVerte = (xLabel / 8) + tailleLigne;

		g.setColor(Color.green);
		g2.setStroke(new BasicStroke(3));
		x1 = xLabel / 8;
		y1 = yLabel / 8;
		x2 = (int) positionFinLigneVerte;
		y2 = yLabel / 8;
		g.drawLine(x1, y1, x2, y2);

	}
}
