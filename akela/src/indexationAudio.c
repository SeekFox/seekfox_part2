/**
 * @file main.c
 * @author Etienne Combelles d'après Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief Comparaison d'un texte .xml (en entrée) avec les fichiers textes déjà indexés
 * @brief Ces descripeurs sont stockés dans le fichier ./data/descripteursAudio
 * @version 0.1
 * @date 28/02/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef __CONFIG__
    #include "../include/config.h"
#endif

#ifndef __TEXTE__
    #include "../include/audio.h"
#endif


/**
 * @brief Variable globale config
 * 
 */
Config config;

int indexationAudio (char * argument){

    //Initialisation
    FILE * output = NULL;
    output = fopen("indexationOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'indexationOut.txt\' dosen\'t exist.\n");
        return 1;
    }

    //recuperation des parametres de config
    config = loadConfig();

    int codeErreur = 0;

    if(strcmp(argument,"all")==0){ //Indexation Totale du fichier fichiersIndexesTexte
        fprintf(output,"Indexation Totale\n");

        codeErreur = indexationTotaleAudio();

    }else{ //Indexation du fichier
        fprintf(output,"Indexation Unique de %s\n",argument);

        codeErreur = indexationFichierAudio(argument);
    }

    if(codeErreur!=0) fprintf(output,"Code Erreur : #%d\n",codeErreur);



    fprintf(output,"Fin\n");
    fclose(output);
    

    return 0;
}