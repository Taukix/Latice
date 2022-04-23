package latice.application.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import latice.application.view.Mainjavafx;

public class ButtonControllerSoundOn implements EventHandler<MouseEvent> {

	private ProgressBar pgb;
	private MediaPlayer media;
	private CheckBox chbx;
	private double volume;
	
	public ButtonControllerSoundOn(ProgressBar pgb, MediaPlayer media, CheckBox chbx) {
		this.pgb = pgb;
		this.media = media;
		this.chbx = chbx;
	}

	@Override
	public void handle(MouseEvent arg0) {
		try {
		if (chbx.isSelected()) {
			return;
		} else {
			volume = ButtonControllerSoundOff.getVolume();
			pgb.setProgress(volume);
			media.setVolume(volume);
		}}
		catch(Exception e) {
			return;
		}
}}


