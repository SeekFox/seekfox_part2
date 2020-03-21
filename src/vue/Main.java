package vue;


import controleur.recherche.ControlRecherche;
import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyException;
import modele.java.TypeRecherche;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean isRunning = true;
		Scanner sc = new Scanner(System.in);
		int choix;

		Ivy bus  = new Ivy("Hamster Jovial","Hamster Jovial est prêt",null);
		//Lancement du Bus
		try{
			bus.start("127.255.255.255.2010");
		}catch(IvyException ie){
			System.err.println("Error : " + ie.getMessage());
		}

		while(isRunning){



			System.out.println("CHOIX \n" +
							   "\t0/ Lancer Recherche MotClef\n" +
								"\t1/ Quitter" +
					""			);

			choix = sc.nextInt();

			if(choix==0){
				System.out.println("Envoi du message");
				ControlRecherche.runRecherche(bus, TypeRecherche.MOT_CLEF,"Paris");

			}else if(choix==1){
				isRunning=false;
			}
		}
		System.out.println("FIN\n");
	}

}
