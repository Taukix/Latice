package latice.application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private List<Tile> gameTileList;
	
	public Game(Player p1, Player p2) {

		this.player1 = p1;
		this.player2 = p2;
		this.board = new Board();
		
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
			this.player1.stack.add(this.gameTileList.remove(0));
			this.player2.stack.add(this.gameTileList.remove(0));
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
			Shape shape = (Shape) iterator.next();
			for (Iterator<ColorTile> iterator2 = colors.iterator(); iterator2.hasNext();) {
				ColorTile color = (ColorTile) iterator2.next();
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
	
	public void nextTurn(Player p1, Player p2) {
		if (p1.turn == true) {
			p1.endTurn();
			p2.startTurn();
		} else {
			p2.endTurn();
			p1.startTurn();
		}
	}
	
	public void playerWon(Player p1, Player p2, ArrayList<TileFx> list1, ArrayList<TileFx> list2) {
		if (p1.getStack().size() == 0 && list1.size() == 0) {
			System.out.println("Player1 won");
		} else if (p2.getStack().size() == 0 && list2.size() == 0){
			System.out.println("Player2 won");
		}
	}
	
}
