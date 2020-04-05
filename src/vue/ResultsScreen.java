/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class ResultsScreen {
    private PApplet p;
    private ScreenName nextScreen = ScreenName.RESULTS;
    private ResultsViewer resultsViewer;
    private Button saveButton;
    private Button backButton;

    public ResultsScreen(PApplet p) {
        this.p = p;
        resultsViewer = new ResultsViewer(0,0,p.width, p.height - 50,p);
        saveButton = new Button(10, p.height-40, 100, 30, 255,"Sauvegarder la recherche", false, p);
        backButton = new Button(p.width-110, p.height-40, 100, 30, 255,"Retour au menu", false, p);
    }

    public void draw() {
        p.background(200);
        resultsViewer.draw();
        saveButton.display();
        backButton.display();
    }

    public void mousePressed(){
        resultsViewer.clickParsing();
        saveButton.clickParsing();
        backButton.clickParsing();
    }

    public void mouseReleased(){
        resultsViewer.release();
        if(saveButton.release())
            nextScreen = ScreenName.MAIN; //TODO Sauvegarder dans l'historique

        if(backButton.release())
            nextScreen = ScreenName.MAIN;


    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.RESULTS;
        return temp;
    }

    public void init(modele.Resultat searchResults){
        resultsViewer.init(searchResults);
    }
}
