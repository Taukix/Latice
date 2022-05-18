package latice.application.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
	public Map<Position, Tile> tiles;
	
	public Board() {
		this.tiles = new HashMap<>();
	}

	public Map<Position, Tile> getTiles() {
		return this.tiles;
	}
	
	public boolean tileAt(Position pos) {
		return this.tiles.containsKey(pos);
	}
	
	public Tile getTileAt(Position pos) {
		return this.tiles.get(pos);
	}
	
	public boolean put(Position position, Tile tile) {
		if (tiles.containsKey(position)) {
			return false;
		} else {
			tiles.put(position, tile);
			return true;
		}
	}
}
 