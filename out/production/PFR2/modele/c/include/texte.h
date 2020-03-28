/**
 * @file texte.h
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief 
 * @version 0.1
 * @date 28/02/2020
 * 
 * @copyright Copyright (c) 2020
 * 
 */

#define TAILLEMAXMOT 128
#define __TEXTE__

/**
 * @brief Structure de la liste des mots
 * 
 */
typedef struct listeMots * ListeMots;

/**
 * @brief Structure du descripteur
 * 
 */
typedef struct descripteurTexte * DescripteurTexte;

/**
 * @brief Initialise un descripteur
 * 
 * @return DescripteurTexte 
 */
DescripteurTexte initDescripteurTexte();

/**
 * @brief Crée un descripteur
 * 
 * @param file 
 * @return DescripteurTexte 
 */
DescripteurTexte lireFichierTexte(char * file);

/**
 * @brief Get the Name Descripteur Texte object
 * 
 * @param descripteur 
 * @return char* 
 */
char * getNameDescripteurTexte(DescripteurTexte descripteur);


/**
 * @brief Initialise une liste de mots
 * 
 * @return ListeMots 
 */
ListeMots initListeMots();

/**
 * @brief Supprimes les mots inutiles de la liste
 * 
 * @param liste 
 * @return ListeMots 
 */
ListeMots epurerListe(ListeMots liste);

/**
 * @brief Ajoute un nouveau mot
 * 
 * @param liste 
 * @param mot 
 * @return ListeMots 
 */
ListeMots ajoutMot(ListeMots liste, char * mot);

/**
 * @brief ajoute un mot et son nombre d'occurence
 * 
 * @param liste 
 * @param mot 
 * @param nbOccur 
 * @return ListeMots 
 */
ListeMots ajoutMotEtNbOccur(ListeMots liste, char * mot, int nbOccur);

/**
 * @brief Affiche la liste des mots
 * 
 * @param liste 
 */
void afficheListeMot(ListeMots liste);

/**
 * @brief 
 * 
 * @param descripteur 
 */
void afficheDescripteurTexte(DescripteurTexte descripteur);

/**
 * @brief Get the Nb Mots Descripteur Texte object
 * 
 * @param descripteur 
 * @return int 
 */
int getNbMotsDescripteurTexte(DescripteurTexte descripteur);

/**
 * @brief Transforme le descripteur en chaine de caractere
 * 
 * @param descripteur 
 * @return char* 
 */
char * descripteurTexteToString(DescripteurTexte descripteur);

/**
 * @brief Transforme la chaine de caractere en descripteur Texte
 * 
 * @param descripteur 
 * @return DescripteurTexte 
 */
DescripteurTexte StringTodescripteurText(char * descripteur);

/**
 * @brief compare deux descripteurs texte
 * 
 * @param desc1 
 * @param desc2 
 * @return float 
 */
float comparerDescripteurTexte(DescripteurTexte desc1, DescripteurTexte desc2);

/**
 * @brief Recherche d'un mot dans le descripteur texte
 * 
 * @param mot 
 * @param descripteur 
 * @return int 
 */
int chercherMotCleDansTexte(char * mot, DescripteurTexte descripteur);

/**
 * @brief reindexation de tout les fichiers indexés
 *
 */
int indexationTotale();


/**
 * @brief Indexer un fichier
 *
 * @param cheminFichier
 */
int indexationFichier(char * cheminFichier);