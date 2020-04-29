/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

public class TypeRecherche {
	//Attributs
	private static TypeRecherche INSTANCE = null;
	private TypeRequete typeRequete;
	private boolean isMultimoteur = false;
	private String requete = "";


	//Constructeur
	private TypeRecherche(){

	}

	//Méthodes
	public static TypeRecherche getINSTANCE(){
		if(INSTANCE==null){
			INSTANCE = new TypeRecherche();
		}

		return INSTANCE;
	}


	public void setTypeRequete(TypeRequete typeRequete){
		this.typeRequete=typeRequete;
	}

	public TypeRequete getTypeRequete(){
		return typeRequete;
	}

	public boolean isMultimoteur() {
		return isMultimoteur;
	}

	public void setMultimoteur(boolean multimoteur) {
		isMultimoteur = multimoteur;
	}

	public String getRequete() {
		return requete;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}
}
