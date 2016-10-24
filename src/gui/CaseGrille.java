package gui;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CaseGrille extends JLabel {

	public CaseGrille(){
		super();
		this.setOpaque(true); // Opaque pour permettre de mettre un background de couleur
		
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		this.setBackground(randomColor);
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
