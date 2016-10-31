package plugins.graphisme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.Case;

/**
 * Plugin qui affiche une barre de vie au dessus du robot
 * 
 * @author Karl
 *
 */
@SuppressWarnings("serial")
public class Barre_de_vie extends Graphisme_de_Base {

	public void paint(Graphics g, Case c) {
		super.paint(g);

		// Taille du label (case)
		int xLabel = c.getWidth();
		int yLabel = c.getHeight();

		Graphics2D g2 = (Graphics2D) g;

		// Dessiner le background de la barre de vie
		g.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		int x1 = xLabel / 8;
		int y1 = yLabel / 8;
		int x2 = (xLabel * 7) / 8;
		int y2 = yLabel / 8;
		g.drawLine(x1, y1, x2, y2);

		// Dessiner la barre de vie TODO A ENLEVER PLUS TARD: on prendra la vie
		// du robot
		float vie = 0.50f;
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
