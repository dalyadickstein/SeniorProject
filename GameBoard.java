import java.awt.Point;
import java.util.ArrayList;

public class GameBoard {

	private int length;
	private int width;
	private ArrayList<GamePiece>[][] board;

	public GameBoard(int length, int width) {
		this.length = length;
		this.width = width;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new ArrayList<GamePiece>();
			}
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	// Returns the list of pieces found in a given square
	public ArrayList<GamePiece> getContents(int x, int y) {
		return board[x][y];
	}

	// Returns the first piece owned by owner in a given square
	public GamePiece getPieceByOwnerAndCoords(int owner, int x, int y) {
		ArrayList<GamePiece> pieces = getContents(x, y);
		int numPieces = pieces.size();
		for (int i = 0; i < numPieces; i++) {
			if (pieces.get(i).getOwner() == owner) {
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
			board[x][y].add(piece);
			return true;
		}
		return false;
	}

	public boolean removePiece(GamePiece piece) {
		Point currCoords = piece.getCoords();
		if (currCoords == null) {
			return false;
		}
		board[currCoords.x][currCoords.y].remove(piece);
		piece.setCoords(null);
		return true;
	}

}
