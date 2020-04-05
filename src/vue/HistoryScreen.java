
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
    public ArrayList<Button> boutonsDeRecherches;
    private int previouspx  , previouspy, previoussx, previoussy;
    private boolean isEmpty = true;
    Button backButton;
    Button plusButton;

   public  ScreenName nextScreen = ScreenName.HISTORY;


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
        sauvegarderRecherche("Recherche 5 = Résultat = ");
        sauvegarderRecherche("Recherche 6 = Résultat = ");
        sauvegarderRecherche("Recherche 7 = Résultat = ");
        sauvegarderRecherche("Recherche 8 = Résultat = ");
        sauvegarderRecherche("Recherche 9 = Résultat = ");
        sauvegarderRecherche("Recherche 10 = Résultat = ");
        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 5 = Résultat = ");
        sauvegarderRecherche("Recherche 6 = Résultat = ");
        sauvegarderRecherche("Recherche 7 = Résultat = ");
        sauvegarderRecherche("Recherche 8 = Résultat = ");
        sauvegarderRecherche("Recherche 9 = Résultat = ");
        sauvegarderRecherche("Recherche 10 = Résultat = ");
    }


    public void sauvegarderRecherche(String recherche){
        listeDeRecherches.add(recherche);
        boutonsDeRecherches.add(new Button( previouspx,previouspy ,previoussx,previoussy,255,recherche,false,p));
        previouspy += 45;
        backButton = new Button(20, p.height - 60, 100, 40,p.color(200,0,0), "Retour",false,p);
        plusButton = new Button(p.width - 120,p.height - 60, 100,40,p.color(255),"Plus",false,p);
        isEmpty = false;
    }

    public void draw(){
        p.background(200);
        if(!isEmpty) {
            for (int i = 0; i < boutonsDeRecherches.size(); i++) {
                if( i < 9){
                    boutonsDeRecherches.get(i).display();
                }
            }
            if(boutonsDeRecherches.size() > 9){
                plusButton.display();
            }
        }
        backButton.display();
    }

    public void mousePressed(){
        for(int i = 0; i < boutonsDeRecherches.size();i++){
            boutonsDeRecherches.get(i).clickParsing();
        }
        backButton.clickParsing();
        plusButton.clickParsing();
        p.mousePressed();
    }

    public void mouseReleased(){
        if(buttonIsReleased(boutonsDeRecherches)){
            nextScreen = ScreenName.RESULTS;
        }
        if(backButton.release()){
            nextScreen = ScreenName.MAIN;
        }
        if(plusButton.release()){
            for(int i = 9; i< boutonsDeRecherches.size();i++){
                previouspy = 15;
                boutonsDeRecherches.get(i).display();
            }
        }
        p.mouseReleased();
    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.HISTORY;
        return temp;
    }

    public boolean buttonIsReleased(ArrayList<Button> boutonsDeRecherches){
        boolean temp = false;
        for(int i=0 ; i< boutonsDeRecherches.size(); i++){
            temp = temp || boutonsDeRecherches.get(i).release();
        }
        return temp;
    }

}
