package plugins.graphisme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import graphics.Case;
import interfacesPlugins.IPluginGraphisme;

@SuppressWarnings("serial")
public class Graphisme_de_Base implements IPluginGraphisme {


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

		c.setOpaque(true);
		c.setBackground(c.getRobot().getCouleur());

		g.setColor(Color.black);

		

	}

}
