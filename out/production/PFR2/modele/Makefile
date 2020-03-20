MAKE = make
GCC = gcc

.SILENT:all
.SILENT:recherche
.SILENT:indexation

all :
	@gcc -c config.c
	@cd recherche/texte && $(MAKE) && cd -
	@cd recherche/motClef && $(MAKE) && cd -
	@cd indexation/texte && $(MAKE) && cd -

recherche :
	@cd recherche/texte && $(MAKE) && cd -
	@cd recherche/motClef && $(MAKE) && cd -

indexation :
	@cd indexation/texte && $(MAKE) && cd -

clean :
	@rm config.o
	@rm recherche/texte/rechercheTexte.out
	@rm recherche/motClef/rechercheMotClef.out
	@rm indexation/texte/indexationTexte.out