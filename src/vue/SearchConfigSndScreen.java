/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.*;
import processing.core.PApplet;

import java.io.File;
import java.util.ArrayList;

import vue.FileChooser.FileChooseType;
import vue.FileChooser.FileChooser;


public class SearchConfigSndScreen {

    private PApplet p;
    private Button ongletImg;
    private Button ongletTxt;
    private Button validerRecherche;
    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_SND;
    private TickBox multimoteur;
    private FileChooser fileChooser = new FileChooser("../base_de_document");
    private Button retour;
    private Button searchFile;

    private ArrayList<ControlRequete> listControlRequete;
    private File file = null;
    private float posY = 0;

    public SearchConfigSndScreen(PApplet p, ArrayList<ControlRequete> listControlRequete){
        this.p = p;
        this.listControlRequete = listControlRequete;

        ongletImg = new Button(p.width/3,0,p.width/3,40,255,"Image",false,p);
        ongletTxt = new Button(0,0,p.width/3,40,255,"Texte",false,p);
        validerRecherche = new Button(p.width/2-60, p.height/2 + 90, 100, 40, 255, "Valider", true, p);
        searchFile = new Button(p.width/2+60, p.height/2 + 90, 100, 40, 255, "Chercher un fichier", true, p);
        retour = new Button(10, p.height-50, 100, 40, 255, "Retour", false, p);
        multimoteur = new TickBox(p.width/2 - 75, p.height/2+25,15,15,true, p);
    }

    private void drawCurrentOnglet(){
        p.rectMode(p.CORNER);
        p.fill(100);
        p.rect(2*(p.width/3f), 0, p.width/3f, 40, 5);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Son",2*(p.width/3f), 0, p.width/3f, 40);
    }

    public void draw(){
        p.background(200);
        p.text("Glissez et déposez le fichier à recherche", p.width/2f, p.height/2f);
        drawCurrentOnglet();
        ongletImg.display();
        ongletTxt.display();
        retour.display();
        searchFile.display();
        multimoteur.display();
        validerRecherche.display();

        if(file!=null){
            p.text(file.getName(),p.width/2f,p.height/3f  - 30 );

            for(int i = p.width/3; i<2*p.width/3; i++){
                //float posY_1 = posY + p.random(-2,2);
                //posY_1 = (posY_1<p.height/3f -20?p.height/3f-20:(posY_1>p.height/3f +20?p.height/3f +20:posY_1));

                float posY_1 = p.random(-5,5);
                //p.line(i,posY, i+1, posY_1);
                p.line(i,
                        p.height/3f+4*p.cos((i)/(2*p.PI))*posY,
                        i+1,
                        p.height/3f+4*p.cos((i+1)/(2*p.PI))*posY_1
                );
                posY=posY_1;
            }
            posY=0;
        }

        p.text("Recherche multimoteur", p.width/2f, p.height/2f + 22);
    }

    public void mousePressed(){
        ongletImg.clickParsing();
        ongletTxt.clickParsing();
        retour.clickParsing();
        multimoteur.clickParsing();
        searchFile.clickParsing();
        validerRecherche.clickParsing();
    }

    public void mouseReleased(){
        if(ongletImg.release())
            nextScreen = ScreenName.SEARCH_CONFIG_IMG;

        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(retour.release())
            nextScreen = ScreenName.MAIN;
        if(searchFile.release()) {
            fileChooser.display(FileChooseType.AUDIO);
            this.setArgumentRecherche(fileChooser.getFile());
        }

        if(validerRecherche.release()&&file!=null) {
            nextScreen = ScreenName.LOADING;    //TODO activer la recherche ptdr & gérer les erreurs
            this.runRecherche();
        }

        multimoteur.release();
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_SND;
        return temp;
    }


    public void runRecherche(){
        try { //Lancer la recherche
            if(file!=null){
                TypeRecherche.getINSTANCE().setTypeRequete(TypeRequete.AUDIO);
                for (ControlRequete controlRequete : listControlRequete) {
                    controlRequete.runRecherche(TypeRequete.AUDIO, "./baseDeDocuments/Audio/" + file.getName());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setArgumentRecherche(File file){
         this.file = file;
    }
}