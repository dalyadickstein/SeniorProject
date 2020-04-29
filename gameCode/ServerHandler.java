package gameCode;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ServerHandler {
	
	public ArrayList<Player> createPlayers();
	
	public boolean gameEnded();

	// To allow for printing winner for clients
	public void handleEndgame(PrintWriter out, BufferedReader in);
	
	// Logic for sending and receiving messages to/from client on client's turn
	public void takeClientTurn(
		PrintWriter out,
		BufferedReader in,
		Player player
	);
	
	// Logic for server player's turn
	public void takeServerTurn(Player player);
}
