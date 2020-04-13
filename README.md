# Projet Fil Rouge 2 - Seekfox

## Arborescence du projet
+ **Impeesa** *[C]*
    + Moteur de recherche codé en C.
+ **HamsterJovial** *[Java]*
    + Interface Utilisateur codée en Java. 
+ **Ivy**


## Run
Pour lancer le projet, il faut lancer la commande à la racine du projet

```
./run.sh
```

Ainsi que executer le fichier *seekfox.jar*

## Compile
Pour compiler le projet, il est necessaire d'installer la librairie d'Ivy.   
Pour cela, vous trouverez un tutoriel pour installer la librairie dans le repertoire */ivy*.   
L'execution du projet est impossible sans compilation de la librairie ivy pour le C.


### Version Alpha - 22.03.2020
La version du *22/03/2020* est orientée sur la liaison Java/C.   
Cette liaison se fait avec un bus logiciel **Ivy** sur le port 2010.

Ainsi, pour montrer le bon fonctionnement de cette liaison, l'interface est textuelle, minimaliste et ne permet qu'une recherche par mot-Clef.