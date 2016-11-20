package interfacesMoteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface IRobot {

	int getVie();

	int getPtEnergie();

	void setPtEnergie(int i);

	void setVie(int i);

	Point getPosition();

	int getPortee();

	int getPtMouvement();

	int getIndice();

	Color getCouleur();

	void dessiner(Graphics g, ICase case1);

	void setPosition(Point posChoisie);

}
