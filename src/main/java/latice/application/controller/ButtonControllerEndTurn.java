package latice.application.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import latice.application.model.Constants;
import latice.application.model.Game;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class ButtonControllerEndTurn implements EventHandler<MouseEvent> {
	private Game game;
	private GridPane gp1;
	private GridPane gp2;
	private ArrayList<TileFx> list1;
	private ArrayList<TileFx> list2;
	
	public ButtonControllerEndTurn(Game game, GridPane gp1, GridPane gp2, ArrayList<TileFx> list1, ArrayList<TileFx> list2) {
		this.game = game;
		this.gp1 = gp1;
		this.gp2 = gp2;
		this.list1 = list1;
		this.list2 = list2;
	}

	@Override
	public void handle(MouseEvent event) {
		this.game.nextTurn(this.game.getPlayer1(), this.game.getPlayer2());
		
		DropShadow shadow = new DropShadow();
		shadow.setRadius(40);
		shadow.setColor(Color.YELLOW);
		
		
		if (this.game.getPlayer1().turn == false) {
			this.game.getPlayer1().refreshRack();
			Mainjavafx.nbrTilesInStack1.setText("Nombre de tuiles restantes: " + game.getPlayer1().getStack().size());
			
			for (int i=gp1.getChildren().size()-1;i>=0;i--) {
				gp1.getChildren().remove(i);
			}
			
			if (this.list1.size() != Constants.RACK_SIZE && this.game.getPlayer1().getStack().size() >= Constants.RACK_SIZE) {
				for (int i=this.list1.size();i<Constants.RACK_SIZE;i++) {
					try {
						TileFx tileOfRack1 = new TileFx(this.game.getPlayer1().getRack().getTiles().get(i), this.list1, this.list2, this.game);
						this.list1.add(tileOfRack1);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			} else if (this.game.getPlayer1().getStack().size() < 5) {
				for (int l=0;l<this.game.getPlayer1().getStack().size();l++) {
					try {
						TileFx tileOfRack2 = new TileFx(this.game.getPlayer1().getRack().getTiles().get(l), this.list1, this.list2, this.game);
						this.list1.add(tileOfRack2);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
			for (int k=0;k<list1.size();k++) {
				gp1.add(list1.get(k).getImageView(), k, 0);
			}
			
			gp1.setEffect(null);
			gp2.setEffect(shadow);
				
		} else {
			this.game.getPlayer2().refreshRack();
			Mainjavafx.nbrTilesInStack2.setText("Nombre de tuiles restantes: " + game.getPlayer2().getStack().size());
			
			for (int i=gp2.getChildren().size()-1;i>=0;i--) {
				gp2.getChildren().remove(i);
			}
				
			if (this.list2.size() != Constants.RACK_SIZE && this.game.getPlayer2().getStack().size() >= Constants.RACK_SIZE) {
				for (int i=this.list2.size();i<Constants.RACK_SIZE;i++) {
					try {
						TileFx tileOfRack2 = new TileFx(this.game.getPlayer2().getRack().getTiles().get(i), this.list1, this.list2, this.game);
						this.list2.add(tileOfRack2);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			} else if (this.game.getPlayer2().getStack().size() < 5) {
				for (int l=0;l<this.game.getPlayer2().getStack().size();l++) {
					try {
						TileFx tileOfRack2 = new TileFx(this.game.getPlayer2().getRack().getTiles().get(l), this.list1, this.list2, this.game);
						this.list2.add(tileOfRack2);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
			for (int k=0;k<list2.size();k++) {
				gp2.add(list2.get(k).getImageView(), k, 0);
			}
			
			gp2.setEffect(null);
			gp1.setEffect(shadow);
			}
		
		game.playerWon(game.getPlayer1(), game.getPlayer2());
}}
