/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import modele.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Classe de controle d'une requete Ivy
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

	/**
	 * Constructeur prennant en parametre le nom du Controleur et le nom du moteur C associé (Impeesa ou Akela)
	 * @param name
	 * @param destinataire
	 */
	public ControlRequete(String name, String destinataire){
		this.name=name;
		this.destinataire = destinataire;
	}

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

			//Acquittement
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

	/**
	 * Tri des résultats après une recherche multimoteur
	 * @param listControlRequete
	 * @return
	 * @throws Exception
	 */
	public static Resultat trierResultats(ArrayList<ControlRequete> listControlRequete) throws Exception{
		if((listControlRequete == null) || (listControlRequete.size() == 0)){
			throw new Exception("La liste des ControlRequete est vide");
		}

		Resultat resultat = new Resultat(listControlRequete.get(0).getResultat().getRequete(), listControlRequete.get(0).getResultat().getType());

		if(listControlRequete.size()==1){
			resultat = listControlRequete.get(0).getResultat();
		}else{
			for (CelluleResultat celluleResultat : listControlRequete.get(0).getResultat().getResultats()) {
				boolean isSameValue = true;

				for (int i = 1; i < listControlRequete.size(); i++) {
					if(!listControlRequete.get(i).getResultat().getResultats().contains(celluleResultat)){
						isSameValue = false;
					}
				}

				if(isSameValue){
					resultat.add(celluleResultat);
				}
			}
		}





		return resultat;
	}

	/**
	 * Lecture du resultat
	 * @return
	 * @throws Exception
	 */
	public Resultat getResultat() throws Exception {
		if (etatRequeteIvy == EtatRequeteIvy.OK) {
			compteur=0;
			return resultat;

		} else if(compteur>300){						//Délai largement supérieur au temps que met une requete a avoir une réponse
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


	/**
	 * Renvoit la liste des fichiers textes indexés
	 * @deprecated
	 * @return
	 */
	public static ArrayList<String> getListeFichierIndexesTexte() {
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


	/**
	 * Lance une recherche complexe
	 * @param requete
	 * @return
	 * @throws Exception
	 */
	public Resultat runRechercheComplexe(String requete) throws Exception {
		Resultat resultat = new Resultat(requete, TypeRequete.MOTCLEF_COMPLEXE);

		ArrayList<MotClefComplexe> motClefComplexeArrayList = new ArrayList<>();
		ArrayList<Resultat> resultatArrayList = new ArrayList<>();

		boolean isAtLeastOnePlusRequete = false;
		boolean isFirstRequete = true;

		this.resultat = new Resultat(requete, TypeRequete.MOTCLEF_COMPLEXE);
		this.etatRequeteIvy = EtatRequeteIvy.WAIT;

		// Décomposition de la chaine
		for (int i = 0; i < requete.split(" ").length; i++) {
			 motClefComplexeArrayList.add(new MotClefComplexe(requete.split(" ")[i]));

			 if(motClefComplexeArrayList.get(i).getPolarite()=='+'){
			 	isAtLeastOnePlusRequete=true;
			 }
		}

		if(!isAtLeastOnePlusRequete){
			throw new IllegalArgumentException("La requete ne contient pas de polarité positive");
		}

		//Lancer les recherches simples
		for (MotClefComplexe motClefComplexe : motClefComplexeArrayList) {
			this.runRecherche(TypeRequete.MOTCLEF, motClefComplexe.getMot());


			//Récupération des resultats
			Resultat r = this.getResultat();

			if(isFirstRequete){
				resultat = r;
			}else{

				if(motClefComplexe.getPolarite()=='+'){
					//UNION
					resultat.addScore(r);
					resultat = resultat.intersection(r);

				}else{
					//DIFFERENCE
					resultat = resultat.difference(r);
				}
			}

			isFirstRequete = false;
		}


		this.resultat = resultat.trier();

		return resultat;
	}
}