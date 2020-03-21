MAKE = make
GCC = gcc
LIBFLAGS =
CFLAGS = -Wall
SRC = $(wildcard src/*.c)
EXC = ./impeesa.out
LIB = $(wildcard lib/*.a)
INCLUDE = include/


.SILENT:clean

all :
	@$(GCC) $(SRC) $(LIB) -o $(EXC)

clean :
	@rm $(EXC)
