package latice.application.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	private static final Position _2_4 = new Position(2, 4);
	private static final Position _1_1 = new Position(1, 1);
	private static final Tile redFlower = new Tile(ColorTile.ONE, Shape.FIVE);
	private static final Tile blueFlower = new Tile(ColorTile.SIX, Shape.FIVE);
	private static final Tile redDolphin = new Tile(ColorTile.ONE, Shape.FOUR);
	private static final Tile blueDolphin = new Tile(ColorTile.SIX, Shape.FOUR);
	Board board;
	
	@BeforeEach
	void cleanGame() {
		board = new Board();
	}
	
	@Test
	void try_to_put_a_tile_not_in_center_on_empty_board_should_return_false() {
		//Arrange
		Tile tile = new Tile(ColorTile.SIX, Shape.FIVE);
		//Act
		boolean result = board.putIn(ConstantPosition.LAST_POSITION.pos(), tile);
		//Assert
		assertFalse(result);
	}
	
	@Test 
	void try_put_a_tile_in_center_on_empty_board_should_return_true() {
		//Arrange
		Tile tile = new Tile(ColorTile.SIX, Shape.FIVE);
		//Act
		boolean placeable = board.isPlaceable(ConstantPosition.CENTER.pos(), tile);
		boolean result = board.putIn(ConstantPosition.CENTER.pos(), tile);
		//Assert
		assertTrue(placeable);
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
	void returnFalseWhenATileCanNotPutBecauseThePositionIsOccupied() {
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
	void returnPositionsAroundASelectedOne() {
		//Arrange
		List<Position> nbTiles;
		//Act
		nbTiles = board.getNearbyPositions(ConstantPosition.CENTER.pos());
		
		//Assert
		assertThat(nbTiles).hasSize(4).contains(ConstantPosition.LEFT_CENTER.pos(),
												ConstantPosition.RIGHT_CENTER.pos(), 
												ConstantPosition.TOP_CENTER.pos(), 
												ConstantPosition.BOTTOM_CENTER.pos());
	}
	
	
	@Test 
	void try_put_a_second_tile_not_around_an_other_tile_should_return_false() {
		//Arrange
		Tile tile = new Tile(ColorTile.SIX, Shape.FIVE);
		board.putIn(ConstantPosition.CENTER.pos(), tile);
		//Act
		boolean result = board.putIn(ConstantPosition.LAST_POSITION.pos(), tile);
		//Assert
		assertFalse(result);
	}
	
	@Test
	void try_put_tile_near_an_other_tile_same_color_should_return_true() {
		//arrange
		Tile tileNotCenter = new Tile(ColorTile.SIX, Shape.FOUR);
		Tile centerTile = new Tile(ColorTile.SIX, Shape.ONE);
		board.putIn(ConstantPosition.CENTER.pos(), centerTile);
		//Act
		boolean result = board.putIn(ConstantPosition.RIGHT_CENTER.pos(), tileNotCenter);
		
		//Assert
		assertTrue(result);
	}

	
	@Test
	void put_tile_around_two_differents_tiles_one_same_shape_one_same_color() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(ConstantPosition.LEFT_CENTER.pos(), blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(ConstantPosition.TOP_CENTER.pos(), redDolphin);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	void put_tile_around_two_differents_tiles() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(ConstantPosition.LEFT_CENTER.pos(), blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(ConstantPosition.TOP_CENTER.pos(), redDolphin);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	void put_tile_around_two_differents_tiles_different_shape_and_color() {
		//Arrange
		board.putIn(ConstantPosition.CENTER.pos(), blueDolphin);
		board.putIn(ConstantPosition.LEFT_CENTER.pos(), blueFlower);
		board.putIn(new Position(4,4), redFlower);
		
		//Act
		boolean result = board.putIn(ConstantPosition.TOP_CENTER.pos(), new Tile(ColorTile.THREE, Shape.TWO));
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	void verify_if_tile_is_a_bonus() {
		//Arrange
		boolean up_right_diagonal = false;
		boolean isNotBonusPos = false;
		boolean isPosBonusVert = false;
		boolean up_left_diagonal = false;
		boolean border_bottom = false;
		boolean border_top = false;
		boolean border_right =false;
		
		//Act
		up_right_diagonal = board.isTileBonus(new Position(9, 1));
		isNotBonusPos = board.isTileBonus(new Position(2, 3));
		border_right = board.isTileBonus(new Position(9, 5));
		border_top = board.isTileBonus(new Position(5,1));
		border_bottom = board.isTileBonus(new Position(5,9));
		up_left_diagonal = board.isTileBonus(new Position(1,9));
		
		//Assert
		assertTrue(up_right_diagonal);
		assertTrue(up_left_diagonal);
		assertTrue(border_right);
		assertTrue(border_top);
		assertTrue(border_bottom);
		assertFalse(isNotBonusPos);
	}
}
