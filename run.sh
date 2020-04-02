#! /bin/sh

cd ./impeesa && make compile && cd -
cd ./impeesa && ./impeesa.out &
java -jar ./out/artifacts/PFR2_jar/PFR2.jar "^(.*)"

exit 0 