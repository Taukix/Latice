package latice.application.model;

public class Tile {
	private Color color;
	private Shape shape;

	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + "]";
	}

	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}

	
	
	
}
