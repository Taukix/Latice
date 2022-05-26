package latice.application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import latice.application.model.Constants;
import latice.application.model.Game;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class ButtonControllerEndTurn implements EventHandler<MouseEvent> {
	private Game game;
	private GridPane gpRack1;
	private GridPane gpRack2;
	private GridPane gpGame;
	public static DropShadow shadowRack;
	public static MediaPlayer mediaFillRackEffect;
	
	
	public ButtonControllerEndTurn(Game game, GridPane gpRack1, GridPane gpRack2, GridPane gpGame) {
		this.game = game;
		this.gpRack1 = gpRack1;
		this.gpRack2 = gpRack2;
		this.gpGame = gpGame;
		
		shadowRack = new DropShadow();
		shadowRack.setRadius(40);
		shadowRack.setColor(Color.YELLOW);
	}

	@Override
	public void handle(MouseEvent event) {
		this.game.nextTurn(this.game.getPlayer1(), this.game.getPlayer2());
		
		// TOUR DU JOUEUR 1
		if (this.game.getPlayer1().getTurn() == false) {
			Mainjavafx.nbrTilesInStack1.setText("Nombre de tuiles restantes: " + game.getPlayer1().getStack().size());
			
			if (gpRack1.getChildren().size() < 5) {
				mediaFillRackEffect = new MediaPlayer(new Media(new File(new File("").getAbsolutePath().concat("/SoundEffect/FillRackSoundEffect.mp3")).toURI().toString()));
				mediaFillRackEffect.volumeProperty().bind(Mainjavafx.pgbSoundEffect.progressProperty());
				mediaFillRackEffect.play();
			}
			
			// On vide le RackFX pour le remplir avec les tuiles qui ont été rajouté
			for (int i=gpRack1.getChildren().size()-1;i>=0;i--) {
				this.gpRack1.getChildren().remove(i);
			}
			
			for (int i=0;i<this.game.getPlayer1().getRack().getTiles().size();i++) {
				try {
					TileFx tileOfRack1 = new TileFx(this.game.getPlayer1().getRack().getTiles().get(i), Mainjavafx.theme);
					DndTileFx.manageSourceDragAndDrop(tileOfRack1, game, gpRack1, gpRack2, gpGame);
					this.gpRack1.getChildren().add(tileOfRack1.getImageView());
					this.gpRack1.setColumnIndex(tileOfRack1.getImageView(), i);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			gpRack1.setEffect(null);
			gpRack2.setEffect(shadowRack);
		
		// TOUR DU JOUEUR 2
			} else {
			this.game.getPlayer2().refreshRack();
			Mainjavafx.nbrTilesInStack2.setText("Nombre de tuiles restantes: " + game.getPlayer2().getStack().size());
			
			if (gpRack2.getChildren().size() < 5) {
				mediaFillRackEffect = new MediaPlayer(new Media(new File(new File("").getAbsolutePath().concat("/SoundEffect/FillRackSoundEffect.mp3")).toURI().toString()));
				mediaFillRackEffect.volumeProperty().bind(Mainjavafx.pgbSoundEffect.progressProperty());
				mediaFillRackEffect.play();
			}
			
			// On vide le RackFX pour le remplir avec les tuiles qui ont été rajouté
			for (int i=gpRack2.getChildren().size()-1;i>=0;i--) {
				gpRack2.getChildren().remove(i);
			}

			// Remplissage
			for (int i=0;i<this.game.getPlayer2().getRack().getTiles().size();i++) {
				try {
					TileFx tileOfRack2 = new TileFx(this.game.getPlayer2().getRack().getTiles().get(i), Mainjavafx.theme);
					DndTileFx.manageSourceDragAndDrop(tileOfRack2, game, gpRack1, gpRack2, gpGame);
					this.gpRack2.getChildren().add(tileOfRack2.getImageView());
					this.gpRack2.setColumnIndex(tileOfRack2.getImageView(), i);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			gpRack2.setEffect(null);
			gpRack1.setEffect(shadowRack);
			}
		
		game.playerWon(game.getPlayer1(), game.getPlayer2());
}}
