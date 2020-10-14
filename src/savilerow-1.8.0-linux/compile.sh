#!/bin/bash

# A simple script to build a jar file.
# 
# to run the generated jar file,
# java -jar -ea -Xmx1G savilerow.jar *.eprime [*.param]


################################################################################
# Generating a java file with hg version in it
# The file will be called "RepositoryVersion" and will have a single public static field
# called "repositoryVersion".
# Copied from the Conjure repository, seems to work there.
################################################################################

if ! [ -d .git ] && [ -f src/RepositoryVersion.java ] ; then
    echo "This is not a git repository, but it contains repository information. Reusing."
    cat src/RepositoryVersion.java | grep "repositoryVersion ="
else
    VERSION="unknown"
    if [ -d .git ] ; then
        VERSION=$(git log -1 --pretty=format:"%h (%ai)")
    fi
    if ( grep "repositoryVersion = \"${VERSION}\"" src/RepositoryVersion.java 2> /dev/null > /dev/null ) ; then
        echo "Reusing src/RepositoryVersion.java with version ${VERSION}."
    else
        echo "Generating src/RepositoryVersion.java with version ${VERSION}."
        echo "package savilerow;"                                           >  src/RepositoryVersion.java
        echo "public final class RepositoryVersion {"                       >> src/RepositoryVersion.java
        echo "    public static String repositoryVersion = \"${VERSION}\";" >> src/RepositoryVersion.java
        echo "}"                                                            >> src/RepositoryVersion.java
    fi
fi

################################################################################


# create the classes directory
rm -rf classes && mkdir classes

# compile every *.java file into the classes dir

export CLASSPATH=$CLASSPATH:lib/trove.jar

# Generate help
./srhelp <sr-help.txt >src/HelpText.java

javac -O -Xlint --release 8 -d classes/ `find src -name "*.java"`

if [ $? -ne 0 ]; then exit; fi

mkdir classes/lib
cp lib/trove.jar classes/lib/

# create the jar file
cd classes/

jar -cfm ../savilerow.jar ../manifest savilerow/

cd ..

#  To compile with GCJ:
#  gcj -c lib/trove.jar -O3 -o lib/trove.o
#  gcj savilerow.jar --classpath=lib/trove.jar lib/trove.o -O3 --main=savilerow.EPrimeTailor

