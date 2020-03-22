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
    printf("heyyy\n");
    //Initialisation
    //FILE * output = NULL;
    //output = fopen("./src/modele/recherche/texte/rechercheTexteOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    /*if(output==NULL){
        printf("ERREUR : \'rechercheTexteOut.txt\' doesn\'t exist.\n");
        return 1;
    }

    //Verification nombre de parametres
    if(argc!=2){
        //fprintf(output,"ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        printf("ERREUR : \'%s\' expected one (1) argument.\n", argv[0]);
        return 2;
    }*/

    FILE * fichier = NULL;
    char * cheminFichier = (char*)malloc(sizeof(char)*strlen(argv[1]));

    strcpy(cheminFichier,argv[1]);
    fichier = fopen(cheminFichier,"r");

    printf("\n%s\n",cheminFichier);
    //fprintf(output,"%s\n",cheminFichier);

    //Fichier existe ?
    if(fichier==NULL){
        //fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", cheminFichier);
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", cheminFichier);
        return 3;
    }

    if(strcmp(getExtensionOfFile(cheminFichier),".xml")!=0){
       // fprintf(output,"ERREUR : \'%s\' isn\'t supported by \'%s\'.\n",cheminFichier,argv[0]);
        printf("ERREUR : \'%s\' isn\'t supported by \'%s\'.\n",cheminFichier,argv[0]);

        return 4;
    }


    
    //recuperation des parametres de config
    config = loadConfig();

    
    //Execution
    DescripteurTexte dt = initDescripteurTexte();
    dt = lireFichierTexte(cheminFichier);
    //afficheDescripteurTexte(dt);

    //fprintf(output,"%s\n",descripteurTexteToString(dt));


    FILE * fDescripteur = NULL;
    fDescripteur = fopen("./data/descripteursTexte","r");

    //Fichier des descripteurs Textes inexistant ?
    if(fDescripteur==NULL){
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
        //fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
        return 5;
    }

    char * stringDescripteurCourant = (char*)malloc(sizeof(char)* 1024);
    DescripteurTexte descripteurCourant = initDescripteurTexte();

    while(fgets(stringDescripteurCourant,1024,fDescripteur)!=NULL){
        descripteurCourant = StringTodescripteurText(stringDescripteurCourant);
        
        int comparaison = comparerDescripteurTexte(dt,descripteurCourant);
        if(comparaison>=20){

            //fprintf(output,"%s\n%s\n",descripteurTexteToString(dt),descripteurTexteToString(descripteurCourant));
            //fprintf(output,"%s;%.2f\n",getNameDescripteurTexte(descripteurCourant)
            //                        ,comparerDescripteurTexte(dt,descripteurCourant)
            //                        );
            printf("%s;%.2f\n",getNameDescripteurTexte(descripteurCourant)
                                                ,comparerDescripteurTexte(dt,descripteurCourant)
                                                );
        }
    }



    //fprintf(output,"CONFIG %s",getLogicielTexte(config));


    fclose(fDescripteur);
    //fclose(output);

    return 0;
}