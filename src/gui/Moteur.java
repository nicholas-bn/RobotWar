package gui;

import java.awt.Point;

public class Moteur {
		
		Robot r = new Robot();
		Grille grille = new Grille();
		int randomx = (int)(Math.random()*(Grille.getNbcolonnesmax()-0));
		int randomy = (int)(Math.random()*(Grille.getNblignesmax()-0));
		Point point = new Point(randomx, randomy);
		
		r.setPoint(point);
		r.setPtMouvement(1);
		
		public void gestionTour() {
			r.seDeplacer(grille);
			
		}
		
		public static void main(String[] args) {
			
			r.setPoint(point);
			r.setPtMouvement(1);
		    
			
		}
}
