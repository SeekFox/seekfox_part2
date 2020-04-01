/**
 * @file texte.c
 * @author Raphaël Bizet
 * @brief Fonctions gerant la création d'un descripteur texte
 * @version 0.1
 * @date 27/01/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef __CONFIG__
    #include "../include/config.h"
#endif

#ifndef __TEXTE__
    #include "../include/texte.h"
#endif


typedef struct listeMots{
    char * mot;
    int nbOccurence;
    struct listeMots * next;
}listeMots_s;

typedef struct descripteurTexte{
    char * name;
    int nbMots;
    ListeMots liste;
}descripteurTexte_s;

DescripteurTexte initDescripteurTexte(){
    DescripteurTexte dt = (DescripteurTexte)malloc(sizeof(struct descripteurTexte));
    dt->name = (char*)malloc(sizeof(char));
    strcpy(dt->name,"");
    dt->liste=initListeMots();
    dt->nbMots=0;

    return dt;
}

ListeMots initListeMots(){
    return NULL;
}

ListeMots ajoutMot(ListeMots liste, char * mot){
    int motAjoute = 0;
    ListeMots lm = liste;
    //Parcours de la liste à la recherche du mot
    while(lm!=NULL){
        if(strcmp(lm->mot,mot)==0){
            lm->nbOccurence++;
            motAjoute=1;
            break;
        }else{
            lm=lm->next;
        }
    }

    lm = liste;

    if(!motAjoute){
        //printf("Nouvel element \"%s\"\n",mot);
        lm=(ListeMots)malloc(sizeof(struct listeMots));
        lm->mot=(char*)malloc(sizeof(char)*strlen(mot)+8);
        lm->next=(struct listeMots * )malloc(sizeof(struct listeMots));
        //lm->mot=mot;
        strncpy(lm->mot,mot,strlen(mot));
        //snprintf(lm->mot,strlen(mot),"%s",mot);
        lm->nbOccurence=1;
        lm->next=liste;
    }

    return lm;
}

void afficheDescripteurTexte(DescripteurTexte descripteur){
    printf("==AFFICHAGE Descripteur==\n");
    printf("Fichier         : %s\n",descripteur->name);
    printf("Nombre de mots  : %d mots\n",descripteur->nbMots);
    afficheListeMot(descripteur->liste);
}

void afficheListeMot(ListeMots liste){
    ListeMots lm = liste;
    printf("LISTEMOTS\n");
    while(lm!=NULL){
        printf("\t%-30s %d\n",lm->mot,lm->nbOccurence);
        
        lm=lm->next;
    }

}

int getNbMotsDescripteurTexte(DescripteurTexte descripteur){
    if(descripteur==NULL) return 0;
    if(descripteur->liste==NULL) return 0;

    int nbMots = 0;
    ListeMots lm = descripteur->liste;

    while(lm!=NULL){
        nbMots+=lm->nbOccurence;
        lm=lm->next;
    }
    //free(lm);
    return nbMots;
}

char * getNameDescripteurTexte(DescripteurTexte descripteur){
    return descripteur->name;
}

DescripteurTexte lireFichierTexte(char * file){
    DescripteurTexte descripteur = initDescripteurTexte();
    descripteur->name = (char*)malloc(sizeof(char)*2*strlen(file));
    strcpy(descripteur->name,file);

    
    
    ListeMots liste = initListeMots();
    FILE * fichier = NULL;
    char * str = (char*)malloc(sizeof(char)*TAILLEMAXMOT);
    strcpy(str,"");
    char c = ' ';


    fichier = fopen(file, "r");

    if(fichier!=NULL){
        do{
            c = fgetc(fichier);
            //Fin de fichier
            if(c==EOF) break; 

            //Caractere non voulus
            if(c=='!'||c=='\''||c=='?'||c==';'||c=='\n'||c=='\r') continue; 

            //Ajout d'un mot
            if(c==' '||c=='.'||c==':'||c==','||c=='('||c==')'||c=='['||c==']'||c=='-'){
                if(strlen(str)>=getTailleMin()){
                    //printf("%s\n",str);
                    liste=ajoutMot(liste,str);
                } 
                
                //printf("==> -%s-\n",str);
                strcpy(str,"");
            }else if(c=='<'){
                while(c!='>') c=fgetc(fichier);
            }else{   //Supression des balises
                //printf("--> -%s--%c-\n",str,c);
                sprintf(str,"%s%c",str,c);  
            } 
        }while(c!=EOF);

        fclose(fichier);
    
    }else{
        printf("Le fichier \"%s\" n'existe pas\n",file);
        return NULL;
    }

    descripteur->liste = epurerListe(liste);
    descripteur->nbMots=getNbMotsDescripteurTexte(descripteur);
    return descripteur;
    
}

char * descripteurTexteToString(DescripteurTexte descripteur){
    char * str = (char*)malloc(sizeof(char)*strlen(descripteur->name) + sizeof(int)*4 + 4);
    strcpy(str,"");
    sprintf(str,"[%s;%d]",descripteur->name,descripteur->nbMots);

    ListeMots lm = descripteur->liste;
    while(lm!=NULL){
        if((lm->nbOccurence)>=getSeuil()){
            str = (char*) realloc(str,sizeof(char)*strlen(str)+  (sizeof(char)*strlen(lm->mot) + sizeof(int) + 4));
            sprintf(str,"%s{%s;%d}",str,lm->mot,lm->nbOccurence);
        }
        
        lm=lm->next;
    }

    str = (char*) realloc(str,sizeof(char)*strlen(str)*strlen("!")+8);
    sprintf(str,"%s%s",str,"!");
    return str;
}

ListeMots ajoutMotEtNbOccur(ListeMots liste, char * mot, int nbOccur){
    int motAjoute = 0;
    ListeMots lm = liste;
    //Parcours de la liste à la recherche du mot
    while(lm!=NULL){
        lm=lm->next;
    }

    lm = liste;

    if(!motAjoute){
        //printf("Nouvel element \"%s\"\n",mot);
        lm=(ListeMots)malloc(sizeof(struct listeMots));
        lm->mot=(char*)malloc(sizeof(char)*strlen(mot));
        strcpy(lm->mot,mot);
        lm->nbOccurence=nbOccur;
        lm->next=liste;
    }

    return lm;
}

DescripteurTexte StringTodescripteurText(char * str){
    DescripteurTexte descripteur = initDescripteurTexte();
    ListeMots liste = initListeMots();

    char * chaine = (char*)malloc(sizeof(char)*TAILLEMAXMOT);
    strcpy(chaine,"");
    char c;

    char * mot; 
    int nbOccur;

    int i=0;

    while(str[i]!='!'&&str[i]!=EOF){
        c=str[i];
        
        if(c==EOF) break;

        //en tête du descripteur
        if(c=='['){
           do{
                i++;
                c=str[i];
                sprintf(chaine,"%s%c",chaine,c);
                if(c==';'){
                    (descripteur->name) = realloc(descripteur->name,(sizeof(char)*strlen(chaine)));
                    strncpy(descripteur->name,chaine,strlen(chaine)-1);
                    
                    strcpy(chaine,"");
                }
                if(c==']'){
                    descripteur->nbMots=atoi(chaine);
                    strcpy(chaine,"");
                }
                
                
            }while(c!=']');
        }

        //Contenu du descripteur
        if(c=='{'){
            //Récuperation d'un mot
            do{
                i++;
                c=str[i];
                sprintf(chaine,"%s%c",chaine,c);
                //Mot
                if(str[i+1]==';'){
                    mot=(char*)malloc(sizeof(char)*strlen(chaine));
                    strcpy(mot,chaine);
                    strcpy(chaine,"");
                    i++;
                }
                //Nombre d'occurence
                if(c=='}'){
                    nbOccur=atoi(chaine);
                    strcpy(chaine,"");
                    //Ajout du mot à la liste
                    liste=ajoutMotEtNbOccur(liste,mot,nbOccur);
                }    
            }while(c!='}');
        }
        i++;
    }

    descripteur->liste=liste;

    return descripteur;
}

float comparerDescripteurTexte(DescripteurTexte desc1, DescripteurTexte desc2){
    int nbMotsEnCommun=0;
    ListeMots l1 = desc1->liste;
    ListeMots l2 = desc2->liste;

    if(desc1->nbMots==0||desc2->nbMots==0) return 0; 

    while(l1!=NULL){
        while(l2!=NULL){
            //printf("\t%s / %s\n",l1->mot, l2->mot);
            if(strcmp(l1->mot, l2->mot)==0){
                nbMotsEnCommun+=l1->nbOccurence+l2->nbOccurence;
            }
            l2=l2->next;
        }
        l2 = desc2->liste;
        l1=l1->next;
    }

    //printf("\n\t>>>Comparaison %d %d %f\n",nbMotsEnCommun,(desc1->nbMots + desc2->nbMots),((float)nbMotsEnCommun)/((float)(desc1->nbMots + desc2->nbMots)) );
    return ((float)nbMotsEnCommun)/((float)(desc1->nbMots + desc2->nbMots))*100;
}

ListeMots epurerListe(ListeMots l){
    ListeMots lm = initListeMots();

    while(l!=NULL){
        if((l->nbOccurence)>=getSeuil()){
            lm = ajoutMotEtNbOccur(lm,l->mot,l->nbOccurence);
        }
        
        l=l->next;
    }

    return lm;
}

int chercherMotCleDansTexte(char * mot, DescripteurTexte descripteur){
    int nbOccur=0;
    ListeMots liste = descripteur->liste;

    if(descripteur->nbMots==0) return 0; 

    while(liste!=NULL){
        if(strcmp(mot, liste->mot)==0){
            return liste->nbOccurence;
        }
        liste=liste->next;
    }

    return nbOccur;
}

/**
 * @brief Indexer tout les fichiers déjà indexés
 *
 * @return int
 */
