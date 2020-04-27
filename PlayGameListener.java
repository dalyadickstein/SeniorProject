// For games without networking
public interface PlayGameListener {
	public boolean gameEnded(Player[] players);
	public void takeTurn(Player player);
}
