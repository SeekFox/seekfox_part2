/**
 * @file rechercheMotClef.c
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

#include "../include/pcre.h"
#include "../include/ivy.h"
#include "../include/ivyloop.h"

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

int rechercheMotClef(char * motClef){

    //Initialisation
    //recuperation des parametres de config
    config = loadConfig();

    
    //Execution
    FILE * fDescripteur = NULL;
    fDescripteur = fopen("./data/descripteursTexte","r");

    //Fichier des descripteurs Textes inexistant ?
    if(fDescripteur==NULL){
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
        return 2;
    }

    char * stringDescripteurCourant = (char*)malloc(sizeof(char)* 1024);
    DescripteurTexte descripteurCourant = initDescripteurTexte();

    while(fgets(stringDescripteurCourant,1024,fDescripteur)!=NULL){
        descripteurCourant = StringTodescripteurText(stringDescripteurCourant);

        int comparaison = chercherMotCleDansTexte(motClef,descripteurCourant);
        if(comparaison > 0){
            IvySendMsg("Baloo type=RESULT file=%s score=%d",getNameDescripteurTexte(descripteurCourant)
                                                                    ,comparaison);
            /*printf("%s;%d\n",getNameDescripteurTexte(descripteurCourant)
                                                 ,comparaison
                                                 );*/
        }
    }



    fclose(fDescripteur);

    return 0;
}