/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import modele.java.TypeRecherche;


public class ControlRechercheMotClef extends ControlRecherche{
	//Attributs

	//Constructeur
	public ControlRechercheMotClef(){
		this.fichierResultat = "src/modele/recherche/motClef/rechercheMotClefOut.txt";
		this.executableC = "./src/modele/recherche/motClef/rechercheMotClef.out";
	}


	//Méthodes
	public void recherche(String args) {
		super.recherche(args, TypeRecherche.MOT_CLEF);

	}
}
