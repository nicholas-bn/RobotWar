package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CaseGrille extends JLabel {

	public CaseGrille() {
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		// Dessine au milieu du JLabel
		g.fillOval(this.getSize().width/2, this.getSize().height/2, 50, 50);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(100, 100);
		jf.setLocation(100, 100);

		CaseGrille cg = new CaseGrille();

		jf.add(cg);
		jf.setVisible(true);
	}

}
