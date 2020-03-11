package controleur;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;

public class ControlRechercheTexte {
	//Attributs
	private static String fichierResultat = "src/modele/recherche/texte/rechercheTexteOut.txt";


	//Constructeur

	//Méthodes
	private void runRechercheTexte(String args){
		String s;
		try {
			System.out.println("./src/modele/recherche/texte/rechercheTexte.out " + args);
			Process p = Runtime.getRuntime().exec("./src/modele/recherche/texte/rechercheTexte.out " + args);

			//Lecture du terminal
			BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));

			while((s = br.readLine())!=null){
				System.out.println(s);
			}

		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	//ToDo
	public void rechercheTexte(String args) {
		this.runRechercheTexte(args);
	}


	//ToDo
	public void lireFichierResultat(){
		ArrayList<String[]> resultats = new ArrayList<>();



		try{
			InputStream ips=new FileInputStream(this.fichierResultat);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				resultats.add(ligne.split(";"));
			}
			br.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

		for(String[] strings : resultats){
			try {
				System.out.println(strings[0] + " - " + strings[1]);
			}catch(Exception e){
				System.out.println(strings[0]);
			}
		}

	}
}
