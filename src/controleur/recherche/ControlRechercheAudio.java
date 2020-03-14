/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import modele.java.TypeRecherche;

public class ControlRechercheAudio extends ControlRecherche {
	//Attributs

	//Constructeur
	public ControlRechercheAudio(){
		this.fichierResultat = "src/modele/recherche/audio/rechercheAudioOut.txt";
		this.executableC = "./src/modele/recherche/audio/rechercheAudio.out";
	}


	//Méthodes
	public void rechercheTexte(String args) {
		super.rechercheTexte(args, TypeRecherche.AUDIO);

	}
}
