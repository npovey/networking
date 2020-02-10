# https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html
# define compiler and compiler flag variables
# java -cp .:gson-2.8.6.jar Driver Seattle
# javac -cp .:gson-2.8.6.jar Coord.java

JFLAGS = -g
JC = javac -d bin -cp .:bin:gson-2.8.6.jar
JVM= java
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

#
# CLASSES is a macro consisting of 4 words (one for each java source file)
#

CLASSES = \
        src/Wind.java \
        src/Weather.java \
        src/Sys.java \
		src/Main.java \
		src/Clouds.java \
		src/Coord.java \
		src/Rain.java \
		src/Result.java \
		src/Source.java \
		src/Article.java \
		src/NewsResult.java \
        src/Driver.java
#
# the default make target entry
#


default: classes

classes: $(CLASSES:.java=.class)
#
# RM is a predefined macro in make (RM = rm -f)
#
clean:
	$(RM) bin/*.class
