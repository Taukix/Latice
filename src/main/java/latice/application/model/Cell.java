package latice.application.model;

public class Cell {
	private Tile tile;
	private Boolean bonus;
	
	private Boolean isEmpty() {
		return tile.equals(null);
	}
}
