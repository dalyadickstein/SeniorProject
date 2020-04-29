package sampleGames.ticTacToe;

import gameCode.*;
import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GameBoard board = new GameBoard(3,3);
		TttServerHandler serverHandler = new TttServerHandler(scanner);
		Server server = new Server(4444, serverHandler);
		server.connect();
	}
}
