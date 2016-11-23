package plugins.graphisme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import interfacesMoteur.ICase;
import interfacesPlugins.IPluginGraphisme;

@SuppressWarnings("serial")
public class Image implements IPluginGraphisme {
	
	private ArrayList<BufferedImage> listImage;
	
	public Image(){ 
		listImage = new ArrayList<>();
		try {
			BufferedImage img = ImageIO.read(this.getClass().getClassLoader().getResource("bb8.png"));
			BufferedImage img2 = ImageIO.read(this.getClass().getClassLoader().getResource("gordon_ramsay.png"));
			listImage.add(img);
			listImage.add(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Color getCouleur() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		return new Color(r, g, b);
	}

	/**
	 * Méthode paintComponent de {@link Case}
	 * 
	 * @param c
	 */
	public void paint(Graphics g, ICase c) {

		c.setOpaque(true);
		c.setBackground(c.getRobot().getCouleur());

		g.setColor(Color.black);

		BufferedImage img = listImage.get(c.getRobot().getIndice()%2);
		
		// Si l'image est plus grande que le JLabel, on le met en 0,0
		// Autrement on on calcule pour mettre le center de la photo 
		// au centre du JLabel
		if(img.getWidth()<c.getWidth() || img.getHeight()<c.getHeight()) {
			int x = (c.getWidth()/2)-(img.getWidth()/2);
			int y = (c.getHeight()/2)-(img.getHeight()/2);
			g.drawImage(img, x, y, null);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}


}
