package sampleGames.mathTest;

import gameCode.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MathServerHandler implements ServerHandler {

	private Scanner scanner;

	public MathServerHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	public ArrayList<Player> createPlayers() {
		Player p0 = new Player(0);
		Player p1 = new Player(1);
		System.out.println("There are " + GameState.getNumPlayers() + " players.");
		return GameState.getPlayers();
	}

	public boolean gameEnded() {
		return false;
	}

	public void takeClientTurn(
		PrintWriter out, 
		BufferedReader in,
		Player player
	) {
		System.out.println("In client's turn.");
		try {
			int x = 15;
			int y = 2;
			int sum = x + y;
			out.println("What's " + x + " + " + y + "?");
			String input = in.readLine();
			int answer = Integer.parseInt(input);
			if (answer == x + y) {
				out.println("Correct!");
			} else {
				out.println(
					"Bzzt! Incorrect! The answer is " + sum + "."
					+ "Press ENTER to continue."
				);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished client's turn.");
	}

	public void takeServerTurn(Player player) {
		int x = 5;
		int y = 6;
		int sum = x + y;
		System.out.println("What's " + x + " + " + y + "? ");
		int answer = scanner.nextInt();
		if (answer == x + y) {
			System.out.println("Correct!");
		} else {
			System.out.println("Bzzt! Incorrect! The answer is " + sum + ".");
		}
	}

}
