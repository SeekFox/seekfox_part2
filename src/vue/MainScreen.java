/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import processing.core.PApplet;

public class MainScreen {
    PApplet p;
    Button launchButton;
    Button historiqueButton;
    Button settingsButton;
    ScreenName nextScreen = ScreenName.MAIN;

    public MainScreen(PApplet p) {
        this.p = p;
        launchButton = new Button(p.width / 2, p.height / 2, p.width / 4, 40, p.color(255), "Chercher", true, p);
        historiqueButton = new Button(20, p.height - 60, p.width / 5, 40, p.color(255), "Historique", false, p);
        settingsButton = new Button(p.width - p.width / 5 - 20, p.height - 60, p.width / 5, 40, p.color(255), "Configuration", false, p);
    }

    public void draw() {
        p.background(200);
        launchButton.display();
        historiqueButton.display();
        settingsButton.display();
    }

    public void mousePressed() {
        launchButton.clickParsing();
        historiqueButton.clickParsing();
        settingsButton.clickParsing();
    }

    public void mouseReleased() {
        if (launchButton.release())
            nextScreen = ScreenName.SEARCH_CONFIG_TXT;
        if (historiqueButton.release())
            nextScreen = ScreenName.HISTORY;
        if (settingsButton.release())
            nextScreen = ScreenName.ADMIN_CONNECTION;
    }

    public void keyPressed() {

    }

    public ScreenName getNextScreen() {
        return ScreenName.MAIN;
    }
}
