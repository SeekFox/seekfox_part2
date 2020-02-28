/**
 * @file config.c
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief Fonctions gerants les parametres de configuration
 * @version 0.2
 * @date 23/12/2019
 * 
 * @copyright Copyright (c) 2019
 * 
 */

#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
#include <unistd.h>
#include <crypt.h>
#include <math.h>

#ifndef __CONFIG__
    #include "config.h"
#endif

/**
 * @brief Structure de Configuration
 * 
 * GENERAL
 *  passwordAdmin           : Mot de passe administrateur chiffre
 * TEXTE
 *  tailleMin               : La taille minimun pour qu'un mot soit regardé
 *  val                     : Les x mots les plus presents
 *  seuil                   : Les mots qui apparaissent plus de x fois
 * IMAGE
 *  nbBits                  : Nombre de bits de quantification de pixel 
 * AUDIO
 *  audio_n                 : Fenetre d'analyse 
 *  audio_m                 : Nombre d'intervalles de la fenetre d'analyse
 * 
 */
struct config_s{
    //General
    char passwordAdmin[32];
    char logiciel_texte[16];
    char logicielOuvertureFichier[16];

    //Texte
    int tailleMin;
    int val;
    int seuil;

    //Image
    int nbBits;

    //Audio
    int audio_n;
    int audio_m;
};

//Getter
char * getPasswordAdmin(){
    return (config->passwordAdmin);
}

int getTailleMin(){
    return (config->tailleMin);
}

int getVal(){
    return (config->val);
}

int getSeuil(){
    return (config->seuil);
}

int getNbBits(){
    return (config->nbBits);
}

int getAudioN(){
    return (config->audio_n);
}

int getAudioM(){
    return (config->audio_m);
}

char * getLogicielTexte(){
    return (config->logiciel_texte);
}


char * getLogicielOuvertureFichier(){
   return (config->logicielOuvertureFichier); 
}

//Setter
void setPasswordAdmin(Config *c, char * pwd){
    strcpy((*c)->passwordAdmin,(char*)crypt(pwd,"456b7016a916a4b178dd72b947c152b7"));
}

void setTailleMin(Config *c, int tailleMin){
    (*c)->tailleMin=tailleMin;
}

void setVal(Config *c, int val){
    (*c)->val=val;
}

void setSeuil(Config *c, int seuil){
    (*c)->seuil=seuil;
}

void setNbBits(Config *c, int nbBits){
    (*c)->nbBits=nbBits;
}

void setAudioN(Config *c, int n){
    (*c)->audio_n = n;
}

void setAudioM(Config *c, int m){
    (*c)->audio_m = m;
}

void setLogicielTexte(Config *c, char * str){
    strcpy((*c)->logiciel_texte,str);
}

void setLogicielOuvertureFichier(Config *c, char * str){
    strcpy((*c)->logicielOuvertureFichier,str);
}

/**
 * @brief Recuperation des informations dans le fichier user.config
 * 
 * @return Config 
 */
Config loadConfig(){
    Config c = (Config)malloc(sizeof(struct config_s));
    FILE * fichier = NULL;
    char line[64] = "";
    int i = 0;

    fichier = fopen("../../../data/user.config","r");
    if(fichier!=NULL){
        while (fgets(line, 64, fichier) != NULL){
            line[strcspn(line,"\r\n")] = 0; //Suppression du \n
            switch (i){
                case 0:
                    setPasswordAdmin(&c,line);
                    break;

                case 1:
                    setTailleMin(&c,atoi(line));
                    break;

                case 2:
                    setVal(&c,atoi(line));
                    break;

                case 3:
                    setSeuil(&c,atoi(line));
                    break;

                case 4:
                    setNbBits(&c,atoi(line));
                    break;

                case 5:
                    setAudioN(&c,atoi(line));
                    break;

                case 6:
                    setAudioM(&c,atoi(line));
                    break;

                case 7:
                    setLogicielTexte(&c,line);
                    break;

                case 8:
                    setLogicielOuvertureFichier(&c,line);
                    break;
                
                default:
                    break;
            }
            i++;

        }
        fclose(fichier);
    }else{
        printf("Fichier de config inexistant\n");
        exit(-1);
    }

    return c;
}

/**
 * @brief Affichage des parametres de configuration
 * 
 */
void displayConfig(){
    printf("CONFIGURATIONS\n");

    printf("GENERAL \n");
    printf("\tLogiciel de visualisation de fichier ");
    color("1");
    printf("%s\n",getLogicielOuvertureFichier(config));
    color("0");

    printf("TEXTE \n");
    printf("\tTaille minimun d'un mot              ");
    color("1");
    printf("%d\n",getTailleMin(config));
    color("0");
    printf("\tValeur limite de mots                ");
    color("1");
    printf("%d\n",getVal(config));
    color("0");
    printf("\tSeuil limite de taille de mot        ");
    color("1");
    printf("%d\n",getSeuil(config));
    color("0");
    printf("\tLogiciel de visualisation            ");
    color("1");
    printf("%s\n",getLogicielTexte(config));
    color("0");

    printf("IMAGE \n");
    printf("\tNombre de bits de quantification     ");
    color("1");
    printf("%d\n",getNbBits(config));
    color("0");

    printf("AUDIO \n");
    printf("\tTaille de la fenetre d'analyse       ");
    color("1");
    printf("%d\n",getAudioN(config));
    color("0");
    printf("\tNombre d'intervalles                 ");
    color("1");
    printf("%d\n",getAudioM(config));
    color("0");
    printf("\n");
}   


/**
 * @brief Mise à jour du fichier user.config
 * 
 */
void majConfigFile(){
    FILE * fichier = NULL;

    fichier = fopen("data/user.config","w+");

    if(fichier!=NULL){
        fprintf(fichier,"%s\n",getPasswordAdmin());
        fprintf(fichier,"%d\n",getTailleMin());
        fprintf(fichier,"%d\n",getVal());
        fprintf(fichier,"%d\n",getSeuil());
        fprintf(fichier,"%d\n",getNbBits());
        fprintf(fichier,"%d\n",getAudioN());
        fprintf(fichier,"%d\n",getAudioM());
        fprintf(fichier,"%s\n",getLogicielTexte());
        fprintf(fichier,"%s\n",getLogicielOuvertureFichier());

    }else{
        printf("Fichier de config inexistant");
        exit(-1);
    }
    fclose(fichier);
}
