/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

/**
 * Cellule d'un resultat d'une requete
 * @see Resultat
 */
public class CelluleResultat{
	//Attributs
	private String fichier;
	private float score;

	//Constructeur

	/**
	 * Constructeur d'une cellule
	 * @param fichier
	 * @param score
	 */
	public CelluleResultat(String fichier,float score){
		this.fichier=fichier;
		this.score = score;
	}

	//Méthodes

	/**
	 *
	 * @return
	 */
	public String getFichier() {
		return fichier;
	}

	/**
	 *
	 * @return
	 */
	public float getScore() {
		return score;
	}

	/**
	 * Ajoute score au score de la cellule
	 * @param score
	 */
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