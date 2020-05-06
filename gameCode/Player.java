package gameCode;

import java.util.ArrayList;

public class Player {
	
	private int playerNum;
	private ArrayList<GamePiece> pieces;
	private double money;

	public Player(int playerNum) {
		this(playerNum, null);
	}

	public Player(int playerNum, ArrayList<GamePiece> pieces) {
		this(playerNum, pieces, 0.0);
	}

	public Player(int playerNum, double money) {
		this(playerNum, null, money);
	}

	public Player(int playerNum, ArrayList<GamePiece> pieces, double money) {
		this.playerNum = playerNum;
		this.pieces = pieces;
		this.money = money;
		GameState.addPlayer(this);
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public ArrayList<GamePiece> getPieces() {
		return pieces;
	}

	public double getMoney() {
		return money;
	}

	protected void addMoney(double amt) {
		if (this.money + amt < Double.MAX_VALUE) {
			this.money += amt;
		} else {
			this.money = Double.MAX_VALUE;
		}
	}

	protected void subtractMoney(double amt) {
		if (this.money - amt > Double.MIN_VALUE) {
			this.money -= amt;
		} else {
			this.money = Double.MIN_VALUE;
		}
	}
	
}
