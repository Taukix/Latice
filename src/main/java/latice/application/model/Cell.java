package latice.application.model;

public class Cell {
	private Tile tile;
	private Boolean bonus;
	
	
	// By default a board's cell has no tile	
	// And it doesn't have any bonus
	private Cell() {
		this.tile = null;
		this.bonus = false;
	}
	
	private Boolean isEmpty() {
		return tile.equals(null);
	}
}
