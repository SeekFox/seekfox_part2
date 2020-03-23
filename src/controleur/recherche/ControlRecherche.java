/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.recherche;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import modele.java.Resultat;
import modele.java.TypeRecherche;

import java.io.*;

public class ControlRecherche {
	//Attributs

	private String fichierResultat = "./src/modele/c/rechercheOut.txt";
	private Resultat resultat;
	private TypeRecherche type;
	private String argument;


	//Constructeur

	//Méthodes
	public Ivy initBus(String name, String msg){
		Ivy bus  = new Ivy(name,msg,null);
		//Lancement du Bus
		try{
			bus.start("127.255.255.255.2010");
		}catch(IvyException ie){
			System.err.println("Error : " + ie.getMessage());
		}

		//Abonnement à l'acquittement
		try {
			bus.bindMsg("^HamsterJovial answer=(.*)$", new IvyMessageListener() {
				@Override
				public void receive(IvyClient ivyClient, String[] strings) {
					//Control d'erreur
					if(strings.length!=1){
						System.err.println("Impeesa a rencontré un problème.");
					}
					if(!strings[0].equals("OK")){
						System.err.println("Impeesa a rencontré un problème.");
					}else{
						resultat = lireResultat();
					}



				}
			});
		} catch (IvyException e) {
			e.printStackTrace();
		}

		return bus;
	}


	public void runRecherche(Ivy bus, TypeRecherche type, String argument){
		String msg = "";
		this.type = type;
		this.argument = argument;
		this.resultat = null;


		//Configuration du message
		switch (type){
			case MOTCLEF:
				msg = "Impeesa type=Recherche_" + type + " argument="+argument;
				break;
			case FIN :
				msg = "Impeesa Bye";
				break;

			default:
				break;
		}


		//Envoi du message
		try{
			int i = bus.sendMsg(msg);
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
		}
	}

	private Resultat lireResultat(){
		String[] res;
		Resultat resultat = new Resultat(argument,type);

		try(
				InputStream ips = new FileInputStream(this.fichierResultat);
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
		){

			String ligne = br.readLine();

			while ((ligne=br.readLine())!=null){

				res = (ligne.split(";"));
				resultat.add(res[0],Float.parseFloat(res[1]));
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		return resultat;
	}

	public Resultat getResultat() {
		if(resultat!=null) {
			return resultat;
		}else{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this.getResultat();
		}

	}
}