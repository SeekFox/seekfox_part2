/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class LoginScreen {
    PApplet p;
    ScreenName nextScreen = ScreenName.ADMIN_CONNECTION;
    Textbox usernameBox;
    Textbox passwordBox;
    Button validerButton;

    public LoginScreen(PApplet p) {
        this.p = p;
        usernameBox = new Textbox(p.width / 2, p.height/2-30, p.width / 4, 40, "Login", false, true, p);
        passwordBox = new Textbox(p.width / 2, p.height/2+30, p.width / 4, 40, "Mot de Passe", true, true, p);

        validerButton = new Button(p.width/2, p.height/2 + 110, 100, 40,p.color(255), "Valider",true,p);
    }

    public void draw() {
        p.background(200);
        usernameBox.display();
        passwordBox.display();
        validerButton.display();
    }

    public void mousePressed() {
        usernameBox.clickParsing();
        passwordBox.clickParsing();
        validerButton.clickParsing();
    }

    public void mouseReleased() {
        usernameBox.release();
        passwordBox.release();

        if(validerButton.release()); //TODO Transition
    }

    public void keyPressed() {
        char key = p.key;
        usernameBox.keyPressedParsing(key);
        passwordBox.keyPressedParsing(key);
    }

    public ScreenName getNextScreen() {
        return nextScreen;
    }
}
