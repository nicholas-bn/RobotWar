package plugins;

import java.awt.Color;

/**
 * Classe indiquant les différents types de plugins possibles
 * 
 * @author Karl
 *
 */
public enum TypePlugin {
	GRAPHISME(Color.BLUE), ATTAQUE(Color.RED), DEPLACEMENT(Color.GREEN);

	private Color couleur;

	TypePlugin(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return couleur;
	}
}
