package latice.application.model;

import java.util.ArrayList;
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
		turn = false;
	}
	
	public void changeTile(final Integer tileNum) {
		//TODO
	}
	
	public void changeRack() {
		//TODO
	}
	
	
	

	public boolean placeTile(Board board,Position pos, int tileOfRack) {
		boolean madeChange = false;
		if(turn) {
			Tile tile = rack.getTiles().remove(tileOfRack);
		
			if(tile != null) {
				if(board.isPlaceable(pos, tile) && (!consumedTurn|| bonus >= 2)) {
					boolean bonusTile = board.isTileBonus(pos);		
					ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) board.getNearbyTilesOfAPosition(pos);
					getBonusPoints(bonusTile, nearbyTiles.size());		
					madeChange = board.putIn(pos, tile);
					consumedTurn = true;
				}
			}
			else {
				rack.getTiles().add(tile);
			}
		}
		return madeChange;
	}
	
	private void getBonusPoints(boolean bonusTile, int countOfNearbyTiles) {
		if(consumedTurn) {
			bonus = bonus - 2;
		}
		if(bonusTile) {
			bonus += 2;
		}
		if(countOfNearbyTiles > 2) {
			bonus += 2;
		} else if (countOfNearbyTiles > 1) {
			bonus += countOfNearbyTiles - 1;
		}
		if(bonus > Constants.MAX_BONUS) {
			bonus = Constants.MAX_BONUS;
		}
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
}
