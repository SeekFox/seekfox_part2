#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "../include/pcre.h"
#include "../include/ivy.h"
#include "../include/ivyloop.h"


#ifndef __RECHERCHE__
    #include "../include/recherche.h"
#endif


/* callback associated to "Hello" messages */
void EcouteCallback (IvyClientPtr app, void *data, int argc, char **argv){
	printf("Message type=%s%s Option=%s\n", argv[0], argv[1], argv[2]);

    if(strcmp(argv[0],"Recherche")==0){

        printf("Recherche\n");
        if(strcmp("Texte",argv[1])==0){

        }else if(strcmp("Image",argv[1])==0){

        }else if(strcmp("Audio",argv[1])==0){

        }else if(strcmp("MotClef",argv[1])==0){
            system("pwd");
            rechercheMotClef(argv[2]);

        }else{

        }


    }else if(strcmp(argv[0],"Recherche")==0){

        printf("Indexation\n");


    }else{

        printf("Inconnu\n");


    }



}

/* callback associated to "Bye" messages */
void ByeCallback (IvyClientPtr app, void *data, int argc, char **argv){
	// On termine le traitement 
	IvyStop ();
}

int main (int argc, char**argv){
	/* initialisation */
	IvyInit ("Impeesa", "Le loup ne dort jamais\n", 0, 0, 0, 0);

	/* On ecoute et on traite les messages qui commencent par n'importe quoi */
	IvyBindMsg (EcouteCallback, 0, "^Impeesa type=(.*)_(.*) argument=(.*)");

	/* On ecoute et on traite les messages 'Bye' */
	IvyBindMsg (ByeCallback, 0, "^Impeesa Bye$");

    IvyStart ("127.255.255.255:2010"); // On lance l'agent sur le bus ivy

	/* main loop */
	IvyMainLoop();
}
