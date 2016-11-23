package plugins.graphisme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import interfacesMoteur.ICase;
import interfacesMoteur.IRobot;
import interfacesPlugins.IPluginGraphisme;

/**
 * Plugin qui affiche une barre d'énergie en dessous de la barre de vie
 * 
 * @author Léonard
 *
 */
@SuppressWarnings("serial")
public class BarreEnergie implements IPluginGraphisme {

	public void paint(Graphics g, ICase c) {

		// Taille du label (case)
		int xLabel = c.getWidth();
		int yLabel = c.getHeight();

		Graphics2D g2 = (Graphics2D) g;

		// Dessiner l'arriere plan noir de la barre d'énergie
		g.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
		int x1 = xLabel / 8;
		int y1 = yLabel / 6;
		int x2 = ((xLabel * 7) / 8) + 1;
		int y2 = yLabel / 6;
		g.drawLine(x1, y1, x2, y2);

		// Dessiner le background de la barre d'energie
		g.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		x1 = xLabel / 8;
		y1 = (yLabel / 6);
		x2 = (xLabel * 7) / 8;
		y2 = (yLabel / 6);
		g.drawLine(x1, y1, x2, y2);

		IRobot robot = c.getRobot();

		// Energie actuel du robot
		float energie = robot.getPtEnergie() / 100f;
		float tailleLigne = (xLabel - (xLabel / 4)) * energie;
		float positionFinLigneBleu = (xLabel / 8) + tailleLigne;

		g.setColor(Color.blue);
		g2.setStroke(new BasicStroke(3));
		x1 = xLabel / 8;
		y1 = (yLabel / 6);
		x2 = (int) positionFinLigneBleu;
		y2 = (yLabel / 6);
		g.drawLine(x1, y1, x2, y2);

	}

}
