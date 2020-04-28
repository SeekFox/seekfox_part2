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

	public void addScore(float score){
		this.score+=score;
	}

	@Override
	public String toString() {
		return "\n\t\t[" + fichier +
				";" + score +
				']';
	}

	public boolean equals(Object o){
		if(o==null || ! (o instanceof CelluleResultat)){
			return false;
		}

		CelluleResultat celluleResultat = (CelluleResultat)o;


		return (celluleResultat.getFichier().equals(this.getFichier()));
	}
}