package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ButtonControllerCloseApplication implements EventHandler<MouseEvent> {

	private BorderPane root;
	
	public ButtonControllerCloseApplication(BorderPane root) {
		this.root = root;
	}

	@Override
	public void handle(MouseEvent event) {
		((Stage) root.getScene().getWindow()).close();
	}
}