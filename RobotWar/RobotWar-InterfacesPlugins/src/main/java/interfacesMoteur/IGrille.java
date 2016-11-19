package interfacesMoteur;

import java.awt.Point;

public interface IGrille {

	int getNbcolonnesmax();

	int getNblignesmax();

	IRobot getRobotFromPoint(Point point);

}
