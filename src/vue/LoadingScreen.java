/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import processing.core.PApplet;

public class LoadingScreen {
    private PApplet p;
    private ScreenName nextScreen = ScreenName.LOADING;
    private int loadingSize =0;
    private ControlRequete controlRequete;
    private ResultsScreen resultsScreen;

    public LoadingScreen(PApplet p, ControlRequete controlRequete, ResultsScreen resultsScreen) {
        this.p = p;
        this.controlRequete = controlRequete;
        this.resultsScreen = resultsScreen;

    }

    public void draw() {
        p.background(200);
        p.rectMode(p.CORNER);
        p.fill(0);
        p.rect(30,400, p.width-60, 40);
        p.fill(255);
        p.rect(30,400, loadingSize, 40);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Veuillez patienter, SeekFox travaille", p.width/2f, 380);
        loadingSize+=5;

        if(loadingSize >= p.width-60)
            nextScreen = ScreenName.RESULTS;
    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.LOADING;
        return temp;
    }

    public void init(){
        loadingSize = 0;

        // Recuperation des resultats
        try {
            resultsScreen.init(controlRequete.getResultat());
            System.out.println(controlRequete.getResultat().toString());
        } catch (Exception e) {
            ProcessingMain.displayError("La connexion Ivy est rompue.");
            //On leve l'erreur ici
            e.printStackTrace();
        }
    }
}
