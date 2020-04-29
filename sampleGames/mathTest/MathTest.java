package sampleGames.mathTest;

import gameCode.*;
import java.util.Scanner;

public class MathTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MathServerHandler serverHandler = new MathServerHandler(scanner);
		Server server = new Server(4444, serverHandler);
		server.connect();
	}
}
