# Bus logiciel ivy

Pour en apprendre plus sur le bus logiciel ivy, regarder la présentation -> [*ici*](https://github.com/truillet/upssitech/blob/master/SRI/3A/ID/Cours/C_ivy_2.3.pdf) <- et plus généralement -> [*cette page*](https://github.com/truillet/upssitech/wiki/Interaction-Distribu%C3%A9e) <- 

## ivy/c sous linux (ou bash ubuntu sous windows)
Ouvrir un nouveau terminal

Télécharger [prcre-7.7](https://github.com/truillet/ivy/blob/master/lib/pcre-7.7.zip) (*P*erl *C*ompatible *R*egex *E*xpression) et dézipper les fichiers dans le répertoire pcre-7.7

Télécharger [ivy C](https://github.com/truillet/ivy/blob/master/lib/ivy.zip) et dézipper les fichiers dans le répertoire ivy

Utiliser les commandes suivantes : compiler la librairie PCRE puis ivy
```cd prce-7.7\n
./configure
make
sudo make install
export LD_LIBRARY_PATH=/lib:/usr/lib:/usr/local/lib
cd ../ivy
make
```
Vous pouvez maintenant essayer l'outil *ivyprobe*

```
./ivyprobe "^(.*)"
```

