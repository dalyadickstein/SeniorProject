package sampleGames.mathTest;

import gameCode.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MathServerHandler implements ServerHandler {

	private Scanner scanner;
	private int serverScore = 0;
	private int clientScore = 0;

	public MathServerHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	public ArrayList<Player> createPlayers() {
		Player p0 = new Player(0);
		Player p1 = new Player(1);
		return GameState.getPlayers();
	}

	public boolean gameEnded() {
		return serverScore != clientScore && serverScore + clientScore > 3;
	}

	public void handleEndgame(PrintWriter out, BufferedReader in) {
		if (serverScore > clientScore) {
			System.out.println("You won!");
			out.println("You lost. Better brush up on your addition.");
		} else {
			out.println("You won!");
			System.out.println("You lost. Better brush up on your addition.");
		}
	}

	// Player must add 2 random numbers from 0-99.
	public void takeClientTurn(
		PrintWriter out, 
		BufferedReader in,
		Player player
	) {
		try {
			Random rand = new Random();
			int x = rand.nextInt(100);
			int y = rand.nextInt(100);
			int sum = x + y;
			out.println(
				"Score:\nYou: " + clientScore + "\nOpponent: " + serverScore
			);
			out.println("QQ:What's " + x + " + " + y + "?");
			int answer;
			while (true) {
				String input = in.readLine();
				try {
					answer = Integer.parseInt(input);
					break;
				} catch (NumberFormatException e) {
					out.println("QQ:Please enter an integer answer.");
				}
			}
			if (answer == x + y) {
				out.println("Correct!");
				clientScore++;
			} else {
				out.println("Bzzt! Incorrect! The answer is " + sum + ".");
			}
			out.println("It's your opponent's turn.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeServerTurn(Player player) {
		Random rand = new Random();
		int x = rand.nextInt(100);
		int y = rand.nextInt(100);
		int sum = x + y;
		System.out.println(
			"Score:\nYou: " + serverScore + "\nOpponent: " + clientScore
		);
		System.out.println("What's " + x + " + " + y + "? ");
		int answer;
		while (true) {
			String input = scanner.nextLine();
			try {
				answer = Integer.parseInt(input);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer answer.");
			}
		}
		if (answer == x + y) {
			System.out.println("Correct!");
			serverScore++;
		} else {
			System.out.println("Bzzt! Incorrect! The answer is " + sum + ".");
		}
		System.out.println("It's your opponent's turn.\n");
	}

}
