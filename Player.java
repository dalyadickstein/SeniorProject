import java.util.ArrayList;

public class Player {
	
	private int playerNum;
	private ArrayList<GamePiece> pieces;

	public Player(int playerNum) {
		this(playerNum, null);
	}

	public Player(int playerNum, ArrayList<GamePiece> pieces) {
		this.playerNum = playerNum;
		this.pieces = pieces;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public ArrayList<GamePiece> getPieces() {
		return pieces;
	}
	
}
