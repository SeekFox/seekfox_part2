/**
 * @file config.h
 * @author Clement Truillet (clement.truillet@univ-tlse3.fr)
 * @brief 
 * @version 0.1
 * @date 22/12/2019
 * 
 * @copyright Copyright (c) 2019
 * 
 */

#define __CONFIG__

/**
 * @brief Vide le buffer
 * 
 */
#ifndef CLEAR_STDIN
	#define CLEAR_STDIN { int c; while((c = getchar()) != '\n' && c != EOF); }
#endif

/**
 * @brief Paramètre  Couleur
 * @brief 30 Noir |31 Rouge | 32 Vert | 33 Jaune | 34 Bleu | 35 Magenta | 36 Cyan | 37 Blanc
 * @brief "1" active la haute intensité des caractères.
 *  
*/
#ifndef COLOR
    #define color(param) printf("\033[%sm",param)
#endif

typedef struct config_s * Config;
extern Config config;

//Getter
/**
 * @brief Get the Password Admin object
 * 
 * @return char* 
 */
char * getPasswordAdmin();

/**
 * @brief Get the Taille Min object
 * 
 * @return int 
 */
int getTailleMin();

/**
 * @brief Get the Val object
 * 
 * @return int 
 */
int getVal();

/**
 * @brief Get the Seuil object
 * 
 * @return int 
 */
int getSeuil();

/**
 * @brief Get the Nb Bits object
 * 
 * @return int 
 */
int getNbBits();

/**
 * @brief Get the Audio N object
 * 
 * @return int 
 */
int getAudioN();

/**
 * @brief Get the Audio M object
 * 
 * @return int 
 */
int getAudioM();

/**
 * @brief Get the Logiciel Texte object
 * 
 * @return char* 
 */
char * getLogicielTexte();

/**
 * @brief Get the Logiciel Ouverture Fichier object
 * 
 * @return char* 
 */
char * getLogicielOuvertureFichier();
//Setter
/**
 * @brief Set the Password Admin object
 * 
 * @param c 
 * @param pwd 
 */
void setPasswordAdmin(Config *c, char * pwd);

/**
 * @brief Set the Taille Min object
 * 
 * @param c 
 * @param tailleMin 
 */
void setTailleMin(Config *c, int tailleMin);

/**
 * @brief Set the Val object
 * 
 * @param c 
 * @param val 
 */
void setVal(Config *c, int val);

/**
 * @brief Set the Seuil object
 * 
 * @param c 
 * @param seuil 
 */
void setSeuil(Config *c, int seuil);

/**
 * @brief Set the Nb Bits object
 * 
 * @param c 
 * @param nbBits 
 */
void setNbBits(Config *c, int nbBits);

/**
 * @brief Set the Audio N object
 * 
 * @param c 
 * @param n 
 */
void setAudioN(Config *c, int n);

/**
 * @brief Set the Audio M object
 * 
 * @param c 
 * @param m 
 */
void setAudioM(Config *c, int m);

/**
 * @brief Set the Logiciel Texte object
 * 
 * @param c 
 * @param str 
 */
void setLogicielTexte(Config *c, char * str);

/**
 * @brief Set the Logiciel Ouverture Fichier object
 * 
 * @param c 
 * @param str 
 */
void setLogicielOuvertureFichier(Config *c, char * str);

/**
 * @brief Recupere les infos du fichier config 
 * 
 * @return Config 
 */
Config loadConfig();

/**
 * @brief Affiche les parametres de configuration
 * 
 */
void displayConfig();

/**
 * @brief Met � jour le fichier user.config
 * 
 */
void majConfigFile();

/**
 * @brief Get the Extension Of File object
 * 
 * @param file 
 * @return const char* 
 */
const char * getExtensionOfFile(const char * file);