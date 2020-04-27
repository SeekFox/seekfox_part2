/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import modele.ScreenName;
import modele.Slider;
import modele.Text;
import processing.core.PApplet;

public class ConfigScreen {

    PApplet p;
    Text sliderNameI;
    Slider sliderImage;
    Text valConfigImage;

    Text sliderNameT;
    Slider sliderTexte;
    Text valConfigTexte;

    Text sliderNameAn;
    Slider sliderAudio_n;
    Text valConfigAudio_n;

    Text sliderNameAm;
    Slider sliderAudio_m;
    Text valConfigAudio_m;

    Button backButton;
    ScreenName nextScreen = ScreenName.CONFIG;


    public ConfigScreen(PApplet p){
        this.p = p;
        sliderNameI = new Text(p.width/2 - 80,p.height/5, "Nb Bits",p);
        sliderImage = new Slider(p.width/2 - 40,p.height/5,50,2,8,p);
        valConfigImage = new Text(p.width/2 + 20 ,p.height/5,(String.valueOf(sliderImage.getValue())),p);

        sliderNameAn = new Text(p.width/2 - 80,p.height * 2/5, "Nb subdivisions",p);
        sliderAudio_n = new Slider(p.width/2 - 40,p.height * 2/5,50,10,100,p);
        valConfigAudio_n = new Text(p.width/2 +20, p.height * 2/5 , String.valueOf(sliderAudio_n.getValue()),p);

        sliderNameAm = new Text(p.width/2 - 80, p.height *3/5 , "insert param audio",p);
        sliderAudio_m = new Slider(p.width/2 - 40,p.height * 3/5,50,500,5000,p);
        valConfigAudio_m = new Text(p.width + 20,p.height* 3/5, String.valueOf(sliderAudio_m.getValue()),p);

        sliderNameT = new Text(p.width/2 - 80, p.height * 4/5, "insert param text",p);
        sliderTexte  = new Slider(p.width/2 - 40,p.height * 4/5,50,3,10,p);
        valConfigTexte = new Text(p.width + 20,p.height * 4/5, String.valueOf(sliderTexte.getValue()),p);

        backButton = new Button(10, p.height-50, 100, 40, p.color(200,0,0), "Retour", false, p);
    }

    public void draw(){
        p.background(200);

        sliderNameI.display();
        sliderImage.display();
        valConfigImage.display();

        sliderNameAn.display();
        sliderAudio_n.display();
        valConfigAudio_n.display();

        sliderNameAm.display();
        sliderAudio_m.display();
        valConfigAudio_m.display();

        sliderNameT.display();
        sliderTexte.display();
        valConfigTexte.display();

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
    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.CONFIG;
        return temp;
    }

}
