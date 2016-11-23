package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import graphics.Case;
import graphics.Grille;
import interfacesMoteur.ICase;
import interfacesMoteur.IRobot;
import plugins.GestionnairePlugins;

public class Robot implements IRobot{

	/** Nombre de point de vie (PV) */
	private int pv;
	
	/** Nombre de point d'énergie (PE) */
	private int pe;

	/** Nombre de points d'actions (PA) */
	private int pa;

	/** Nombre de points de mouvements (PM) */
	private int pm;

	/** Portée de l'arme (PO) */
	private int po;

	/** Position du robot */
	private Point position;

	/** Couleur du robot */
	private Color couleur;

	/** Indice du robot */
	private int indice;

	/** Gestionnaire des plugins */
	private GestionnairePlugins gestionnairePlugins;

	/**
	 * Constructeur de la classe {@link Robot}
	 * 
	 * @param gestionnairePlugins
	 */
	public Robot(GestionnairePlugins gestionnairePlugins) {
		// Initialisation de la vie du robot à 100
		pv = 100;
		// Initialisation des points d'actions du robot à 1
		pa = 1;
		// Initialisation des points de mouvements du robot à 1
		pm = 1;
		// Initialisation de la portee du robot à 1
		po = 1;
		// Initialisation des points d'énergies du robot à 100
		pe = 100;

		this.gestionnairePlugins = gestionnairePlugins;

		// On demande au gestionnaire la couleur du robot
		couleur = gestionnairePlugins.getCouleurRobot();

	}

	public int getPortee() {
		return po;
	}

	public void setPortee(int portee) {
		po = portee;
	}

	public int getVie() {
		return pv;
	}

	public void setVie(int vie) {
		pv = vie;
	}

	public int getPtAction() {
		return pa;
	}

	public void setPtAction(int ptAction) {
		pa = ptAction;
	}
	
	public int getPtEnergie() {
		return pe;
	}

	public void setPtEnergie(int ptEnergie) {
		pe = ptEnergie;
	}

	public int getPtMouvement() {
		return pm;
	}

	public void setPtMouvement(int ptMouvement) {
		pm = ptMouvement;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point point) {
		position = point;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	/**
	 * Méthode qui permet au robot d'attaquer un autre robot
	 * 
	 * @param grille
	 */
	public Robot attaquer(Grille grille) {

		// On demande au gestionnaire le robot cible (peut etre null)
		Robot robotCible = gestionnairePlugins.attaquer(grille, this);

		// Si un robot a été choisi
		if (robotCible != null) {
			System.out.println("Le robot " + this + " attaque le robot " + robotCible);
		}

		// On retourne le robot choisit (peut etre null)
		return robotCible;
	}

	/**
	 * Méthode qui permet au robot de se déplacer sur la grille
	 * 
	 * @param grille
	 */
	public void seDeplacer(Grille grille) {

		// On demande au gestionnaire la nouvelle position
		Point posChoisie = gestionnairePlugins.seDeplacer(grille, this);

		// On déplace le robot sur la grille
		grille.deplacerRobot(this, posChoisie);

		// On redessine la grille
		grille.repaint();
	}

	/**
	 * Méthode qui permet de dessiner le robot
	 * 
	 * @param g
	 * @param caseRobot
	 */
	public void dessiner(Graphics g, ICase caseRobot) {

		// On demande au gestionnaire de dessiner le robot
		gestionnairePlugins.dessiner(g, (Case) caseRobot);
	}
	
	/**
	 * Méthode qui permet de savoir si un robot est toujours vivant
	 * 
	 */
	public boolean isVivant() {
		if (this.getVie() > 0)
			return true;
		return false;
	}

	/**
	 * Override du toString pour afficher les valeurs importantes
	 * 
	 */
	public String toString() {
		return "Robot " + this.getIndice() + "( X:" + this.getPosition().getX() + " Y:" + this.getPosition().getY()
				+ ")";
	}

	

}
