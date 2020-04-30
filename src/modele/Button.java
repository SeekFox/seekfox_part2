/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */
package modele;


import processing.core.*;

/**
 * Button processing
 */
public class Button {
    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;
    private PApplet p;

    private int buttonColor;
    private int buttonColorWhenPressed;
    private String buttonText;
    private boolean isPressed = false;
    private boolean isCentered;

    /**
     * Constructeur d'un bouton
     * @param px
     * @param py
     * @param sx
     * @param sy
     * @param buttonColor
     * @param buttonText
     * @param isCentered
     * @param app
     */
    public Button(int px, int py, int sx, int sy, int buttonColor, String buttonText, boolean isCentered, PApplet app) {
        p = app;
        this.isCentered = isCentered;
        posX = px;
        posY = py;
        sizeX = sx;
        sizeY = sy;
        buttonColorWhenPressed = p.color(100);
        this.buttonColor = buttonColor;
        this.buttonText = buttonText;
    }

    /**
     * Affichage du boutton
     */
    public void display() {
        if (isCentered)
            p.rectMode(p.CENTER);
        else
            p.rectMode(p.CORNER);

        if (!isPressed)
            p.fill(buttonColor);
        else
            p.fill(buttonColorWhenPressed);


        p.rect(posX, posY, sizeX, sizeY, 5);
        p.fill(0);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(buttonText, posX, posY, sizeX, sizeY);
    }

    /**
     * Renvoi vrai si le bouton est pressé
     * @return
     */
    public boolean isPressed() {
        return Detection.isPressed(p.mouseX, p.mouseY, posX, posY, sizeX, sizeY, isCentered);
    }

    /**
     *
     */
    public void clickParsing() {
        isPressed = isPressed();
    }

    /**
     *
     * @return
     */
    public boolean release() {
        if (isPressed) {
            isPressed = false;
            return true;
        }
        return false;

    }

    /**
     * Set posY du boutton
     * @param posY
     */
    public void setPosY(int posY){
        this.posY = posY;
    }

    /**
     * Get posY du boutton
     * @return
     */
    public int getposY(){
        return this.posY;
    }

    /**
     *
     * @return
     */
    public String getButtonText() {
        return buttonText;
    }
}
