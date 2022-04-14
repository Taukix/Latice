package latice.application.controller;

import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

public class ButtonControllerProgressBarDown implements EventHandler<MouseEvent> {

	private ProgressBar pgb;
	private MediaPlayer media;
	
	public ButtonControllerProgressBarDown(ProgressBar pgb, MediaPlayer media) {
		this.pgb = pgb;
		this.media = media;
	}

	@Override
	public void handle(MouseEvent arg0) {
		try {
		if (this.pgb.getProgress() > 0) {
			this.pgb.setProgress(this.pgb.getProgress() - 0.1);
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}