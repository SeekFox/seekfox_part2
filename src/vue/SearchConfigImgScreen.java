/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import controleur.ControlRequete;
import modele.*;
import modele.Button;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import vue.FileChooser.FileChooseType;
import vue.FileChooser.FileChooser;

public class SearchConfigImgScreen {

    private PApplet p;
    private modele.Button ongletTxt;
    private modele.Button ongletSnd;
    private modele.Button retour;
    private modele.Button validerRecherche;
    private modele.Button accessFile;

    private Slider rouge;
    private Slider vert;
    private Slider bleu;

    private boolean isRechercheImage = false;
    private File file;
    private PImage image;

    private Color color;

    private ScreenName nextScreen = ScreenName.SEARCH_CONFIG_IMG;

    private ArrayList<ControlRequete> listControlRequete;
//    private TickBox multimoteur;
    private FileChooser fileChooser = new FileChooser("../base_de_document");

    public SearchConfigImgScreen(PApplet p, ArrayList<ControlRequete> listControlRequete){
        this.p = p;
        this.listControlRequete = listControlRequete;
        ongletTxt = new modele.Button(0,0,p.width/3,40,255,"Texte",false,p);
        ongletSnd = new modele.Button(2*(p.width/3),0,p.width/3,40,255,"Son",false,p);
        retour = new modele.Button(10, p.height-50, 100, 40, 255, "Retour", false, p);

//        multimoteur = new TickBox(p.width/2 - 75, p.height/2 +115,15,15,true, p);
        validerRecherche = new modele.Button(p.width/2-60, p.height/2+150, 100, 40, 255, "Valider", true, p);
        accessFile = new Button(p.width/2+60, p.height/2+150, 100, 40, 255, "Chercher un fichier", true, p);
        rouge = new Slider(p.width/2 - (255/2), p.height/2, 255, 0, 255,p);
        vert = new Slider(p.width/2 - (255/2), p.height/2+30, 255, 0, 255,p);
        bleu = new Slider(p.width/2 - (255/2), p.height/2+60, 255, 0, 255,p);

        this.color = new Color(rouge.getValue(), vert.getValue(), bleu.getValue());
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

        if(isRechercheImage){
            p.image(image, p.width/2f-45,p.width/2f - 170-45,91,91);
        }else{
            p.fill(rouge.getValue(), vert.getValue(), bleu.getValue());
            p.rectMode(p.CENTER);
            p.rect(p.width/2f,p.width/2f - 170,90,90);
        }

        validerRecherche.display();
//        multimoteur.display();
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
//        multimoteur.clickParsing();

        this.color = new Color((int)(rouge.getValue()), (int)(vert.getValue()), (int)(bleu.getValue()));

        accessFile.clickParsing();
    }

    public void mouseReleased(){
        if(ongletTxt.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;

        if(ongletSnd.release())
            nextScreen = ScreenName.SEARCH_CONFIG_SND;

        if(retour.release())
            nextScreen = ScreenName.MAIN;

        if(validerRecherche.release()) {
            nextScreen = ScreenName.LOADING;    //TODO activer la recherche ptdr & gérer les erreurs
            this.runRecherche();
        }

        if(rouge.release() || vert.release() || bleu.release()){
            if(!this.color.equals(new Color((int)(rouge.getValue()), (int)(vert.getValue()), (int)(bleu.getValue())))){
                isRechercheImage = false;
            }

        }
//        multimoteur.release();
        if(accessFile.release()) {
            fileChooser.display(FileChooseType.IMAGE);
            if(fileChooser.getFile() != null)
                this.setArgumentRecherche(fileChooser.getFile());
        }
    }

    public ScreenName getNextScreen(){
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.SEARCH_CONFIG_IMG;
        return temp;
    }


    public void setArgumentRecherche(File file){
        //Affichage de l'image
        image = p.loadImage(file.getPath());

        isRechercheImage = true;
        this.file = file;
    }

    public void runRecherche(){
        try { //Lancer la recherche
            if(isRechercheImage){
                if(file!=null){
                    if(file.getParent().endsWith("RGB")){
                        TypeRecherche.getINSTANCE().setTypeRequete(TypeRequete.IMAGE);
                        for (ControlRequete controlRequete : listControlRequete) {
                            controlRequete.runRecherche(TypeRequete.IMAGE, "./baseDeDocuments/Image/RGB/" + file.getName());
                        }

                    }else if(file.getParent().endsWith("NB")){
                        TypeRecherche.getINSTANCE().setTypeRequete(TypeRequete.IMAGE);
                        for (ControlRequete controlRequete : listControlRequete) {
                            controlRequete.runRecherche(TypeRequete.IMAGE, "./baseDeDocuments/Image/NB/" + file.getName());
                        }

                    }

                    isRechercheImage=false;
                }
            }else {
                TypeRecherche.getINSTANCE().setTypeRequete(TypeRequete.COULEURDOMINANTE);
                for (ControlRequete controlRequete : listControlRequete) {
                    controlRequete.runRecherche(TypeRequete.COULEURDOMINANTE, "" + Integer.toHexString(color.getRGB()).substring(2));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}