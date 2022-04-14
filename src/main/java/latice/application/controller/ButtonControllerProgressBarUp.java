package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

public class ButtonControllerProgressBarUp implements EventHandler<MouseEvent> {

	private ProgressBar pgb;
	private MediaPlayer media;
	private CheckBox chbx;
	
	public ButtonControllerProgressBarUp(ProgressBar pgb, MediaPlayer media, CheckBox chbx) {
		this.pgb = pgb;
		this.media = media;
		this.chbx = chbx;
	}

	@Override
	public void handle(MouseEvent arg0) {
		if (chbx.isSelected()) {
			return;
		} else if (pgb.getProgress() < 1) {
		pgb.setProgress(pgb.getProgress() + 0.1);
	}
	
}}
