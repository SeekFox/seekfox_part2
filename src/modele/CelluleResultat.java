/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

public class CelluleResultat{
	//Attributs
	private String fichier;
	private float score;

	//Constructeur
	public CelluleResultat(String fichier,float score){
		this.fichier=fichier;
		this.score = score;
	}

	//Méthodes
	public String getFichier() {
		return fichier;
	}

	public float getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "\n\t\t[" + fichier +
				";" + score +
				']';
	}
}