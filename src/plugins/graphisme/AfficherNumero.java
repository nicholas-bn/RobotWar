package plugins.graphisme;

import java.awt.Graphics;

import javax.swing.JLabel;

import gui.Case;

/**
 *  Classe permettant de différencier les robot en affichant
 *  dans un coin de la case le numéro
 * 
 * @author Nicho
 *
 */
@SuppressWarnings("serial")
public class AfficherNumero extends Graphisme_de_Base {

	public void paint(Graphics g, Case c) {
		super.paint(g);
		
		if(c.getRobot() != null)
			c.setText(c.getRobot().getIndice()+"");
		c.setHorizontalTextPosition(JLabel.LEFT);
		c.setVerticalTextPosition(JLabel.BOTTOM);
	}
}
