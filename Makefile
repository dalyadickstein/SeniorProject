JFLAGS = -g -Xlint:unchecked
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	gameCode/Bank.java \
	gameCode/BoardGame.java \
	gameCode/Client.java \
	gameCode/Die.java \
	gameCode/GameBoard.java \
	gameCode/GamePiece.java \
	gameCode/GameState.java \
	gameCode/PlayGame.java \
	gameCode/PlayGameListener.java \
	gameCode/Player.java \
	gameCode/Server.java \
	gameCode/ServerHandler.java \
	gameCode/TurnController.java \
	sampleGames/ticTacToe/TttServerHandler.java \
	sampleGames/ticTacToe/TicTacToe.java \
	sampleGames/mathTest/MathServerHandler.java \
	sampleGames/mathTest/MathTest.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
