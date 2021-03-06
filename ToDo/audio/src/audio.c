/**
 * @file audio.c
 * @author Gael Gamba
 * @brief Fonctions necessaire au bon fonctionnement du traitement des fichiers audios.
 * @version 0.1
 * 
 * @copyright Copyright (c) 2019
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef __AUDIO__
    #include "../include/audio.h"
#endif

#ifndef __CONFIG__
   #include "../include/config.h"
#endif



#define SIMLIARITY_MAX_VALUE 0.003
#define SECONDE_PAR_VALEUR 0.0000616795061349 

	///////////////////////////////////
	//    Librarie du descripteur    //
	///////////////////////////////////

Histogramme initHistogramme(){return NULL;}

Histogramme addFenetre(Histogramme oldHistogram){

	Histogramme newHistogram = (Histogramme)malloc(sizeof(Fenetre));

	newHistogram->nextFenetre = oldHistogram;
	newHistogram->subdivision = init_PILE();
	if(oldHistogram == NULL)
		newHistogram->name = 0;
	else
		newHistogram->name = oldHistogram->name+1;
	return newHistogram;
}

Histogramme appendFenetre(Histogramme oldHistogram, unsigned long nameOfNewFenetre, PILE pileOfNewFenetre){	//Pas optimisé, mais flemme de faire 
	Histogramme index = oldHistogram;																		//mieux j'ai pas trop le temps là
	Histogramme newFenetre = initHistogramme();

	newFenetre = (Histogramme)malloc(sizeof(Fenetre));	//On créé une nouvelle fenêtre
	newFenetre->name = nameOfNewFenetre;				//On remplit avec le nom 
	newFenetre->subdivision = pileOfNewFenetre;			//On y cale la nouvelle pile
	newFenetre->nextFenetre = NULL;						//La prochaine fenetre est null parce qu'on est a la fin

	if(index == NULL)							//Si y'a rien de base, on renvoie juste ce qu'on a créé
		return newFenetre;

	while(index->nextFenetre != NULL){	//On va a l'avant dernier élement pour pouvoir le rajouter
		index = index->nextFenetre;		
	}
	index->nextFenetre = newFenetre;

	return oldHistogram;		
}

Histogramme deleteFenetre(Histogramme oldHistogram, Fenetre* oldFenetre){
	Histogramme newHistogram = oldHistogram;

	newHistogram = newHistogram->nextFenetre;
	*oldFenetre = *oldHistogram;
	free(oldHistogram);
	return newHistogram;
}




	//////////////////////////////
	//   CREER UN DESCRIPTEUR   //
	//////////////////////////////
void resetFileCursor(FILE* p_file, int fileType){
	if(fileType == WAV_FILE)	//Les .wav ont un en tete de 56 octets qui ne nous est pas utile ici
		fseek(p_file, WAV_OFFSET, SEEK_SET);
	else
		fseek(p_file, 0, SEEK_SET);
}


unsigned int getAudioFileSize(FILE* p_file, int fileType){ //Calculer la taille d'un fichier audio, /!\ change le curseur de position, possible
	unsigned int size;
	double placeholder; //On est pas intéressé par les valeurs, faut juste une variable pour pouvoir lire

	resetFileCursor(p_file, fileType);

	do{
		fread(&placeholder, sizeof(double), 1, p_file);
		size++;
	}while(!feof(p_file));

	return size;
}
 
int getSubdivisionValue(double val, int nbSubdivisions){	//Les sous-divisions sont comprises entre -1 et 1
	double temp;
	temp = (nbSubdivisions/2)*val + (nbSubdivisions/2); 
	return (int)temp;
}

DescripteurAudio creerDescripteurAudio(FILE* p_file, int tailleFenetre, int nbSubdivisions, int fileType){	
	DescripteurAudio newDescripteur;
	
	double newFenetre[tailleFenetre];					//Nouvelle fenêtre de travail
	int newHistogramLine[nbSubdivisions+1];			//Nouvelle ligne de l'histogramme final
	int subPosition;
	unsigned long int FenetresCount = 0;
	int nbElementsLus = 0;

	resetFileCursor(p_file, fileType);	
	Histogramme newHistogram = initHistogramme();

	do{																
		nbElementsLus = 0;
		for(int x = 0; x < nbSubdivisions; x++)
			newHistogramLine[x] = 0;	//RAZ de l'histogramme

		nbElementsLus = fread(&newFenetre, sizeof(double),tailleFenetre, p_file);	//Lire une nouvelle fenetre
		FenetresCount++;
		newHistogram = addFenetre(newHistogram);					//La rajouter à l'histogramme

		for(int i = 0; i < nbElementsLus; i++){									//Remplir les sousdivisions de cette fenetre
			subPosition = getSubdivisionValue(newFenetre[i], nbSubdivisions);
			newHistogramLine[subPosition]++;			
		}

		for(int i = 0; i < nbSubdivisions; i++){										//Pile possiblement stockée a l'envers, on vois plus tard si ça pose soucis	
			newHistogram->subdivision = emPILEVal(newHistogram->subdivision, newHistogramLine[i]);		//De toute façon si tout est formé de la même manière ça devrait pas changer les comparaisons
		}


	}while(!feof(p_file));

	newDescripteur.data = newHistogram;
	newDescripteur.nbFenetres = FenetresCount;
	newDescripteur.nbSubdivisions = nbSubdivisions;
	newDescripteur.tailleFenetre = tailleFenetre;

	return newDescripteur;
}

void displayFenetre(Histogramme display){
	if(display == NULL)
		printf("Fenetre est vide\n");
	else{
		printf("nom : %5lu comprenant : ", display->name);
		affiche_PILE(display->subdivision);
		printf("\n");
	}
}

void displayDescripteurAudio(DescripteurAudio display){
	Histogramme index;

	printf("Descripteur de %lu fenetres de %u valeurs, avec %d subdivisions\n", display.nbFenetres, display.tailleFenetre, display.nbSubdivisions);
	index = display.data;
	for(int i = 0; i < display.nbFenetres; i++){
		displayFenetre(index);
		index = index->nextFenetre;
	}
}

char* fenetreToString(Fenetre workingFenetre, int* size){ //Attention, cela détruit la fenetre
	PILE workingPile = workingFenetre.subdivision;

	unsigned int taillePile = taillePILE(workingPile);
	char* newString = (char*)malloc(sizeof(char) * taillePile*7);//On laisse 5 chiffres + 1 séparateur par élement de la pile+1 de marge au cas où
	char temp[6] = "";
	int dataToSave = 0;
	unsigned int currentSize = 0;
	sprintf(newString,"%7lu:", workingFenetre.name);
	currentSize = 8;
	if(newString == NULL)printf("PUTAIN");

	while(!PILE_estVide(workingPile)){
		workingPile = dePILE(workingPile,&dataToSave);
		sprintf(temp, "%4d,", dataToSave);
		for(int i = 0; i < 5; i++){
			newString[currentSize] = temp[i];
			currentSize++;
		}
	}
	newString[currentSize-1] = '|';
	*size = currentSize; 
	return newString;
}

char* descripteurAudioToString(DescripteurAudio descToString){	//nbSubdivisions;tailleFenetre;nbFenetres
	char* newString = (char*)malloc(sizeof(char)*21);
	unsigned long int currentSize = 21;
	unsigned long int index = 21;
	int newFenetreSize = 0;
	Fenetre fenetreATraiter;
	char* newFenetreString;
	sprintf(newString,"%4u;%7u;%7lu;", descToString.nbSubdivisions, descToString.tailleFenetre, descToString.nbFenetres);

	for(long unsigned int i =0; i < descToString.nbFenetres; i ++){
		descToString.data = deleteFenetre(descToString.data, &fenetreATraiter);		//On supprime la fenetre & créé une fenetre
		newFenetreString = fenetreToString(fenetreATraiter, &newFenetreSize);		
		index = currentSize;	//L'ancienne position de currentSize, donc l'endroit où commencer a écrire;
		currentSize+=newFenetreSize;												//On rajoute la place qu'il faut
		newString = (char*)realloc(newString,sizeof(char)*(currentSize+3));
		for(int i = 0; i<newFenetreSize; i++){				
			newString[index+i] = newFenetreString[i];
		}
	}
	newString[currentSize-1] = '!';
	newString[currentSize] ='\0';
	return newString;
}


DescripteurAudio stringToDescripteurAudio(char* stringToParse){
	DescripteurAudio newDescripteur;

	Histogramme newHistogram = initHistogramme();
	PILE newPile;
	unsigned long newFenetreName;
	int valueToStore;
	int index = 21;
	char tempString [30];

	for(int i = 0; i < 21 ; i++){ 	//Get les 21 permiers caractères, c'est les infos du descripteur
		tempString[i] = stringToParse[i];
	}
	tempString[21] = '\0';	//On mets un \0 pour s'assurer que ce soit bien un string, pas sûr que ce soit nécessaire mais ça peut pas faire de mal
	sscanf(tempString,"%4u;%7u;%7lu;", &newDescripteur.nbSubdivisions, &newDescripteur.tailleFenetre, &newDescripteur.nbFenetres);
	
	for(int fenetreCount = 0; fenetreCount < newDescripteur.nbFenetres; fenetreCount++){
		newPile = init_PILE();	//Raz de la pile a stocker

		for(int i = 0; i < 8; i++)		//Chopper fenetre name
			tempString[i] = stringToParse[index+i];
		index+=8;			//On avance l'index de lecture
		tempString[8] = '\0';
		sscanf(tempString,"%7lu:",&newFenetreName);		//Chopper le nom de de la fenetre

		for(int i = 0; i < newDescripteur.nbSubdivisions; i++){		//Get tous les élements de la pile (ils sont dans le sens inverse a cause de la pile)
			for(int j = 0; j < 5; j++)		//Chopper un element de la pile
				tempString[j] = stringToParse[index+j];
			index+=5;
			tempString[5] = '\0';
			sscanf(tempString, "%4d", &valueToStore);		//Lire la valeur et la stocker dans la pile
			newPile = emPILEVal(newPile, valueToStore);
		}
		//printf("\n");

		newPile = inversePILE(newPile);	//On remet la pile dans le bon ordre
		newHistogram = appendFenetre(newHistogram, newFenetreName, newPile);
	}

	newDescripteur.data = newHistogram;
	return newDescripteur;
}

float getSimilarityValue(PILE* pile1, PILE* pile2, int tailleFenetre){
	float sommeDesDifferences = 0;
	unsigned int nbSubdivisions = 0;
	int val1 = 0;
	int val2 = 0;
	PILE cpy1, cpy2;		
	cpy1 = init_PILE();
	cpy2 = init_PILE();
	

	cpy1 = coPILE(pile1);
	cpy2 = coPILE(pile2);
	
	
	while((!PILE_estVide(cpy1)) && (!PILE_estVide(cpy2))){
		
		cpy1 = dePILE(cpy1, &val1);
		cpy2 = dePILE(cpy2, &val2);
		sommeDesDifferences += (float)(abs(val1 - val2));
		nbSubdivisions++;
	}

	return (sommeDesDifferences/(tailleFenetre*nbSubdivisions));		//Plus on est proche de 0 plus c'est la même chose
}

PILE comparerDescripteursAudio(DescripteurAudio jingle, DescripteurAudio fichierAudio){
	if(jingle.nbFenetres >= fichierAudio.nbFenetres){
		return NULL;
	}
	int jingleEstComprisDansLeFichier = 0;
	int nameToSeconds = 0;
	PILE listeDesTimingsDesJingle = init_PILE();

	if(jingle.tailleFenetre != fichierAudio.tailleFenetre || jingle.nbSubdivisions != fichierAudio.nbSubdivisions)
		return NULL;

	Histogramme jingleHist = jingle.data;		//Juste pour éviter de taper jingle.data a chaque fois	
	Histogramme fileHist = fichierAudio.data;	//



	while(jingleHist != NULL && fileHist != NULL){
		
		if(getSimilarityValue(&jingleHist->subdivision, &fileHist->subdivision, jingle.tailleFenetre) <= SIMLIARITY_MAX_VALUE){	//Si on trouve la premiere fenetre dans le truc, on check les autres
			jingleEstComprisDansLeFichier = 1;
			
			while((jingleHist != NULL) && (fileHist!=NULL) && (jingleEstComprisDansLeFichier == 1)){			// On a la premiere fenetre, on vérifie que toutes les fenetres ressemblent une a une (probablement pas une bonne idée)
				
				if(getSimilarityValue(&jingleHist->subdivision, &fileHist->subdivision, jingle.tailleFenetre) > SIMLIARITY_MAX_VALUE){		// Si le truc est trop différent, on dit que c'est pas bon
					jingleEstComprisDansLeFichier = 0;
					break;
				}else{
					jingleHist = jingleHist->nextFenetre;
					fileHist = fileHist->nextFenetre;
				}
			}

			if(jingleEstComprisDansLeFichier == 1 && fileHist != NULL){	//Si ici on s'est arrété parce que le jingle est fini, et pas parce que y'a eut une différence ou la fin du fichier audio
				nameToSeconds = (int)((fileHist->name - jingle.nbFenetres) *jingle.tailleFenetre* SECONDE_PAR_VALEUR);
				listeDesTimingsDesJingle = emPILEVal(listeDesTimingsDesJingle, nameToSeconds);	//C'est que le jingle est bien compris dedans
			}
			
			jingleHist = jingle.data;	//remise a zero de l'index du jingle
		}
		if(fileHist != NULL)
			fileHist = fileHist->nextFenetre;
	}

	return listeDesTimingsDesJingle;
}


/**
 * @brief Indexer tout les fichiers déjà indexés
 *
 * @return int
 */
