/**
 * @file rechercheAudio.c
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief Comparaison d'un fichier audio .xml (en entrée) avec les fichiers audio déjà indexés
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

#include "../include/pcre.h"
#include "../include/ivy.h"
#include "../include/ivyloop.h"


#ifndef __CONFIG__
    #include "../include/config.h"
#endif


//#ifndef __AUDIO__
//    #include "../include/audio.h"
//#endif


#ifndef __RECHERCHE__
    #include "../include/recherche.h"
#endif
/**
 * @brief Variable globale config
 *
 */
Config config;


int rechercheAudio(char * cheminFichier){

    //Initialisation
    /*
    FILE * output = NULL;
    output = fopen("./rechercheOut.txt","w+");

    //Le fichier de sortie est ouvert ?
    if(output==NULL){
        printf("ERREUR : \'rechercheOut.txt\' doesn\'t exist.\n");
        return 1;
    }

//    fprintf(output,"%s\n",cheminFichier);
    fprintf(output,"%s\n","./baseDeDocuments/Audio/corpus_fi.bin");
*/

    FILE * fichier = NULL;

    fichier = fopen(cheminFichier,"r");


    //Fichier existe ?
    if(fichier==NULL){
        //fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", cheminFichier);
        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", cheminFichier);
        return 2;
    }

    if(strcmp(getExtensionOfFile(cheminFichier),".bin")!=0){
       // fprintf(output,"ERREUR : \'%s\' isn\'t supported by \'%s\'.\n",cheminFichier,argv[0]);
        printf("ERREUR : \'%s\' isn\'t supported by rechercheAudio.\n",cheminFichier);

        return 3;
    }



//    DONNEES EN DUR
    IvySendMsg("Baloo type=RESULT file=%s score=%f","./baseDeDocuments/Audio/corpus_fi.bin"
                                                                        ,28.79);

    /*fprintf(output,"%s;%.2f\n","./baseDeDocuments/Audio/corpus_fi.bin"
                              ,28.79
                              );*/

//    FIN DONNEES EN DUR



//    recuperation des parametres de config
//    config = loadConfig();


//    Execution
//    DescripteurTexte dt = initDescripteurTexte();
//    dt = lireFichierTexte(cheminFichier);
//    afficheDescripteurTexte(dt);

//    fprintf(output,"%s\n",descripteurTexteToString(dt));


//    FILE * fDescripteur = NULL;
//    fDescripteur = fopen("./data/descripteursAudio","r");

//    Fichier des descripteurs Textes inexistant ?
//    if(fDescripteur==NULL){
//        printf("ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
//        fprintf(output,"ERREUR : \'%s\' can\'t be read or doesn\'t exist.\n", "./data/descripteursTexte");
//        return 5;
//    }

//    char * stringDescripteurCourant = (char*)malloc(sizeof(char)* 1024);
//    DescripteurTexte descripteurCourant = initDescripteurTexte();
//
//    while(fgets(stringDescripteurCourant,1024,fDescripteur)!=NULL){
//        descripteurCourant = StringTodescripteurText(stringDescripteurCourant);
//
//        int comparaison = comparerDescripteurTexte(dt,descripteurCourant);
//        if(comparaison>=20){
//            fprintf(output,"%s;%.2f\n",getNameDescripteurTexte(descripteurCourant)
//                                    ,comparerDescripteurTexte(dt,descripteurCourant)
//                                    );
//            printf("%s;%.2f\n",getNameDescripteurTexte(descripteurCourant)
//                                               ,comparerDescripteurTexte(dt,descripteurCourant)
//                                               );
//        }
//    }



    //fprintf(output,"CONFIG %s",getLogicielTexte(config));


//    fclose(fDescripteur);
    //fclose(output);

    return 0;
}