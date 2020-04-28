/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

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
	public void remove(CelluleResultat celluleResultat){
		for (int i = 0; i < this.resultats.size(); i++) {
			 if(celluleResultat.equals(this.resultats.get(i))){
			 	this.resultats.remove(celluleResultat);
			 }

		}
	}


	public void add(String fichier, String score){
		this.add(fichier,Float.valueOf(score));
	}

	public void add(CelluleResultat celluleResultat) {

		//GERER LES DOUBLONS
		if(!resultats.contains(celluleResultat)) {
			this.add(celluleResultat.getFichier(), celluleResultat.getScore());
		}
	}

	public void add(String fichier, float score) {
		boolean isResultatAdd = false;

		for(int i=0; (i<this.resultats.size()&&!isResultatAdd);i++){
			if(fichier.equals(this.resultats.get(i).getFichier())){
				isResultatAdd=true;
			}else if(this.resultats.get(i).getScore()<score){
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

	public Resultat trier(){
		Resultat resultatTri = new Resultat(this.getRequete(), this.getType());

		for (CelluleResultat celluleResultat : resultats) {
			resultatTri.add(celluleResultat);
		}


		return resultatTri;
	}

	public void addScore(Resultat r){
		for (CelluleResultat celluleResultat : this.resultats) {
			for (CelluleResultat celluleResultatINPUT : r.getResultats()) {
				if(celluleResultat.equals(celluleResultatINPUT)){
					celluleResultat.addScore(celluleResultatINPUT.getScore());
				}
			}
		}
	}


	public Resultat intersection(Resultat r){
		System.out.println("INTERSECTION");
		System.out.println(r);
		System.out.println(this);

		Resultat intersection = new Resultat(this.getRequete() + " " + r.getRequete(), TypeRequete.MOTCLEF_COMPLEXE);

		for (CelluleResultat celluleResultat : this.resultats) {
			if(r.getResultats().contains(celluleResultat)){
				intersection.add(celluleResultat);
			}
		}


		return intersection;
	}

	public Resultat difference(Resultat r){
		System.out.println("DIFFERENCE");
		System.out.println(r);
		System.out.println(this);

		Resultat difference = new Resultat(this.getRequete() + " " + r.getRequete(), TypeRequete.MOTCLEF_COMPLEXE);

		for (CelluleResultat celluleResultat : this.getResultats()) {
			if(!r.getResultats().contains(celluleResultat)){
				difference.add(celluleResultat);
			}
		}

		return difference;
	}
}
