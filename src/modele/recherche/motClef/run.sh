#!/bin/sh

#Verification du nombre de parametres
if [ $# -ne 1 -a $# -ne 2 ]
then
	echo "Usage: $0 need one argument." >&2
	exit 1
fi

make

./rechercheMotClef.out $1

exit 0

