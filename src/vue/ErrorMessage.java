/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * Affichage d'un message d'erreur
 */
public class ErrorMessage {
    private static PImage errorIcon;
    private Button okButton;
    private PApplet p;
    private String errorMessage;

    /**
     *
     * @param p
     */
    public ErrorMessage(PApplet p) {
        this.p = p;
        okButton = new Button(p.width/2, p.height/2 + 20, 100, 40, 255, "Oups", true, p);
        errorIcon = p.loadImage("doc/error.png","png");
    }

    /**
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Affichage
     */
    public void display(){
        p.fill(200);
        p.rectMode(PConstants.CENTER);
        p.rect(p.width/2f,p.height/2f ,300,100,5);
        p.fill(200,0,0);
        p.image(errorIcon,p.width/2f-140,p.height/2f-40,40,40);
        p.text(errorMessage, p.width/2f,p.height/2f-20 ,300,100);
        okButton.display();
    }

    /**
     *
     */
    public void clickParsing(){
        okButton.clickParsing();
    }


    /**
     *
     * @return
     */
    public boolean release(){
        return okButton.release();
    }
}
