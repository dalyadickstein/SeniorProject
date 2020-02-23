import java.util.ArrayList;

public class GameState {
	// turns

	private static int numPlayers;
	private static GameBoard board;
	private static ArrayList<GamePiece> allPieces;

	public static int getNumPlayers() {
		return numPlayers;
	}

	public static ArrayList<GamePiece> getAllPieces() {
		return allPieces;
	}

	public static GameBoard createBoard(int length, int width) {
		GameBoard board = new GameBoard(length, width);
		return board;
	}

}
