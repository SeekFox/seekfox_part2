/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class SearchConfigImgScreen {

    PApplet p;
    Button ongletTxt;
    Button ongletSnd;

    ScreenName nextScreen = ScreenName.SEARCH_CONFIG_IMG;

    public SearchConfigImgScreen(PApplet p){
        this.p = p;
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
        ongletSnd = new Button(2*(p.width/3),0,p.width/3,40,255,"Son",false,p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(50);
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
    }

    public void mousePressed(){
        ongletTxt.clickParsing();
        ongletSnd.clickParsing();
    }

    public void mouseReleased(){
        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(ongletSnd.release())
            nextScreen = ScreenName.SEARCH_CONFIG_SND;
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_IMG;
        return temp;
    }


}