package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe représentant une case de la grille
 * 
 * @author Barnini Nicholas
 *
 */
@SuppressWarnings("serial")
public class Case extends JLabel {

	/**
	 * Constructeur de la classe {@link Case}
	 */
	public Case() {
		super();
		
		// Opaque pour permettre de mettre un background de couleur
		this.setOpaque(true);

		// On choisit un float random pour le bleu, vert et rouge
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		this.setBackground(randomColor);

	}

	
	/**
	 * Méthode paintComponent de {@link Case}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		// Taille du label
		int xLabel = this.getSize().width;
		int yLabel = this.getSize().height;
		
		// Centre du label
		int xDraw = xLabel / 2 ;
		int yDraw = yLabel / 2 ;
		
		// Dessine au milieu du JLabel
		g.fillOval((xLabel - xDraw)/2, (yLabel - yDraw)/2, xDraw, yDraw);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(100, 100);
		jf.setLocation(100, 100);
		//test commit
		System.out.println("madaaaaame");

		Case cg = new Case();

		jf.add(cg);
		jf.setVisible(true);
	}

}
