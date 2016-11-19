package interfacesMoteur;

import java.awt.Color;
import java.awt.Point;

public interface IRobot {

	int getVie();

	int getPtEnergie();

	void setPtEnergie(int i);

	void setVie(int i);

	Point getPosition();

	int getPortee();

	int getPtMouvement();

	String getIndice();

	Color getCouleur();

}
