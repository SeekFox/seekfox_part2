MAKE = make
GCC = gcc
LIBFLAGS =
CFLAGS = -Wall 
SRC = $(wildcard *.c)
EXC = ./indexationTexte.out
LIB = 
INCLUDE = .


.SILENT:clean

all :
	@$(GCC) $(CFLAGS) -I $(INCLUDE) -c $(SRC) $(LIBFLAGS)
	@$(GCC) $(CFLAGS) -o $(EXC) $(LIB)*.o ../../config.o $(LIBFLAGS)
	@rm *.o

clean :
	@rm $(EXC)
