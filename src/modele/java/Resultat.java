/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele.java;

import java.util.ArrayList;

public class Resultat {
	//Attributs
	private String requete;
	private TypeRequete type;
	private ArrayList<CelluleResultat> resultats = new ArrayList<>();

	//Constructeur
	public Resultat(String requete, TypeRequete type) {
		this.requete = requete;
		this.type = type;
	}

	//Méthodes
	public void add(String fichier, float score) {
		boolean isResultatAdd = false;

		for(int i=0; (i<this.resultats.size()&&!isResultatAdd);i++){
			if(this.resultats.get(i).getScore()<score){
				this.resultats.add(i,new CelluleResultat(fichier, score));
				isResultatAdd= true;
			}
		}

		if (!isResultatAdd) {
			this.resultats.add(new CelluleResultat(fichier, score));
		}

	}

	public String getRequete() {
		return requete;
	}

	public TypeRequete getType() {
		return type;
	}

	public ArrayList<CelluleResultat> getResultats() {
		return resultats;
	}

	@Override
	public String toString() {
		return "Resultat{" +
				"\n\trequete='" + requete + '\'' +
				", \n\ttype=" + type +
				", \n\tresultats=" + resultats +
				"\n}";
	}

	//Inner class
	private class CelluleResultat{
		//Attributs
		private String fichier;
		private float score;

		//Constructeur
		private CelluleResultat(String fichier,float score){
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
}
