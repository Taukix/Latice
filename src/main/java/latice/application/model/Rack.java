package latice.application.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	private List<Tile> tiles;
	
	Rack(final List<Tile> tiles){
		this.tiles = tiles;
	}
	
	Rack() {
		this.tiles = new ArrayList<>();
	}
	
	public void fillRackWithTiles(final List<Tile> tiles){
		while(!tiles.isEmpty() && this.tiles.size() < Constants.RACK_SIZE) {
			this.tiles.add(tiles.remove(0));
		}
	}

	public List<Tile> getTiles() {
		return tiles;
	}
	
	public boolean isFull() {
		return tiles.size() == Constants.RACK_SIZE;
	}
	
	public Integer countTilesInRack() {
		return tiles.size();
	}
}
