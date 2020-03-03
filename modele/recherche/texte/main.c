/**
 * @file main.c
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief 
 * @version 0.1
 * @date 28/02/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "texte.h"
#include "../config.h"

/**
 * @brief Variable globale config
 * 
 */
Config config;

int main (int argc, char * argv[]){

    //Initialisation
    FILE * output = NULL;
    output = fopen("rechercheTexteOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'rechercheTexteOut.txt\' dosen\'t exist.\n");
        return 1;
    }

    //Verification nombre de parametres
    if(argc!=2){
        fprintf(output,"ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        return 2;
    }

    FILE * fichier = NULL;
    char * cheminFichier = (char*)malloc(sizeof(char)*strlen(argv[1]));

    strcpy(cheminFichier,argv[1]);
    fichier = fopen(cheminFichier,"r");

    //Fichier exite ?
    if(fichier==NULL){
        fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", cheminFichier);
        return 3;
    }

    if(strcmp(getExtensionOfFile(cheminFichier),".xml")!=0){
        fprintf(output,"ERREUR : \'%s\' isn\'t supported by \'%s\'.\n",cheminFichier,argv[0]);
        return 4;
    }

    fprintf(output,"Extension : %s\n",getExtensionOfFile(cheminFichier));

    
    //recuperation des parametres de config
    config = loadConfig();

    
    //Execution
    DescripteurTexte dt = initDescripteurTexte();
    dt = lireFichierTexte(cheminFichier);
    afficheDescripteurTexte(dt);


    //fprintf(output,"CONFIG %s",getLogicielTexte(config));


    return 0;
}