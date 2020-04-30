package gameCode;

public class Bank {

	private boolean limited;
	private double balance;

	public Bank() {
		this.balance = Double.MAX_VALUE;
		this.limited = false;
		GameState.setBank(this);
	}

	public Bank(double balance) {
		this.balance = balance;
		this.limited = true;
		GameState.setBank(this);
	}

	public double getBalance() {
		return balance;
	}

	public void payPlayer(Player player, double amt) {
		if (limited) {
			double actualAmt = amt > balance ? balance : amt;
			balance -= actualAmt;
			player.addMoney(actualAmt);
		} else {
			player.addMoney(amt);
		}
	}

	public void chargePlayer(Player player, double amt) {
		player.subtractMoney(amt);
		balance += amt;
	}

}
