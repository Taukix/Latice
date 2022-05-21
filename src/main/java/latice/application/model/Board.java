package latice.application.model;

import java.util.ArrayList;
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
	
	private boolean isPlaceable(Position pos, Tile tile) {
		boolean placeable = false;
		int x = 0;
		int y = 0;

		// Check if tile already there
		placeable = !tileAt(pos);
		if(isEmpty() && placeable) {
			placeable = (pos.equals(Constants.CENTER));
		}
		else {
			// Check on top, under, on left and on right of the tile

			ArrayList<Tile> buffer = new ArrayList<>();
			for(int i = 0; i <= 1; i++) {
				Position posHorizontal = new Position(pos.x() - 1 + 2 * i, pos.y());
				Position posVertical = new Position(pos.x(), pos.y() - 1 + 2 * i);
				boolean tileInHorizontalPosition = tileAt(posHorizontal);
				boolean tileInVerticalPosition = tileAt(posVertical);
				if(tileInHorizontalPosition){
					buffer.add(getTileAt(posHorizontal));
				}
				if(tileInVerticalPosition) {
					buffer.add(getTileAt(posVertical));
				}
			}
			System.out.println(tiles.keySet().contains(new Position(Constants.CENTER.x(), Constants.CENTER.y())));
			if(buffer.isEmpty()) {
				placeable = false;
			}
			else {
			for (Tile bufferTile : buffer) {
				placeable = placeable && (tile.getShape() == bufferTile.getShape() || tile.getColor() == bufferTile.getColor());
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
 