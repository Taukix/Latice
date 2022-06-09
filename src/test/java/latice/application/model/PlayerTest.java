package latice.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	Game game;
	Player player1;
	Player player2;
	Board board;
	
	
	@BeforeEach
	void gameGeneration(){
		player1 = new Player("p1");
		player2 = new Player("p2");
		
		game = new Game(player1, player2);
		board = game.getBoard();
		
	}
	
	
	@Test
	void checkPlayerCreation() {
		//Arrange
		Player player;
		
		//Act
		player = new Player("Player test");
		game = new Game(player, new Player("Random player"));
		
		//Assert
		assertEquals("Player test", player.getUsername());
		assertEquals(Player.class, player.getClass());
		assertEquals(0, player.getBonus());
		assertTrue(player.getTurn());
		assertEquals(31,player.getStack().size());
		assertFalse(player.getConsumedTurn());
		assertEquals(5,player.getRack().countTilesInRack());
	}
	
	@Test
	void changeRackWithNotEnoughPoints() {
		//Arrange
		Rack currentRack = new Rack(player1.getRack().getTiles());
		
		//Act
		player1.changeRack();
		
		//Assert
		assertFalse(player1.changeRack());
		assertEquals(player1.getRack(), currentRack);
	}
	
	
	
	@Test
	void changeRackWithEnoughPoints() {
		//Arrange
		Rack currentRack = new Rack();
		currentRack.fillRackWithTiles(player1.getRack().getTiles());
		player1.setBonus(5);
		
		//Act
		player1.changeRack();
		
		//Assert
		assertTrue(player1.changeRack());
		assertNotEquals(currentRack.getTiles(),player1.getRack().getTiles());
	}
	
	@Test
	void playerCanPlayOnEmptyBoard() {
		//Arrange
		Board board = new Board();
		boolean result = false;
		//Act
		result = player1.canPlay(board);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	void playerCantPlayOnFullBoard() {
		//Arrange
		
		
		//Need further improvement, the for loop of 8 times is really not optimal to fulfill the board
		for(int fulfill = 0; fulfill <= 8; fulfill++) {
			for(int x = 1; x <= Constants.BOARD_SIZE.value(); x++) {
				for(int y = 1; y <= Constants.BOARD_SIZE.value(); y++) {
					board.putIn(new Position(x, y), new Tile(ColorTile.ONE, Shape.ONE));
				}
			}
		}
		boolean result = false;	
		//act
		result = player1.canPlay(board);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	void lookForFirstTilePlayerRack() {
		//Arrange
		Tile tilePos0;
		Tile tilePos1;
		
		//Act
		tilePos0 = player1.getTileOfTheRack(0);
		tilePos1 = player1.getTileOfTheRack(1);
		
		//Assert 
		assertEquals(null, tilePos0);
		assertNotEquals(null, tilePos1);
	}
	
	@Test
	void playerPlaceTileOnBonusCell() {
		//Arrange
		Tile firstPlayerTile = player1.getTileOfTheRack(1);
		board.putIn(ConstantPosition.CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.RIGHT_CENTER.pos(), firstPlayerTile);
		board.putIn(new Position(5, 7), firstPlayerTile);
		board.putIn(new Position(4, 7), firstPlayerTile);
		//Act
		player1.placeTile(game.getBoard(), new Position(3,7), 1);
		//Assert
		assertEquals(2, player1.getBonus());
	}
	
	@Test
	void playerTryToPlaceTileWhileTurnConsumed() {
		//Arrange
		player1.placeTile(board, ConstantPosition.CENTER.pos(), 1);
		
		//Act 
		boolean isPlaced = player1.placeTile(board, ConstantPosition.LEFT_CENTER.pos(), 2);
		
		//Arrange
		assertFalse(isPlaced);
	}
	
	@Test
	void playerTriesToPlace2TimesTheSameIndexOfTile() {
		//Arrange
		player1.placeTile(board, ConstantPosition.CENTER.pos(), 1);
		
		//Act
		boolean isPlaced = player1.placeTile(board, ConstantPosition.LEFT_CENTER.pos(), 1);
		
		//Arrange
		assertFalse(isPlaced);
	}
	
	@Test 
	void thePlayerConsumeABonusToPlaceATile(){
		//Arrange
		player1.setBonus(2);
		player1.setConsumedTurn(true);
		
		//Act
		player1.placeTile(board, ConstantPosition.CENTER.pos(), 1);

		//Assert
		assertEquals(0, player1.getBonus());
		assertTrue(player1.getConsumedTurn());
		assertFalse(board.isEmpty());
	}
	
	@Test
	void playerChangeHisRack() {
		//Arrange
		player1.setBonus(2);
		Rack rack = new Rack();
		rack.fillRackWithTiles(player1.getRack().getTiles());
		//Act
		player1.changeRack();
		
		//Assert
		assertEquals(0, player1.getBonus());
		assertNotEquals(rack.getTiles(), player1.getRack().getTiles());
	}
	
	@Test
	void playerEndHisTurn() {
		//Arrange
		Rack rack = new Rack();
		for(Tile tile : player1.getRack().getTiles()) {
			rack.addTile(tile);
		}
		//Act
		player1.endTurn();
		//Assert
		assertFalse(player1.getTurn());
		assertTrue(player1.getConsumedTurn());
		assertEquals(rack.getTiles(), player1.getRack().getTiles());
	}
	
	@Test
	void playerEndHisTurnAfterPlaying() {
		//Arrange
		Rack rack = new Rack();
		for(Tile tile : player1.getRack().getTiles()) {
			rack.addTile(tile);
		}
		player1.placeTile(board, ConstantPosition.CENTER.pos(), 1);
		//Act
		player1.endTurn();
		//Assert
		assertFalse(player1.getTurn());
		assertTrue(player1.getConsumedTurn());
		assertNotEquals(rack.getTiles(), player1.getRack().getTiles());
	}
	
	@Test
	void playerAddTileToStack() {
		//Arrange
		int nbTilesInStackBeforeAdd = player1.countTilesInStack();
		int nbTilesInStack;
		//Act
		player1.addTileToStack(new Tile(ColorTile.ONE, Shape.ONE));
		nbTilesInStack = player1.countTilesInStack();
		//Assert
		assertEquals(nbTilesInStack, nbTilesInStackBeforeAdd+1);
		assertThat(player1.getStack()).last().isEqualTo(new Tile(ColorTile.ONE, Shape.ONE));
	}
	
	@Test
	void playerGet1BonusPointByPuttingTileBetween2Tiles() {
		//Arrange
		Tile firstPlayerTile = player1.getTileOfTheRack(1);
		board.putIn(ConstantPosition.CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.RIGHT_CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.TOP_CENTER.pos(), firstPlayerTile);
		//Act
		player1.placeTile(board, new Position(4, 6), 1);
		
		//Assert
		assertEquals(1,player1.getBonus());
	}
	
	@Test
	void playerGet2BonusPointByPuttingTileBetween3Tiles() {
		//Arrange
		Tile firstPlayerTile = player1.getTileOfTheRack(1);
		board.putIn(ConstantPosition.CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.RIGHT_CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.TOP_CENTER.pos(), firstPlayerTile);
		board.putIn(new Position(3,5), firstPlayerTile);
		board.putIn(new Position(3, 6), firstPlayerTile);
		//Act
		player1.placeTile(board, new Position(4, 6), 1);
		
		//Assert
		assertEquals(2,player1.getBonus());
	}
	
	@Test
	void playerGet4BonusPointByPuttingTileBetween4Tiles() {
		//Arrange
		Tile firstPlayerTile = player1.getTileOfTheRack(1);
		board.putIn(ConstantPosition.CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.RIGHT_CENTER.pos(), firstPlayerTile);
		board.putIn(ConstantPosition.TOP_CENTER.pos(), firstPlayerTile);
		board.putIn(new Position(3,5), firstPlayerTile);
		board.putIn(new Position(3, 6), firstPlayerTile);
		board.putIn(new Position(3, 7), firstPlayerTile);
		board.putIn(new Position(4, 7), firstPlayerTile);
		
		//Act
		player1.placeTile(board, new Position(4, 6), 1);
		
		//Assert
		assertEquals(4,player1.getBonus());
	}
}
