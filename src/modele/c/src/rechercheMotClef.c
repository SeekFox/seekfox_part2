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

#ifndef __CONFIG__
    #include "../include/config.h"
#endif

#ifndef __TEXTE__
    #include "../include/texte.h"
#endif

#ifndef __RECHERCHE__
    #include "../include/recherche.h"
#endif

/**
 * @brief Variable globale config
 * 
 */
Config config;

void rechercheMotClef(char * motClef){

    //Initialisation
    FILE * output = NULL;
    output = fopen("./rechercheMotClefOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'rechercheMotClefOut.txt\' doesn\'t exist.\n");
        return;
    }

    fprintf(output,"%s\n",motClef);

    
    //recuperation des parametres de config
    config = loadConfig();

    
    //Execution
    FILE * fDescripteur = NULL;
    fDescripteur = fopen("./data/descripteursTexte","r");

    //Fichier des descripteurs Textes inexistant ?
    if(fDescripteur==NULL){
        fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
        return;
    }

    char * stringDescripteurCourant = (char*)malloc(sizeof(char)* 1024);
    DescripteurTexte descripteurCourant = initDescripteurTexte();

    while(fgets(stringDescripteurCourant,1024,fDescripteur)!=NULL){
        descripteurCourant = StringTodescripteurText(stringDescripteurCourant);
        //fprintf(output,"%s\n",descripteurTexteToString(descripteurCourant));
        int comparaison = chercherMotCleDansTexte(motClef,descripteurCourant);
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
}