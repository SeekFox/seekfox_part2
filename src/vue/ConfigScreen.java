/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class ConfigScreen {

    PApplet p;
    Text sliderNameI;
    Slider sliderImage;

    Text sliderNameT;
    Slider sliderTexte;


    Text sliderNameAn;
    Slider sliderAudio_n;

    Text sliderNameAm;
    Slider sliderAudio_m;

    Button backButton;
    ScreenName nextScreen = ScreenName.CONFIG;


    public ConfigScreen(PApplet p){
        this.p = p;
        sliderNameI = new Text(p.width/2 - 100,p.height/5, "Nb Bits",p);
        sliderImage = new Slider(p.width/2 - 40,p.height/5,80,2,8,p);

        sliderNameAn = new Text(p.width/2 - 100,p.height * 2/5, "Nb subdivisions",p);
        sliderAudio_n = new Slider(p.width/2 - 40,p.height * 2/5,80,10,100,p);


        sliderNameAm = new Text(p.width/2 - 100, p.height *3/5 , "insert param audio",p);
        sliderAudio_m = new Slider(p.width/2 - 40,p.height * 3/5,80,500,5000,p);

        sliderNameT = new Text(p.width/2 - 100, p.height * 4/5, "insert param text",p);
        sliderTexte  = new Slider(p.width/2 - 40,p.height * 4/5,80,3,10,p);


        backButton = new Button(10, p.height-50, 100, 40, p.color(200,0,0), "Retour", false, p);
    }

    public void draw(){
        p.background(200);

        sliderNameI.display();
        sliderImage.display();
        p.text((int)sliderImage.getValue(),p.width/2 + 80,p.height/5);


        sliderNameAn.display();
        sliderAudio_n.display();
        p.text((int)sliderAudio_n.getValue(),p.width/2 + 80,p.height * 2/5);

        sliderNameAm.display();
        sliderAudio_m.display();
        p.text((int)sliderAudio_m.getValue(),p.width/2 + 80,p.height* 3/5);


        sliderNameT.display();
        sliderTexte.display();
        p.text((int)sliderTexte.getValue(),p.width/2 + 80,p.height * 4/5);

        backButton.display();

    }

    public void mousePressed(){
        sliderTexte.clickParsing();
        sliderImage.clickParsing();
        sliderAudio_m.clickParsing();
        sliderAudio_n.clickParsing();
        backButton.clickParsing();
    }

    public void mouseReleased(){
        sliderTexte.release();
        sliderImage.release();
        sliderAudio_m.release();
        sliderAudio_n.release();
        if(backButton.release()){
            nextScreen = ScreenName.MAIN;
        }

    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.CONFIG;
        return temp;
    }

}
