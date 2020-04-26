package modele;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Historique {
	
	public static void addHistorique (Resultat recherche) {
		/* Ajoute une recherche au fichier d'historique */
		try {
			File f = new File("historique.txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f, true);
			fw.write("<recherche>"+recherche.toString()+"<\\recherche>\n");
			fw.flush();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<Resultat> getHistorique () {
		/* R�cup�re une liste de String (� adapter �ventuellement) contenant tout l'historique des recherches effectu�es */
		String s = "";
		try {
			File f = new File("historique.txt");
			f.createNewFile();
			FileReader fr = new FileReader(f);

			int c = fr.read();
			while(c>=0) {
				s += (char)c;
				c = fr.read();
			}
			fr.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (s.length()>0) {
			List<String> recherches =  cut(s);		// S�pare chaque recherche de l'historique
			List<Resultat> resultats = new ArrayList<Resultat>();
			for(String str:recherches) resultats.add(toResultat(str));		// Pour chaque recherche de l'historique, fait une conversion String->Resultat
			return resultats;
		} else return null;
	}
	
	public static void resetHistorique () {
		/* Supprime l'historique actuel */
		try {
			File f = new File("historique.txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f, false);
			fw.write("");
			fw.flush();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static List<String> cut (String s) {
		
		List<String> liste = new ArrayList<String>();
		char[] histo = s.toCharArray();
		
		boolean inBalises = false;		// Dit si le caract�re actuel est entre deux balises
		boolean isBalise = false;		// Dit si le caract�re appartient � une balise
		int i=0;
		
		String recherche = "";			// Cha�ne de caract�res contenant une recherche
		String balise = "";				// Cha�ne de caract�res contenant une balise
		
		while (i<s.length()) {
			
			char c = histo[i];		// Caract�re courant
			
			if(c=='<' && isBalise==false) isBalise=true;	// D'abord on v�rifie que le caract�re ne nous fait pas entrer dans une balise
			
			if(inBalises==true && isBalise==false) recherche+=c;		// On ajoute le caract�re o� il faut selon la configuration actuelle
			if(isBalise==true) balise+=c;
			
			if(c=='>' && isBalise==true) {		// Si le caract�re lu marque la fin d'une balise, on agit selon la balise : nouvelle recherche, ou fin d'une recherche
				if(balise.equals("<recherche>")) inBalises=true;
				if(balise.equals("<\\recherche>")) {
					inBalises=false;
					liste.add(recherche);
					recherche = "";
				}
				balise="";
				isBalise=false;
			}
			
			i++;
		}
		
		return liste;
	}
	
	private static Resultat toResultat (String recup) {
		char[] aconvertir = recup.toCharArray();
		
		// R�cup�ration de l'intitul� de la requ�te
		String requete = "";
		int i=0;
		while(aconvertir[i]!='\'' && i<aconvertir.length) i++;
		i++;
		while(aconvertir[i]!='\'' && i<aconvertir.length) {
			requete+=aconvertir[i];
			i++;
		}
		i++;
		
		// R�cup�ration du type de recherche
		String typeS = "";
		while(aconvertir[i]!='=' && i<aconvertir.length) i++;
		i++;
		while(aconvertir[i]!=',' && i<aconvertir.length) {
			typeS+=aconvertir[i];
			i++;
		}
		i++;
		TypeRequete type;
		switch(typeS) {
		case "TEXTE" : 
			type = TypeRequete.TEXTE;
			break;
		case "AUDIO" : 
			type = TypeRequete.AUDIO;
			break;
		case "IMAGE" : 
			type = TypeRequete.IMAGE;
			break;
		case "MOTCLEF" : 
			type = TypeRequete.MOTCLEF;
			break;
		default :
			type = TypeRequete.FIN;
			break;
		}
		
		// Cr�ation du r�sultat de la recherche
		Resultat res = new Resultat(requete, type);
		
		while(aconvertir[i]!='[' && i<aconvertir.length) i++;		// On se d�place jusqsu'au d�but de la liste des r�sultats
		i++;
		// R�cup�ration de tous les r�sultats de la recherche
		while(i<aconvertir.length) {
			if(aconvertir[i]=='[') {
				i++;
				String fichier = "";
				String scoreS = "";
				while(aconvertir[i]!=';' && i<aconvertir.length) {
					fichier+=aconvertir[i];
					i++;
				}
				i++;
				while(aconvertir[i]!=']' && i<aconvertir.length) {
					scoreS+=aconvertir[i];
					i++;
				}
				float score = Float.parseFloat(scoreS);
				res.add(fichier, score);
			} else i++;
		}
		
		return res;
	}
	
	
	
	/*public static void main (String[] args) {
		Resultat rech = new Resultat("Test", TypeRequete.AUDIO);
		rech.add("fich1", 90);
		rech.add("fich2", 50);
		addHistorique(rech);
		List<Resultat> liste = getHistorique();
		for(Resultat s:liste) {
			System.out.println(s.toString()+"\n");
		}
		resetHistorique();
	}*/
}
