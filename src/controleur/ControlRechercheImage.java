/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import modele.java.TypeRecherche;

public class ControlRechercheImage extends ControlRecherche {
	//Attributs

	//Constructeur
	public ControlRechercheImage(){
		this.fichierResultat = "src/modele/recherche/image/rechercheImageOut.txt";
		this.executableC = "./src/modele/recherche/image/rechercheImage.out";
	}


	//Méthodes
	public void rechercheTexte(String args) {
		super.rechercheTexte(args, TypeRecherche.IMAGE);

	}



}
