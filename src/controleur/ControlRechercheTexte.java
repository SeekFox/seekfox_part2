package controleur;

import modele.java.Resultat;
import modele.java.TypeRecherche;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;

public class ControlRechercheTexte {
	//Attributs
	private static String fichierResultat = "src/modele/recherche/texte/rechercheTexteOut.txt";
	private Resultat resultat;


	//Constructeur

	//Méthodes
	private void runRechercheTexte(String args){
		String s;
		try {
			System.out.println("./src/modele/recherche/texte/rechercheTexte.out " + args) ;
			Process p = Runtime.getRuntime().exec("./src/modele/recherche/texte/rechercheTexte.out " + args);

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

	public void rechercheTexte(String args) {
		this.resultat = new Resultat(args, TypeRecherche.TEXTE);

		this.runRechercheTexte(args);

	}


	//ToDo
	public void lireFichierResultat(){
		String res[];

		try{
			InputStream ips=new FileInputStream(this.fichierResultat);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);

			String ligne = br.readLine();

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
}
