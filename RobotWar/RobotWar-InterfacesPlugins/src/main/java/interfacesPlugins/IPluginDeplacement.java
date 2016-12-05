package interfacesPlugins;

import java.awt.Point;

import interfacesMoteur.IGrille;
import interfacesMoteur.IRobot;

public interface IPluginDeplacement {

	public Point choisirDeplacement(IRobot robot, IGrille grille);

}