int indexationTotaleAudio(){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("../../../../data/fichiersIndexesAudio","r+");
    if(fichierIndexes==NULL) return 11;

    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("../../../../data/descripteursAudio","w");
    if(fichierDescripteurs==NULL) return 12;


    char * fichierCourant = (char*)malloc(sizeof(char)*64);
    char * cheminFichierCourant = (char*)malloc(sizeof(char)*128);

    DescripteurAudio descripteurCourant;
    FILE * fichier = NULL;


    FILE * newFichierIndexes = NULL;
    newFichierIndexes = fopen("../../../../data/TMP_fichiersIndexesAudio","a");
    if(newFichierIndexes==NULL) return 13;


    //Generation des descritpeurs et ecriture dans descripteursTexte
    while(fgets(fichierCourant,1024,fichierIndexes)!=NULL){
        fichierCourant[strcspn(fichierCourant,"\r\n")] = 0; //Suppression du \n
        sprintf(cheminFichierCourant,"../../../../base_de_document/Audio/%s",fichierCourant);

        fichier = fopen(cheminFichierCourant,"r");
        if(fichier!=NULL){
            descripteurCourant = creerDescripteurAudio(fichier, getAudioM(), getAudioN(), getExtensionOfFile(cheminFichierCourant));

            fprintf(fichierDescripteurs,"%s\n",descripteurAudioToString(descripteurCourant));
            fprintf(newFichierIndexes,"%s\n",fichierCourant);
            fclose(fichier);
            fichier = NULL;
        }
    }

    fclose(fichierIndexes);
    fclose(fichierDescripteurs);

    rename("../../../../data/TMP_fichiersIndexesAudio","../../../data/fichiersIndexesAudio");
    fclose(newFichierIndexes);

    //free(fichierCourant);
    //free(cheminFichierCourant);


    return 0;
}


