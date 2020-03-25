/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.requete.ControlRequete;
import modele.java.TypeRequete;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		boolean isRunning = true;
		ControlRequete controlRequete = new ControlRequete();
		Scanner sc = new Scanner(System.in);
		int choix;
		String argument = "";

		controlRequete.initBus("HamsterJovial", "HamsterJovial toujours pret");

		System.out.println("============================\n" +
				"           SEEKFOX          \n" +
				"============================");

		try {
			while (isRunning) {
				System.out.println("CHOIX \n" +
						"\t0/ Lancer Recherche MotClef\n" +
						"\t1/ Lancer Recherche Texte\n" +
						"\t2/ Quitter"
				);

				choix = sc.nextInt();
				sc.nextLine();

				if (choix == 0) {
					System.out.println("Entrez le mot clef");

					argument = sc.nextLine();

					System.out.println(argument);
					controlRequete.runRecherche(TypeRequete.MOTCLEF, argument);

					System.out.println(controlRequete.getResultat());

				} else if (choix == 1) {
					System.out.println("Entrez le chemin vers le fichier");

					argument = sc.nextLine();

					System.out.println(argument);
					controlRequete.runRecherche(TypeRequete.TEXTE, argument);

					System.out.println(controlRequete.getResultat());

				} else if (choix == 2) {
					controlRequete.stop();
					isRunning = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			controlRequete.stop();
		}

		System.out.println("Fin de Piste");
	}

}
