package plugins.attaque;

import graphics.Grille;
import main.Robot;

public interface IPluginAttaque {
	
	public Robot choisirCible(Grille grille, Robot robot); 
	

}
