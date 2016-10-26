package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe représentant une grille de jeu
 * 
 * @author Karl
 *
 */
@SuppressWarnings("serial")
public class Grille extends JLabel {

	/** Nombre max de cases par colonnes */
	private static final int nbColonnes = 10;
	/** Nombre max de cases par lignes */
	private static final int nbLignes = 10;

	private Case[][] elementsGrille;

	/**
	 * Constructeur de la classe {@link Grille}
	 */
	public Grille() {
		super();
		// Layout utilisé
		setLayout(new GridLayout(nbLignes, nbColonnes));

		elementsGrille = new Case[nbLignes][nbColonnes];

		// Construction de la grille :
		for (int row = 0; row < nbLignes; row++) {
			for (int col = 0; col < nbColonnes; col++) {
				// Création d'une case
				Case caseGrille = new Case();

				// Ajout de la case à la grille
				add(caseGrille, row, col);

				elementsGrille[row][col] = caseGrille;
			}
		}
		
	}

	public Case[][] getElementsGrille() {
		return elementsGrille;
	}
	
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test de la classe Grille");
		Grille grille = new Grille();
		frame.setContentPane(grille);

		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
