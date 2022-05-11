package latice.application.model;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Player {
	public String username;
	public Integer score;
	public List<Tile> stack;
	public Rack rack;
	
	public Player(String username){
		this.username = username;
		this.score = 0;
		this.stack = new ArrayList<>();
	}
	
	public void changeTile(final Integer tileNum) {
		//TODO
	}
	
	public void changeRack() {
		//TODO
	}
	
	public void placeTile(Game game, int x, int y, int tileOfRack) {
		Position inGamePos = new Position(x, y);
		Tile tile = rack.getTiles().get(tileOfRack);
		if(!tile.equals(null)) {
			if(x > 0 && x <= Constants.BOARD_SIZE && y > 0 && y <= Constants.BOARD_SIZE) {				
				Boolean bonus;
				//Check if bonus is in the cross of the map
				bonus = ((x == y) || (x == y - Constants.BOARD_SIZE)) && (x <= 3 || x >= 7) && (y <= 3 || y >= 7);
				
				//Check if bonus is in on the border of the map
				bonus = bonus || (y == 4 && (x == 1 || x == 9)) || (x == 4 && (y == 1 || y == 9)) ;
				
				game.getBoard().cells.put(inGamePos, new Cell(tile, bonus));
			}
		}
	}

	public void addTileToStack(final Tile tile){
		this.stack.add(tile);
	}
	
	public void generateRack() {
		this.rack = new Rack();
		this.rack.FillRack(stack);
	}
	
	public Integer countTilesInStack() {
		return stack.size();
	}

	public String getUsername() {
		return username;
	}

	public Integer getScore() {
		return score;
	}

	public List<Tile> getStack() {
		return stack;
	}

	public Rack getRack() {
		return rack;
	}
}
