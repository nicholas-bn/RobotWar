package interfacesMoteur;

import java.awt.Color;

public interface ICase {

	IRobot getRobot();
	
	void setRobot(IRobot robot);

	void setText(String string);

	void setVerticalAlignment(int bottom);

	void setHorizontalAlignment(int left);

	void setForeground(Color white);

	int getWidth();

	int getHeight();

	void setOpaque(boolean b);

	void setBackground(Color couleur);

}
