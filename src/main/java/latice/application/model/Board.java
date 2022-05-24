package latice.application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private Map<Position, Tile> tiles;
	
	public Board() {
		this.tiles = new HashMap<>();
	}

	public Map<Position, Tile> getTiles() {
		return this.tiles;
	}
	
	public boolean tileAt(Position pos) {
		boolean isTileFound = this.tiles.containsKey(pos);
		if(!isTileFound) {
			isTileFound = this.tiles.containsKey(new Position(pos.x(), pos.y()));
		}
		return isTileFound;
	}
	
	public Tile getTileAt(Position pos) {
		return this.tiles.get(new Position(pos.x(), pos.y()));
	}
	
	public boolean isTileBonus(Position pos) {
		boolean bonusTile = false;
		int x = pos.x();
		int y = pos.y();
		//Check if the position is on a bonus of the cross of the map
		bonusTile = ((x == y) || (x + y == 10)) && (x <= 3 || x >= 7) && (y <= 3 || y >= 7);
		
		//Check if the position is on a bonus of the border of the map
		bonusTile = bonusTile || (y == 4 && (x == 1 || x == 9)) || (x == 4 && (y == 1 || y == 9));
		return bonusTile;
	}
	
	public List<Tile> getNearbyTilesOfAPosition(Position tilePos){
		ArrayList<Tile> nearbyTiles = new ArrayList<>();
		for(int i = 0; i <= 1; i++) {
			Position horizontalPos = new Position(tilePos.x()-1+2*i, tilePos.y());
			Position verticalPos = new Position(tilePos.x(), tilePos.y()-1+2*i);
			if(getTileAt(horizontalPos) != null) nearbyTiles.add(getTileAt(horizontalPos));
			if(getTileAt(verticalPos) != null) nearbyTiles.add(getTileAt(verticalPos));
		}
		return nearbyTiles;
	}
	
	public boolean isPlaceable(Position pos, Tile tile) {
		boolean placeable = false;
		
		// Check if tile already there
		placeable = !tileAt(pos);
		
		//Check if there is at least 1 tile else, it should be place in the center
		if(isEmpty() && placeable) {
			placeable = (pos.equals(Constants.CENTER));
		}
		else {
			// Check on top, under, on left and on right of the tile

			ArrayList<Tile> buffer = (ArrayList<Tile>) getNearbyTilesOfAPosition(pos);
			if(buffer.isEmpty()) {
				placeable = false;
			}
			else {
			for (Tile bufferTile : buffer) {
				placeable = placeable && tile.hasCommonTraits(bufferTile);
			}
			}
		}
		return placeable;
	}
	
	private boolean isEmpty() {
		return this.tiles.isEmpty();
	}
	
	public boolean putIn(Position position, Tile tile) {
		boolean putable = false;
		if (isPlaceable(position, tile)){
			this.tiles.put(position, tile);
			putable = true;
		}
		return putable;
	}
}
 