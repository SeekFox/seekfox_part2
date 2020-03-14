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
#include "../../config.h"

/**
 * @brief Variable globale config
 * 
 */
Config config;

int main (int argc, char * argv[]){

    //Initialisation
    FILE * output = NULL;
    output = fopen("./src/modele/recherche/motClef/rechercheMotClefOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'rechercheMotClefOut.txt\' dosen\'t exist.\n");
        return 1;
    }

    //Verification nombre de parametres
    if(argc!=2){
        fprintf(output,"ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        printf("ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        return 2;
    }


    char * motClef = (char*)malloc(sizeof(char)*strlen(argv[1]));

    strcpy(motClef,argv[1]);
    
    fprintf(output,"%s\n",motClef);

    
    //recuperation des parametres de config
    config = loadConfig();

    
    //Execution
    FILE * fDescripteur = NULL;
    fDescripteur = fopen("./data/descripteursTexte","r");

    //Fichier des descripteurs Textes inexistant ?
    if(fDescripteur==NULL){
        fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "../../../../data/descripteursTexte");
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "../../../../data/descripteursTexte");
        return 3;
    }

    char * stringDescripteurCourant = (char*)malloc(sizeof(char)* 1024);
    DescripteurTexte descripteurCourant = initDescripteurTexte();

    while(fgets(stringDescripteurCourant,1024,fDescripteur)!=NULL){
        descripteurCourant = StringTodescripteurText(stringDescripteurCourant);
        //fprintf(output,"%s\n",descripteurTexteToString(descripteurCourant));
        int comparaison = chercherMotCleDansTexte(argv[1],descripteurCourant);
        if(comparaison > 0){
            fprintf(output,"%s;%d\n",getNameDescripteurTexte(descripteurCourant)
                                    ,comparaison
                                    );
            /*printf("%s;%d\n",getNameDescripteurTexte(descripteurCourant)
                                                 ,comparaison
                                                 );*/
        }
    }



    fclose(fDescripteur);
    fclose(output);
    

    return 0;
}