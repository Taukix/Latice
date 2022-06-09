package latice.application.model;

import java.util.Objects;

public class Tile {
	private ColorTile color;
	private Shape shape;

	public Tile(ColorTile color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	public Tile(Tile anotherTile) {
		super();
		this.color = anotherTile.getColor();
		this.shape = anotherTile.getShape();
	}

	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + "]";
	}

	public ColorTile getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, shape);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return color == other.color && shape == other.shape;
	}

	public Shape getShape() {
		return shape;
	}
	
	public boolean hasCommonTraits(Tile tile) {
		return this.getShape() == tile.getShape() || this.getColor() == tile.getColor();
	}
	
}
