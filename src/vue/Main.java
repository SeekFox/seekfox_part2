package vue;

import controleur.ControlRechercheMotClef;
import controleur.ControlRechercheTexte;

public class Main {

	public static void main(String[] args) {
		ControlRechercheTexte controlRechercheTexte = new ControlRechercheTexte();

		controlRechercheTexte.rechercheTexte("./base_de_document/Texte/05-Le_Colombien_Juan_Pablo_Montoya.xml");

		controlRechercheTexte.lireFichierResultat();

		ControlRechercheMotClef controlRechercheMotClef = new ControlRechercheMotClef();

		controlRechercheMotClef.rechercheTexte("reportage");

		controlRechercheMotClef.lireFichierResultat();

	}

}
