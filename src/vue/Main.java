package vue;

import controleur.ControlRechercheTexte;

public class Main {

	public static void main(String[] args) {
		ControlRechercheTexte controlRechercheTexte = new ControlRechercheTexte();

		controlRechercheTexte.rechercheTexte("./base_de_document/Texte/05-Le_Colombien_Juan_Pablo_Montoya.xml\n");

		controlRechercheTexte.lireFichierResultat();

	}

}
