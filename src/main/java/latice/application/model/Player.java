package latice.application.model;

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
		generateRack();
	}
	
	public void changeTile(final Integer tileNum) {
		//TODO
	}
	
	public void changeRack() {
		//TODO
	}
	
	public void placeTile() {
		//TODO
	}

	public void addTileToStack(final Tile tile){
		this.stack.add(tile);
	}
	
	public void generateRack() {
		for(int i = 0; i < Constants.RACK_SIZE; i++) {
			this.rack = new Rack(stack);
		}
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
