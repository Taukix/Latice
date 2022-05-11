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
		List<Color> colors = new ArrayList<>();
		Collections.addAll(colors, EnumSet.allOf(Color.class).toArray(new Color[0]));
		
		//Put in a list every elements of the Shape Enum class
		List<Shape> shapes = new ArrayList<>();
		Collections.addAll(shapes, EnumSet.allOf(Shape.class).toArray(new Shape[0]));
		
		//Add every combination of color and shape 2 times
		gameTileList = new ArrayList<>();
		for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
			Shape shape = (Shape) iterator.next();
			for (Iterator<Color> iterator2 = colors.iterator(); iterator2.hasNext();) {
				Color color = (Color) iterator2.next();
				gameTileList.add(new Tile(color, shape));
				gameTileList.add(new Tile(color, shape));
			}
		}
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
	
	
	
}
