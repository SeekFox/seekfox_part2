/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import modele.ScreenName;
import modele.Textbox;
import processing.core.PApplet;

/**
 * Ecran de connection
 */
public class LoginScreen {
    private final static String adminLogin = "admin";
    private final static String adminPassword = "123456";
    private boolean isconnected = false;
    PApplet p;

    boolean errorTextIsVisible = false;

    ScreenName nextScreen = ScreenName.ADMIN_CONNECTION;
    Textbox usernameBox;
    Textbox passwordBox;
    Button validerButton;
    Button backButton;

    /**
     * Constructeur
     * @param p
     */
    public LoginScreen(PApplet p) {
        this.p = p;
        usernameBox = new Textbox(p.width / 2, p.height/2-20, p.width / 4, 30, "Login", false, true, p);
        passwordBox = new Textbox(p.width / 2, p.height/2+20, p.width / 4, 30, "Mot de Passe", true, true, p);

        validerButton = new Button(p.width/2, p.height/2 + 110, 100, 40,p.color(255), "Valider",true,p);
        backButton = new Button(20, p.height - 60, 100, 40,p.color(255), "Retour",false,p);
    }

    /**
     * Affichage
     */
    public void draw() {
        p.background(200);
        usernameBox.display();
        passwordBox.display();
        validerButton.display();
        backButton.display();
        if(errorTextIsVisible) {
            p.fill(255,0,0);
            p.rectMode(p.CENTER);
            //noinspection IntegerDivisionInFloatingPointContext
            p.text("Mot de passe incorrect", p.width / 2, p.height - 100);
        }
    }

    /**
     *
     */
    public void mousePressed() {
        usernameBox.clickParsing();
        passwordBox.clickParsing();
        validerButton.clickParsing();
        backButton.clickParsing();
    }

    /**
     *
     */
    public void mouseReleased() {
        usernameBox.release();
        passwordBox.release();

        if(validerButton.release()){
            if(usernameBox.getWrittenText().equals(adminLogin)  && passwordBox.getWrittenText().equals(adminPassword)){
                nextScreen = ScreenName.CONFIG;
                isconnected = true;
            }
            else
                errorTextIsVisible = true;
        }
        if(backButton.release())
            nextScreen = ScreenName.MAIN;

    }


    /**
     *
     * @param key
     */
    public void keyPressed(char key) {
        usernameBox.keyPressedParsing(key);
        passwordBox.keyPressedParsing(key);
    }

    /**
     *
     * @return
     */
    public ScreenName getNextScreen() {
        ScreenName temp = nextScreen;
        if(!isconnected){
            nextScreen = ScreenName.ADMIN_CONNECTION;
        }
        else{
            nextScreen = ScreenName.CONFIG;
        }
        return temp;
    }

    /**
     * Initialisation
     */
    public void init() {
        usernameBox.resetText();
        passwordBox.resetText();
        errorTextIsVisible = false;

    }
}
