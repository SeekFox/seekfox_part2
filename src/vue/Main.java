package vue;

import controleur.recherche.ControlRechercheMotClef;
import controleur.recherche.ControlRechercheTexte;

public class Main {

	public static void main(String[] args) {
		ControlRechercheTexte controlRechercheTexte = new ControlRechercheTexte();
		controlRechercheTexte.rechercheTexte("./base_de_document/Texte/01.xml");
		controlRechercheTexte.lireFichierResultat();

		ControlRechercheMotClef controlRechercheMotClef = new ControlRechercheMotClef();
		controlRechercheMotClef.rechercheTexte("reportage");
		controlRechercheMotClef.lireFichierResultat();

	}

}
