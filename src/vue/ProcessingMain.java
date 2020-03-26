package vue;
import processing.core.*;


public class ProcessingMain extends PApplet{
    private ScreenName currentScreen = ScreenName.MAIN;
    private MainScreen mainScreen;

    public void settings(){
        size(640,480);

    }

    public void setup(){
        mainScreen = new MainScreen(this);


    }




    public void draw(){
        //Check for change of screen
        switch(currentScreen){
            case MAIN:
                currentScreen = mainScreen.getNextScreen();
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


        //Draw everything
        switch(currentScreen){
            case MAIN:
                mainScreen.draw();
                break;
            case CONFIG:


                break;
            case HISTORY:
                break;
            case SEARCH_CONFIG:
                background(0);
                currentScreen = ScreenName.MAIN;
                break;
            case RESULTS:
                break;
        }



}




    public void mousePressed(){
        switch(currentScreen){
            case MAIN:
                mainScreen.mousePressed();
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
    public void mouseReleased(){
        switch(currentScreen){
            case MAIN:
                mainScreen.mouseReleased();
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

    public void keyPressed(){
        switch(currentScreen){
            case MAIN:
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

    public static void main(String... args){
        PApplet.main("vue.ProcessingMain");
    }

}
