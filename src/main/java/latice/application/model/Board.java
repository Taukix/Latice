package latice.application.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
	public Map<Position, Cell> cells;
	
	public Board() {
		this.cells = new HashMap<>();
	}


	public Map<Position, Cell> getCells() {
		return cells;
	}
}
 