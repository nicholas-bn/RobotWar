package main;

import javax.swing.JFileChooser;

import gui.VueMenuDuJeu;

/**
 * @author Barnini
 *
 */
public class Launcher {

	/**
	 * Constructeur de la classe Launcher
	 */
	public Launcher() {

		new VueMenuDuJeu();
	}

	// public void initJARPluginsChooser(String repertoireChoisi) {
	//
	// JFrame jf = new JFrame();
	// jf.setLocation(100, 100);
	// jf.setSize(100, 100);
	// jf.setTitle("Choisir les plugins à activer: ");
	// jf.setVisible(true);
	// jf.addWindowListener(new WindowAdapter() {
	// public void windowClosing(WindowEvent evt) {
	// new Moteur(2, 4, 4);
	// try {
	// Thread.sleep(500);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// jf.setVisible(false);
	// }
	// });
	// }

	/**
	 * Permet de choisir un répertoire d'où extraire les plugins
	 * 
	 * @return String - Chemin vers le répertoire des plugins
	 */
	private String choisirRepertoirePluginsJAR() {
		JFileChooser jfc = new JFileChooser("D:\\TRAVAIL\\S7\\Prog_Avancée\\Repertoire_Test_JAR");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	public static void main(String[] args) {
		new Launcher();
	}
}
