package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SourdineOffWhenSliderProgress implements EventHandler<MouseEvent> {

	private CheckBox chbx;
	
	public SourdineOffWhenSliderProgress(CheckBox chbx) {
		this.chbx = chbx;
	}

	@Override
	public void handle(MouseEvent event) {
		chbx.setSelected(false);
	} 

}
