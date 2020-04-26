/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import modele.CelluleResultat;
import modele.EtatRequeteIvy;
import modele.Resultat;
import modele.TypeRequete;
import vue.ResultsScreen;

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
	private Resultat resultat;
	private String name;
	private String destinataire;
	private Ivy bus;
	private EtatRequeteIvy etatRequeteIvy = EtatRequeteIvy.WAIT;

	private int compteur = 0;


	//Constructeur
	public ControlRequete(String name, String destinataire){
		this.name=name;
		this.destinataire = destinataire;
	}

	//Méthodes

	/**
	 * Initialisation du bus logiciel Ivy
	 *
	 * @param name
	 * @param msg☺
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

		//Abonnement au retour d'Impeesa
		try {
			bus.bindMsg("^" + this.name +" type=(.*) file=(.*) score=(.*)$", new IvyMessageListener() {
				@Override
				public void receive(IvyClient ivyClient, String[] strings) {
					if (strings.length !=3){
						etatRequeteIvy = EtatRequeteIvy.ERROR;
						System.err.println( destinataire +" a rencontré un problème.");
					}

					switch (strings[0]){
						case "RESULT":
							resultat.add(strings[1],strings[2]);
							break;

						case "ERROR":
						default:
							etatRequeteIvy = EtatRequeteIvy.ERROR;
							System.err.println( destinataire +" a rencontré un problème.");
							break;
					}
				}
			});

			bus.bindMsg("^"+this.name+" type=(OK|ERROR)", new IvyMessageListener() {
				@Override
				public void receive(IvyClient ivyClient, String[] strings) {
					if(strings.length!=1){
						etatRequeteIvy = EtatRequeteIvy.ERROR;
						System.err.println( destinataire +" a rencontré un problème.");
					}
					switch (strings[0]){
						case "OK":
						case "END":
							etatRequeteIvy = EtatRequeteIvy.OK;
							break;

						case "ERROR":
						default:
							etatRequeteIvy = EtatRequeteIvy.ERROR;
							System.err.println( destinataire +" a rencontré un problème.");
							break;

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
		this.resultat = new Resultat(argument, type);
		this.etatRequeteIvy = EtatRequeteIvy.WAIT;



		//Configuration du message
		switch (type) {
			case TEXTE:
			case MOTCLEF:
			case AUDIO:
			case IMAGE:
			case COULEURDOMINANTE:
				msg = this.destinataire + " type=Recherche_" + type + " argument=" + argument;
				break;

			default:
				break;
		}


		//Envoi du message
		try {
			bus.sendMsg(msg);
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
		this.etatRequeteIvy = EtatRequeteIvy.WAIT;


		//Configuration du message
		switch (type) {
			case TEXTE:
			case AUDIO:
			case IMAGE:
				msg =  this.destinataire + " type=Indexation_" + type + " argument=" + argument;

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
			bus.sendMsg(this.destinataire + " Bye");
		} catch (IvyException ie) {
			System.err.println("Error : " + ie.getMessage());
			this.etatRequeteIvy = EtatRequeteIvy.ERROR;
		}
		bus.stop();



	}
	/**
	 * Renvoi True si le fichier est supporté par le moteur de recherche
	 *
	 * @param fichier
	 * @param type
	 * @return
	 */
	private boolean isGoodExtension(String fichier, TypeRequete type) {
		if (fichier.equals("all") && (!type.equals(TypeRequete.MOTCLEF) && !type.equals(TypeRequete.FIN))) return true;
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


	public static Resultat trierResultats(ArrayList<ControlRequete> listControlRequete) throws Exception{
		if(listControlRequete==null ||listControlRequete.size()==0){
			throw new Exception("La liste des ControlRequete est vide");
		}

		Resultat resultat = new Resultat(listControlRequete.get(0).getResultat().getRequete(), listControlRequete.get(0).getResultat().getType());

		for (ControlRequete controlRequete : listControlRequete) {
			Resultat r = controlRequete.getResultat();

			for (CelluleResultat celluleResultat : r.getResultats()) {
				resultat.add(celluleResultat);
			}
		}



		return resultat;
	}

	/**
	 * Lecture du resultat
	 *
	 * @return
	 * @throws Exception
	 */
	public Resultat getResultat() throws Exception {
		if (etatRequeteIvy == EtatRequeteIvy.OK) {
			compteur=0;
			return resultat;
		} else if(compteur>300){
			compteur=0;
			throw new Exception("La connexion Ivy a été rompue.");

		} else if (etatRequeteIvy == EtatRequeteIvy.WAIT) {
			try {
				Thread.sleep(10);
				compteur++;
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
	 *
	 * @return
	 */
	public EtatRequeteIvy getEtatRequeteIvy() {
		return etatRequeteIvy;
	}


	public ArrayList<String> getListeFichierIndexesTexte() {
		ArrayList<String> listefichiers = new ArrayList<>();

		try (
				InputStream ips = new FileInputStream("./impeesa/data/fichiersIndexesTexte");
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
		) {

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