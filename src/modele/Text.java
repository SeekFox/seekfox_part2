/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

import processing.core.PApplet;

/**
 * Classe de texte
 * @see PApplet
 */
public class Text {
    public String text;
    PApplet p;
    public int textX;
    public int textY;


    public Text(int textX,int textY,String text,PApplet p){
        this.text = text;
        this.textX = textX;
        this.textY = textY;
        this.p = p;
    }

    public void display(){
        p.fill(0);
        p.text(text,textX,textY);
    }
}
