package plugins.graphisme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import graphics.Case;

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

		

	}
}
