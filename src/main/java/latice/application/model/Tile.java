package latice.application.model;

public class Tile {
	private ColorTile color;
	private Shape shape;

	public Tile(ColorTile color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + "]";
	}

	public ColorTile getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}
}
