package graphics;

import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.VueMenuDuJeu;

public class PanelChoixPlugins extends JPanel {

	/** Vue principale */
	private VueMenuDuJeu vueMenuDuJeu;

	/** Liste des checkbox */
	private ArrayList<JCheckBoxPlugins> listCheckBox;

	/**
	 * Constructeur de la classe {@link PanelChoixPlugins}
	 * 
	 * @param vue
	 */
	public PanelChoixPlugins(VueMenuDuJeu vue) {
		super();

		vueMenuDuJeu = vue;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		listCheckBox = new ArrayList<>();
	}

	/**
	 * Permet de modifier la liste des plugins affichés
	 * 
	 * @param listPlugins
	 */
	public void modifierListePlugins(ArrayList<File> listPlugins) {

		for (JCheckBox cbOld : listCheckBox) {
			// On enlève les anciennes checkbox
			remove(cbOld);
		}

		// On vide la liste des checkbox
		listCheckBox.clear();

		// Pour chaque plugins :
		for (File plugins : listPlugins) {

			// Création d'une checkbox avec comme texte le nom du plugin
			JCheckBoxPlugins cb = new JCheckBoxPlugins(plugins);

			// On ajoute la checkbox à la liste
			listCheckBox.add(cb);

			// On ajoute la checkbox au panel
			add(cb);
		}

		revalidate();
		repaint();
	}

	/**
	 * Permet de retourner la liste des plugins choisis par l'utilisateur (les
	 * checkbox sélectionnés)
	 * 
	 * @return
	 */
	public ArrayList<File> getListPluginsChoisis() {
		ArrayList<File> listPluginsChoisis = new ArrayList<>();

		// On parcourt la liste des CheckBox :
		for (JCheckBoxPlugins jc : listCheckBox) {
			// Si elle est cochée
			if (jc.isSelected()) {
				// On l'ajoute à la liste des plugins choisis
				listPluginsChoisis.add(jc.getFile());
			}
		}
		return listPluginsChoisis;
	}
}
