/**
 * @file main.c
 * @author Etienne Combelles d'après Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief Comparaison d'un son (en entrée) avec les fichiers audios déjà indexés
 * @brief Ces descripeurs sont stockés dans le fichier ./data/descripteursAudio
 * @version 0.1
 * @date 18/03/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "audio.h"
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
    output = fopen("indexationAudioOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'indexationAudioOut.txt\' dosen\'t exist.\n");
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

            if(codeErreur!=0) fprintf(output,"Code Erreur : #%d\n",codeErreur);

        }else{ //Indexation du fichier
            fprintf(output,"Indexation Unique de %s\n",argv[1]);

            int codeErreur = indexationFichier(argv[1]);
            
            if(codeErreur!=0) fprintf(output,"Code Erreur : #%d\n",codeErreur);
        }
    }



    fprintf(output,"Fin\n");
    fclose(output);
    

    return 0;
}