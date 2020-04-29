package sampleGames.ticTacToe;

import gameCode.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TttServerHandler implements ServerHandler {

	private int winner = -1; // -1 for ongoing, -2 for tie
	private Scanner scanner;
	
	public TttServerHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	public ArrayList<Player> createPlayers() {
		Player p0 = new Player(0);
		Player p1 = new Player(1);
		return GameState.getPlayers();
	}

	public boolean gameEnded() {
		checkWinner();
		return (winner != -1);
	}

	public void handleEndgame(PrintWriter out, BufferedReader in) {
		if (winner == 0) {
			System.out.println("You won!\n");
			out.println("You lose! Good day, sir!\n");
		} else if (winner == 1) {
			out.println("You won!\n");
			System.out.println("You lose! Good day, sir!\n");
		} else {
			System.out.println("Tie game.\n");
			out.println("Tie game.\n");
		}
	}

	public void takeClientTurn(
		PrintWriter out, 
		BufferedReader in,
		Player player
	) {
		out.println("You are O. Your opponent is X.");
		takeTurn(out, in, player);
	}

	public void takeServerTurn(Player player) {
		System.out.println("You are X. Your opponent is O.");
		takeTurn(null, null, player);
	}

	private void takeTurn(PrintWriter out, BufferedReader in, Player player) {
		GameBoard board = GameState.getBoard();
		Server.printMsg(getBoardPrint(board), out, in);
		Server.printMsg(
			"QQ:Choose your tile (0-8, top left as 0 and top middle as 1): ",
			out,
			in
		);
		int tile;
		while (true) {
			String input = getInput(in);
			try {
				tile = Integer.parseInt(input);
				if (tile < 0 || tile > 8) {
					Server.printMsg(
						"QQ:Please choose a valid tile (0-8).", out, in
					);
				} else if (!markTile(player, tile, board)) {
					Server.printMsg("QQ:Please choose an empty tile.", out, in);
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				Server.printMsg("QQ:Please enter an integer answer.", out, in);
			}
		}
		Server.printMsg(getBoardPrint(board), out, in);
		Server.printMsg("It's your opponent's turn.\n", out, in);
	}

	private String getInput(BufferedReader in) {
		if (in == null) { // server's turn: get input from scanner
			return scanner.nextLine();
		} else {
			try {
				return in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Mark tile for player. Return true if tile was empty, and false if not.
	private boolean markTile(Player player, int tile, GameBoard board) {
		int row = tile / 3;
		int col = tile % 3;
		if (!board.isEmpty(row, col)) {
			return false;
		}
		board.addPiece(new GamePiece(player), row, col);
		return true;
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
				continue;
			}
			int col0PlayerNum = col0.get(0).getOwner().getPlayerNum();
			if (
				col1.get(0).getOwner().getPlayerNum() == col0PlayerNum
				&& col2.get(0).getOwner().getPlayerNum() == col0PlayerNum
			) {
				winner = col0PlayerNum;
				return;
			}
		}
		// check columns for win
		for (int i = 0; i < 2; i++) {
			ArrayList<GamePiece> row0 = board.getContents(0,i);
			ArrayList<GamePiece> row1 = board.getContents(1,i);
			ArrayList<GamePiece> row2 = board.getContents(2,i);
			if (row0.isEmpty() || row1.isEmpty() || row2.isEmpty()) {
				fullBoard = false;
				continue;
			}
			int row0PlayerNum = row0.get(0).getOwner().getPlayerNum();
			if (
				row1.get(0).getOwner().getPlayerNum() == row0PlayerNum
				&& row2.get(0).getOwner().getPlayerNum() == row0PlayerNum
			) {
				winner = row0PlayerNum;
				return;
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
			return;
		}
		int midPlayerNum = mid.get(0).getOwner().getPlayerNum();
		if (!(topL.isEmpty() || botR.isEmpty())) {
			if (
				topL.get(0).getOwner().getPlayerNum() == midPlayerNum
				&& botR.get(0).getOwner().getPlayerNum() == midPlayerNum
			) {
				winner = midPlayerNum;
				return;
			}
		} else if (!(topR.isEmpty() || botL.isEmpty())) {
			if (
				topR.get(0).getOwner().getPlayerNum() == midPlayerNum
				&& botL.get(0).getOwner().getPlayerNum() == midPlayerNum
			) {
				winner = midPlayerNum;
				return;
			}
		}
		if (fullBoard) {
			winner = -2; // -2 for tie
			return;
		}
		winner = -1; // -1 for no winner yet
		return;
	}

	private String getBoardPrint(GameBoard board) {
		String boardStr = "\nBoard:\n-------------\n";
		ArrayList<Player> players = GameState.getPlayers();
		for (int row = 0; row < 3; row++) {
			boardStr += "|";
			for (int col = 0; col < 3; col++) {
				if (board.isEmpty(row,col)) {
					boardStr += "   |";
				} else if (board.playerOccupies(players.get(0), row, col)) {
					boardStr += " x |";
				} else {
					boardStr += " o |";
				}
			}
			boardStr += "\n-------------\n";
		}
		return boardStr;
	}
}
