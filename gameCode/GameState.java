package gameCode;

import java.util.ArrayList;

public class GameState {
	// turns

	private static int numPlayers = 0;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static GameBoard board;
	private static ArrayList<GamePiece> allPieces = new ArrayList<GamePiece>();
	private static Bank bank;
	private static ArrayList<Die> dice = new ArrayList<Die>();

	// Used in GameBoard's constructor
	protected static void setGameBoard(GameBoard board) {
		GameState.board = board;
	}

	// Used in Bank's constructor
	protected static void setBank(Bank bank) {
		GameState.bank = bank;
	}

	// Used in Player's constructor
	protected static void addPlayer(Player player) {
		players.add(player);
		numPlayers++;
	}

	// Used in Die's constructor
	protected static void addDie(Die die) {
		dice.add(die);
	}

	public static int getNumPlayers() {
		return numPlayers;
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<Die> getDice() {
		return dice;
	}

	public static void addPiece(GamePiece piece) {
		allPieces.add(piece);
	}

	public static ArrayList<GamePiece> getAllPieces() {
		return allPieces;
	}

	public static GameBoard getBoard() {
		return board;
	}

	public static Bank getBank() {
		return bank;
	}

}
