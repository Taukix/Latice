package latice.application.model;

public class Cell {
	private Tile tile;
	private Boolean bonus;
	
	

	public Cell(Tile tile, Boolean bonus) {
		this.tile = tile;
		this.bonus = bonus;
	}
	
	public Cell(Tile tile) {
		this.tile = tile;
		this.bonus = false;
	}

	
	public Cell() {
		this.tile = null;
		this.bonus = false;
	}
	
	private Boolean isEmpty() {
		return tile.equals(null);
	}
}
