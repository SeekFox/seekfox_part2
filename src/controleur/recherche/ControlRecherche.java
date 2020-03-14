/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import modele.java.Resultat;
import modele.java.TypeRecherche;

import java.io.*;

public class ControlRecherche {
	//Attributs
	protected String fichierResultat;
	protected String executableC;
	protected Resultat resultat;


	//Constructeur

	//Méthodes
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

		//this.runRecherche(args);

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
	}
}