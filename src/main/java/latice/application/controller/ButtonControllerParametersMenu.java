package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ButtonControllerParametersMenu implements EventHandler<MouseEvent> {

	private Button btnActivated;
	private Button btnDesactivated1;
	private GridPane gp;
	private GridPane SecondGp;
	
	public ButtonControllerParametersMenu(Button btnActivated, Button btnDesactivated1, GridPane gp, GridPane secondGp) {
		this.btnActivated = btnActivated;
		this.btnDesactivated1 = btnDesactivated1;
		this.gp = gp;
		this.SecondGp = secondGp;
	}
	
	@Override
	public void handle(MouseEvent event) {
		btnActivated.setStyle("-fx-background-color: #DCDCDC");
		btnDesactivated1.setStyle("-fx-background-color: #FFF");
		gp.setVisible(true);
		SecondGp.setVisible(false);
	}

}
