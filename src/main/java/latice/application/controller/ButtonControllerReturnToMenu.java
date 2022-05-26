package latice.application.controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ButtonControllerReturnToMenu implements EventHandler<MouseEvent> {
	private BorderPane root;
	private VBox vbCenterMainMenu;
	private VBox vbTopMainMenu;
	
	public ButtonControllerReturnToMenu(BorderPane root, VBox vbCenterMainMenu, VBox vbTopMainMenu) {
		this.root = root;
		this.vbCenterMainMenu = vbCenterMainMenu;
		this.vbTopMainMenu = vbTopMainMenu;
	}

	@Override
	public void handle(MouseEvent event) {
		root.setTop(vbTopMainMenu);
		root.setLeft(null);
		root.setRight(null);
		root.setBottom(null);
		root.setCenter(vbCenterMainMenu);
		
		root.setMargin(vbTopMainMenu, new Insets(20,0,0,0));
	}

}
