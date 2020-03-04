/**
 * @file indexation.c
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief 
 * @version 0.1
 * @date 04/03/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "texte.h"
#include "../../config.h"

int indexationTotale(){
    FILE * fichierIndexes = NULL;

    fichierIndexes = fopen("../../../data/fichiersIndexesTexte","r+");
    if(fichierIndexes==NULL) return 1;

    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("../../../data/descripteursTexte","w");
    if(fichierDescripteurs==NULL) return 2;

    char * fichierCourant = (char*)malloc(sizeof(char)*64);
    char * cheminFichierCourant = (char*)malloc(sizeof(char)*128);

    DescripteurTexte descripteurCourant = initDescripteurTexte();

    while(fgets(fichierCourant,1024,fichierIndexes)!=NULL){
        fichierCourant[strcspn(fichierCourant,"\r\n")] = 0; //Suppression du \n
        sprintf(cheminFichierCourant,"../../../base_de_document/Texte/%s",fichierCourant);

        descripteurCourant = lireFichierTexte(cheminFichierCourant);
        
        fprintf(fichierDescripteurs,"%s\n",descripteurTexteToString(descripteurCourant));
        
    }




    return 0;
    
}


int indexationFichier(char * cheminFichier){

    return 0;

}
