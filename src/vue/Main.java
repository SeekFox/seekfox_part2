package vue;


import controleur.recherche.ControlRecherche;
import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyException;
import modele.java.TypeRecherche;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean isRunning = true;
		ControlRecherche controlRecherche = new ControlRecherche();
		Scanner sc = new Scanner(System.in);
		int choix;
		String argument = "";

		Ivy bus  = controlRecherche.initBus("HamsterJovial","HamsterJovial toujours pret");
		System.out.println("============================\n" +
						   "           SEEKFOX          \n" +
						   "============================");



		while(isRunning){
			System.out.println("CHOIX \n" +
					"\t0/ Lancer Recherche MotClef\n" +
					"\t1/ Quitter"
			);

			choix = sc.nextInt();
			sc.nextLine();

			if(choix==0){
				System.out.println("Entrez le mot clef");

				argument = sc.nextLine();

				System.out.println(argument);
				controlRecherche.runRecherche(bus, TypeRecherche.MOTCLEF,argument);

				System.out.println(controlRecherche.getResultat());



			}else if(choix==1){
				controlRecherche.runRecherche(bus, TypeRecherche.FIN,"");
				isRunning=false;
			}
		}

		bus.stop();
		System.out.println("FIN\n");
	}

}
