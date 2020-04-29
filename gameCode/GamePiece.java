package gameCode;

import java.awt.Point;

public class GamePiece {

	private String id;
	private Player owner;
	private Point coords;

	public GamePiece(Player owner) {
		this(null, owner);
	}

	public GamePiece(String id, Player owner) {
		this.id = id;
		this.owner = owner;
	}

	public GamePiece(String id, Player owner, int x, int y) {
		this.id = id;
		this.owner = owner;
		this.coords = new Point(x, y);
	}

	public Player getOwner() {
		return owner;
	}

	public String getID() {
		return id;
	}

	public Point getCoords() {
		return coords;
	}

	public void setCoords(Point to) {
		coords = to;
	}
	
}
