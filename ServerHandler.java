import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ServerHandler {
	public Player[] createPlayers();
	public boolean gameEnded(Player[] players);
	public void takeClientTurn(PrintWriter out, BufferedReader in, Player player);
	public void takeServerTurn(Player player);
}
