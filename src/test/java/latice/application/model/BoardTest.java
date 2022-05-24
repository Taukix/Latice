package latice.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
	private static final Position _2_4 = new Position(2, 4);
	private static final Position _1_1 = new Position(1, 1);
	private static final Position LAST_POSITION = new Position(Constants.BOARD_SIZE.value(), Constants.BOARD_SIZE.value());
	static final Position BOTTOM_CENTER = new Position(6,5);
	static final Position TOP_CENTER = new Position(4,5);
	static final Position LEFT_CENTER = new Position(5,4);
	static final Position RIGHT_CENTER = new Position(5,6);
	private static final Tile redFlower = new Tile(ColorTile.RED, Shape.FLOWER);
	private static final Tile blueFlower = new Tile(ColorTile.BLUE, Shape.FLOWER);
	private static final Tile redDolphin = new Tile(ColorTile.RED, Shape.DOLPHIN);
	private static final Tile blueDolphin = new Tile(ColorTile.BLUE, Shape.DOLPHIN);
	Board board;
	
	@BeforeEach
	void cleanGame() {
		board = new Board();
	}
	
	@Test
	void try_to_put_a_tile_not_in_center_on_empty_board_should_return_false() {
		//Arrange
		Tile tile = new Tile(ColorTile.BLUE, Shape.FLOWER);
		//Act
		boolean result = board.putIn(LAST_POSITION, tile);
		//Assert
		assertFalse(result);
	}
	
	@Test 
	void try_put_a_tile_in_center_on_empty_board_should_return_true() {
		//Arrange
		Tile tile = new Tile(ColorTile.BLUE, Shape.FLOWER);
		//Act
		boolean result = board.putIn(ConstantPosition.CENTER.pos(), tile);
		//Assert
		assertTrue(result);
	}
	
	@Test
	void returnFalseWhenATileIsPutAtAPositionWithoutTileNeabyNotInTheMiddle() {
		//Arrange
		boolean tileWellPut;
		
		//Act
		tileWellPut = board.putIn(_2_4, redFlower);
		
		//Assert
		assertFalse(tileWellPut);
		assertTrue(board.getTiles().isEmpty());
		assertFalse(board.tileAt(_2_4));
	}
	
	@Test
	public void returnFalseWhenATileCanNotPutBecauseThePositionIsOccupied() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), redFlower);
		assertTrue(board.tileAt(ConstantPosition.CENTER.pos()));
		boolean tileWellPut;
		//Act
		tileWellPut = board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		//Assert
		assertFalse(tileWellPut);
		assertTrue(board.tileAt(ConstantPosition.CENTER.pos()));
	}
	
	@Test 
	void try_put_a_second_tile_not_around_an_other_tile_should_return_false() {
		//Arrange
		Tile tile = new Tile(ColorTile.BLUE, Shape.FLOWER);
		board.putIn(ConstantPosition.CENTER.pos(), tile);
		//Act
		boolean result = board.putIn(LAST_POSITION, tile);
		//Assert
		assertFalse(result);
	}
	
	@Test
	void try_put_tile_near_an_other_tile_same_color_should_return_true() {
		//arrange
		Tile tileNotCenter = new Tile(ColorTile.BLUE, Shape.DOLPHIN);
		Tile centerTile = new Tile(ColorTile.BLUE, Shape.BIRD);
		board.putIn(ConstantPosition.CENTER.pos(), centerTile);
		//Act
		boolean result = board.putIn(RIGHT_CENTER, tileNotCenter);
		
		//Assert
		assertTrue(result);
	}

	
	@Test
	void put_tile_around_two_differents_tiles_one_same_shape_one_same_color() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(LEFT_CENTER, blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(TOP_CENTER, redDolphin);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	void put_tile_around_two_differents_tiles() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(LEFT_CENTER, blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(TOP_CENTER, redDolphin);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	void put_tile_around_two_differents_tiles_different_shape_and_color() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(LEFT_CENTER, blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(TOP_CENTER, new Tile(ColorTile.GREEN, Shape.FEATHER));
		
		//Assert
		assertFalse(result);
	}
}
