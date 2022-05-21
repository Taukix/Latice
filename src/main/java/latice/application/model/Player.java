package latice.application.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String username;
	private Integer score;
	private boolean consumedTurn;
	private boolean turn;
	private List<Tile> stack;
	private Rack rack;
	
	public Player(String username){
		this.username = username;
		this.score = 0;
		this.stack = new ArrayList<>();
		turn = false;
	}
	
	public void changeTile(final Integer tileNum) {
		//TODO
	}
	
	public void changeRack() {
		//TODO
	}
	
	
	

	public boolean placeTile(Game game, Position pos, int tileOfRack) {
		boolean inMap = false;
		if(turn) {
		int x = pos.x();
		int y = pos.y();
		Tile tile = rack.getTiles().remove(tileOfRack);
		
		if(tile != null) {
			if(x > 0 && x <= Constants.BOARD_SIZE && y > 0 && y <= Constants.BOARD_SIZE && (!consumedTurn|| score > 2)) {				
				boolean bonus;
				if(consumedTurn) {
					score = score - 2;
				}
				//Check if bonus is in the cross of the map
				bonus = ((x == y) || (x == y - Constants.BOARD_SIZE)) && (x <= 3 || x >= 7) && (y <= 3 || y >= 7);
				
				//Check if bonus is in on the border of the map
				bonus = bonus || (y == 4 && (x == 1 || x == 9)) || (x == 4 && (y == 1 || y == 9)) ;
				
				game.getBoard().putIn(pos, tile);
				if(bonus && score%2 < 3) {
					score += 2;
				}
				consumedTurn = true;
				//inMap = canPlaceTileAt(game, pos, tile);
			}
		}
		else {
			rack.getTiles().add(tile);
		}
		}
		return inMap;
	}
	
	public void endTurn() {
		refreshRack();
		consumedTurn = true;
		turn = false;
	}
	
	public void startTurn() {
		consumedTurn = false;
		turn = true;
	}
	
	public void refreshRack() {
		this.rack.fillRackWithTiles(stack);
	}

	public void addTileToStack(final Tile tile){
		this.stack.add(tile);
	}
	
	public void generateRack() {
		this.rack = new Rack();
		this.rack.fillRackWithTiles(stack);
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
	
	public boolean getTurn() {
		return this.turn;
	}
}
