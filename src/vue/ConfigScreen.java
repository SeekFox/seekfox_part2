/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.*;
import processing.core.PApplet;

import java.util.ArrayList;

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
    private ArrayList<ControlRequete> controlRequeteArrayList;


    public ConfigScreen(PApplet p, ArrayList<ControlRequete> controlRequeteArrayList){
        this.p = p;
        config.loadConfig();

        sliderNameI = new Text(p.width/2 - 100,p.height/5, "Image : nombre de \nbits de quantification",p);			//Nb Bits
        sliderImage = new Slider(p.width/2 - 40,p.height/5,80,2,8,p);
        sliderImage.setValue(config.getIMAGE_nbBIts());

        sliderNameAn = new Text(p.width/2 - 100,p.height * 2/5, "Audio : nombre \nd'intervalles \nde l'histogramme",p);		//Nb subdivisions
        sliderAudio_n = new Slider(p.width/2 - 40,p.height * 2/5,80,10,100,p);
        sliderAudio_n.setValue(config.getAUDIO_n());

        sliderNameAm = new Text(p.width/2 - 100, p.height *3/5 , "Audio : taille des\n fenetres \nd'échantillonnage",p);		// insert param audio
        sliderAudio_m = new Slider(p.width/2 - 40,p.height * 3/5,80,500,5000,p);
        sliderAudio_m.setValue(config.getAUDIO_m());

        sliderNameT = new Text(p.width/2 - 100, p.height * 4/5, "Texte : taille minimale \ndes mots pour qu'ils \nsoient pris en compte",p);							//insert param text
        sliderTexte  = new Slider(p.width/2 - 40,p.height * 4/5,80,3,10,p);
        sliderTexte.setValue(config.getTEXTE_tailleMin());

        backButton = new Button(10, p.height-50, 100, 40, p.color(255), "Retour", false, p);
        


        this.controlRequeteArrayList = controlRequeteArrayList;
    }

    public void draw(){
        p.background(200);

        sliderNameI.display();
        sliderImage.display();

        sliderNameAn.display();
        sliderAudio_n.display();

        sliderNameAm.display();
        sliderAudio_m.display();

        sliderNameT.display();
        sliderTexte.display();

        p.fill(0);
        p.text((int)sliderImage.getValue(),p.width/2 + 80,p.height/5);
        p.text((int)sliderAudio_n.getValue(),p.width/2 + 80,p.height * 2/5);
        p.text((int)sliderAudio_m.getValue(),p.width/2 + 80,p.height* 3/5);
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
        if(backButton.release()){		// Cas o� on quitte l'�cran : on applique les changements
        	config.setIMAGE_nbBIts((int)sliderImage.getValue());
        	config.setAUDIO_n((int)sliderAudio_n.getValue());
        	config.setAUDIO_m((int)sliderAudio_m.getValue());
        	config.setTEXTE_tailleMin((int)sliderTexte.getValue());
        	config.majConfig();

            for (ControlRequete controlRequete : controlRequeteArrayList) {
                controlRequete.runIndexation(TypeRequete.TEXTE, "all");
            }


        	nextScreen = ScreenName.MAIN;
        }

    }

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.CONFIG;
        return temp;
    }

}
