/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.java.Config;
import modele.java.TypeRequete;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
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
						"\t2/ Indexation Texte\n" +
						"\t3/ Voir les configs\n" +
						"\t4/ Voir les fichiers Textes indexes\n" +
						"\t5/ Quitter"
				);

				choix = sc.nextInt();
				sc.nextLine();

				switch (choix) {
					case 0:
						System.out.println("Entrez le mot clef");

						argument = sc.nextLine();

						System.out.println(argument);
						controlRequete.runRecherche(TypeRequete.MOTCLEF, argument);

						System.out.println(controlRequete.getResultat());

						break;

					case 1:
						System.out.println("Entrez le chemin vers le fichier");

						argument = sc.nextLine();

						System.out.println(argument);
						controlRequete.runRecherche(TypeRequete.TEXTE, argument);

						break;

					case 2:
						System.out.println("[all/fichier.xml]");

						argument = sc.nextLine();

						System.out.println(argument);
						controlRequete.runIndexation(TypeRequete.TEXTE, argument);

						break;

					case 3:
						Config config = Config.getInstance();
						config.loadConfig();
						config.setPasswordAdmin("admin");
						config.majConfig();
						System.out.println(config);
						break;

					case 4:
						System.out.println("Fichiers Textes Indexes = {");
						for(String s : controlRequete.getListeFichierIndexesTexte()){
							System.out.println("\t" + s);
						}
						System.out.println("}");

						break;

					default:
						controlRequete.stop();
						isRunning = false;
						break;
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
