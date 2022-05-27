package latice.application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import latice.application.model.Constants;
import latice.application.model.Game;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class ButtonControllerEndTurn implements EventHandler<MouseEvent> {
	private Game game;
	private GridPane gpRack1;
	private GridPane gpRack2;
	private GridPane gpGame;
	public static Label lblWinner;
	public static HBox hbWinner;
	public static VBox vbWinner;
	private Button btnQuitEndingScene;
	private BorderPane root;
	public static DropShadow shadowRack;
	public static MediaPlayer mediaFillRackEffect;
	
	
	public ButtonControllerEndTurn(Game game, GridPane gpRack1, GridPane gpRack2, GridPane gpGame, Button btnQuitEndingScene, BorderPane root) {
		this.game = game;
		this.gpRack1 = gpRack1;
		this.gpRack2 = gpRack2;
		this.gpGame = gpGame;
		this.btnQuitEndingScene = btnQuitEndingScene;
		this.root = root;
		
		shadowRack = new DropShadow();
		shadowRack.setRadius(40);
		shadowRack.setColor(Color.YELLOW);
		
		hbWinner = new HBox(50);
		vbWinner = new VBox(200);
		
		lblWinner = new Label(Game.winner);
        lblWinner.setFont(new Font("Calibri", 40));
        lblWinner.setTextFill(Color.WHITESMOKE);
        lblWinner.setEffect(Mainjavafx.yellowShadow);
	}

	@Override
	public void handle(MouseEvent event) {
		this.game.nextTurn(this.game.getPlayer1(), this.game.getPlayer2());
		
		if (this.game.getNumberOfTurn() >= 0) {
			Mainjavafx.lblNumberOfTurn.setText("Nombre de tour(s) restant(s) : " + this.game.getNumberOfTurn());			
		}
		
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
		
		if (Game.winner != "") {
			// Changement de scene en fin de partie
			lblWinner.setText(Game.winner);
			
			hbWinner.getChildren().addAll(lblWinner, gpGame);
	        hbWinner.setAlignment(Pos.CENTER);

	        vbWinner.getChildren().addAll(hbWinner, btnQuitEndingScene);
	        vbWinner.setAlignment(Pos.CENTER);
            
			root.setCenter(vbWinner);
			root.setTop(null);
			root.setLeft(null);
			root.setRight(null);
			root.setBottom(null);
		}
}}
