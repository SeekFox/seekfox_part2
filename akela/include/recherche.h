/**
 * @file recherche.h
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief
 * @version 0.1
 * @date 21/03/2020
 *
 * @copyright Copyright (c) 2020
 *
 */
 #define __RECHERCHE__

 int rechercheMotClef(char * motClef);

 int rechercheTexte(char * cheminFichier);

 int rechercheAudio(char * cheminFichier);

 int rechercheImage(char * cheminFichier);

 /* Fonctions de couleur dominante */
 
 int RVBouNB (char *);
 int stringToNB (char *);
 int* stringToRGB (char *);

 int rechercheCouleurDominanteRGB (int, int, int);

 int rechercheCouleurDominanteNB (int);