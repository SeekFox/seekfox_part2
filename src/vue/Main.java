package vue;

import controleur.ControlRechercheTexte;

public class Main {

	public static void main(String[] args) {
		ControlRechercheTexte controlRechercheTexte = new ControlRechercheTexte();
		controlRechercheTexte.rechercheTexte("pwd");

		controlRechercheTexte.rechercheTexte("./src/modele/recherche/texte/rechercheTexte.out ");
	}

}
