/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Resultat;
import processing.core.PApplet;
import java.util.ArrayList;

public class ResultsViewer {
    private PApplet p;


    //Config de l'element
    private final int elementCaseSizeY = 30;
    private final int buttonSizeX = 80;
    private final int buttonSizeY = 30;
    private final int footerSizeY = 50;

    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;

    private boolean nextButtonIsNeeded = true;
    private boolean previousButtonIsNeeded = true;

    private Button nextButton;
    private Button previousButton;
    private int currentPage = 1;
    private int nbPages;

    private ArrayList<String> listOfElementsLeft = new ArrayList<>();     //Ce qu'il y a écrit a gauche de la case
    private ArrayList<String> listOfElementsRight = new ArrayList<>();    //Ce qu'il y a écrit sur la droite de la case

    public ResultsViewer(int posX, int posY, int sizeX, int sizeY,PApplet p) {
        this.p = p;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        nextButton = new Button(posX+sizeX-10-buttonSizeX, posY+sizeY-10-buttonSizeY, buttonSizeX,buttonSizeY, p.color(240), "Suivant", false, p);
        previousButton = new Button(posX+10, posY+sizeY-10-buttonSizeY, buttonSizeX,buttonSizeY, p.color(240), "Précédent", false, p);
    }


    public void draw(){

        p.fill(150);
        p.rectMode(p.CORNER);
        p.rect(posX,posY,sizeX,sizeY,3);


        if(nextButtonIsNeeded || previousButtonIsNeeded){
            p.fill(200);
            p.rect(posX, posY+sizeY-footerSizeY, sizeX, footerSizeY);
        }

        if(nextButtonIsNeeded)
            nextButton.display();
        if(previousButtonIsNeeded)
            previousButton.display();

    }

    /*public int nbPagesNeeded(ArrayList listOfElements){
        return (listOfElements.size()*elementCaseSizeY)/(sizeY-footerSizeY);
    }

    public void fillResultsWindow(Resultat resultats){
        ArrayList<CelluleResultats>
    }
*/
}