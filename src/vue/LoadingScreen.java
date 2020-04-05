/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class LoadingScreen {
    private PApplet p;
    private ScreenName nextScreen = ScreenName.LOADING;
    private int loadingSize =0;

    public LoadingScreen(PApplet p) {
        this.p = p;

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
        loadingSize+=2;

        if(loadingSize >= p.width-60)
            nextScreen = ScreenName.RESULTS;
    }

    public ScreenName getNextScreen() {
        return ScreenName.LOADING;
    }

    public void init(){
        loadingSize = 0;
    }
}
