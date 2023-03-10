package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ButtonControllerShadowOn implements EventHandler<MouseEvent> {

	private Button btnShadow;
	
	public ButtonControllerShadowOn(Button btnShadow) {
		this.btnShadow = btnShadow;
	}
	
	
	@Override
	public void handle(MouseEvent event) {
		//SHADOWS BUTTONS
		DropShadow shadow = new DropShadow();
		shadow.setRadius(20);
		shadow.setColor(Color.WHITESMOKE);
		
		btnShadow.setEffect(shadow);
	}

		
}
