package latice.application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private List<Tile> gameTileList;
	private int turn;
	public static String winner;
	
	public Game(Player p1, Player p2) {

		this.player1 = p1;
		this.player2 = p2;
		this.turn = 10;
		this.board = new Board();
		this.player1.startTurn();
		winner = "";
		
		generateNewGameTileList();
		
		Collections.shuffle(gameTileList);

		giveEachPlayerStack();
		generateEachPlayerRack();
		
	}

	private void generateEachPlayerRack() {
		this.player1.generateRack();
		this.player2.generateRack();
	}
	
	private void giveEachPlayerStack() {
		while(!this.gameTileList.isEmpty()) {
			this.player1.getStack().add(this.gameTileList.remove(0));
			this.player2.getStack().add(this.gameTileList.remove(0));
		}
	}

	private void generateNewGameTileList() {
		//Put in a list every elements of the Color Enum class
		List<ColorTile> colors = new ArrayList<>();
		Collections.addAll(colors, EnumSet.allOf(ColorTile.class).toArray(new ColorTile[0]));
		
		//Put in a list every elements of the Shape Enum class
		List<Shape> shapes = new ArrayList<>();
		Collections.addAll(shapes, EnumSet.allOf(Shape.class).toArray(new Shape[0]));
		
		//Add every combination of color and shape 2 times
		gameTileList = new ArrayList<>();
		for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
			Shape shape = iterator.next();
			for (Iterator<ColorTile> iterator2 = colors.iterator(); iterator2.hasNext();) {
				ColorTile color = iterator2.next();
				gameTileList.add(new Tile(color, shape));
				gameTileList.add(new Tile(color, shape));
			}
		}
	}

	public void startPlayerTurn(Player p) {
		p.refreshRack();
	}
	
	public Player getPlayer1() {
		return this.player1;
	}

	public Player getPlayer2() {
		return this.player2;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int getNumberOfTurn() {
		return this.turn;
	}
	
	public void nextTurn(Player p1, Player p2) {
		if (p1.getTurn()) {
			p1.endTurn();
			p2.startTurn();
		} else {
			p2.endTurn();
			this.turn -= 1;
			if(turn != 0) {
				p1.startTurn();	
			}
		}
		playerWon(p1, p2);
	}
	
	public boolean playerWon(Player p1, Player p2) {
		if (p1.getStack().isEmpty() && p1.getRack().getTiles().isEmpty() || (!player1.canPlay(board) && !player2.canPlay(board))) {
			winner = p1.getUsername() + " won !";
			return true;
		} else if (p2.getStack().isEmpty() && p2.getRack().getTiles().isEmpty() || (!player1.canPlay(board) && !player2.canPlay(board))) {
			winner = p2.getUsername() + " won !";
			return true;
		}
		else if(this.turn == 0) {
			if(p1.countTilesInStack() < p2.countTilesInStack()) {
				winner = p1.getUsername() + " won !";
				return true;
			}
			else if(p1.countTilesInStack() == p2.countTilesInStack()) {
				winner = "It's a draw ! ";
				return true;
			}
			else {
				winner = p2.getUsername() + " won !";
				return true;
			}
		}
		return false;
	}
}
