package latice.application.model;

import java.util.ArrayList;

public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private ArrayList<Tile> gameTileList;
	
	private Game(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
		// Need to make the board constructor
		//this.board = new Board();
	}
}
