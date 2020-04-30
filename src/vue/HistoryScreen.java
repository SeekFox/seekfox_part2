
/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;
import modele.*;
import processing.core.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Ecran d'historique
 */
public class HistoryScreen {
    public ScrollBar scrollbar;
    public int k = 0 , l = 0;

    PApplet p;
    public ArrayList<Resultat> listeDeRecherches;
    public ArrayList<Button> boutonsDeRecherches;
    private int previouspx  , previouspy, previoussx, previoussy;
    private boolean isEmpty = true;
    private ResultWindows resultWindows = new ResultWindows();

    Button backButton;
    Button plusButton;

    //private List<Resultat> historique;		// Stockage de l'historique

   public ScreenName nextScreen = ScreenName.HISTORY;

    //constructeur

    /**
     *
     * @param p
     */
    public HistoryScreen(PApplet p){
        this.p = p;
        scrollbar = new ScrollBar(p.width - 20 , 0 , 19 , 20, p);
        previouspx = 110;
        previouspy = 15;
        previoussx = 450;
        previoussy = 30 ;
        //historique = getHistorique();				// R�cup�ration de l'historique

        // L'historique est une liste d'objets de type Recherche, donc leur contenu peut �tre r�cup�r� pour �tre affich� avec les m�thodes de la classe Recherche
        listeDeRecherches = new ArrayList<>();
        boutonsDeRecherches = new ArrayList<Button>();

        if(Historique.getHistorique()!=null){
            listeDeRecherches.clear();
            boutonsDeRecherches.clear();
            previouspy=15;
            for (Resultat resultat : Historique.getHistorique()) {
                sauvegarderRecherche(resultat);
            }
        }



        backButton = new Button(20, p.height - 60, 80, 40,p.color(255), "Retour",false,p);
        plusButton = new Button(p.width - 120,p.height - 60, 100,40,p.color(255),"Plus",false,p);
    }

    /**
     * Méthode qui sert à sauvegarder les recherches
     * @param recherche
     */
    public void sauvegarderRecherche(Resultat recherche){
        String requete;

        switch (recherche.getType()){
            case TEXTE:
            case IMAGE:
            case AUDIO:
                requete = recherche.getRequete().substring(recherche.getRequete().lastIndexOf('/')+1);
                break;
            case COULEURDOMINANTE:
                requete = "#"+recherche.getRequete();
                break;
            case MOTCLEF:
            case MOTCLEF_COMPLEXE:
                requete = recherche.getRequete();
                break;
            default:
                requete = "null";
                break;

        }

        listeDeRecherches.add(recherche);
        boutonsDeRecherches.add(new Button( previouspx,
                previouspy ,
                previoussx,
                previoussy,
                255,
                requete,
                false,
                p)
        );
        previouspy += 45;
    }

    /**
     * Initialisation
     */
    public void init(){
        if(Historique.getHistorique()!=null){
            listeDeRecherches.clear();
            boutonsDeRecherches.clear();
            previouspy=15;
            if(Historique.getHistorique()!=null) {
                for (Resultat resultat : Historique.getHistorique()) {
                    sauvegarderRecherche(resultat);
                }
            }
        }
    }

    /**
     * Affichage
     */
    public void draw(){
        p.background(200);
        if(boutonsDeRecherches.size() > 9){
            scrollbar.display();
        }
        backButton.display();
        scrolldown();

    }

    /**
     *
     */
    public void mousePressed(){

        for(int i = 0; i < boutonsDeRecherches.size();i++){
            boutonsDeRecherches.get(i).clickParsing();
        }

        backButton.clickParsing();
        plusButton.clickParsing();
        scrollbar.clickParsing();
    }

    /**
     *
     */
    public void mouseReleased(){
        if(buttonIsReleased(boutonsDeRecherches)){
            //nextScreen = ScreenName.RESULTS;
        }
        if(backButton.release()){
            nextScreen = ScreenName.MAIN;
            scrollbar.reset(p.width - 20 , 0 , 19 , 20);
            k=0;
            l=0;
        }
        scrollbar.release();
        k = 0;
    }

    /**
     *
     * @return
     */
    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.HISTORY;
        return temp;
    }

    /**
     *
     * @param boutonsDeRecherches
     * @return
     */
    public boolean buttonIsReleased(ArrayList<Button> boutonsDeRecherches){
        boolean temp = false;
        for(int i=0 ; i< boutonsDeRecherches.size(); i++){
            temp = temp || boutonsDeRecherches.get(i).release();


            if(boutonsDeRecherches.get(i).isPressed()) {
                this.resultWindows.setResultat(listeDeRecherches.get(i));
                this.resultWindows.setVisible();

                System.out.println(listeDeRecherches.get(i));
            }


        }
        return temp;
    }

    /**
     *
     */
    private void scrolldown(){
        scrollbar.setSy(p.height - 16 * boutonsDeRecherches.size());
        if( l < 0) l = 0;
        if( k < 0) k = 0;
        for (int i = 0; i < boutonsDeRecherches.size(); i++) {
            boutonsDeRecherches.get(i).display();
        }
        if(scrollbar.getPosy() > k) {

            for(int j = 0 ;j < boutonsDeRecherches.size(); j++){
                boutonsDeRecherches.get(j).setPosY(boutonsDeRecherches.get(j).getposY() - 16);
                k++;
                l++;
            }
        }
        else if(scrollbar.getPosy() < l){

            for(int j = 0 ;j < boutonsDeRecherches.size(); j++){
                boutonsDeRecherches.get(j).setPosY(boutonsDeRecherches.get(j).getposY() + 16);
                l--;
                System.out.println(" l = " + l + "\n k = " + k );
            }
            k--;
        }
    }


}
