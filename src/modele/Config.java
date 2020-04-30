/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

import java.io.*;
import java.util.ArrayList;

/**
 * Singleton des configuration des moteurs
 */
public class Config {
	//Attributs
	private ArrayList<String> configArrayList = new ArrayList<>();

	private String passwordAdmin;
	private int TEXTE_tailleMin;
	private int TEXTE_val;
	private int TEXTE_seuil;
	private int IMAGE_nbBIts;
	private int AUDIO_n;
	private int AUDIO_m;

	private static Config INSTANCE = null;

	//Constructeur

	/**
	 * Constructeur des configs
	 */
	private Config(){
		this.configArrayList.add("./impeesa/data/user.config");
		this.configArrayList.add("./akela/data/user.config");

	}

	//Méthodes
	public static Config getInstance(){
		if(INSTANCE==null){
			INSTANCE = new Config();
		}

		return INSTANCE;
	}


	/**
	 * Mises à jour des configurations des moteurs
	 */
	public void majConfig(){
		try {
			for (String config : configArrayList) {
				File f = new File(config);
				f.createNewFile();

				//Vider le fichier
				FileWriter fw;
				fw = new FileWriter(f, false);
				fw.write("");
				fw.flush();
				fw.close();

				//Ecrire dans le fichier
				fw = new FileWriter(f, true);
				fw.write(this.passwordAdmin+"\n" +
						this.TEXTE_tailleMin + "\n" +
						this.TEXTE_val + "\n" +
						this.TEXTE_seuil + "\n" +
						this.IMAGE_nbBIts + "\n" +
						this.AUDIO_n + "\n" +
						this.AUDIO_m + "\n"
				);
				fw.flush();
				fw.close();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}


	/**
	 * Récupération des configurations des moteurs
	 */
	public void loadConfig(){
		try (
				InputStream ips = new FileInputStream(this.configArrayList.get(0));
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr)
		){

			String ligne;
			int i=0;
			while ((ligne = br.readLine()) != null) {
				switch(i){
					case 0:
						this.passwordAdmin = ligne;
						break;

					case 1:
						this.TEXTE_tailleMin =Integer.parseInt(ligne);
						break;

					case 2:
						this.TEXTE_val =Integer.parseInt(ligne);
						break;

					case 3:
						this.TEXTE_seuil =Integer.parseInt(ligne);
						break;

					case 4:
						this.IMAGE_nbBIts =Integer.parseInt(ligne);
						break;

					case 5:
						this.AUDIO_n =Integer.parseInt(ligne);
						break;

					case 6:
						this.AUDIO_m =Integer.parseInt(ligne);
						break;

					default:
						break;
				}
				i++;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public String toString() {
		return "Config{" +
				"passwordAdmin='" + passwordAdmin + '\'' +
				", TEXTE_tailleMin=" + TEXTE_tailleMin +
				", TEXTE_val=" + TEXTE_val +
				", TEXTE_seuil=" + TEXTE_seuil +
				", IMAGE_nbBIts=" + IMAGE_nbBIts +
				", AUDIO_n=" + AUDIO_n +
				", AUDIO_m=" + AUDIO_m +
				'}';
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}

	public int getTEXTE_tailleMin() {
		return TEXTE_tailleMin;
	}

	public void setTEXTE_tailleMin(int TEXTE_tailleMin) {
		if(TEXTE_tailleMin>0){
			this.TEXTE_tailleMin = TEXTE_tailleMin;
		}
	}

	public int getTEXTE_val() {
		return TEXTE_val;
	}

	public void setTEXTE_val(int TEXTE_val) {
		if(TEXTE_val>0) {
			this.TEXTE_val = TEXTE_val;
		}
	}

	public int getTEXTE_seuil() {
		return TEXTE_seuil;
	}

	public void setTEXTE_seuil(int TEXTE_seuil) {
		if(TEXTE_seuil>0){
			this.TEXTE_seuil = TEXTE_seuil;
		}
	}

	public int getIMAGE_nbBIts() {
		return IMAGE_nbBIts;
	}

	public void setIMAGE_nbBIts(int IMAGE_nbBIts) {
		if(IMAGE_nbBIts>0)
		{
			this.IMAGE_nbBIts = IMAGE_nbBIts;
		}
	}

	public int getAUDIO_n() {
		return AUDIO_n;
	}

	public void setAUDIO_n(int AUDIO_n) {
		if(AUDIO_n>0){
			this.AUDIO_n = AUDIO_n;
		}
	}

	public int getAUDIO_m() {
		return AUDIO_m;
	}

	public void setAUDIO_m(int AUDIO_m) {
		if(AUDIO_m>0){
			this.AUDIO_m = AUDIO_m;
		}
	}
}
