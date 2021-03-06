/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

import processing.core.PApplet;
import java.util.ArrayList;

/**
 * Classe permettant de visualiser les resultats
 */
public class ResultsViewer {
    private PApplet p;


    //Config de l'element
    private int elementCaseSizeY;
    private final int buttonSizeX = 80;
    private final int buttonSizeY = 30;
    private final int footerSizeY = 50;
    private final int nbItemsPerPage = 10;

    private int posX;
    private int posY;
    private int sizeX;
    private int sizeY;

    private boolean nextButtonIsNeeded = false;
    private boolean previousButtonIsNeeded = false;

    private Button nextButton;
    private Button previousButton;
    private int currentPage = 1;
    private int nbPages;
    private int nbItems;


    private ArrayList<String> listOfElementsLeft = new ArrayList<>();     //Ce qu'il y a écrit a gauche de la case
    private ArrayList<String> listOfElementsRight = new ArrayList<>();    //Ce qu'il y a écrit sur la droite de la case
    
    //private Resultat resultat;			// Stockage des r�sultats affich�s, utilis� pour l'historique

    /**
     * Constructeur de la classe
     * @param posX
     * @param posY
     * @param sizeX
     * @param sizeY
     * @param p
     */
    public ResultsViewer(int posX, int posY, int sizeX, int sizeY, PApplet p) {
        this.p = p;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        nextButton = new Button(posX + sizeX - 10 - buttonSizeX, posY + sizeY - 10 - buttonSizeY, buttonSizeX, buttonSizeY, p.color(255), "Suivant", false, p);
        previousButton = new Button(posX + 10, posY + sizeY - 10 - buttonSizeY, buttonSizeX, buttonSizeY, p.color(255), "Précédent", false, p);

        elementCaseSizeY = (sizeY-footerSizeY)/nbItemsPerPage;
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    /**
     * Affichage des resultats
     */
    public void draw() {
        int currentYPosition = posY;
        int currentIndex;
        p.fill(150);
        p.rectMode(p.CORNER);
        p.rect(posX, posY, sizeX, sizeY, 3);

        for(int i = 0; i < nbItemsPerPage; i++){
            currentIndex = (currentPage-1)*nbItemsPerPage + i;
            if(currentIndex >= nbItems)
                break;
            p.fill(200);
            p.rectMode(p.CORNER);
            p.rect(posX, currentYPosition, sizeX, elementCaseSizeY);
            p.fill(0);
            p.textAlign(p.LEFT,p.CENTER);
            p.text("Fichier : "+listOfElementsLeft.get(currentIndex),posX+10,currentYPosition+elementCaseSizeY/2);

            //ToDo -> Recherche motClef, c'est des occurences, pas des %
            // Same avec l'audio

            p.textAlign(p.RIGHT,p.CENTER);
            switch ( TypeRecherche.getINSTANCE().getTypeRequete()){
                case TEXTE:
                case COULEURDOMINANTE:
                case IMAGE:
                    p.text((Math.round(Float.parseFloat(listOfElementsRight.get(currentIndex)))) + " %.",posX+sizeX-10,currentYPosition+elementCaseSizeY/2);
                    break;

                case AUDIO:
                    p.text((Math.round(Float.parseFloat(listOfElementsRight.get(currentIndex)))) + " s.",posX+sizeX-10,currentYPosition+elementCaseSizeY/2);
                    break;

                case MOTCLEF:
                    p.text((Math.round(Float.parseFloat(listOfElementsRight.get(currentIndex)))) + " occur.",posX+sizeX-10,currentYPosition+elementCaseSizeY/2);
                    break;

                case MOTCLEF_COMPLEXE:
                default:

                    break;

            }


            //p.text(listOfElementsRight.get(currentIndex) + "%",posX+sizeX-10,currentYPosition+elementCaseSizeY/2);

            currentYPosition += elementCaseSizeY;
        }



        //Draw footer
        if (nextButtonIsNeeded || previousButtonIsNeeded) {
            p.fill(240);
            p.rect(posX, posY + sizeY - footerSizeY, sizeX, footerSizeY);
        }


        //Draw buttons
        if (nextButtonIsNeeded)
            nextButton.display();
        if (previousButtonIsNeeded)
            previousButton.display();

    }

    /**
     * Renvoit le nombre de page necessaire
     * @param listOfElements
     * @return
     */
    private int nbPagesNeeded(ArrayList<String> listOfElements) {
        return (listOfElements.size() * elementCaseSizeY) / (sizeY - footerSizeY) +1;
    }

    /**
     * Initialise les resultats
     * @param resultat
     */
    public void init(Resultat resultat){
        TypeRecherche.getINSTANCE().setTypeRequete(resultat.getType());
    	//this.resultat = resultat;
        listOfElementsLeft.clear();
        listOfElementsRight.clear();
        fillResultsWindow(resultat);
        nbPages = nbPagesNeeded(listOfElementsLeft);
        nbItems = listOfElementsLeft.size();
        nextButtonIsNeeded = nextButtonIsNeeded();
        previousButtonIsNeeded = previousButtonIsNeeded();

    }


    private void fillResultsWindow(Resultat resultats) {
        ArrayList<CelluleResultat> everyResults = resultats.getResultats();

        for (CelluleResultat current : everyResults) {
            listOfElementsLeft.add(current.getFichier());
            listOfElementsRight.add(String.valueOf(current.getScore()));
        }

    }

    private boolean nextButtonIsNeeded() {
        return currentPage != nbPages;
    }

    private boolean previousButtonIsNeeded() {
        return currentPage != 1;
    }


    public void clickParsing() {
        if (nextButtonIsNeeded)
            nextButton.clickParsing();
        if (previousButtonIsNeeded)
            previousButton.clickParsing();
    }

    public void release() {
        if (nextButtonIsNeeded) {
            if (nextButton.release())
                currentPage++;
        }

        if (previousButtonIsNeeded) {
            if (previousButton.release())
                currentPage--;
        }
        nextButtonIsNeeded = nextButtonIsNeeded();
        previousButtonIsNeeded = previousButtonIsNeeded();
    }
    
    //public void ajouterResultatHistorique () {
    //	if (this.resultat==null) return;
    //	else addHistorique(this.resultat);
    //}

    public void setCurrentPage(int page){
        this.currentPage=page;
    }

}