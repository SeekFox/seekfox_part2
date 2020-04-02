/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import modele.Config;

/**
 * Classe de control des Configs
 */
public class ControlConfig {
	//Attributs
	private ControlRequete controlRequete;
	private Config config = Config.getInstance();


	//Constructeur
	public ControlConfig(ControlRequete controlRequete) {
		this.controlRequete = controlRequete;
	}


	//Méthodes
	public void truc(){

	}

}
