/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */
package modele;


import processing.core.*;


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

    public boolean isPressed() {
        return Detection.isPressed(p.mouseX, p.mouseY, posX, posY, sizeX, sizeY, isCentered);
    }

    public void clickParsing() {
        isPressed = isPressed();
    }

    public boolean release() {
        if (isPressed) {
            isPressed = false;
            return true;
        }
        return false;

    }


}
