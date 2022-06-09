package latice.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rack {
	private List<Tile> tiles;
	
	Rack(final List<Tile> tiles){
		this.tiles = tiles;
	}
	
	Rack() {
		this.tiles = new ArrayList<>();
	}
	
	public void fillRackWithTiles(final List<Tile> tiles){
		while(!tiles.isEmpty() && this.tiles.size() < Constants.RACK_SIZE.value()) {
			this.tiles.add(tiles.remove(0));
		}
	}
	
	public void addTile(final Tile tile) {
		this.tiles.add(tile);
	}
	
	public void clear() {
		this.tiles.clear();
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tiles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rack other = (Rack) obj;
		return Objects.equals(tiles, other.tiles);
	}

	public boolean isFull() {
		return tiles.size() == Constants.RACK_SIZE.value();
	}
	
	public Integer countTilesInRack() {
		return tiles.size();
	}
}
