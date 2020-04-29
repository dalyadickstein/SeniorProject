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

	public static void printMsg(
		String msg,
		PrintWriter out,
		BufferedReader in
	) {
		if (out == null) { // print in server
			if (msg.substring(0,3).equals("QQ:")) { // remove QQ flag for server
				System.out.println(msg.substring(3));	
			} else {
				System.out.println(msg);
			}
		} else { // print in client
			out.println(msg);
		}
	}

	public void connect() {
		try {
			System.out.println(
				"Starting up server on port " + port + ". " 
				+ "Waiting for connection from client.\n"
			);
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
				System.out.println("Sanity");
				for (int i = 0; i < numPlayers; i++) {
					if (i == 0) { 
						handler.takeServerTurn(players.get(i));
					} else {
						handler.takeClientTurn(out, in, players.get(i));
					}
				}
			}
			handler.handleEndgame(out, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
