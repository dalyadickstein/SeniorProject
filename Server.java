import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port;
	private ServerHandler handler;

	public Server(int port, ServerHandler handler) {
		this.port = port;
		this.handler = handler;
	}

	public void connect() {
		try {
			ServerSocket serverSkt = new ServerSocket(port);
			Socket clientSkt = serverSkt.accept();
			PrintWriter out =
				new PrintWriter(clientSkt.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSkt.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));

			// create players: player[0] is server, others are clients
			Player[] players = handler.createPlayers();
			// take turns
			while (!handler.gameEnded(players)) {
				int numPlayers = players.length;
				for (int i = 0; i < numPlayers; i++) {
					if (i == 0) { 
						handler.takeServerTurn(players[i]);
					} else {
						handler.takeClientTurn(out, in, players[i]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
