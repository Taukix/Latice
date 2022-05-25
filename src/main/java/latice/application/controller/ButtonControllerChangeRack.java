package latice.application.controller;

import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import latice.application.model.Game;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class ButtonControllerChangeRack implements EventHandler<MouseEvent> {
	private Game game;
	private GridPane gpRack1;
	private GridPane gpRack2;
	private GridPane gpGame;
	
	public ButtonControllerChangeRack(Game game, GridPane gpRack1, GridPane gpRack2, GridPane gpGame) {
		this.game = game;
		this.gpRack1 = gpRack1;
		this.gpRack2 = gpRack2;
		this.gpGame = gpGame;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (game.getPlayer1().getTurn() && !game.getPlayer1().getConsumedTurn()) {
			game.getPlayer1().changeRack();
			
			// On vide le Rack JavaFX
			for (int i=gpRack1.getChildren().size()-1;i>=0;i--) {
				gpRack1.getChildren().remove(i);
			}
			
			// On le remplit avec les nouvelles tuiles
			for (int j=0;j<game.getPlayer1().getRack().getTiles().size();j++) {
				TileFx tileAfterChangeRack;
				try {
					tileAfterChangeRack = new TileFx(game.getPlayer1().getRack().getTiles().get(j), Mainjavafx.theme);
					DndTileFx.manageSourceDragAndDrop(tileAfterChangeRack, game, gpRack1, gpRack2, gpGame);
					gpRack1.getChildren().add(tileAfterChangeRack.getImageView());
					gpRack1.setColumnIndex(tileAfterChangeRack.getImageView(), j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			game.nextTurn(game.getPlayer1(), game.getPlayer2());
			
			gpRack1.setEffect(null);
			gpRack2.setEffect(ButtonControllerEndTurn.shadowRack);
			
		} else if (game.getPlayer2().getTurn() && !game.getPlayer2().getConsumedTurn()){
			game.getPlayer2().changeRack();
			
			// On vide le Rack JavaFX
			for (int i=gpRack2.getChildren().size()-1;i>=0;i--) {
				gpRack2.getChildren().remove(i);
			}
						
			// On le remplit avec les nouvelles tuiles
			for (int j=0;j<game.getPlayer2().getRack().getTiles().size();j++) {
				TileFx tileAfterChangeRack;
				try {
					tileAfterChangeRack = new TileFx(game.getPlayer2().getRack().getTiles().get(j), Mainjavafx.theme);
					DndTileFx.manageSourceDragAndDrop(tileAfterChangeRack, game, gpRack1, gpRack2, gpGame);
					gpRack2.getChildren().add(tileAfterChangeRack.getImageView());
					gpRack2.setColumnIndex(tileAfterChangeRack.getImageView(), j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			game.nextTurn(game.getPlayer1(), game.getPlayer2());
						
			gpRack2.setEffect(null);
			gpRack1.setEffect(ButtonControllerEndTurn.shadowRack);	
		}
	}

}
