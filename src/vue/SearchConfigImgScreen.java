/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import processing.core.PApplet;
import vue.FileChooser.FileChooseType;
import vue.FileChooser.FileChooser;

public class SearchConfigImgScreen {

    private PApplet p;
    private Button ongletTxt;
    private Button ongletSnd;
    private Button retour;
    private Button validerRecherche;
    private Button accessFile;

    private Slider rouge;
    private Slider vert;
    private Slider bleu;

    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_IMG;

    private ControlRequete controlRequete;
    private TickBox multimoteur;
    private FileChooser fileChooser = new FileChooser("../base_de_document");

    public SearchConfigImgScreen(PApplet p, ControlRequete controlRequete){
        this.p = p;
        this.controlRequete = controlRequete;
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
        ongletSnd = new Button(2*(p.width/3),0,p.width/3,40,255,"Son",false,p);
        retour = new Button(10, p.height-50, 100, 40, 255, "Retour", false, p);

        multimoteur = new TickBox(p.width/2 - 75, p.height/2 +115,15,15,true, p);
        validerRecherche = new Button(p.width/2-60, p.height/2+150, 100, 40, 255, "Valider", true, p);
        accessFile = new Button(p.width/2+60, p.height/2+150, 100, 40, 255, "Chercher un fichier", true, p);
        rouge = new Slider(p.width/2 - (255/2), p.height/2, 255, 0, 255,p);
        vert = new Slider(p.width/2 - (255/2), p.height/2+30, 255, 0, 255,p);
        bleu = new Slider(p.width/2 - (255/2), p.height/2+60, 255, 0, 255,p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(100);
        p.rect(p.width/3f, 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Image",p.width/3f, 0, p.width/3f, 40);

    }

    private void drawColorText(){
        p.fill(rouge.getValue(),0,0);
        p.rectMode(p.CENTER);
        p.textAlign(p.CENTER, p.CENTER);
        p.text((int)rouge.getValue(), p.width/2f + (255/2f)+20, p.height/2f-2);

        p.fill(0,vert.getValue(),0);
        p.rectMode(p.CENTER);
        p.textAlign(p.CENTER, p.CENTER);
        p.text((int)vert.getValue(), p.width/2f + (255/2f)+20, p.height/2f+30-2);

        p.fill(0,0,bleu.getValue());
        p.rectMode(p.CENTER);
        p.textAlign(p.CENTER, p.CENTER);
        p.text((int)bleu.getValue(), p.width/2f + (255/2f)+20, p.height/2f+60-2);
    }

    public void draw(){
        p.background(200);
        accessFile.display();
        drawCurrentOnglet();
        ongletTxt.display();
        ongletSnd.display();
        p.fill(150);
        p.rect(150, 70, 350,270);
        retour.display();
        rouge.display();
        vert.display();
        bleu.display();

        drawColorText();

        p.fill(rouge.getValue(), vert.getValue(), bleu.getValue());
        p.rectMode(p.CENTER);
        p.rect(p.width/2f,p.width/2f - 170,90,90);

        validerRecherche.display();
        multimoteur.display();
        p.text("Recherche multimoteur",p.width/2f , p.height/2f +112);
        p.text("Glissez et déposez l'image que vous voulez rechercher", p.width/2f, p.height-20);

    }

    public void mousePressed(){
        ongletTxt.clickParsing();
        ongletSnd.clickParsing();
        retour.clickParsing();
        rouge.clickParsing();
        vert.clickParsing();
        bleu.clickParsing();
        validerRecherche.clickParsing();
        multimoteur.clickParsing();
        accessFile.clickParsing();
    }

    public void mouseReleased(){
        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(ongletSnd.release())
            nextScreen = ScreenName.SEARCH_CONFIG_SND;

        if(retour.release())
            nextScreen = ScreenName.MAIN;

        if(validerRecherche.release())
            nextScreen = ScreenName.LOADING;    //TODO lancer la recherche

        rouge.release();
        vert.release();
        bleu.release();
        multimoteur.release();
        if(accessFile.release())
            fileChooser.display(FileChooseType.IMAGE);
        //TODO Faire le lien ici aussi ptdr
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_IMG;
        return temp;
    }


    public void launchSearch(String filePath) {
        //TODO le lien avec le reste du projet
    }
}