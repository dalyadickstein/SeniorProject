package gameCode;

import java.awt.Point;
import java.util.ArrayList;

public class GameBoard {

	private int length;
	private int width;
	// board is a matrix, with each cell containing an ArrayList of GamePieces.
	private ArrayList<ArrayList<ArrayList<GamePiece>>> board;

	public GameBoard(int length, int width) {
		this.length = length;
		this.width = width;
		this.board = new ArrayList<ArrayList<ArrayList<GamePiece>>>();
		for (int i = 0; i < length; i++) {
			board.add(new ArrayList<ArrayList<GamePiece>>());
			ArrayList<ArrayList<GamePiece>> row = board.get(i);
			for (int j = 0; j < width; j++) {
				row.add(new ArrayList<GamePiece>());
			}
		}
		GameState.setGameBoard(this);
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	// Returns the list of pieces found in a given square
	public ArrayList<GamePiece> getContents(int x, int y) {
		return board.get(x).get(y);
	}

	public boolean isEmpty(int x, int y) {
		return board.get(x).get(y).isEmpty();
	}

	// Returns the first piece owned by pNum in a given square
	public GamePiece getPieceByPlayerNumAndCoords(int pNum, int x, int y) {
		ArrayList<GamePiece> pieces = getContents(x, y);
		int numPieces = pieces.size();
		for (int i = 0; i < numPieces; i++) {
			if (pieces.get(i).getOwner().getPlayerNum() == pNum) {
				return pieces.get(i);
			}
		}
		return null;
	}

	// Moves a piece to square x,y. Returns true if allowed and false if not.
	public boolean movePiece(GamePiece piece, int x, int y) {
		if (!addPiece(piece, x, y)) {
			return false;
		}
		removePiece(piece);
		return true;
	}

	// Adds a piece to square x,y. Returns true if allowed and false if not.
	public boolean addPiece(GamePiece piece, int x, int y) {
		if (x < width && y < length) {
			board.get(x).get(y).add(piece);
			return true;
		}
		return false;
	}

	public boolean removePiece(GamePiece piece) {
		Point currCoords = piece.getCoords();
		if (currCoords == null) {
			return false;
		}
		board.get(currCoords.x).get(currCoords.y).remove(piece);
		piece.setCoords(null);
		return true;
	}

}
