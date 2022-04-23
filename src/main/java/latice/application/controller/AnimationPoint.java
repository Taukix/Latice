package latice.application.controller;

import javafx.scene.control.Label;

public class AnimationPoint {
	public Label lbl;
	public boolean bool;

	public AnimationPoint(Label lbl, boolean bool) {
		this.lbl = lbl;
		this.bool = bool;
	}
	
	public static void PointAnimationLoadingScene(Label lbl, boolean bool) {
		lbl.setVisible(bool);
	}
}
