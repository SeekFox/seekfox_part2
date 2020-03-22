/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyException;
import modele.java.Resultat;
import modele.java.TypeRecherche;

import java.io.*;

public class ControlRecherche {
	//Attributs

	private String fichierResultat;
	private Resultat resultat;


	//Constructeur

	//Méthodes
	public static void runRecherche(Ivy bus, TypeRecherche type, String argument){
		String msg = "";



		//Configuration du message
		switch (type){
			case MOT_CLEF:
				msg = "Impeesa type=Recherche_MotClef argument="+argument;
				break;

			default:
				break;
		}


		//Envoi du message
		try{
			int i = bus.sendMsg(msg);
			System.out.println("clients = " + i);
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
		}


	}



/*
	private void runRecherche(String args){
		String s;
		try {
			Process p = Runtime.getRuntime().exec(executableC + " " + args);

			//Lecture du terminal
			BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));

			while((s = br.readLine())!=null){
				System.out.println(s);
			}

			br.close();

		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	public void recherche(String args, TypeRecherche type) {
		this.resultat = new Resultat(args, type);

		this.runRecherche(args);

		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			this.lireFichierResultat();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void lireFichierResultat() throws IOException {
		String[] res;

		try(
				InputStream ips = new FileInputStream(this.fichierResultat);
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
				){

			String ligne = br.readLine();

			while ((ligne=br.readLine())!=null){

				res = (ligne.split(";"));
				this.resultat.add(res[0],Float.parseFloat(res[1]));
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		System.out.println(this.resultat);

	}

	@Override
	public String toString() {
		return "ControlRecherche{" +
				"fichierResultat='" + fichierResultat + '\'' +
				", executableC='" + executableC + '\'' +
				", resultat=" + resultat +
				'}';
	}*/
}