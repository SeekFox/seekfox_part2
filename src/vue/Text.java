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


    public Text(int textX,int textY,String text){
        this.text = text;
        this.textX = textX;
        this.textY = textY;
    }

    public void display(){
        p.text(text,textX,textY);
    }
}
