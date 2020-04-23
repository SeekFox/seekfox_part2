/**
 * IMPORTANT :
 * La recherche par couleur dominante n'a pas été implémentée dans le moteur en C.
 * Ce fichier et ses fonctions renvoient des fichiers selon des critères prédéfinis,
 * et n'a pour but que d'être utilisé à des fins de test de l'interface du moteur en java.
 * 
 * @file rechercheCouleurDominante.c
 * @author Etienne COMBELLES
 * @date 23/04/2020
 * 
*/

#include <stdio.h>
#include <stdlib.h>

#include "../include/pcre.h"
#include "../include/ivy.h"
#include "../include/ivyloop.h"

#ifndef __RECHERCHE__
    #include "../include/recherche.h"
#endif

int RVBouNB (char * argv) {         // Renvoie 1 si la chaîne de caractères est du NB, 2 si c'est du RVB, 0 sinon
    int i=0;
    while (argv[i]!='\n') i++;
    if (i==4) return 1;
    if (i==12) return 2;
    else return 0;
}

int stringToNB (char * argv) {       // Renvoie la valeur de noir entre 0 et 255 pour les images NB
    char * valeur = malloc(3*sizeof(char));
    for(int i=1; i<4; i++) {
        if(argv[i]=='\0') return -1;
        valeur[i-1]=argv[i];
    }
    int val = atoi(valeur);
    free(valeur);
    return val;
}

int * stringToRGB (char * argv) {
    char * rouge = malloc(3*sizeof(char));
    char * vert = malloc(3*sizeof(char));
    char * bleu = malloc(3*sizeof(char));
    for(int i=1; i<4; i++) {
        if(argv[i]=='\0') return NULL;
        rouge[i-1]=argv[i];
    }
    for(int i=4; i<8; i++) {
        if(argv[i]=='\0') return NULL;
        vert[i-4]=argv[i];
    }
    for(int i=8; i<12; i++) {
        if(argv[i]=='\0') return NULL;
        bleu[i-8]=argv[i];
    }
    int r = atoi(rouge);
    int v = atoi(vert);
    int b = atoi(bleu);
    free(rouge);
    free(vert);
    free(bleu);
    int * RGB = malloc(3*sizeof(int));
    RGB[0] = r;
    RGB[1] = v;
    RGB[2] = b;
    return RGB;
}

int rechercheCouleurDominanteRGB (int rouge, int vert, int bleu) {

    // On vérifie que les couleurs sont bien données dans l'intervalle (0; 255)
    if ( (rouge<0 || rouge>255) || (vert<0 || vert>255) || (bleu<0 || bleu>255)) {
        printf("ERREUR : au moins un code couleur est incorrect. \n");
        return 1;
    }

    // Selon les combinaisons de couleurs, différents fichiers pourront être renvoyés
    if(rouge==vert && vert==bleu) {
        // Cas 1 : toutes les couleurs sont identiques : nuances de gris
        if(rouge>175) {     // On renvoie des images avec du blanc
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/27.jpg", 90.00);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/26.jpg", 82.50);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/25.jpg", 68.75);
        }
        else if(rouge<80) {     // Images avec du noir
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/28.jpg", 71.30);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/16.jpg", 45.50);
        } else {        // Images avec du gris
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/04.jpg", 90.00);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/07.jpg", 87.40);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/21.jpg", 51.00);
        }

    } else if (rouge>vert && rouge>bleu) {
        if (rouge>vert+50 && rouge>bleu+50) {       // Images avec beaucoup de rouge
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/16.jpg", 95.70);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/38.jpg", 72.00);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/43.jpg", 81.45);
        } else {
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/40.jpg", 78.63);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/33.jpg", 47.03);
        }

    } else if (vert>rouge && vert>bleu) {
        if(vert>rouge+50 && vert>bleu+50) {     // Plus de vert
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/32.jpg", 89.40);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/35.jpg", 61.25);
        } else {
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/39.jpg", 72.70);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/37.jpg", 33.33);
        }

    } else if (bleu>rouge && bleu>vert) {
        if(bleu>rouge+50 && bleu>vert+50) {     // Plus de bleu
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/45.jpg", 83.20);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/20.jpg", 80.00);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/49.jpg", 45.80);
        } else {
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/29.jpg", 78.70);
            IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/48.jpg", 59.00);
        }

    } else {        // Trucs en vrac
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/kim.jpg", 100.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/30.jpg", 90.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/35.jpg", 80.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/37.jpg", 70.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/RGB/16.jpg", 60.00);
    }
    return 0;
}


int rechercheCouleurDominanteNB (int valeur) {

    // On vérifie que les couleurs sont bien données dans l'intervalle (0; 255)
    if (valeur<0 || valeur>255) {
        printf("ERREUR : code couleur incorrect. \n");
        return 1;
    }

    if(valeur>175) {     // On renvoie des images avec du blanc
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/53.bmp", 90.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/52.bmp", 82.50);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/51.bmp", 68.75);
    } else if(valeur<80) {     // Images avec du noir
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/62.bmp", 71.30);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/57.bmp", 45.50);
    } else {        // Images avec du gris
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/63.bmp", 90.00);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/54.bmp", 87.40);
        IvySendMsg("HamsterJovial type=RESULT file=%s score=%f","./baseDeDocuments/Image/NB/53.bmp", 51.00);
    }

    return 0;
}