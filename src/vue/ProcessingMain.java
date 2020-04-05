package vue;

import modele.Config;
import modele.Resultat;
import modele.TypeRequete;
import processing.core.*;


public class ProcessingMain extends PApplet {
    private ScreenName previousScreen;
    private ScreenName currentScreen = ScreenName.MAIN;


    private MainScreen mainScreen;
    private LoginScreen loginScreen;
    private HistoryScreen historyScreen;
    private SearchConfigTxtScreen searchConfigTxtScreen;
    private SearchConfigSndScreen searchConfigSndScreen;
    private SearchConfigImgScreen searchConfigImgScreen;
    private LoadingScreen loadingScreen;
    private ResultsScreen resultsScreen;
    private ConfigScreen configScreen;


    public void settings() {
        size(640, 480);

    }

    public void setup() {
        mainScreen = new MainScreen(this);
        loginScreen = new LoginScreen(this);
        historyScreen = new HistoryScreen(this);
        searchConfigTxtScreen = new SearchConfigTxtScreen(this);
        searchConfigImgScreen = new SearchConfigImgScreen(this);
        searchConfigSndScreen = new SearchConfigSndScreen(this);
        loadingScreen = new LoadingScreen(this);
        resultsScreen = new ResultsScreen(this);
        configScreen = new ConfigScreen(this);
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
                    Resultat testResults =  new Resultat("ptdr", TypeRequete.TEXTE);
                    testResults.add("Fichier 1", 0.50f);
                    testResults.add("Fichier 2", 0.40f);
                    testResults.add("Fichier 3", 0.30f);
                    testResults.add("Fichier 4", 0.20f);
                    testResults.add("Fichier 5", 0.10f);
                    testResults.add("Fichier 6", 0.09f);
                    testResults.add("Fichier 7", 0.08f);
                    testResults.add("Fichier 8", 0.07f);
                    testResults.add("Fichier 9", 0.06f);
                    testResults.add("Fichier 10", 0.05f);
                    testResults.add("Fichier 11", 0.04f);
                    testResults.add("Fichier 12", 0.03f);
                    testResults.add("Fichier 13", 0.02f);
                    testResults.add("Fichier 14", 0.01f);
                    testResults.add("Fichier 15", 0.001f);
                    resultsScreen.init(testResults);
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

    public static void main(String... args) {
        PApplet.main("vue.ProcessingMain");
    }

}
