package gameCode;

import java.util.List;
import java.util.Random;

public class Die {

	private static Random rand = new Random();

	private int numSides;

	public Die(int numSides) {
		this.numSides = numSides;
		GameState.addDie(this);
	}

	public int roll() {
		return 1 + rand.nextInt(numSides);
	}

	public static int rollDice(List<Die> dice) {
		int sum = 0;
		for (Die die : dice) {
			sum += die.roll();
		}
		return sum;
	} 
}
