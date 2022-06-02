package latice.application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private String username;
	private Integer bonus;
	private boolean consumedTurn;
	private boolean turn;
	private List<Tile> stack;
	private Rack rack;
	
	public Player(String username){
		this.username = username;
		this.bonus = 0;
		this.stack = new ArrayList<>();
		this.turn = false;
		this.consumedTurn = false;
	}
	
	
	public boolean changeRack() {
		if(bonus >= 2) {
			for(Tile tile : rack.getTiles()) {
				stack.add(tile);
			}
			rack.clear();
			Collections.shuffle(stack);
			rack.fillRackWithTiles(stack);
			consumedTurn = true;
			bonus = bonus -2;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean canPlay(Board board) {
		boolean playable = false;
		if(board.isEmpty() && rack.countTilesInRack() > 0) {
			return true;
		}
		else {
			playable = board.checkTilesPlayableInAList(rack.getTiles());
		}
		
		return playable;
	}
	
	

	public boolean placeTile(Board board,Position pos, int tileOfRack) {
		boolean madeChange = false;
		if(turn) {
			Tile tile = rack.getTiles().get(tileOfRack);
		
			if(tile != null && board.isPlaceable(pos, tile) && (!consumedTurn|| bonus >= 2)) {
					rack.getTiles().remove(tileOfRack);
					if(consumedTurn) {
						bonus = bonus - 2;
					}
					boolean bonusTile = board.isTileBonus(pos);		
					ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) board.getNearbyTilesOfAPosition(pos);
					getBonusPoints(bonusTile, nearbyTiles.size());		
					madeChange = board.putIn(pos, tile);
					consumedTurn = true;
			}
		}
		return madeChange;
	}
	
	private void getBonusPoints(boolean bonusTile, int countOfNearbyTiles) {
		if(bonusTile) {
			bonus += 2;
		}
		if(countOfNearbyTiles > 1) {
			bonus += (int) Math.pow(2, countOfNearbyTiles- (double) 2);
		}
	}
	
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
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

	public Integer getBonus() {
		return bonus;
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
	
	public boolean getConsumedTurn() {
		return consumedTurn;
	}
}
