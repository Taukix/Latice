package latice.application.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LaticeTest {
	Game game;
	
	private static final Position _2_4 = new Position(2, 4);
	private static final Position _1_1 = new Position(1, 1);
	private static final Position LAST_POSITION = new Position(Constants.BOARD_SIZE, Constants.BOARD_SIZE);
	private static final Player player1 = new Player("Player1");
	private static final Player player2 = new Player("Player2");

	
	@BeforeEach
	public void cleanGame() {
		game = new Game(player1, player2);
	}
	
	@Test
	public void PlayerGotHisTilesWhenTheGameStarts() {
		assertEquals(5, player1.getRack().countTilesInRack());
	}
	
	@Test
	public void NumberOfTilesInStackOfEachPlayerWhenGameStartsShouldBe31() {
		assertEquals(31, player1.countTilesInStack());
		assertEquals(31, player2.countTilesInStack());
	}

	@Test
	public void player1PutHisFirstTileOn_1_1() {
		Tile placedTile = player1.getRack().getTiles().get(1);
		player1.placeTile(game, _1_1, 1);
		assertEquals(placedTile, game.getBoard().cells.get(_1_1).getTile());
	}
}
