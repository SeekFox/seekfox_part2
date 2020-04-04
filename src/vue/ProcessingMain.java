package vue;

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
            case ADMIN_CONNECTION:
                currentScreen = loginScreen.getNextScreen();
                break;
            case CONFIG:
                break;
            case HISTORY:
                currentScreen = historyScreen.getNextScreen();
                break;
            case SEARCH_CONFIG_TXT:
                currentScreen = searchConfigTxtScreen.getNextScreen();
                break;
            case RESULTS:
                break;
        }
    }

    public void initScreen(){
        if(currentScreen!=previousScreen) {
            switch (currentScreen) {
                case MAIN:
                    break;
                case SEARCH_CONFIG_IMG:
                    break;
                case SEARCH_CONFIG_SND:
                    break;
                case ADMIN_CONNECTION:
                    loginScreen.init();
                    break;
                case CONFIG:
                    break;
                case HISTORY:
                    break;
                case SEARCH_CONFIG_TXT:
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
            case ADMIN_CONNECTION:
                loginScreen.draw();
                break;
            case CONFIG:
                break;
            case HISTORY:
                historyScreen.draw();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.draw();
                break;
            case RESULTS:
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
                break;
            case HISTORY:
                historyScreen.mousePressed();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.mousePressed();
                break;
            case RESULTS:
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
                break;
            case HISTORY:
                historyScreen.mouseReleased();
                break;
            case SEARCH_CONFIG_TXT:
                searchConfigTxtScreen.mouseReleased();
                break;
            case RESULTS:
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
