/**
 * @file pyle_dynamiqueAudio.h
 * @author Gael Gamba
 * @brief 
 * @version 0.1
 * 
 * @copyright Copyright (c) 2019
 * 
 */

#ifndef __PILE_AUDIO__

#include "elementAudio.h"

typedef struct CELLULE{
	ELEMENT valeur;
	struct CELLULE* nextCell;
}CELLULE;

typedef CELLULE* PILE;

PILE init_PILE();
void affiche_PILE(PILE display);
int PILE_estVide(PILE pileVide);

PILE emPILE(PILE p_oldPile, ELEMENT e);
PILE emPILEVal(PILE p_oldPile, int valeur);

PILE dePILE(PILE newPile, int* oldTete);
PILE coPILE(PILE *cpy);	//ptdr le jeu de mots hahahahhahha je pleur
int taillePILE(PILE pileATester);
PILE inversePILE(PILE oldPile);
int getElementValue(PILE pileAVider, int elementNb);

#endif