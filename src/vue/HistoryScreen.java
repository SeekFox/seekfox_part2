
/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;
import processing.core.*;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryScreen {

    PApplet p;
    public ArrayList<String> listeDeRecherches;
    //public HashMap<Integer,Button> boutonsDeRecherches;
    public ArrayList<Button> boutonsDeRecherches;
    private int previouspx  , previouspy, previoussx, previoussy;
    Button backButton;

    ScreenName nextScreen = ScreenName.HISTORY;




    public HistoryScreen(PApplet p){
        this.p = p;
        previouspx = 15;
        previouspy = 15;
        previoussx = 610;
        previoussy = 30 ;
        listeDeRecherches = new ArrayList<String>();
        boutonsDeRecherches = new ArrayList<Button>();
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
    }

    public void sauvegarderRecherche(String recherche){
        listeDeRecherches.add(recherche);
        boutonsDeRecherches.add(new Button( previouspx,previouspy ,previoussx,previoussy,255,recherche,false,p));
        previouspy += 45;
        backButton = new Button(20, p.height - 60, 100, 40,p.color(200,0,0), "Retour",false,p);

    }

    public void draw(){
        p.background(200);
        for(int i = 0; i < boutonsDeRecherches.size();i++){
            boutonsDeRecherches.get(i).display();
        }
        backButton.display();
    }

    public void mousePressed(){
        backButton.clickParsing();
    }

    public void mouseReleased(){

        if(backButton.release()){
            nextScreen = ScreenName.MAIN;
        }
    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.HISTORY;
        return nextScreen;
    }

}
