/**
 * @file main.c
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief Comparaison d'un texte .xml (en entrée) avec les fichiers textes déjà indexés
 * @brief Ces descripeurs sont stockés dans le fichier ./data/descripteursTexte
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
#include "indexation.h"
#include "../../config.h"


/**
 * @brief Variable globale config
 * 
 */
Config config;

int main (int argc, char * argv[]){

    //Initialisation
    FILE * output = NULL;
    output = fopen("indexationTexteOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'indexationTexteOut.txt\' dosen\'t exist.\n");
        return 1;
    }

    //Verification nombre de parametres
    if(argc!=2){
        fprintf(output,"ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        return 2;
    }else{
        //recuperation des parametres de config
        config = loadConfig();


        if(strcmp(argv[1],"all")==0){ //Indexation Totale du fichier fichiersIndexesTexte
            fprintf(output,"Indexation Totale\n");

            int codeErreur = indexationTotale();

            if(codeErreur!=0) fprintf(output,"Code Erreur : #%d",codeErreur);

        }else{ //Indexation du fichier
            fprintf(output,"Indexation Unique de %s\n",argv[1]);

            int codeErreur = indexationFichier(argv[1]);
            
            if(codeErreur!=0) fprintf(output,"Code Erreur : #%d",codeErreur);
        }
    }



    fprintf(output,"Fin\n");
    fclose(output);
    

    return 0;
}