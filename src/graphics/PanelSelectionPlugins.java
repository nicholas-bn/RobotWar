package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.VueMenuDuJeu;

@SuppressWarnings("serial")
public class PanelSelectionPlugins extends JPanel implements ActionListener {

	/** Vue principale */
	private VueMenuDuJeu vuePrincipale;

	/** Label de texte explicatif */
	private JLabel label;

	/** Bouton pour lancer la partie */
	private JButton bouton;

	/**
	 * Constructeur de la classe {@link PanelSelectionPlugins}
	 * 
	 * @param vue
	 */
	public PanelSelectionPlugins(VueMenuDuJeu vue) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		vuePrincipale = vue;

		// Label de texte
		label = new JLabel("Choisir les plugins : ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		// Bouton pour lancer la partie
		bouton = new JButton("Lancer la partie");
		bouton.addActionListener(this);
		bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(bouton);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		// Si on a cliqué sur le bouton
		if (action.getSource() == bouton) {
			vuePrincipale.lancerLaPartie();
		}

	}

}
