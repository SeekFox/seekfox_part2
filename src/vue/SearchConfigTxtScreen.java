/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.TypeRequete;
import processing.core.PApplet;

//TODO Drag&Drop
public class SearchConfigTxtScreen {

    private PApplet p;
    private Button ongletImg;
    private Button ongletSnd;
    private Textbox searchBox;
    private Button validerRecherche;
    private Button retour;
    private ControlRequete controlRequete;
    private TickBox multimoteur;
    private boolean isRechercheLaunch = false;

    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_TXT;

    public SearchConfigTxtScreen(PApplet p, ControlRequete controlRequete){
        this.p = p;
        this.controlRequete = controlRequete;
        ongletImg = new Button(p.width/3,0,p.width/3,40,255,"Image",false,p);
        ongletSnd = new Button(2*(p.width/3),0,p.width/3,40,255,"Son",false,p);

        searchBox = new Textbox(p.width/2, p.height/2, p.width/2, 30, "Chercher",false,true,p);
        multimoteur = new TickBox(p.width/2 - 75, p.height/2 + 43,15,15,true, p);
        validerRecherche = new Button(p.width/2, p.height/2 + 90, 100, 40, 255, "Valider", true, p);

        retour = new Button(10, p.height-50, 100, 40, 255, "Retour", false, p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(100);
        p.rect(0, 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Texte",0, 0, p.width/3f, 40);
    }

    public void draw(){

        p.background(200);
        p.text("Glissez et déposez le fichier à rechercher ou utilisez les critères ci-dessous", p.width/2f, p.height/2f - 40);
        drawCurrentOnglet();
        ongletImg.display();
        ongletSnd.display();
        searchBox.display();
        validerRecherche.display();
        retour.display();
        multimoteur.display();
        p.text("Recherche multimoteur", p.width/2f, p.height/2f + 40);
    }

    public void mousePressed(){
        ongletImg.clickParsing();
        ongletSnd.clickParsing();
        searchBox.clickParsing();
        validerRecherche.clickParsing();
        retour.clickParsing();
        multimoteur.clickParsing();
    }

    public void mouseReleased(){
        if(ongletImg.release())
            nextScreen = ScreenName.SEARCH_CONFIG_IMG;

        if(ongletSnd.release())
            nextScreen = ScreenName.SEARCH_CONFIG_SND;

        if(validerRecherche.release()) {
            this.runRecherche();
            nextScreen = ScreenName.LOADING;    //TODO activer la recherche ptdr & gérer les erreurs
        }

        if(retour.release())
            nextScreen = ScreenName.MAIN;

        searchBox.release();
        multimoteur.release();
    }

    public void keyPressed(char key){
        searchBox.keyPressedParsing(key);
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_TXT;
        return temp;
    }


    public void init() {
        searchBox.resetText();
        this.isRechercheLaunch = false;
    }

    public void runRecherche(){
        if(!isRechercheLaunch) {
            try { //Lancer la recherche
                this.isRechercheLaunch = true;
                controlRequete.runRecherche(TypeRequete.MOTCLEF, searchBox.getWrittenText());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void launchSearch(String filePath){
        //TODO implémenter avec le vrai truc
    }
}