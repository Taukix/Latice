package latice.application.model;

import java.util.HashMap;
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
	
	public boolean isPlaceable(Position pos, Tile tile) {
		System.out.println(getTileAt(new Position(5,5)));
		boolean placeable = false;
		int x = 0;
		int y = 0;
		
		// Check if tile already there
		placeable = !tileAt(pos);
		
		System.out.println(tile.toString() + placeable);
		
		// Check on top, under, on left and on right of the tile
		for(int i = 0; i <= 1; i++) {
			int y1 = pos.y() - 1 + 2*i;
			Position newPos = new Position(pos.x(),y1);
			System.out.println(newPos.x() + " " +  newPos.y());
			System.out.println(tileAt(newPos) && y1 < Constants.BOARD_SIZE && y1 > 0);
			if(tileAt(newPos) && y1 < Constants.BOARD_SIZE && y1 > 0) {
				placeable = placeable && (getTileAt(newPos).getColor() == tile.getColor() || getTileAt(newPos).getShape() == tile.getShape()); 
			}
		}
		return placeable;
	}
	
	public boolean putIn(Position position, Tile tile) {
		if (isPlaceable(position, tile)) {
			return false;
		} else {
			this.tiles.put(position, tile);
			return true;
		}
	}
}
 