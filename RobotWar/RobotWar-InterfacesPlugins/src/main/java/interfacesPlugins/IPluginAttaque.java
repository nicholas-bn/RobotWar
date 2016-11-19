package interfacesPlugins;


import interfacesMoteur.IGrille;
import interfacesMoteur.IRobot;

public interface IPluginAttaque {
	
	public IRobot choisirCible(IGrille grille, IRobot robot); 
	

}
