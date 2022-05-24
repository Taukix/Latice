package latice.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LaticeTest {
	private Game game;
	private static Player player1;
	private static Player player2;
	
	private static final Position _2_4 = new Position(2, 4);
	private static final Position _1_1 = new Position(1, 1);
	private static final Position LAST_POSITION = new Position(Constants.BOARD_SIZE, Constants.BOARD_SIZE);
	
	static final Position BOTTOM_CENTER = new Position(6,5);
	static final Position TOP_CENTER = new Position(4,5);
	static final Position LEFT_CENTER = new Position(5,4);
	static final Position RIGHT_CENTER = new Position(5,6);
	private static final Tile redFlower = new Tile(ColorTile.RED, Shape.FLOWER);
	private static final Tile blueFlower = new Tile(ColorTile.BLUE, Shape.FLOWER);
	private static final Tile redDolphin = new Tile(ColorTile.RED, Shape.DOLPHIN);
	private static final Tile blueDolphin = new Tile(ColorTile.BLUE, Shape.DOLPHIN);
	
	@BeforeEach
	public void cleanGame() {
		player1 = new Player("player1");
		player2 = new Player("player2");
		game = new Game(player1, player2);
	}
	
	@Test
	public void PlayerGotHisTilesWhenTheGameStarts() {
		assertEquals(5, player1.getRack().countTilesInRack());
	}
	
	@Test
	public void returnFalseWhenPositionIsWithoutTile() {
		assertFalse(game.getBoard().tileAt(LAST_POSITION));
		assertFalse(game.getBoard().tileAt(Constants.CENTER));
		assertFalse(game.getBoard().tileAt(_1_1));
		assertFalse(game.getBoard().tileAt(_2_4));
		assertFalse(game.getBoard().tileAt(RIGHT_CENTER));
		assertFalse(game.getBoard().tileAt(BOTTOM_CENTER));
		assertFalse(game.getBoard().tileAt(LEFT_CENTER));
		assertFalse(game.getBoard().tileAt(TOP_CENTER));
	}
	
	@Test
	public void boardIsEmptyAtTheBeginningOfGame() {
		assertTrue(game.getBoard().getTiles().isEmpty());
	}
	
	@Test
	public void NumberOfTilesInStackOfEachPlayerWhenGameStartsShouldBe31() {
		assertEquals(31, player1.countTilesInStack());
		assertEquals(31, player2.countTilesInStack());
	}
	
	@Test
	public void player1RemovesTilesOnHisRackByPlayingThemOnTheBoard() {
		player1.startTurn();
		player1.placeTile(game.getBoard(), _1_1, 0);
		player1.placeTile(game.getBoard(), _2_4, 0);
		player1.placeTile(game.getBoard(), LAST_POSITION, 0);
		assertEquals(2,player1.getRack().getTiles().size());
	}
	

	@Test
	public void player1PutHisFirstTileOn_1_1() {
		//Arrange
		player1.startTurn();
		Tile placedTile = player1.getRack().getTiles().get(1);
		
		//Act
		player1.placeTile(game.getBoard(), _1_1, 1);
		
		//Assert
		assertThat(game.getBoard().getTileAt(_1_1)).isEqualTo(placedTile);
		//assertNotEquals(placedTile, player1.getRack().getTiles().get(1));
		//assertEquals(2, player1.getScore());
	}
	
	@Test
	public void get_shape_of_a_tile_based_on_a_new_position() {
		// TODO
	}
	
	@Test
	public void tile_at_position_already_used() {
		game.getBoard().putIn(Constants.CENTER, blueDolphin);
		assertTrue(game.getBoard().tileAt(Constants.CENTER));
		assertFalse(game.getBoard().tileAt(LAST_POSITION));
	}
	
	@Test
	public void get_tile_at_position_aldready_used() {
		game.getBoard().putIn(Constants.CENTER, blueDolphin);
		System.out.println(game.getBoard().getTileAt(new Position(Constants.CENTER.x(), Constants.CENTER.y())));
		System.out.println(game.getBoard().getTileAt(Constants.CENTER));
		assertEquals(blueDolphin, game.getBoard().getTileAt(new Position(Constants.CENTER.x(), Constants.CENTER.y())));
		assertEquals(null, game.getBoard().getTileAt(LAST_POSITION));
	}
	
	/*
	@Test
	public void player1_place_a_second_tile() {
		//Arrange
		game.getBoard().putIn(new Position(CENTER.x(), CENTER.y()),redFlower);
		
		//Act

		//Assert
		assertTrue(game.getPlayer1().canPlaceTileAt(game, LEFT_CENTER, redDolphin));
		assertTrue(game.getPlayer1().canPlaceTileAt(game, LEFT_CENTER, blueFlower));
		System.out.println("------");
		assertFalse(game.getPlayer1().canPlaceTileAt(game, LEFT_CENTER, blueDolphin));
		//assertFalse(game.getPlayer1().canPlaceTileAt(game, TOP_CENTER, blueDolphin));
		//assertFalse(game.getPlayer1().canPlaceTileAt(game, RIGHT_CENTER, blueDolphin));
		//assertFalse(game.getPlayer1().canPlaceTileAt(game, BOTTOM_CENTER, blueDolphin));

	}*/
	
	@Test
	public void player1EndHisTurnAndPlayer2PlaceATileOn_2_4() {
		//Arrange
		player1.endTurn();
		player2.startTurn();
		//Act
		player1.placeTile(game.getBoard(), _1_1, 1);
		player2.placeTile(game.getBoard(), _2_4, 1);
		//Assert
		assertThat(game.getBoard().getTileAt(_1_1)).isNull();;
		
		assertThat(game.getBoard().getTileAt(_2_4)).isNotNull().isInstanceOf(Tile.class);
	}
	
	@Test
	public void player1GotHisTilesAfterHisTurnAndAfterPlayingTiles() {
		player1.startTurn();
		player1.placeTile(game.getBoard(), _1_1, 4);
		assertEquals(4,player1.getRack().getTiles().size());
		player1.placeTile(game.getBoard(), _2_4, 3);
		assertEquals(3,player1.getRack().getTiles().size());
		player1.endTurn();
		assertEquals(5,player1.getRack().getTiles().size());
	}
	
	@Test
	public void theBoardIsEmptyWhenItIsCleared() {
		game.getBoard().putIn(Constants.CENTER, blueDolphin);
		game.getBoard().putIn(BOTTOM_CENTER, blueFlower);
		assertFalse(game.getBoard().getTiles().isEmpty());
		
		game.getBoard().getTiles().clear();
		assertTrue(game.getBoard().getTiles().isEmpty());
	}
	
	@Test
	public void player1GotVictoryWhenHeHasNoTilesLeftInStackAndRack() {
		System.out.println(player1.countTilesInStack());
		player1.startTurn();
		for (int i=0;i<7;i++) {
			for (int j=4;j>=0;j--) {
				player1.placeTile(game.getBoard(), new Position(j,i), j);
			}
			player1.refreshRack();
		}
		player1.placeTile(game.getBoard(), LAST_POSITION, 0);
		System.out.println(player1.getRack().getTiles().size());
		assertEquals(true, game.playerWon(player1, player2));
	}
	
	@Test
	public void player2ShouldNotPlayWhenItIsNotHisTurn() {
		player1.startTurn();
		player2.placeTile(game.getBoard(), LAST_POSITION, 0);
		assertEquals(5,player2.getRack().getTiles().size());
	}
	
	@Test
	public void playerWhoCanPlayChangeAfterEachTurn() {
		player1.startTurn();
		game.nextTurn(player1, player2);
		assertEquals(false,player1.getTurn());
		assertEquals(true,player2.getTurn());
	}
}
