/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

/**
 * ScrollBar
 */
public class ScrollBar {
    PApplet p;
    public int posx;
    public int posy;
    public int sx;
    public int sy;

    private boolean isPressed = false;

    /**
     *
     * @param posx
     * @param posy
     * @param sx
     * @param sy
     * @param p
     */
    public ScrollBar(int posx, int posy, int sx, int sy,PApplet p){
        this.p = p;
        this.posx = posx;
        this.posy = posy;
        this.sx = sx;
        this.sy = sy;
    }

    /**
     * Affichage
     */
    public void display(){
        if (isPressed){
            if(posy >= 0 && posy <= p.height){
                p.text(posy,10,10);
                if(p.mouseY >= 0 && (p.mouseY /*+ sy*/) < p.height){
                    posy = p.mouseY;
                }
            }
            else if( posy > p.height){
                posy = p.height - sy;
            }
            else if( posy < 0){
                posy = 0;
            }
        }

        p.fill(255);
        p.rect(posx,posy,sx,sy,5);
    }

    /**
     *
     */
    public void clickParsing(){
        isPressed();
    }

    /**
     *
     */
    public void isPressed(){
        isPressed = p.mouseX >= posx && p.mouseX <= (posx + sx) && p.mouseY >= posy && p.mouseY <= (posy + sy);
    }

    /**
     *
     */
    public void release(){
        isPressed = false;
    }

    /**
     *
     * @param X
     * @param Y
     * @param x
     * @param y
     */
    public void reset(int X,int Y, int x ,int y){
        this.posx = X;
        this.posy = Y;
        this.sx = x;
        this.sy = y;
    }

    /**
     *
     * @return
     */
    public int getPosx() {
        return posx;
    }

    /**
     *
     * @return
     */
    public int getPosy() {
        return posy;
    }

    /**
     *
     * @return
     */
    public int getSx() {
        return sx;
    }

    /**
     *
     * @return
     */
    public int getSy() {
        return sy;
    }

    /**
     *
     * @param posx
     */
    public void setPosx(int posx) {
        this.posx = posx;
    }

    /**
     *
     * @param posy
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }

    /**
     *
     * @param sx
     */
    public void setSx(int sx) {
        this.sx = sx;
    }

    /**
     *
     * @param sy
     */
    public void setSy(int sy) {
        this.sy = sy;
    }
}
