MAKE = make
GCC = gcc
LIBFLAGS =
CFLAGS = -Wall
SRC = $(wildcard src/*.c)
EXC = ./akela.out
LIB = $(wildcard lib/*.a)
INCLUDE = include/


.SILENT:clean

all :
	@$(MAKE) compile -s
	@$(MAKE) run -s

compile :
	@$(GCC) $(SRC) $(LIB) -o $(EXC)

run :
	@$(EXC)

clean :
	@rm $(EXC)
