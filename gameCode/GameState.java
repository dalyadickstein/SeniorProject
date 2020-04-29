package gameCode;

import java.util.ArrayList;

public class GameState {
	// turns

	private static int numPlayers = 0;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static GameBoard board;
	private static ArrayList<GamePiece> allPieces = new ArrayList<GamePiece>();

	// Used in GameBoard's constructor
	protected static void setGameBoard(GameBoard board) {
		GameState.board = board;
	}

	protected static void addPlayer(Player player) {
		players.add(player);
		numPlayers++;
	}

	public static int getNumPlayers() {
		return numPlayers;
	}

	public static void addPiece(GamePiece piece) {
		allPieces.add(piece);
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<GamePiece> getAllPieces() {
		return allPieces;
	}

	public static GameBoard getBoard() {
		return board;
	}

}
