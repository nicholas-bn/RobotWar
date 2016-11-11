package plugins.graphisme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.Case;

/**
 * Plugin qui affiche une tourelle sur le robot
 * 
 * @author Karl
 *
 */
@SuppressWarnings("serial")
public class Tourelle extends Graphisme_de_Base {

	public void paint(Graphics g, Case c) {
		super.paint(g);

		g.setColor(Color.black);
		
		// Taille du label
		int xLabel = c.getWidth();
		int yLabel = c.getHeight();

		// Centre du label
		int xDraw = xLabel / 2;
		int yDraw = yLabel / 2;

		// Dessine le cercle au milieu du JLabel
		g.fillOval((xLabel - xDraw) / 2, (yLabel - yDraw) / 2, xDraw, yDraw);

		// Dessine le trait du canon
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.drawLine(xDraw, yDraw, ((7 * xLabel) / 8), yDraw);
	}
}
