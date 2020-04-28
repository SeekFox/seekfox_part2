/*
 * Copyright (c) 2020.
 * ClÃ©ment Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import modele.Config;
import modele.ScreenName;
import modele.Slider;
import modele.Text;
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
    
    Config config = Config.getInstance();


    public ConfigScreen(PApplet p){
        this.p = p;
        sliderNameI = new Text(p.width/2 - 100,p.height/5, "Image : nombre de \nbits de quantification",p);			//Nb Bits
        sliderImage = new Slider(p.width/2 - 40,p.height/5,80,2,8,p);

        sliderNameAn = new Text(p.width/2 - 100,p.height * 2/5, "Audio : nombre \nd'intervalles \nde l'histogramme",p);		//Nb subdivisions
        sliderAudio_n = new Slider(p.width/2 - 40,p.height * 2/5,80,10,100,p);


        sliderNameAm = new Text(p.width/2 - 100, p.height *3/5 , "Audio : taille des\n fenêtres \nd'échantillonnage",p);		// insert param audio
        sliderAudio_m = new Slider(p.width/2 - 40,p.height * 3/5,80,500,5000,p);

        sliderNameT = new Text(p.width/2 - 100, p.height * 4/5, "Texte : taille minimale \ndes mots pour qu'ils \nsoient pris en compte",p);							//insert param text
        sliderTexte  = new Slider(p.width/2 - 40,p.height * 4/5,80,3,10,p);


        backButton = new Button(10, p.height-50, 100, 40, p.color(200,0,0), "Retour", false, p);
        
        config.loadConfig();
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
        if(backButton.release()){		// Cas où on quitte l'écran : on applique les changements
        	config.setIMAGE_nbBIts((int)sliderImage.getValue());
        	config.setAUDIO_n((int)sliderAudio_n.getValue());
        	config.setAUDIO_m((int)sliderAudio_m.getValue());
        	config.setTEXTE_tailleMin((int)sliderTexte.getValue());
        	config.majConfig();
        	nextScreen = ScreenName.MAIN;
        }

    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.CONFIG;
        return temp;
    }

}
