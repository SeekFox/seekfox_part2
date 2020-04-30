/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Fenetre d'erreur
 */
public class ErrorWindow extends PApplet {
	//Attributs
	private boolean isVisible;
	private String text;
	private PFont font;

	private Button okButton;

	//Constructeur

	/**
	 *
	 * @param text
	 */
	public ErrorWindow(String text){
		super();
		this.text=text;
		PApplet.runSketch(new String[] {"Seekfox - Erreur"}, this);
		noLoop();
		surface.setVisible(false);
		this.isVisible = false;

		this.okButton =  new Button(width/2, 2*height/3, 100, 40, 255, "OK", true, this);

	}

	//Méthodes

	/**
	 * Spécifie les parametres de la fenetre avant sa construction
	 */
	public void settings(){
		size(400,300);
	}

	/**
	 * Specifie les parametres de la fenetre au moment de sa construction
	 */
	public void setup(){
		PImage icon = loadImage("../doc/icon.png","png");
		surface.setIcon(icon);
		surface.setTitle("Seakfox - Erreur");
		surface.setResizable(false);

		font = createFont("Dialog.bold", 22);
	}

	/**
	 * Méthode loop qui dessine l'image des regles toutes les x fois par seconde
	 */
	public void draw(){
		textFont(font);
		background(255,0,0);
		textAlign(CENTER, CENTER);
		text(text,width/2,height/3);
		okButton.display();


	}

	/**
	 *
	 */
	public void mousePressed(){
		okButton.clickParsing();
	}


	/**
	 *
	 */
	public void mouseReleased(){
		if(okButton.release()) {
			this.exit();
		}
	}

	/**
	 * Affichage de la fenetre
	 */
	public void setVisible() {
		if (!this.isVisible) {
			surface.setVisible(true);
			this.isVisible = true;
		}
	}

	/**
	 * Surcharge de la méthode exit de PApplet
	 */
	public void exit(){
		this.isVisible = false;
		surface.setVisible(false);

	}
}

