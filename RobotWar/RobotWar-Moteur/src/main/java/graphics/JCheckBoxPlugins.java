package graphics;

import java.awt.Color;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.UIManager;

import plugins.TypePlugin;

public class JCheckBoxPlugins extends JCheckBox {

	/** Le nom du plugin */
	private String nom;

	/** Le file .class */
	private File fichier;

	public JCheckBoxPlugins(File fichier) {
		this.fichier = fichier;
		nom = fichier.getName();

		traitementTypePlugin();

		setText(nom);
	}

	public void traitementTypePlugin() {
		//String chemin = fichier.getAbsolutePath();
		//String tab[] = chemin.split("\\\\");
		//TypePlugin typePlugin = TypePlugin.valueOf(tab[tab.length - 2].toUpperCase());
		
	}

	public File getFile() {
		return fichier;
	}
}
