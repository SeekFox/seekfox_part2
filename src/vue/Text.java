/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

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
