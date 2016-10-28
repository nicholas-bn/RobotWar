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
	private static int nbColonnesMax = 5;
	/** Nombre max de cases par lignes */
	private static int nbLignesMax = 5;

	private Case[][] elementsGrille;

	/**
	 * Constructeur de la classe {@link Grille}
	 */
	public Grille(int xGrille, int yGrille) {
		super();
		this.nbColonnesMax = xGrille;
		this.nbLignesMax = yGrille;
		// Layout utilisé
		setLayout(new GridLayout(nbLignesMax, nbColonnesMax));

		elementsGrille = new Case[nbLignesMax][nbColonnesMax];

		// Construction de la grille :
		for (int row = 0; row < nbLignesMax; row++) {
			for (int col = 0; col < nbColonnesMax; col++) {
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

	public static int getNbcolonnesmax() {
		return nbColonnesMax;
	}

	public static int getNblignesmax() {
		return nbLignesMax;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test de la classe Grille");
		Grille grille = new Grille(10, 10);
		frame.setContentPane(grille);

		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
