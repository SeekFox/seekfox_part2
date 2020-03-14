/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import modele.java.Resultat;
import modele.java.TypeRecherche;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ControlRecherche {
	//Attributs
	protected String fichierResultat;
	protected String executableC;
	protected Resultat resultat;


	//Constructeur

	//Méthodes
	private void runRechercheTexte(String args){
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

	public void rechercheTexte(String args, TypeRecherche type) {
		this.resultat = new Resultat(args, type);

		//this.runRechercheTexte(args);

	}

	public void lireFichierResultat(){
		String res[];

		try{
			InputStream ips=new FileInputStream(this.fichierResultat);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);

			br.readLine();
			String ligne;

			while ((ligne=br.readLine())!=null){

				res = (ligne.split(";"));
				this.resultat.add(res[0],Float.parseFloat(res[1]));
			}
			br.close();
			ips.close();
			ipsr.close();
		}
		catch (Exception e){
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