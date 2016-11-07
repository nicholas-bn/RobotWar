package graphics;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.VueMenuDuJeu;

public class PanelChoixPlugins extends JPanel {

	private VueMenuDuJeu vueMenuDuJeu;

	private ArrayList<JCheckBox> listCheckBox;

	public PanelChoixPlugins(VueMenuDuJeu vue) {
		super();

		vueMenuDuJeu = vue;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		listCheckBox = new ArrayList<>();

		JCheckBox cb = new JCheckBox("1");

		// On ajoute la checkbox à la liste
		listCheckBox.add(cb);

	}

	public void modifierListePlugins(ArrayList<String> listPlugins) {

		for (JCheckBox cbOld : listCheckBox) {
			// On enlève les anciennes checkbox
			vueMenuDuJeu.remove(cbOld);
		}

		// On vide la liste des checkbox
		listCheckBox.clear();

		// Pour chaque plugins :
		for (String plugins : listPlugins) {

			// Création d'une checkbox avec comme texte le nom du plugin
			JCheckBox cb = new JCheckBox(plugins);

			// On ajoute la checkbox à la liste
			listCheckBox.add(cb);

			// On ajoute la checkbox au panel
			add(cb);
		}

		revalidate();
		repaint();
	}

	public ArrayList<String> getListPluginsChoisis() {
		ArrayList<String> listPluginsChoisis = new ArrayList<>();

		// On parcourt la liste des CheckBox :
		for (JCheckBox jc : listCheckBox) {
			// Si elle est cochée
			if (jc.isSelected()) {
				// On l'ajoute à la liste des plugins choisis
				listPluginsChoisis.add(jc.getText());
			}
		}
		return listPluginsChoisis;
	}
}
