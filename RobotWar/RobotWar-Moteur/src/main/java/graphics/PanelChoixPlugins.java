package graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import gui.VueMenuDuJeu;
import plugins.GestionnairePlugins;

public class PanelChoixPlugins extends JPanel {

	/** Vue principale */
	private VueMenuDuJeu vueMenuDuJeu;

	/** Liste des checkbox */
	private ArrayList<JCheckBoxPlugins> listCheckBox;

	/** Gestionnaire de plugins */
	private GestionnairePlugins gestionnairePlugins;

	/**
	 * Constructeur de la classe {@link PanelChoixPlugins}
	 * 
	 * @param vue
	 */
	public PanelChoixPlugins(VueMenuDuJeu vue) {
		super();

		vueMenuDuJeu = vue;

		gestionnairePlugins = vueMenuDuJeu.getGestionnairePlugins();

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		listCheckBox = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(gestionnairePlugins.PATH_TO_FILE));
			// Si le fichier est non vide
			if (br.readLine() != null && br.readLine() != "" && br.readLine() != "\n") {
				ArrayList<String> listStringSauvegarde = gestionnairePlugins.lectureFichier();
				ArrayList<File> listFileSauvegarde = new ArrayList<File>();
				for (String chemin : listStringSauvegarde) {
					listFileSauvegarde.add(new File(chemin.split(" ")[0]));

				}
				modifierListePlugins(listFileSauvegarde);

				cocherPlugins(listStringSauvegarde);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cocherPlugins(ArrayList<String> listStringSauvegarde) {
		for (String s : listStringSauvegarde) {
			String[] tab = s.split(" ");

			if (tab[2].equalsIgnoreCase("true")) {
				for (JCheckBoxPlugins jcb : listCheckBox) {
					if (jcb.getFile().getAbsolutePath().equals(tab[0])) {
						jcb.setSelected(true);
					}
				}
			}
		}

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
