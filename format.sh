for i in `find . -name '*.java'` ; do echo $i && javafmt -i $i; done
