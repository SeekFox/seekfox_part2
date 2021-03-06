/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

import modele.Detection;
import processing.core.PApplet;

import static processing.core.PConstants.*;

/**
 * Classe de Textbox
 * @see PApplet
 */
public class Textbox {

    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;
    PApplet p;
    private String textBoxText;
    private String writtenText = "";
    private boolean isPressed = false;
    private boolean isPassword;
    private boolean isCentered;


    /**
     * Constructeur de la textBox
     * @param px
     * @param py
     * @param sx
     * @param sy
     * @param textBoxText
     * @param isPassword
     * @param isCentered
     * @param app
     */
    public Textbox(int px, int py, int sx, int sy, String textBoxText, boolean isPassword, boolean isCentered, PApplet app) {
        p = app;
        this.isPassword = isPassword;
        posX = px;
        posY = py;
        sizeX = sx;
        sizeY = sy;
        this.isCentered = isCentered;
        this.textBoxText = textBoxText;
    }


    public String getWrittenText() {
        return writtenText;
    }

    /**
     * Affichage de la textbox
     */
    public void display() {
        if (!isCentered)
            p.rectMode(p.CORNER);
        else
            p.rectMode(p.CENTER);

        if (!isPressed)
            p.fill(255);
        else
            p.fill(150);

        p.rect(posX, posY, sizeX, sizeY, 5);

        p.fill(0, 0, 0);
        p.textAlign(p.CENTER, p.CENTER);
        if (!isPressed && writtenText.length() == 0) {
            p.fill(150);
            p.text(textBoxText, posX, posY, sizeX, sizeY);

        } else {
            if(!isPassword)
                p.text(writtenText, posX, posY, sizeX, sizeY);
            else{
                String text = "";
                for (int i = 0; i < writtenText.length(); i++) {
                    text += "*";
                }

                p.text(text, posX, posY, sizeX, sizeY);   //Écrire des étoiles si mdp
            }
        }
    }

    public void clickParsing() {
        isPressed = Detection.isPressed(p.mouseX, p.mouseY, posX,posY,sizeX,sizeY, isCentered);
    }

    public boolean release() {
        if (isPressed) {
            return true;
        }
        return false;
    }

    public void keyPressedParsing(char keypressed) {
        if (isPressed) {
            switch (keypressed) {
                case BACKSPACE:
                case DELETE:
                    if (writtenText.length() > 0)
                        writtenText = writtenText.substring(0, writtenText.length() - 1);
                    break;
                case ENTER:
                    isPressed =false;
                    break;
                default:
                    if (keypressed != p.CODED)
                        writtenText = writtenText + keypressed;
            }

        }
    }

    public void setText(String text){
        writtenText=text;
    }

    public void resetText(){
        this.setText("");
    }

}