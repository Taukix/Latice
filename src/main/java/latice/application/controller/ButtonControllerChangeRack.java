package latice.application.controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import latice.application.model.Game;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class ButtonControllerChangeRack implements EventHandler<MouseEvent> {
	private Game game;
	private GridPane gpRack1;
	private GridPane gpRack2;
	private GridPane gpGame;
	private Button btnQuitEndingScene;
	private BorderPane root;
	
	public ButtonControllerChangeRack(Game game, GridPane gpRack1, GridPane gpRack2, GridPane gpGame, Button btnQuitEndingScene, BorderPane root) {
		this.game = game;
		this.gpRack1 = gpRack1;
		this.gpRack2 = gpRack2;
		this.gpGame = gpGame;
		this.btnQuitEndingScene = btnQuitEndingScene;
		this.root = root;		
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (game.getPlayer1().changeRack()) {
			Mainjavafx.nbrBonusPoint1.setText("Point(s) Bonus : " + game.getPlayer1().getBonus());
			
			ButtonControllerEndTurn.mediaFillRackEffect = new MediaPlayer(new Media(new File(new File("").getAbsolutePath().concat("/SoundEffect/FillRackSoundEffect.mp3")).toURI().toString()));
			ButtonControllerEndTurn.mediaFillRackEffect.volumeProperty().bind(Mainjavafx.pgbSoundEffect.progressProperty());
			ButtonControllerEndTurn.mediaFillRackEffect.play();
			
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
			
			if (this.game.getNumberOfTurn() >= 0) {
				Mainjavafx.lblNumberOfTurn.setText("Nombre de tour(s) restant(s) : " + this.game.getNumberOfTurn());			
			}
			
			gpRack1.setEffect(null);
			gpRack2.setEffect(ButtonControllerEndTurn.shadowRack);
			
		} else if (game.getPlayer2().changeRack()) {
			
			Mainjavafx.nbrBonusPoint2.setText("Point(s) Bonus : " + game.getPlayer2().getBonus());
			
			ButtonControllerEndTurn.mediaFillRackEffect = new MediaPlayer(new Media(new File(new File("").getAbsolutePath().concat("/SoundEffect/FillRackSoundEffect.mp3")).toURI().toString()));
			ButtonControllerEndTurn.mediaFillRackEffect.volumeProperty().bind(Mainjavafx.pgbSoundEffect.progressProperty());
			ButtonControllerEndTurn.mediaFillRackEffect.play();
			
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
			
			if (this.game.getNumberOfTurn() >= 0) {
				Mainjavafx.lblNumberOfTurn.setText("Nombre de tour(s) restant(s) : " + this.game.getNumberOfTurn());			
			}
						
			gpRack2.setEffect(null);
			gpRack1.setEffect(ButtonControllerEndTurn.shadowRack);
		}
		
		if (Game.winner != "") {
			// Changement de scene en fin de partie
			ButtonControllerEndTurn.lblWinner.setText(Game.winner);
				
			ButtonControllerEndTurn.hbWinner.getChildren().addAll(ButtonControllerEndTurn.lblWinner, gpGame);
		    ButtonControllerEndTurn.hbWinner.setAlignment(Pos.CENTER);
		    
		    ButtonControllerEndTurn.vbWinner.getChildren().addAll(ButtonControllerEndTurn.hbWinner, btnQuitEndingScene);
		    ButtonControllerEndTurn.vbWinner.setAlignment(Pos.CENTER);
            
			root.setCenter(ButtonControllerEndTurn.vbWinner);
			root.setTop(null);
			root.setLeft(null);
			root.setRight(null);
			root.setBottom(null);
		}
	}

}
