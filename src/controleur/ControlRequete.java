/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import modele.java.EtatRequeteIvy;
import modele.java.Resultat;
import modele.java.TypeRequete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Classe de controle d'une requete
 */
public class ControlRequete {
	//Attributs
	private String fichierResultatRecherche = "./src/modele/c/rechercheOut.txt";
	private Resultat resultat;
	private TypeRequete type;
	private String argument;
	private Ivy bus;
	private EtatRequeteIvy etatRequeteIvy = EtatRequeteIvy.WAIT;


	//Constructeur

	//Méthodes

	/**
	 * Initialisation du bus logiciel Ivy
	 *
	 * @param name
	 * @param msg
	 * @return
	 */
	public Ivy initBus(String name, String msg) {
		bus = new Ivy(name, msg, null);
		//Lancement du Bus
		try {
			bus.start("127.255.255.255.2010");
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}

		//Abonnement à l'acquittement
		try {
			bus.bindMsg("^HamsterJovial answer=(.*)$", new IvyMessageListener() {
				@Override
				public void receive(IvyClient ivyClient, String[] strings) {
					//Control d'erreur
					if (strings.length != 1) {

						etatRequeteIvy = EtatRequeteIvy.ERROR;
						System.err.println("Impeesa a rencontré un problème.");

					}
					if(!strings[0].equals("OK")) {

						etatRequeteIvy = EtatRequeteIvy.ERROR;
						System.err.println("Impeesa a rencontré un problème.");

					} else {

						etatRequeteIvy = EtatRequeteIvy.OK;
						resultat = lireResultat();
					}


				}
			});
		} catch (IvyException e) {
			e.printStackTrace();
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}

		return bus;
	}


	/**
	 * Méthode de lancement d'une recherche
	 *
	 * @param type
	 * @param argument
	 */
	public void runRecherche(TypeRequete type, String argument) throws Exception {
		if (!this.isGoodExtension(argument, type)) {
			throw new Exception(argument + "n'est pas supporté par le moteur de recherche");
		}


		String msg = "";
		this.type = type;
		this.argument = argument;
		this.resultat = null;
		this.etatRequeteIvy = EtatRequeteIvy.WAIT;


		//Configuration du message
		switch (type) {
			case TEXTE:
			case MOTCLEF:
			case AUDIO:
			case IMAGE:
				msg = "Impeesa type=Recherche_" + type + " argument=" + argument;
				break;

			default:
				break;
		}


		//Envoi du message
		try {
			int i = bus.sendMsg(msg);
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}
	}

	/**
	 * Méthode de lancement d'une indexation
	 *
	 * @param type
	 * @param argument
	 */
	public void runIndexation(TypeRequete type, String argument) {
		String msg = "";
		this.type = type;
		this.argument = argument;
		this.etatRequeteIvy = EtatRequeteIvy.WAIT;


		//Configuration du message
		switch (type) {
			case TEXTE:
			case AUDIO:
			case IMAGE:
				msg = "Impeesa type=Indexation_" + type + " argument=" + argument;

				break;
			default:
				break;
		}


		//Envoi du message
		try {
			int i = bus.sendMsg(msg);
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}
	}

	/**
	 * Méthode de stop du bus
	 */
	public void stop() {
		//Envoi du message
		try {
			int i = bus.sendMsg("Impeesa Bye");
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}

		bus.stop();

	}

	/**
	 * Recuperation du resultat
	 *
	 * @return
	 */
	private Resultat lireResultat() {
		String[] res;
		Resultat resultat = new Resultat(argument, type);

		try (
				InputStream ips = new FileInputStream(this.fichierResultatRecherche);
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
		){

			String ligne = br.readLine();

			while ((ligne = br.readLine()) != null) {

				res = (ligne.split(";"));
				resultat.add(res[0], Float.parseFloat(res[1]));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return resultat;
	}


	/**
	 * Renvoi True si le fichier est supporté par le moteur de recherche
	 *
	 * @param fichier
	 * @param type
	 * @return
	 */
	private boolean isGoodExtension(String fichier, TypeRequete type) {
		if(fichier.equals("all") && (!type.equals(TypeRequete.MOTCLEF) && !type.equals(TypeRequete.FIN) )) return true;
		switch (type) {
			case TEXTE:
				return fichier.endsWith(".xml");

			case AUDIO:
				return fichier.endsWith(".bin");

			case IMAGE:
				return (fichier.endsWith(".bmp") || fichier.endsWith(".jpg"));

			default:
				return true;
		}

	}

	/**
	 * Lecture du resultat
	 *
	 * @return
	 * @throws Exception
	 */
	public Resultat getResultat() throws Exception {
		if (etatRequeteIvy == EtatRequeteIvy.OK) {
			return resultat;
		} else if (etatRequeteIvy == EtatRequeteIvy.WAIT) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this.getResultat();
		} else {
			throw new Exception("La requete Ivy s'est mal passée.");

		}

	}

	/**
	 * Get EtatRequeteIvy
	 * @return
	 */
	public EtatRequeteIvy getEtatRequeteIvy() {
		return etatRequeteIvy;
	}


	public ArrayList<String> getListeFichierIndexesTexte(){
		ArrayList<String> listefichiers = new ArrayList<>();

		try (
				InputStream ips = new FileInputStream("./src/modele/c/data/fichiersIndexesTexte");
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
		){

			String ligne;

			while ((ligne = br.readLine()) != null) {
				listefichiers.add(ligne);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return listefichiers;
	}
}