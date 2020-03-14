package vue;

import controleur.recherche.ControlRechercheMotClef;
import controleur.recherche.ControlRechercheTexte;

public class Main {

	public static void main(String[] args) {
		ControlRechercheTexte controlRechercheTexte = new ControlRechercheTexte();
		controlRechercheTexte.recherche("./base_de_document/Texte/01.xml");

		ControlRechercheMotClef controlRechercheMotClef = new ControlRechercheMotClef();
		controlRechercheMotClef.recherche("reportage");

	}

}
