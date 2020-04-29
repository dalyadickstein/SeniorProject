// For games without networking
package gameCode;

public interface PlayGameListener {
	public boolean gameEnded(Player[] players);
	public void takeTurn(Player player);
}
