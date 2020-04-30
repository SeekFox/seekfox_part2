/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;


import controleur.*;
import modele.*;
import modele.Resultat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Classe Main de test
 * @deprecated
 * @see ProcessingMain
 */
public class Main {
	public static void main(String[] args) {
		boolean isRunning = true;
		ArrayList<ControlRequete> listControlRequete = new ArrayList<>();


		listControlRequete.add(new ControlRequete("HamsterJovial", "Impeesa"));
		listControlRequete.get(0).initBus("HamsterJovial", "HamsterJovial est pret !");

		listControlRequete.add(new ControlRequete("Baloo", "Akela"));
		listControlRequete.get(1).initBus("Baloo", "Baloo est pret !");

		Scanner sc = new Scanner(System.in);
		int choix;


		//NEW VERSION
		ArrayList<MotClefComplexe> motClefComplexeArrayList = new ArrayList<>();




		int choixAdd;
		String polarite = "";
		String chaineRecherche = "";
		String argument = "";
		String tabChaine[];
		String tabPolarite[];
		String tabMot[];
		int indiceList = 0;
		boolean testPolarite = true;
		boolean conditionTailleTab;
		boolean conditionPolarite1Mot;
		ArrayList<ArrayList<String>> listCritere = new ArrayList<>();
		ArrayList<Resultat> listResultat = new ArrayList<>();
		ArrayList<CelluleResultat> listResultatApresComparaison = new ArrayList<>();
		ArrayList<String> listeFichierIndexesTexte = new ArrayList<>();


		System.out.println("============================\n" +
				"           SEEKFOX          \n" +
				"============================");



		while(isRunning){
			System.out.println("CHOIX \n" +
					"\t0/ Lancer Recherche Simple 	MotClef\n" +
					"\t1/ Lancer Recherche Complexe MotClef\n" +
					"\t2/ Quitter"
			);

			choix = sc.nextInt();
			sc.nextLine();

			switch(choix){
				case 0:
					listeFichierIndexesTexte = ControlRequete.getListeFichierIndexesTexte();
					System.out.println("Liste= " + listeFichierIndexesTexte.get(0));
					break;

				case 1:
					System.out.println("Entrer la recherche complexe");
					chaineRecherche = sc.nextLine();

					for (ControlRequete controlRequete : listControlRequete) {
						try {
							System.out.println(controlRequete.runRechercheComplexe(chaineRecherche));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					break;

				default:
					isRunning = false;
					break;
			}
		}

		for (ControlRequete controlRequete : listControlRequete) {
			controlRequete.stop();
		}
		System.out.println("FIN\n");
	}

}
