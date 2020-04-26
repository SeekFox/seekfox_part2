/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlDrop;
import controleur.ControlRequete;
import drop.*;
import modele.Config;
import modele.Resultat;
import modele.TypeRequete;
import processing.core.*;

import java.io.File;


public class ProcessingMain extends PApplet {
    private PApplet processing;

    private ScreenName previousScreen;
    private ScreenName currentScreen = ScreenName.MAIN;
    private SDrop drop;


    private MainScreen mainScreen;
    private LoginScreen loginScreen;
    private HistoryScreen historyScreen;
    private SearchConfigTxtScreen searchConfigTxtScreen;
    private SearchConfigSndScreen searchConfigSndScreen;
    private SearchConfigImgScreen searchConfigImgScreen;
    private LoadingScreen loadingScreen;
    private ResultsScreen resultsScreen;
    private ConfigScreen configScreen;

    private ControlRequete controlRequete = new ControlRequete();
    private ControlDrop controlDrop;

    public static void main(String... args) {
        PApplet.main("vue.ProcessingMain");
    }


    public void settings() {
        processing = this;
        size(640, 480);


    }

    public void setup() {
        PImage icon = loadImage("doc/icon.png","png");
        surface.setIcon(icon);
        surface.setTitle("SeekFox");
        mainScreen = new MainScreen(this);
        loginScreen = new LoginScreen(this);
        historyScreen = new HistoryScreen(this);
        resultsScreen = new ResultsScreen(this);
        searchConfigTxtScreen = new SearchConfigTxtScreen(this, controlRequete);
        searchConfigImgScreen = new SearchConfigImgScreen(this, controlRequete);
        searchConfigSndScreen = new SearchConfigSndScreen(this, controlRequete);

        controlDrop = new ControlDrop(searchConfigTxtScreen, searchConfigSndScreen, searchConfigImgScreen);
        loadingScreen = new LoadingScreen(this, controlRequete, resultsScreen);

        configScreen = new ConfigScreen(this);

        drop = new SDrop(this);

        controlRequete.initBus("HamsterJovial", "HamsterJovial est pret !");
    }

    public void changeScreen(){
        previousScreen = currentScreen;
        switch (currentScreen) {
            case MAIN:
                currentScreen = mainScreen.getNextScreen();
                break;
            case SEARCH_CONFIG_IMG:
                currentScreen = searchConfigImgScreen.getNextScreen();
                break;
            case SEARCH_CONFIG_SND:
                currentScreen = searchConfigSndScreen.getNextScreen();
                break;
            case LOADING:
                currentScreen = loadingScreen.getNextScreen();
                break;
            case ADMIN_CONNECTION:
                currentScreen = loginScreen.getNextScreen();
                break;
            case CONFIG:
                currentScreen = configScreen.getNextScreen();
                break;
            case HISTORY:
                currentScreen = historyScreen.getNextScreen();
                break;
            case SEARCH_CONFIG_TXT:
                currentScreen = searchConfigTxtScreen.getNextScreen();
                break;
            case RESULTS:
                currentScreen = resultsScreen.getNextScreen();
                break;
        }
    }

    public void initScreen(){

        //TODO Setup tous les init
        if(currentScreen!=previousScreen) {
            switch (currentScreen) {
                case MAIN:
                    break;
                case SEARCH_CONFIG_IMG:
                    break;
                case SEARCH_CONFIG_SND:
                    break;
                case LOADING:
                    loadingScreen.init();
                    break;
                case ADMIN_CONNECTION:
                    loginScreen.init();
                    break;
                case CONFIG:
                    break;
                case HISTORY:
                    break;
                case SEARCH_CONFIG_TXT:
                    searchConfigTxtScreen.init();
                    break;
                case RESULTS:
                    break;
            }
        }

    }



    public void draw() {
        //Check if we need to change screens
        changeScreen();
        initScreen();

        //Draw everything
        switch (currentScreen) {
            case MAIN:
                mainScreen.draw();
                break;
            case SEARCH_CONFIG_IMG:
                searchConfigImgScreen.draw();
                break;
            case SEARCH_CONFIG_SND:
                searchConfigSndScreen.draw();
                break;
            case LOADING:
                loadingScreen.draw();
                break;
            case ADMIN_CONNECTION:
                loginScreen.draw();
                break;
            case CONFIG:
                configScreen.draw();
                break;
            case HISTORY:
                historyScreen.draw();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.draw();
                break;
            case RESULTS:
                resultsScreen.draw();
                break;
        }


    }


    public void mousePressed() {
        switch (currentScreen) {
            case MAIN:
                mainScreen.mousePressed();
                break;
            case SEARCH_CONFIG_IMG:
                searchConfigImgScreen.mousePressed();
                break;
            case SEARCH_CONFIG_SND:
                searchConfigSndScreen.mousePressed();
                break;
            case ADMIN_CONNECTION:
                loginScreen.mousePressed();
                break;
            case CONFIG:
                configScreen.mousePressed();
                break;
            case HISTORY:
                historyScreen.mousePressed();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.mousePressed();
                break;
            case RESULTS:
                resultsScreen.mousePressed();
                break;
        }
    }

    public void mouseReleased() {
        switch (currentScreen) {
            case MAIN:
                mainScreen.mouseReleased();
                break;
            case SEARCH_CONFIG_IMG:
                searchConfigImgScreen.mouseReleased();
                break;
            case SEARCH_CONFIG_SND:
                searchConfigSndScreen.mouseReleased();
                break;
            case ADMIN_CONNECTION:
                loginScreen.mouseReleased();
                break;
            case CONFIG:
                configScreen.mouseReleased();
                break;
            case HISTORY:
                historyScreen.mouseReleased();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.mouseReleased();
                break;
            case RESULTS:
                resultsScreen.mouseReleased();
                break;
        }
    }

    public void keyPressed() {
        char pressedKey = key;
        switch (currentScreen) {
            case MAIN:
                break;
            case SEARCH_CONFIG_IMG:
                break;
            case SEARCH_CONFIG_SND:
                break;
            case ADMIN_CONNECTION:
                loginScreen.keyPressed(pressedKey);
                break;
            case CONFIG:
                break;
            case HISTORY:
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.keyPressed(pressedKey);
                break;
            case RESULTS:
                break;
        }
    }

    public void exit(){
        controlRequete.stop();
        super.exit();
    }

    void dropEvent(DropEvent theDropEvent) {
        controlDrop.dropEvent(theDropEvent, currentScreen);
    }

    public static void displayError(String text){
        ErrorWindow errorWindow = new ErrorWindow(text);
        errorWindow.setVisible();
    }
}
