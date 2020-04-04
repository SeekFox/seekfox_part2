/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class SearchConfigSndScreen {

    PApplet p;
    Button ongletImg;
    Button ongletTxt;

    ScreenName nextScreen = ScreenName.SEARCH_CONFIG_SND;

    public SearchConfigSndScreen(PApplet p){
        this.p = p;
        ongletImg = new Button(p.width/3,0,p.width/3,40,255,"Image",false,p);
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(50);
        p.rect(2*(p.width/3f), 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Son",2*(p.width/3f), 0, p.width/3f, 40);
    }

    public void draw(){
        p.background(200);
        drawCurrentOnglet();
        ongletImg.display();
        ongletTxt.display();
    }

    public void mousePressed(){
        ongletImg.clickParsing();
        ongletTxt.clickParsing();
    }

    public void mouseReleased(){
        if(ongletImg.release())
            nextScreen = ScreenName.SEARCH_CONFIG_IMG;

        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_SND;
        return temp;
    }


}