/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.*;
import modele.Button;
import processing.core.PApplet;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ecran des resultats
 */
public class ResultsScreen {
    private PApplet p;
    private ScreenName nextScreen = ScreenName.RESULTS;
    private ResultsViewer resultsViewer;
    private Button saveButton;
    private Button backButton;
    private Desktop desktop = Desktop.getDesktop();
    private modele.Resultat results;

    /**
     *
     * @param p
     */
    public ResultsScreen(PApplet p) {
        this.p = p;
        resultsViewer = new ResultsViewer(0,0,p.width, p.height - 50,p);
        saveButton = new Button(10, p.height-40, 100, 30, 255,"Sauvegarder la recherche", false, p);
        backButton = new Button(p.width-110, p.height-40, 100, 30, 255,"Retour au menu", false, p);
    }

    /**
     * Affichage
     */
    public void draw() {
        p.background(200);
        resultsViewer.draw();

        if(results!=null && results.getResultats().size()>0){
            saveButton.display();
        }

        backButton.display();
    }

    /**
     *
     */
    public void mousePressed(){
        resultsViewer.clickParsing();

        if(results != null && results.getResultats() != null && results.getResultats().size()>0){
            saveButton.clickParsing();
        }

        backButton.clickParsing();
    }

    /**
     *
     */
    public void mouseReleased(){
        resultsViewer.release();
        if(saveButton.release()) {
            results.setRequete(TypeRecherche.getINSTANCE().getRequete());
            results.setType(TypeRecherche.getINSTANCE().getTypeRequete());
            Historique.addHistorique(results);
            //nextScreen = ScreenName.MAIN;
        }

        if(backButton.release()) {
            this.resultsViewer.setCurrentPage(1);
            nextScreen = ScreenName.MAIN;
        }


    }

    /**
     *
     * @return
     */
    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.RESULTS;
        return temp;
    }

    /**
     * Initialisation
     * @param searchResults
     */
    public void init(modele.Resultat searchResults){
        resultsViewer.init(searchResults);
        results = searchResults;
        TypeRequete search =  searchResults.getType();
        ArrayList<CelluleResultat> everyResults = searchResults.getResultats();
        if(everyResults.size()>0) {
            String filePath = everyResults.get(0).getFichier();
            try {

                switch (search) {
                    case AUDIO:
                        if (filePath.contains(".bin")){
                            filePath = filePath.replace(".bin", ".wav");
                        }
                        desktop.open( new File("impeesa/" + filePath));

                        break;

                    default:
                        desktop.open(new File("impeesa/" + filePath));

                        break;
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
