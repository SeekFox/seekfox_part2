/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele.java;

import java.io.*;

public class Config {
	//Attributs
	private String fichierConfig =  "./src/modele/c/data/user.config";

	private String passwordAdmin;
	private int TEXTE_tailleMin;
	private int TEXTE_val;
	private int TEXTE_seuil;
	private int IMAGE_nbBIts;
	private int AUDIO_n;
	private int AUDIO_m;

	private static Config INSTANCE = null;

	//Constructeur
	private Config(){

	}

	//Méthodes
	public static Config getInstance(){
		if(INSTANCE==null){
			INSTANCE = new Config();
		}

		return INSTANCE;
	}


	public void majConfig(){
		try {
			File f = new File(this.fichierConfig);
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
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void loadConfig(){
		try (
				InputStream ips = new FileInputStream(this.fichierConfig);
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
		this.TEXTE_tailleMin = TEXTE_tailleMin;
	}

	public int getTEXTE_val() {
		return TEXTE_val;
	}

	public void setTEXTE_val(int TEXTE_val) {
		this.TEXTE_val = TEXTE_val;
	}

	public int getTEXTE_seuil() {
		return TEXTE_seuil;
	}

	public void setTEXTE_seuil(int TEXTE_seuil) {
		this.TEXTE_seuil = TEXTE_seuil;
	}

	public int getIMAGE_nbBIts() {
		return IMAGE_nbBIts;
	}

	public void setIMAGE_nbBIts(int IMAGE_nbBIts) {
		this.IMAGE_nbBIts = IMAGE_nbBIts;
	}

	public int getAUDIO_n() {
		return AUDIO_n;
	}

	public void setAUDIO_n(int AUDIO_n) {
		this.AUDIO_n = AUDIO_n;
	}

	public int getAUDIO_m() {
		return AUDIO_m;
	}

	public void setAUDIO_m(int AUDIO_m) {
		this.AUDIO_m = AUDIO_m;
	}
}
