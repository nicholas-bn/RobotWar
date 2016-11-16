package plugins.deplacement;

import java.awt.Point;

import graphics.Grille;
import main.Robot;

public interface IPluginDeplacement {

	public Point choisirDeplacement(Grille grille, Robot robot);

}
