package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SliderProgressBarDown implements EventHandler<MouseEvent> {
	
	public Slider sld;
	public ProgressBar pgb;
	
	public SliderProgressBarDown(Slider sld, ProgressBar pgb) {
		this.sld = sld;
		this.pgb = pgb;
	}
	
	@Override
	public void handle(MouseEvent event) {
		
	}
}
