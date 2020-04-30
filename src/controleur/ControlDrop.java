/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import drop.DropEvent;
import modele.ScreenName;
import vue.SearchConfigImgScreen;
import vue.SearchConfigSndScreen;
import vue.SearchConfigTxtScreen;

import java.io.File;

/**
 * Classe de controle au Drop d'un fichier dans la fenetre
 * @see drop.SDrop
 */
public class ControlDrop {
	//Attributs
	private SearchConfigImgScreen searchConfigImgScreen;
	private SearchConfigSndScreen searchConfigSndScreen;
	private SearchConfigTxtScreen searchConfigTxtScreen;


	//Constructeur

	/**
	 *
	 * @param searchConfigTxtScreen
	 * @param searchConfigSndScreen
	 * @param searchConfigImgScreen
	 */
	public ControlDrop(SearchConfigTxtScreen searchConfigTxtScreen, SearchConfigSndScreen searchConfigSndScreen, SearchConfigImgScreen searchConfigImgScreen){
		this.searchConfigImgScreen = searchConfigImgScreen;
		this.searchConfigSndScreen = searchConfigSndScreen;
		this.searchConfigTxtScreen = searchConfigTxtScreen;
	}

	//Méthodes

	/**
	 * Méthode levée lors d'un drop
	 * @param theDropEvent
	 * @param currentScreen
	 */
	public void dropEvent(DropEvent theDropEvent, ScreenName currentScreen) {
		if(isGoodType(theDropEvent.file(), currentScreen)){
			File file = theDropEvent.file();

			switch(currentScreen){
				case SEARCH_CONFIG_TXT:
					searchConfigTxtScreen.setArgumentRecherche(file);
					System.out.println("Drop Txt");
					break;
				case SEARCH_CONFIG_IMG:
					searchConfigImgScreen.setArgumentRecherche(file);
					System.out.println("Drop img");
					break;
				case SEARCH_CONFIG_SND:
					searchConfigSndScreen.setArgumentRecherche(file);
					System.out.println("Drop snd");
					break;
				default:
					break;

			}
		}



	}


	/**
	 * Retourne vrai si l'extension du fichier est bonne suivant l'écran où on est.
	 * @param file
	 * @param screenName
	 * @return
	 */
	private boolean isGoodType(File file, ScreenName screenName){
		switch (screenName){
			case SEARCH_CONFIG_TXT:
				return (file.getName().endsWith(".xml"));

			case SEARCH_CONFIG_SND:
				return (file.getName().endsWith(".bin"));

			case SEARCH_CONFIG_IMG:
				return (file.getName().endsWith(".jpg") || file.getName().endsWith(".bmp"));

			default:
				return false;
		}
	}


}
