// For games without networking
public class PlayGame {

	public static void playGame(PlayGameListener listener, Player[] players) {
		while (!listener.gameEnded(players)) {
			for (Player p : players) {
				listener.takeTurn(p);
			}
		}
	}
}
