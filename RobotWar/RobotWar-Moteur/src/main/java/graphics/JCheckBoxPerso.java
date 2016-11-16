package graphics;

import java.io.File;

import javax.swing.JCheckBox;

public class JCheckBoxPerso extends JCheckBox {

	private String nom;

	private String cheminComplet;

	public JCheckBoxPerso(File fichier) {
		cheminComplet = fichier.getAbsolutePath();

		nom = fichier.getName();
	}
}
