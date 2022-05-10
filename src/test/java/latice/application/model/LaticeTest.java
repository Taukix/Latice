package latice.application.model;

import org.junit.jupiter.api.BeforeEach;

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
}
