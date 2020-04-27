import java.util.ArrayList;

public class GameState {
	// turns

	private static int numPlayers;
	private static GameBoard board;
	private static ArrayList<GamePiece> allPieces;

	public static int getNumPlayers() {
		return numPlayers;
	}

	public static void addPiece(GamePiece piece) {
		allPieces.add(piece);
	}

	public static GameBoard createBoard(int length, int width) {
		if (board == null) {
			GameBoard newBoard = new GameBoard(length, width);
			board = newBoard;
		}
		return board;
	}

	public static ArrayList<GamePiece> getAllPieces() {
		return allPieces;
	}

	public static GameBoard getBoard() {
		return board;
	}

}
