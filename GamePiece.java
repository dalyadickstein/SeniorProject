import java.awt.Point;

public class GamePiece {

	private String id;
	private int owner;
	private Point coords;

	public GamePiece(int owner) {
		this(null, owner);
	}

	public GamePiece(String id, int owner) {
		this.id = id;
		this.owner = owner;
	}

	public GamePiece(String id, int owner, int x, int y) {
		this.id = id;
		this.owner = owner;
		this.coords = new Point(x, y);
	}

	public int getOwner() {
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
