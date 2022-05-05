package latice.application.model;

public class Tile {
	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + "]";
	}

	private Color color;
	private Shape shape;
	
	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	
}
