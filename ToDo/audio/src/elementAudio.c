/**
 * @file elementAudio.c
 * @author Gael Gamba
 * @brief Element constituant la Pile audio
 * @version 0.1
 * 
 * @copyright Copyright (c) 2019
 * 
 */

#include <stdio.h>
#include <stdlib.h>

#ifndef __ELEMENT_AUDIO__
    #include "../include/elementAudio.h"
#endif


void affiche_ELEMENT(ELEMENT display){
	printf("%3d", display.val);
}

ELEMENT saisir_ELEMENT(){
	ELEMENT newElement;
	scanf("%3d", &newElement.val);
	return newElement;
}

ELEMENT affect_ELEMENT(int value){
	ELEMENT newElement;
	newElement.val = value;
	return newElement;	
}

int compare_ELEMENT(ELEMENT a, ELEMENT b){
	if(a.val == b.val)
		return 1;
	else
		return 0;
}