package latice.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LaticeTest {
	private Game game;
	private static Player player1;
	private static Player player2;
	
	private static final Position _2_4 = new Position(2, 4);
	private static final Position _1_1 = new Position(1, 1);
	private static final Position LAST_POSITION = new Position(Constants.BOARD_SIZE, Constants.BOARD_SIZE);
	
	@BeforeEach
	public void cleanGame() {
		player1 = new Player("Player1");
		player2 = new Player("Player2");
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
	public void player1RemovesTilesOnHisRackByPlayingThemOnTheBoard() {
		player1.startTurn();
		player1.placeTile(game, _1_1, 0);
		player1.placeTile(game, _2_4, 0);
		player1.placeTile(game, LAST_POSITION, 0);
		assertEquals(2,player1.getRack().getTiles().size());
	}

	@Test
	public void player1PutHisFirstTileOn_1_1() {
		player1.startTurn();
		Tile placedTile = player1.getRack().getTiles().get(1);
		assertTrue(player1.placeTile(game, _1_1, 1));
		assertNotEquals(placedTile, player1.getRack().getTiles().get(1));
		assertEquals(2, player1.getScore());
	}
	
	@Test
	public void player1EndHisTurnAndPlayer2PlaceATileOn_2_4() {
		player1.endTurn();
		player2.startTurn();
		assertEquals(false,player1.placeTile(game, _1_1, 1));
		assertEquals(true,player2.placeTile(game, _1_1, 1));
	}
	
	@Test
	public void player1GotHisTilesAfterHisTurnAndAfterPlayingTiles() {
		player1.startTurn();
		player1.placeTile(game, _1_1, 4);
		assertEquals(4,player1.getRack().getTiles().size());
		player1.placeTile(game, _2_4, 3);
		assertEquals(3,player1.getRack().getTiles().size());
		player1.refreshRack();
		assertEquals(5,player1.getRack().getTiles().size());
	}
	
	@Test
	public void player1GotVictoryWhenHeHasNoTilesLeftInStackAndRack() {
		System.out.println(player1.countTilesInStack());
		player1.startTurn();
		for (int i=0;i<7;i++) {
			for (int j=4;j>=0;j--) {
				player1.placeTile(game, new Position(j,i), j);
			}
			player1.refreshRack();
		}
		player1.placeTile(game, LAST_POSITION, 0);
		System.out.println(player1.getRack().getTiles().size());
		assertEquals(true, game.playerWon(player1, player2));
	}
	
	@Test
	public void player1ShouldNotPlayTilesInSamePlaceOnBoard() {
		player1.startTurn();
		player1.placeTile(game, LAST_POSITION, 0);
		player1.placeTile(game, LAST_POSITION, 0);
		assertEquals(4,player1.getRack().getTiles().size());
	}
	
	@Test
	public void player2ShouldNotPlayWhenItIsNotHisTurn() {
		player1.startTurn();
		player2.placeTile(game, LAST_POSITION, 0);
		assertEquals(5,player2.getRack().getTiles().size());
	}
	
	@Test
	public void playerWhoCanPlayChangeAfterEachTurn() {
		player1.startTurn();
		game.nextTurn(player1, player2);
		assertEquals(false,player1.turn);
		assertEquals(true,player2.turn);
	}
}
