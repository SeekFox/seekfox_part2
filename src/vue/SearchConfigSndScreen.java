/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;
//TODO Drag and drop
public class SearchConfigSndScreen {

    private PApplet p;
    private Button ongletImg;
    private Button ongletTxt;
    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_SND;
    private TickBox multimoteur;
    private Button retour;

    public SearchConfigSndScreen(PApplet p){
        this.p = p;
        ongletImg = new Button(p.width/3,0,p.width/3,40,255,"Image",false,p);
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
        retour = new Button(10, p.height-50, 100, 40, 255, "Retour", false, p);
        multimoteur = new TickBox(p.width/2 - 75, p.height/2+25,15,15,true, p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(100);
        p.rect(2*(p.width/3f), 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Son",2*(p.width/3f), 0, p.width/3f, 40);
    }

    public void draw(){
        p.background(200);
        p.text("Glissez et déposez le fichier à recherche", p.width/2f, p.height/2f);
        drawCurrentOnglet();
        ongletImg.display();
        ongletTxt.display();
        retour.display();
        multimoteur.display();
        p.text("Recherche multimoteur", p.width/2f, p.height/2f + 22);
    }

    public void mousePressed(){
        ongletImg.clickParsing();
        ongletTxt.clickParsing();
        retour.clickParsing();
        multimoteur.clickParsing();
    }

    public void mouseReleased(){
        if(ongletImg.release())
            nextScreen = ScreenName.SEARCH_CONFIG_IMG;

        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(retour.release())
            nextScreen = ScreenName.MAIN;

        multimoteur.release();
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_SND;
        return temp;
    }


    public void launchSearch(String filePath) {
        //TODO le lien avec le projet
    }
}