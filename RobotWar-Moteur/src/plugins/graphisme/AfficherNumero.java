package plugins.graphisme;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import graphics.Case;

/**
 * Classe permettant de différencier les robot en affichant dans un coin de la
 * case le numéro
 * 
 * @author Nicho
 *
 */
@SuppressWarnings("serial")
public class AfficherNumero extends Graphisme_de_Base {

	public void paint(Graphics g, Case c) {
		super.paint(g);

		// Si la case contient un robot on ajoute son indice et on
		// spécifie la position du texte
		if (c.getRobot() != null) {
			c.setText(c.getRobot().getIndice() + "");
			c.setHorizontalTextPosition(JLabel.LEFT);
			c.setVerticalTextPosition(JLabel.BOTTOM);

			// On récupère la couleur du robot
			Color background = c.getRobot().getCouleur();
			
			// Suivant la couleur du background on modifie la couleur du text
			if ((background.getRed()+ background.getGreen() + background.getBlue()) < 383 ) {
				c.setForeground(Color.white);
			} else {
				c.setForeground(Color.black);
			}

		}
	}
}
