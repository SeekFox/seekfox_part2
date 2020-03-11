package controleur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;

public class ControlRechercheTexte {
	//Attributs

	//Constructeur

	//Méthodes
	public void rechercheTexte(String recherche) {
		String s;
		try {
			Process p = Runtime.getRuntime().exec(recherche);
			BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));

			while((s = br.readLine())!=null){
				System.out.println(s);
			}


		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
