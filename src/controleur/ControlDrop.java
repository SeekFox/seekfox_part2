/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur;

import drop.DropEvent;
import vue.ScreenName;
import vue.SearchConfigImgScreen;
import vue.SearchConfigSndScreen;
import vue.SearchConfigTxtScreen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ControlDrop {
	//Attributs
	private SearchConfigImgScreen searchConfigImgScreen;
	private SearchConfigSndScreen searchConfigSndScreen;
	private SearchConfigTxtScreen searchConfigTxtScreen;


	//Constructeur
	public ControlDrop(SearchConfigTxtScreen searchConfigTxtScreen, SearchConfigSndScreen searchConfigSndScreen, SearchConfigImgScreen searchConfigImgScreen){
		this.searchConfigImgScreen = searchConfigImgScreen;
		this.searchConfigSndScreen = searchConfigSndScreen;
		this.searchConfigTxtScreen = searchConfigTxtScreen;
	}

	//Méthodes
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
