/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.ScreenName;
import modele.TypeRecherche;
import processing.core.PApplet;

import java.util.ArrayList;

public class LoadingScreen {
    private PApplet p;
    private ScreenName nextScreen = ScreenName.LOADING;
    private int loadingSize =0;
    private ArrayList<ControlRequete> listControlRequete;
    private ResultsScreen resultsScreen;

    public LoadingScreen(PApplet p, ArrayList<ControlRequete> listControlRequete, ResultsScreen resultsScreen) {
        this.p = p;
        this.listControlRequete = listControlRequete;
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

            //ToDo : Multimoteur
            if(TypeRecherche.getINSTANCE().isMultimoteur()){
                resultsScreen.init(ControlRequete.trierResultats(listControlRequete));
                System.out.println(ControlRequete.trierResultats(listControlRequete).toString());
            }else{
                ControlRequete.trierResultats(listControlRequete);
                resultsScreen.init(listControlRequete.get(0).getResultat());
            }


        } catch (Exception e) {
            ProcessingMain.displayError("La connexion Ivy est rompue.");
            nextScreen = ScreenName.MAIN;
            //On leve l'erreur ici
            e.printStackTrace();
        }
    }
}
