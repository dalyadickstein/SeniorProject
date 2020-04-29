package gameCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private int port;
	private ServerHandler handler;

	public Server(int port, ServerHandler handler) {
		this.port = port;
		this.handler = handler;
	}

	public void connect() {
		try {
			System.out.println("Starting up server on port " + port);
			ServerSocket serverSkt = new ServerSocket(port);
			Socket clientSkt = serverSkt.accept();
			PrintWriter out =
				new PrintWriter(clientSkt.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSkt.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));

			// create players: player[0] is server, others are clients
			ArrayList<Player> players = handler.createPlayers();
			// take turns
			int numPlayers = players.size();
			while (!handler.gameEnded()) {
				System.out.println("The game must go on.");
				for (int i = 0; i < numPlayers; i++) {
					System.out.println("Player " + i + " turn.");
					if (i == 0) { 
						System.out.println("Server's turn.");
						handler.takeServerTurn(players.get(i));
					} else {
						System.out.println("Client's turn.");
						handler.takeClientTurn(out, in, players.get(i));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
