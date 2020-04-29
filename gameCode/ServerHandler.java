package gameCode;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ServerHandler {
	
	public ArrayList<Player> createPlayers();
	
	public boolean gameEnded();
	
	public void takeClientTurn(
		PrintWriter out,
		BufferedReader in,
		Player player
	);
	
	public void takeServerTurn(Player player);
}