int indexationTotaleTexte(){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("./data/fichiersIndexesTexte","r+");
    if(fichierIndexes==NULL) return 11;

    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("./data/descripteursTexte","w");
    if(fichierDescripteurs==NULL) return 12;


    char * fichierCourant = (char*)malloc(sizeof(char)*64);
    char * cheminFichierCourant = (char*)malloc(sizeof(char)*128);

    DescripteurTexte descripteurCourant = initDescripteurTexte();
    FILE * fichier = NULL;


    FILE * newFichierIndexes = NULL;
    newFichierIndexes = fopen("./data/TMP_fichiersIndexesTexte","w+");
    if(newFichierIndexes==NULL) return 13;


    //Generation des descritpeurs et ecriture dans descripteursTexte
    while(fgets(fichierCourant,1024,fichierIndexes)!=NULL){
        fichierCourant[strcspn(fichierCourant,"\r\n")] = 0; //Suppression du \n
        sprintf(cheminFichierCourant,"./baseDeDocuments/Texte/%s",fichierCourant);

        //printf("-%s-\n", cheminFichierCourant);

        fichier = fopen(cheminFichierCourant,"r");

        if(fichier!=NULL){
            descripteurCourant = lireFichierTexte(cheminFichierCourant);

            fprintf(fichierDescripteurs,"%s\n",descripteurTexteToString(descripteurCourant));
            fprintf(newFichierIndexes,"%s\n",fichierCourant);
            fclose(fichier);
            fichier = NULL;
        }else{
            printf("zsedfg\n");
        }
    }

    fclose(fichierIndexes);
    fclose(fichierDescripteurs);

    rename("./data/TMP_fichiersIndexesTexte","./data/fichiersIndexesTexte");
    fclose(newFichierIndexes);

    //free(fichierCourant);
    //free(cheminFichierCourant);


    return 0;
}


int indexationFichierTexte(char * cheminFichier){
    // Ouverture du fichier fichiersIndexesTexte
    FILE * fichierIndexes = NULL;
    fichierIndexes = fopen("./data/fichiersIndexesTexte","a+");
    if(fichierIndexes==NULL) return 21;


    // Ouverture du fichier des descripteurs textes
    FILE * fichierDescripteurs = NULL;
    fichierDescripteurs = fopen("./data/descripteursTexte","a+");
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
        sprintf(cheminFichierCourant,"./baseDeDocuments/Texte/%s",fichierCourant);
        //printf("\t%s-%s-%d\n",cheminFichier,cheminFichierCourant,strcmp(cheminFichier,cheminFichierCourant));
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
    //printf("%s%s\n","../../../../base_de_document/Texte",strrchr(cheminFichier,'/'));

    char * newCheminFichier = (char*)malloc(sizeof(char)*128);
    sprintf(newCheminFichier,"%s%s","./baseDeDocuments/Texte",strrchr(cheminFichier,'/'));
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