/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

import modele.Detection;
import processing.core.PApplet;

/**
 * Classe de TickBox
 */
public class TickBox {

    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;
    private PApplet p;


    private boolean isPressed = false;
    private boolean isTicked = false;
    private boolean isCentered;

    /**
     * Constructeur
     * @param px
     * @param py
     * @param sx
     * @param sy
     * @param isCentered
     * @param app
     */
    public TickBox(int px, int py, int sx, int sy, boolean isCentered, PApplet app) {
        p = app;
        this.isCentered = isCentered;
        posX = px;
        posY = py;
        sizeX = sx;
        sizeY = sy;
    }

    public boolean isTicked() {
        return isTicked;
    }

    /**
     * Affichage de la TickBox
     */
    public void display() {
        if (isCentered)
            p.rectMode(p.CENTER);
        else
            p.rectMode(p.CORNER);

        if (!isPressed)
            p.fill(150);
        else
            p.fill(100);

        p.rect(posX, posY, sizeX, sizeY, 2);
        if(isTicked){
            p.fill(255);
            if(!isCentered)
                p.rect(posX +2, posY+2, sizeX-4, sizeY-4, 2);
            else
                p.rect(posX, posY, sizeX-4, sizeY-4, 2);
        }
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        //p.text(buttonText, posX, posY, sizeX, sizeY);
    }

    public boolean isPressed() {
        return Detection.isPressed(p.mouseX, p.mouseY, posX, posY, sizeX, sizeY, isCentered);
    }

    public void clickParsing() {
        isPressed = isPressed();
    }

    public boolean release() {
        if (isPressed) {
            isPressed = false;
            isTicked = !isTicked;
            return true;
        }
        return false;

    }
}
