import gameCode.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class TttServerHandler implements ServerHandler {

	private int winner = -1; // -1 for ongoing, -2 for tie
	
	public ArrayList<Player> createPlayers() {
		return GameState.getPlayers();
	}

	public boolean gameEnded() {
		checkWinner();
		return (winner != -1);
	}

	public void takeClientTurn(
		PrintWriter out, 
		BufferedReader in,
		Player player
	) {

	}

	public void takeServerTurn(Player player) {
		System.out.print("Choose your tile (0-9): ");
		
	}

	private int getWinner() {
		return winner;
	}

	private void checkWinner() {
		GameBoard board = GameState.getBoard();
		boolean fullBoard = true;
		// check rows for win
		for (int i = 0; i < 2; i++) {
			ArrayList<GamePiece> col0 = board.getContents(i,0);
			ArrayList<GamePiece> col1 = board.getContents(i,1);
			ArrayList<GamePiece> col2 = board.getContents(i,2);
			if (col0.isEmpty() || col1.isEmpty() || col2.isEmpty()) {
				fullBoard = false;
				break;
			}
			int col0PlayerNum = col0.get(0).getOwner().getPlayerNum();
			if (
				col1.get(0).getOwner().getPlayerNum() == col0PlayerNum
				&& col2.get(0).getOwner().getPlayerNum() == col0PlayerNum
			) {
				winner = col0PlayerNum;
			}
		}
		// check columns for win
		for (int i = 0; i < 2; i++) {
			ArrayList<GamePiece> row0 = board.getContents(0,i);
			ArrayList<GamePiece> row1 = board.getContents(1,i);
			ArrayList<GamePiece> row2 = board.getContents(2,i);
			if (row0.isEmpty() || row1.isEmpty() || row2.isEmpty()) {
				fullBoard = false;
				break;
			}
			int row0PlayerNum = row0.get(0).getOwner().getPlayerNum();
			if (
				row1.get(0).getOwner().getPlayerNum() == row0PlayerNum
				&& row2.get(0).getOwner().getPlayerNum() == row0PlayerNum
			) {
				winner = row0PlayerNum;
			}
		}
		// check diagonals
		ArrayList<GamePiece> topL = board.getContents(0,0);
		ArrayList<GamePiece> topR = board.getContents(0,2);
		ArrayList<GamePiece> botL = board.getContents(2,0);
		ArrayList<GamePiece> botR = board.getContents(2,2);
		ArrayList<GamePiece> mid = board.getContents(1,1);
		if (mid.isEmpty()) {
			winner = -1;
		}
		int midPlayerNum = mid.get(0).getOwner().getPlayerNum();
		if (!(topL.isEmpty() || botR.isEmpty())) {
			if (
				topL.get(0).getOwner().getPlayerNum() == midPlayerNum
				&& botR.get(0).getOwner().getPlayerNum() == midPlayerNum
			) {
				winner = midPlayerNum;
			}
		} else if (!(topR.isEmpty() || botL.isEmpty())) {
			if (
				topR.get(0).getOwner().getPlayerNum() == midPlayerNum
				&& botL.get(0).getOwner().getPlayerNum() == midPlayerNum
			) {
				winner = midPlayerNum;
			}
		}
		if (fullBoard) {
			winner = -2; // -2 for tie
		}
		winner = -1; // -1 for no winner yet
	}
}
