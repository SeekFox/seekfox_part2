package controleur;

import modele.java.TypeRecherche;


public class ControlRechercheTexte extends ControlRecherche{
	//Attributs

	//Constructeur
	public ControlRechercheTexte(){
		this.fichierResultat = "src/modele/recherche/texte/rechercheTexteOut.txt";
		this.executableC = "./src/modele/recherche/texte/rechercheTexte.out";
	}


	//Méthodes
	public void rechercheTexte(String args) {
		super.rechercheTexte(args, TypeRecherche.TEXTE);

	}
}
