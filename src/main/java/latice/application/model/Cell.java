package latice.application.model;

public class Cell {
	private Tile tile;
	private Boolean bonus;
	
	
	// Par défaut une cellule du plateau n'a pas de tuile
	private Cell(Boolean bonus) {
		this.tile = null;
		this.bonus = bonus;
	}
	
	private Boolean isEmpty() {
		return tile.equals(null);
	}
}
