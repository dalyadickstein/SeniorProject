package gameCode;

public class TurnController {

	private static int numPlayers;
	private static int playerTurn;

	public static int getPlayerTurn() {
		return playerTurn;
	}

	public static void endTurn() {
		playerTurn = (playerTurn + 1) % numPlayers;
	}
}
