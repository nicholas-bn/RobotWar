package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import interfacesMoteur.ICase;
import interfacesMoteur.IRobot;
import main.Robot;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */
@SuppressWarnings("serial")
public class Case extends JLabel implements ICase{

	private IRobot robot;

	/**
	 * Constructeur de la classe {@link Case}
	 */
	public Case() {
		super();
		this.setRobot(null);

		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public IRobot getRobot() {
		return robot;
	}

	public void setRobot(IRobot robot) {
		this.robot = robot;
	}

	/**
	 * Méthode paint de {@link Case}
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (robot != null) {
			robot.dessiner(g, this);
		}

		else {
			setBackground(null);
		}
	}

}
