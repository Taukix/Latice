package latice.application.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rack {
	private Map<Integer, Tile> tiles;
	
	Rack(final Map<Integer, Tile> tiles){
		this.tiles = tiles;
	}
	
	Rack() {
		this.tiles = new HashMap<>();
	}
	
	public void FillRack(final List<Tile> tiles){
		int i = 0;
		while(i < Constants.RACK_SIZE) {
			this.tiles.put(i, tiles.remove(0));
			i++;
		}
	}

	public Map<Integer, Tile> getTiles() {
		return tiles;
	}
}
