package latice.application.controller;

import java.io.FileNotFoundException;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import latice.application.model.Constants;
import latice.application.model.Game;
import latice.application.view.TileFx;

public class ButtonControllerEndTurn implements EventHandler<MouseEvent> {
	private Button btn;
	private Game game;
	private GridPane gp1;
	private GridPane gp2;
	private ArrayList<TileFx> list1;
	private ArrayList<TileFx> list2;
	
	public ButtonControllerEndTurn(Button btn, Game game, GridPane gp1, GridPane gp2, ArrayList<TileFx> list1, ArrayList<TileFx> list2, GridPane gpRackOfPlayer1, GridPane gpRackOfPlayer2) {
		this.btn = btn;
		this.game = game;
		this.gp1 = gp1;
		this.gp2 = gp2;
		this.list1 = list1;
		this.list2 = list2;
	}

	@Override
	public void handle(MouseEvent event) {
		game.nextTurn(game.getPlayer1(), game.getPlayer2());
		
		System.out.println(list1);
		System.out.println(game.getPlayer1().getRack().getTiles());

		DropShadow shadow = new DropShadow();
		shadow.setRadius(40);
		shadow.setColor(Color.YELLOW);
			
		if (game.getPlayer1().turn == false) {
			game.getPlayer1().refreshRack();
		}
}}
