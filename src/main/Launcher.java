package main;

import javax.swing.JFileChooser;

public class Launcher {
	
	public Launcher (){
		
		JFileChooser jfc = new JFileChooser("src");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = jfc.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("Répertoire choisi: " +	jfc.getSelectedFile().getName());
	    }
		new Moteur (2, 10, 10);
	}
	
	public static void main(String[] args) {
		new Launcher();
	}
}


