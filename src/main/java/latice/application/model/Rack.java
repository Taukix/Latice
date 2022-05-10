package latice.application.model;

import java.util.List;
import java.util.Map;

public class Rack {
	private Map<Integer, Tile> tiles;
	
	Rack(final Map<Integer, Tile> tiles){
		this.tiles = tiles;
	}
	
	Rack(final List<Tile> tiles){
		int i = 0;
		while(!tiles.isEmpty() && i < Constants.RACK_SIZE) {
			this.tiles.put(i, tiles.remove(0));
		}
	}
	
	
}
