package sampleGames.mathTest;

import gameCode.*;
import java.util.Scanner;

// Game where players must add 2 random numbers from 0-99. Player with the
// highest score wins once scores total > 3.
public class MathTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MathServerHandler serverHandler = new MathServerHandler(scanner);
		Server server = new Server(4444, serverHandler);
		server.connect();
	}
}
