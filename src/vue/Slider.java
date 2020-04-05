/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class Slider {
    private int posX;
    private int posY;
    private int sizeX;
    private PApplet p;
    public float sliderPosition = 0f; //goes between 0 and 1

    private float valMin;
    private float valMax;

    private boolean isPressed = false;

    public Slider(int posX, int posY, int sizeX, float valMin, float valMax, PApplet app) {
        p = app;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;

        this.valMin = valMin;
        this.valMax = valMax;
    }


    public void display() {
        //CLICK AND DRAG CALCULATIONS
        if (isPressed) {
            sliderPosition = ((p.mouseX - posX) / (float) sizeX);
            if (sliderPosition < 0)
                sliderPosition = 0;
            if (sliderPosition > 1)
                sliderPosition = 1;
        }

        //ACTUAL DISPLAY
        p.fill(30);
        p.rectMode(p.CORNER);
        p.rect(posX, posY - 3, sizeX, 5);
        if (!isPressed)
            p.fill(255);
        else
            p.fill(200);
        p.rectMode(p.CENTER);
        p.rect(posX + sizeX * sliderPosition, posY, 10, 16);
    }

    public void clickParsing() {
        isPressed();
    }

    public float getValue() {
        return valMin + (valMax - valMin) * sliderPosition;
    }

    public void isPressed() {
        isPressed = p.mouseX >= posX + sizeX * sliderPosition - 5 && p.mouseX <= posX + sizeX * sliderPosition + 5 && p.mouseY >= posY - 8 && p.mouseY <= posY + 8;
    }

    public void release() {
        isPressed = false;
    }

}