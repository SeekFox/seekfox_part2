/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue;

import modele.Button;
import modele.Resultat;
import modele.ResultsViewer;
import processing.core.PApplet;
import processing.core.PImage;

public class ResultWindows extends PApplet {
	//Attributs
	private boolean isVisible;

	private Button backButton;
	private Resultat resultat;
	private ResultsViewer resultsViewer;

	//Constructeur
	public ResultWindows(){
		super();
		PApplet.runSketch(new String[] {"Seekfox - Resultat"}, this);

		surface.setVisible(false);
		this.isVisible = false;
		this.resultsViewer = new ResultsViewer(0,0,width, height - 50,this);

		this.backButton = new Button(width-110, height-40, 100, 30, 255,"Retour au menu", false, this);
	}


	//Méthodes
	/**
	 * Spécifie les parametres de la fenetre avant sa construction
	 */
	public void settings(){
		size(640,480);
	}

	/**
	 * Specifie les parametres de la fenetre au moment de sa construction
	 */
	public void setup(){
		PImage icon = loadImage("../doc/icon.png","png");
		surface.setIcon(icon);
		surface.setTitle("Seekfox - Resultat");
		surface.setResizable(false);
	}

	/**
	 * Méthode loop qui dessine l'image des regles toutes les x fois par seconde
	 */
	public void draw(){
		background(200);
		this.resultsViewer.draw();


		backButton.display();


	}

	public void setResultat(Resultat resultat){
		this.resultat=resultat;
		resultsViewer.init(resultat);

	}

	public void mousePressed(){
		resultsViewer.clickParsing();
		backButton.clickParsing();
	}

	public void mouseReleased(){
		resultsViewer.release();
		if(backButton.release()) {
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
		this.resultsViewer.setCurrentPage(1);
		this.isVisible = false;
		surface.setVisible(false);

	}
}

