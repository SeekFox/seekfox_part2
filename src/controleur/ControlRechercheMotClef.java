package controleur;

import modele.java.TypeRecherche;


public class ControlRechercheMotClef extends ControlRecherche{
	//Attributs

	//Constructeur
	public ControlRechercheMotClef(){
		this.fichierResultat = "src/modele/recherche/motClef/rechercheMotClefOut.txt";
		this.executableC = "./src/modele/recherche/motClef/rechercheMotClef.out";
	}


	//Méthodes
	public void rechercheTexte(String args) {
		super.rechercheTexte(args, TypeRecherche.MOT_CLEF);

	}
}
