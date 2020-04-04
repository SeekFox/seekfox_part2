/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;
//TODO Drag & Drop
public class SearchConfigImgScreen {

    private PApplet p;
    private Button ongletTxt;
    private Button ongletSnd;
    private Button retour;

    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_IMG;

    public SearchConfigImgScreen(PApplet p){
        this.p = p;
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
        ongletSnd = new Button(2*(p.width/3),0,p.width/3,40,255,"Son",false,p);
        retour = new Button(10, p.height-50, 100, 40, 255, "Retour", false, p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(100);
        p.rect(p.width/3f, 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Image",p.width/3f, 0, p.width/3f, 40);
    }

    public void draw(){
        p.background(200);
        drawCurrentOnglet();
        ongletTxt.display();
        ongletSnd.display();
        retour.display();
    }

    public void mousePressed(){
        ongletTxt.clickParsing();
        ongletSnd.clickParsing();
        retour.clickParsing();
    }

    public void mouseReleased(){
        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(ongletSnd.release())
            nextScreen = ScreenName.SEARCH_CONFIG_SND;

        if(retour.release())
            nextScreen = ScreenName.MAIN;
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_IMG;
        return temp;
    }


}