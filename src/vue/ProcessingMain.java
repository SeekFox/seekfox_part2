package vue;

import processing.core.*;


public class ProcessingMain extends PApplet {
    private ScreenName previousScreen;
    private ScreenName currentScreen = ScreenName.MAIN;


    private MainScreen mainScreen;
    private LoginScreen loginScreen;
    private SearchConfigScreen searchConfigScreen;
    private HistoryScreen historyScreen;

    public void settings() {
        size(640, 480);

    }

    public void setup() {
        mainScreen = new MainScreen(this);
        loginScreen = new LoginScreen(this);
        searchConfigScreen = new SearchConfigScreen(this);
        historyScreen = new HistoryScreen(this);
    }

    public void changeScreen(){
        previousScreen = currentScreen;
        switch (currentScreen) {
            case MAIN:
                currentScreen = mainScreen.getNextScreen();
                break;
            case ADMIN_CONNECTION:
                currentScreen = loginScreen.getNextScreen();
                break;
            case CONFIG:
                break;
            case HISTORY:
                historyScreen.getNextScreen();
                break;
            case SEARCH_CONFIG:
                searchConfigScreen.getNextScreen();
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
                case ADMIN_CONNECTION:
                    loginScreen.init();
                    break;
                case CONFIG:
                    break;
                case HISTORY:
                    break;
                case SEARCH_CONFIG:
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
            case ADMIN_CONNECTION:
                loginScreen.draw();
                break;
            case CONFIG:
                break;
            case HISTORY:
                historyScreen.draw();
                break;
            case SEARCH_CONFIG:
                searchConfigScreen.draw();
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
            case ADMIN_CONNECTION:
                loginScreen.mousePressed();
                break;
            case CONFIG:
                break;
            case HISTORY:
                historyScreen.mousePressed();
                break;
            case SEARCH_CONFIG:
                searchConfigScreen.mousePressed();
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
            case ADMIN_CONNECTION:
                loginScreen.mouseReleased();
                break;
            case CONFIG:
                break;
            case HISTORY:
                historyScreen.mouseReleased();
                break;
            case SEARCH_CONFIG:
                searchConfigScreen.mouseReleased();
                break;
            case RESULTS:
                break;
        }
    }

    public void keyPressed() {
        switch (currentScreen) {
            case MAIN:
                break;
            case ADMIN_CONNECTION:
                loginScreen.keyPressed();
                break;
            case CONFIG:
                break;
            case HISTORY:
                break;
            case SEARCH_CONFIG:
                break;
            case RESULTS:
                break;
        }
    }

    public static void main(String... args) {
        PApplet.main("vue.ProcessingMain");
    }

}
