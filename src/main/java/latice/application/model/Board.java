package latice.application.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
	public Map<Position, Tile> tiles;
	
	public Board() {
		this.tiles = new HashMap<>();
	}


	public Map<Position, Tile> getTiles() {
		return tiles;
	}
}
 