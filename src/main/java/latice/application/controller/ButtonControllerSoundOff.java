package latice.application.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import latice.application.view.Mainjavafx;

public class ButtonControllerSoundOff implements EventHandler<MouseEvent> {

	private Slider sld;
	private MediaPlayer media;
	private CheckBox chbx;
	private static double volume;
	
	public ButtonControllerSoundOff(Slider sld, MediaPlayer media, CheckBox chbx) {
		this.sld = sld;
		this.media = media;
		this.chbx = chbx;
	}

	@Override
	public void handle(MouseEvent arg0) {
		try {
		if (chbx.isSelected()) {
			volume = sld.getValue();
			sld.setValue(0);
		}}
		catch(Exception e) {
			return;
		}}
		
	public static double getVolume() {
		return volume;
	}
}

