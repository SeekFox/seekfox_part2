/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

import static processing.core.PConstants.*;

public class Textbox{

    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;
    PApplet p;
    private String textBoxText;
    private String writtenText ="";
    private boolean isPressed = false;

    public Textbox(int px, int py, int sx, int sy, String textBoxText, PApplet app){
        p = app;
        posX = px;
        posY = py;
        sizeX = sx;
        sizeY = sy;
        this.textBoxText = textBoxText;
    }

    public void display(){

        p.rectMode(p.CORNER);
        if(!isPressed)
            p.fill(255);
        else
            p.fill(150);


        p.rect(posX, posY, sizeX, sizeY,5);
        p.fill(0,0,0);
        p.textAlign(p.CENTER,p.CENTER);
        if(!isPressed && writtenText.length() == 0)
            p.text(textBoxText, posX+5, posY, sizeX, sizeY);
        else
            p.text(writtenText, posX+5, posY, sizeX, sizeY);
    }

    public boolean clickParsing(){
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

    }

    public void keyPressedParsing(char keypressed){
        if(isPressed){
            switch(keypressed){
                case BACKSPACE:
                case DELETE:
                    if(writtenText.length()>0)
                        writtenText = writtenText.substring(0, writtenText.length()-1);
                    break;

                default:
                    if(keypressed != p.CODED)
                        writtenText = writtenText + keypressed;
            }

        }
    }

}