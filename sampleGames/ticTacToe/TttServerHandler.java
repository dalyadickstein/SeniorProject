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

	public void handleEndgame(PrintWriter out, BufferedReader in) {}

	private void takeTurn(Player player, PrintWriter out, BufferedReader in) {
		GameBoard board = GameState.getBoard();
		// out.println(getBoardPrint(board));
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
		// out.println(getBoardPrint(board));
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

	public void takeClientTurn(
		PrintWriter out, 
		BufferedReader in,
		Player player
	) {
		try {
			GameBoard board = GameState.getBoard();
			// out.println(getBoardPrint(board));
			Server.printMsg("Testing printMsg.", out, in);
			out.println(
				"QQ:Choose your tile (0-8, top left as 0 and top middle as 1): "
			);
			int tile;
			while (true) {
				String input = in.readLine();
				try {
					tile = Integer.parseInt(input);
					if (tile < 0 || tile > 8) {
						out.println("QQ:Please choose a valid tile (0-8).");
					} else if (!markTile(player, tile, board)) {
						out.println("QQ:Please choose an empty tile.");
					} else {
						break;
					}
				} catch (NumberFormatException e) {
					out.println("QQ:Please enter an integer answer.");
				}
			}
			// out.println(getBoardPrint(board));
			out.println("It's your opponent's turn.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeServerTurn(Player player) {
		GameBoard board = GameState.getBoard();
		// System.out.println(getBoardPrint(board));
		Server.printMsg("Testing printMsg.", null, null);
		System.out.println(
			"Choose your tile (0-8, top left as 0 and top middle as 1): "
		);
		int tile;
		while (true) {
			String input = scanner.nextLine();
			try {
				tile = Integer.parseInt(input);
				if (tile < 0 || tile > 8) {
					System.out.println("Please choose a valid tile (0-8).");
				} else if (!markTile(player, tile, board)) {
					System.out.println("Please choose an empty tile.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer answer.");
			}
		}
		// System.out.println(getBoardPrint(board));
		System.out.println("It's your opponent's turn.\n");
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
				break;
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
}
