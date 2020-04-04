/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class SearchConfigScreen {

    PApplet p;
    Button rechercheMotClef;
    Button rechercheTexte;
    Button rechercheAudio;
    Button rechercheImage;
    Button rechercheCouleurDominante;
    Button requêteSimple;
    Button requêteComplexe;
    Button backButton;
    ScreenName nextScreen = ScreenName.SEARCH_CONFIG;

    public SearchConfigScreen(PApplet p){
        this.p = p;
        rechercheMotClef = new Button(15,15,150,30,255,"RechercheMotClef",false,p);
        rechercheTexte = new Button(15,60,150,30,255,"RechercheTexte",false,p);
        rechercheAudio = new Button(15,105,150,30,255,"rechercheAudio",false,p);
        rechercheImage = new Button(15,150,150,30,255,"RechercheImage",false,p);
        rechercheCouleurDominante = new Button(15,195,150,30,255,"RechercheCouleurDominante",false,p);
        requêteSimple = new Button(15,240,150,30,255,"ReqêteSimple",false,p);
        requêteComplexe = new Button(15,285,150,30,255,"RequêteComplexe",false,p);
        backButton = new Button(20, p.height - 60, 100, 40,p.color(200,0,0), "Retour",false,p);
    }

    public void draw(){
        p.background(200);
        rechercheMotClef.display();
        rechercheTexte.display();
        rechercheAudio.display();
        rechercheImage.display();
        rechercheCouleurDominante.display();
        requêteSimple.display();
        requêteComplexe.display();
        backButton.display();
    }

    public void mousePressed(){
        rechercheMotClef.clickParsing();
        rechercheTexte.clickParsing();
        rechercheAudio.clickParsing();
        rechercheImage.clickParsing();
        rechercheCouleurDominante.clickParsing();
        requêteSimple.clickParsing();
        requêteComplexe.clickParsing();
        backButton.clickParsing();
    }

    public void mouseReleased(){
        rechercheMotClef.release();
        rechercheTexte.release();
        rechercheAudio.release();
        rechercheImage.release();
        rechercheCouleurDominante.release();
        requêteSimple.release();
        requêteComplexe.release();
        backButton.release();
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG;
        return nextScreen;
    }


}