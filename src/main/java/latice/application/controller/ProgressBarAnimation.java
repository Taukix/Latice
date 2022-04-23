package latice.application.controller;

import javafx.scene.control.ProgressBar;

public class ProgressBarAnimation {
	public ProgressBar pgb;

	public ProgressBarAnimation(ProgressBar pgb) {
		this.pgb = pgb;
	}
	
	public static void increaseProgress(ProgressBar pgb) {
		pgb.setProgress(pgb.getProgress() + 0.0013332);
	}}