int indexationFichierAudio(char * cheminFichier){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("../../../../data/fichiersIndexesAudio","a+");
    if(fichierIndexes==NULL) return 21;


    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("../../../../data/descripteursAudio","a+");
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
        sprintf(cheminFichierCourant,"../../../../base_de_document/Audio/%s",fichierCourant);

        if(strcmp(cheminFichier,cheminFichierCourant)==0){
            fclose(fichierIndexes);
            fclose(fichierDescripteurs);
            return 24;
        }
    }

    DescripteurAudio descripteurCourant;
    descripteurCourant = creerDescripteurAudio(fichier, getAudioM(), getAudioN(), getExtensionOfFile(cheminFichierCourant));

    //printf("%s\n",cheminFichier);
    //printf("%s\n",strrchr(cheminFichier,'/'));
    //printf("%s%s\n","../../../../base_de_document/Texte",strrchr(cheminFichier,'/'));

    char * newCheminFichier = (char*)malloc(sizeof(char)*128);
    sprintf(newCheminFichier,"%s%s","../../../../base_de_document/Audio",strrchr(cheminFichier,'/'));
    rename(cheminFichier,newCheminFichier);

    strcpy(newCheminFichier,strrchr(cheminFichier,'/'));
    strcpy(newCheminFichier,strrchr(newCheminFichier,newCheminFichier[1]));

    fprintf(fichierIndexes,"%s\n",newCheminFichier);
    fprintf(fichierDescripteurs,"%s\n",descripteurAudioToString(descripteurCourant));




    fclose(fichierIndexes);
    fclose(fichierDescripteurs);

    free(fichierCourant);
    free(cheminFichierCourant);
    free(newCheminFichier);

    return 0;

}
