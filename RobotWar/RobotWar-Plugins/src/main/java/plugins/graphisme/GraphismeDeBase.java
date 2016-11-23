package plugins.graphisme;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import interfacesMoteur.ICase;
import interfacesPlugins.IPluginGraphisme;

@SuppressWarnings("serial")
public class GraphismeDeBase implements IPluginGraphisme {


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
	public void paint(Graphics g, ICase c) {
		
		c.setOpaque(true);
		c.setBackground(c.getRobot().getCouleur());

		g.setColor(Color.black);

		

	}

}
