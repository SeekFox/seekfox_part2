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
#include "texte.h"
#include "../config.h"

/**
 * @brief Variable globale config
 * 
 */
Config config;

int main (int argc, char * argv[]){

    printf("RECHERCHE TEXTE\n\n");

    
    //recuperation des parametres de config
    config = loadConfig();

    for(int i = 0; i<argc; i++){
        printf("%s\n",argv[i]);
    }

    printf("CONFIG %s",getLogicielTexte(config));


    return 0;
}