#include <stdlib.h>
#include <stdio.h>

#include "pcre.h"

#include "ivy.h"
#include "ivyloop.h"

/* callback associated to "Hello" messages */
void EcouteCallback (IvyClientPtr app, void *data, int argc, char **argv){
	printf("Message type=%s Option=%s\n", argv[0], argv[1]);
}

/* callback associated to "Bye" messages */
void ByeCallback (IvyClientPtr app, void *data, int argc, char **argv){
	// On termine le traitement 
	IvyStop ();
}

int main (int argc, char**argv)
{
	/* initialisation */
	IvyInit ("Impeesa", "Le loup ne dort jamais\n", 0, 0, 0, 0);

	/* On �oute et on traite les messages qui commencent par n'importe quoi */
	IvyBindMsg (EcouteCallback, 0, "^Impeesa type=(.*) argument=(.*)");

	/* On �oute et on traite les messages 'Bye' */
	IvyBindMsg (ByeCallback, 0, "^Impeesa Bye$");

    IvyStart ("127.255.255.255:2010"); // On lance l'agent sur le bus ivy

	/* main loop */
	IvyMainLoop();
}
