package plugins.graphisme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import gui.Case;

@SuppressWarnings("serial")
public class Graphisme_de_Base extends Component {

	public void dessiner(Case c, Graphics g) {
		paint(g, c);
	}

	public Color getCouleur() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		return new Color(r, g, b);
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

		// Dessine le cercle au milieu du JLabel
		g.fillOval((xLabel - xDraw) / 2, (yLabel - yDraw) / 2, xDraw, yDraw);

		// Dessine le trait du canon
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.drawLine(xDraw, yDraw, ((7 * xLabel) / 8), yDraw);

	}
}
