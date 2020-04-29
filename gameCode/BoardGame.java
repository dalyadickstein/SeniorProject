// have own game extend BoardGame and implement takeTurn and checkWinner
package gameCode;

public abstract class BoardGame {

	public abstract void takeTurn(int player);
	public abstract int checkWinner(); // returns winner or <0 if no winner

	// network
	// new GameState
	// set board
	// control turns

	public void playGame() {
		int winner = checkWinner();
		while (winner < 0) {
			takeTurn(TurnController.getPlayerTurn());
			TurnController.endTurn();
		}
	}
}
