#! /bin/sh

cd ./src/modele/c && make compile && cd -
cd ./src/modele/c && ./impeesa.out &
java -jar ./out/artifacts/PFR2_jar/PFR2.jar "^(.*)"

exit 0 