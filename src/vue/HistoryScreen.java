
/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;
import modele.Button;
import modele.ScreenName;
import processing.core.*;

import java.util.ArrayList;

public class HistoryScreen {
    public ScrollBar scrollbar;
    public int k = 0 , l = 0;

    PApplet p;
    public ArrayList<String> listeDeRecherches;
    public ArrayList<Button> boutonsDeRecherches;
    private int previouspx  , previouspy, previoussx, previoussy;
    private boolean isEmpty = true;
    Button backButton;
    Button plusButton;

    //private List<Resultat> historique;		// Stockage de l'historique

   public ScreenName nextScreen = ScreenName.HISTORY;

    //constructeur
    public HistoryScreen(PApplet p){
        this.p = p;
        scrollbar = new ScrollBar(p.width - 20 , 0 , 19 , 20, p);
        previouspx = 110;
        previouspy = 15;
        previoussx = 450;
        previoussy = 30 ;
        //historique = getHistorique();				// R�cup�ration de l'historique

        // L'historique est une liste d'objets de type Recherche, donc leur contenu peut �tre r�cup�r� pour �tre affich� avec les m�thodes de la classe Recherche
        listeDeRecherches = new ArrayList<String>();
        boutonsDeRecherches = new ArrayList<Button>();

        sauvegarderRecherche("Recherche 1 = Résultat = ");
        sauvegarderRecherche("Recherche 2 = Résultat = ");
        sauvegarderRecherche("Recherche 3 = Résultat = ");
        sauvegarderRecherche("Recherche 4 = Résultat = ");
        sauvegarderRecherche("Recherche 5 = Résultat = ");
        sauvegarderRecherche("Recherche 6 = Résultat = ");
        sauvegarderRecherche("Recherche 7 = Résultat = ");
        sauvegarderRecherche("Recherche 8 = Résultat = ");
        sauvegarderRecherche("Recherche 9 = Résultat = ");
        sauvegarderRecherche("Recherche 10 = Résultat = ");
        sauvegarderRecherche("Recherche 11 = Résultat = ");
        sauvegarderRecherche("Recherche 12 = Résultat = ");
        sauvegarderRecherche("Recherche 13 = Résultat = ");
        sauvegarderRecherche("Recherche 14 = Résultat = ");
        sauvegarderRecherche("Recherche 15 = Résultat = ");
        sauvegarderRecherche("Recherche 16 = Résultat = ");
        sauvegarderRecherche("Recherche 17 = Résultat = ");
        sauvegarderRecherche("Recherche 18 = Résultat = ");
        sauvegarderRecherche("Recherche 19 = Résultat = ");

    }

    //méthode quisert à sauvegarder les recherches
    public void sauvegarderRecherche(String recherche){
        listeDeRecherches.add(recherche);
        boutonsDeRecherches.add(new Button( previouspx,previouspy ,previoussx,previoussy,255,recherche,false,p));
        previouspy += 45;
        backButton = new Button(20, p.height - 60, 80, 40,p.color(200,0,0), "Retour",false,p);
        plusButton = new Button(p.width - 120,p.height - 60, 100,40,p.color(255),"Plus",false,p);

    }

    public void draw(){
        p.background(200);
        if(boutonsDeRecherches.size() > 9){
            scrollbar.display();
        }
        backButton.display();
        scrolldown();

    }

    public void mousePressed(){

        for(int i = 0; i < boutonsDeRecherches.size();i++){
            boutonsDeRecherches.get(i).clickParsing();
        }

        backButton.clickParsing();
        plusButton.clickParsing();
        scrollbar.clickParsing();
    }

    public void mouseReleased(){
        if(buttonIsReleased(boutonsDeRecherches)){
            nextScreen = ScreenName.RESULTS;
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

    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        nextScreen = ScreenName.HISTORY;
        return temp;
    }

    public boolean buttonIsReleased(ArrayList<Button> boutonsDeRecherches){
        boolean temp = false;
        for(int i=0 ; i< boutonsDeRecherches.size(); i++){
            temp = temp || boutonsDeRecherches.get(i).release();
        }
        return temp;
    }

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
