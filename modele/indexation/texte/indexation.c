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

/**
 * @brief Indexer tout les fichiers déjà indexés
 * 
 * @return int 
 */
int indexationTotale(){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("../../../data/fichiersIndexesTexte","r+");
    if(fichierIndexes==NULL) return 11;

    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("../../../data/descripteursTexte","w");
    if(fichierDescripteurs==NULL) return 12;


    char * fichierCourant = (char*)malloc(sizeof(char)*64);
    char * cheminFichierCourant = (char*)malloc(sizeof(char)*128);

    DescripteurTexte descripteurCourant = initDescripteurTexte();
    FILE * fichier = NULL;


    FILE * newFichierIndexes = NULL;
    newFichierIndexes = fopen("../../../data/TMP_fichiersIndexesTexte","a");
    if(newFichierIndexes==NULL) return 13;

    
    //Generation des descritpeurs et ecriture dans descripteursTexte
    while(fgets(fichierCourant,1024,fichierIndexes)!=NULL){
        fichierCourant[strcspn(fichierCourant,"\r\n")] = 0; //Suppression du \n
        sprintf(cheminFichierCourant,"../../../base_de_document/Texte/%s",fichierCourant);

        fichier = fopen(cheminFichierCourant,"r");
        if(fichier!=NULL){
            descripteurCourant = lireFichierTexte(cheminFichierCourant);
        
            fprintf(fichierDescripteurs,"%s\n",descripteurTexteToString(descripteurCourant));
            fprintf(newFichierIndexes,"%s\n",fichierCourant);
            fclose(fichier);
            fichier = NULL;
        }
    }

    fclose(fichierIndexes);
    fclose(fichierDescripteurs);

    rename("../../../data/TMP_fichiersIndexesTexte","../../../data/fichiersIndexesTexte");
    fclose(newFichierIndexes);

    //free(fichierCourant);
    //free(cheminFichierCourant);


    return 0;
}


int indexationFichier(char * cheminFichier){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("../../../data/fichiersIndexesTexte","a+");
    if(fichierIndexes==NULL) return 21;


    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("../../../data/descripteursTexte","a+");
    if(fichierDescripteurs==NULL){
        fclose(fichierIndexes);
        return 22;
    } 


    

    FILE * fichier = NULL;
    fichier = fopen(cheminFichier,"r");
    //Le fichier n'existe pas
    if(fichier==NULL){
        fclose(fichierIndexes);
        fclose(fichierDescripteurs);
        return 23;
    } 

    char * fichierCourant = (char*)malloc(sizeof(char)*64);
    char * cheminFichierCourant = (char*)malloc(sizeof(char)*128);

    //Generation des descritpeurs et ecriture dans descripteursTexte
    while(fgets(fichierCourant,1024,fichierIndexes)!=NULL){
        fichierCourant[strcspn(fichierCourant,"\r\n")] = 0; //Suppression du \n
        sprintf(cheminFichierCourant,"../../../base_de_document/Texte/%s",fichierCourant);
        
        if(strcmp(cheminFichier,cheminFichierCourant)==0){
            fclose(fichierIndexes);
            fclose(fichierDescripteurs);
            return 24;
        }
    }

    DescripteurTexte descripteurCourant = initDescripteurTexte();
    descripteurCourant = lireFichierTexte(cheminFichier);

    //printf("%s\n",cheminFichier);
    //printf("%s\n",strrchr(cheminFichier,'/'));
    //printf("%s%s\n","../../../base_de_document/Texte",strrchr(cheminFichier,'/'));

    char * newCheminFichier = (char*)malloc(sizeof(char)*128);
    sprintf(newCheminFichier,"%s%s","../../../base_de_document/Texte",strrchr(cheminFichier,'/'));
    rename(cheminFichier,newCheminFichier);

    strcpy(newCheminFichier,strrchr(cheminFichier,'/'));
    strcpy(newCheminFichier,strrchr(newCheminFichier,newCheminFichier[1]));

    fprintf(fichierIndexes,"%s\n",newCheminFichier);
    fprintf(fichierDescripteurs,"%s\n",descripteurTexteToString(descripteurCourant));




    fclose(fichierIndexes);
    fclose(fichierDescripteurs);

    free(fichierCourant);
    free(cheminFichierCourant);
    free(newCheminFichier);

    return 0;

}
