package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ButtonControllerParametersMenu implements EventHandler<MouseEvent> {

	private Button btnActivated;
	private Button btnDesactivated1;
	private Button btnDesactivated2;
	private GridPane gp;
	private GridPane SecondGp;
	private GridPane gp3;
	
	public ButtonControllerParametersMenu(Button btnActivated, Button btnDesactivated1, Button btnDesactivated2,
			GridPane gp, GridPane secondGp, GridPane gp3) {
		this.btnActivated = btnActivated;
		this.btnDesactivated1 = btnDesactivated1;
		this.btnDesactivated2 = btnDesactivated2;
		this.gp = gp;
		this.SecondGp = secondGp;
		this.gp3 = gp3;
	}
	
	@Override
	public void handle(MouseEvent event) {
		btnActivated.setStyle("-fx-background-color: #DCDCDC");
		btnDesactivated1.setStyle("-fx-background-color: #FFF");
		btnDesactivated2.setStyle("-fx-background-color: #FFF");
		gp.setVisible(true);
		SecondGp.setVisible(false);
		gp3.setVisible(false);
		
	}

}
