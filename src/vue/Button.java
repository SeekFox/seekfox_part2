/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */
package vue;


import processing.core.*;


public class Button{
    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;
    private PApplet p;

    private int buttonColor;
    private int buttonColorWhenPressed;
    private String buttonText;
    private boolean isPressed = false;

    public Button(int px, int py, int sx, int sy, int buttonColor, String buttonText, PApplet app){
        p = app;
        posX = px;
        posY = py;
        sizeX = sx;
        sizeY = sy;
        buttonColorWhenPressed =  p.color(50,50,50);
        this.buttonColor = buttonColor;
        this.buttonText = buttonText;
    }

    public void display(){
        p.rectMode(p.CORNER);
        if(!isPressed)
            p.fill(buttonColor);
        else
            p.fill(buttonColorWhenPressed);


        p.rect(posX, posY, sizeX, sizeY,5);
        p.fill(0,0,0);
        p.textAlign(p.CENTER,p.CENTER);
        p.text(buttonText, posX+5, posY, sizeX, sizeY);
    }

    public boolean isPressed(){
        if(p.mouseX >= (posX) && p.mouseX <=  (posX+sizeX)
                && p.mouseY >= (posY) && p.mouseY <=  (posY+sizeY)){
            isPressed = true;
            return true;
        }
        else{
            isPressed = false;
            return false;
        }
    }

    public void release(){
        isPressed = false;
    }


}
