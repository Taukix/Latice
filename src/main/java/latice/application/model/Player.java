package latice.application.model;

import java.util.HashMap;
import java.util.Map;

public class Player {
	private String username;
	private Integer score;
	private Map<Integer, Tile> stack;
	private Rack rack;
	
	public Player(String username){
		this.username = username;
		this.score = 0;
		this.stack = new HashMap<>();
		this.rack = new Rack();
	}
	
	private void changeTile(final Integer tileNum) {
		//TODO
	}
	
	private void changeRack() {
		//TODO
	}
	
	private void placeTile() {
		//TODO
	}
}
