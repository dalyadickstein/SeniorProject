JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	BoardGame.java \
	Client.java \
	GameBoard.java \
	GamePiece.java \
	GameState.java \
	PlayGame.java \
	PlayGameListener.java \
	Player.java \
	Server.java \
	ServerHandler.java \
	TurnController.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
