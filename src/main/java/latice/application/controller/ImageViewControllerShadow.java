package latice.application.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import latice.application.view.Mainjavafx;

public class ImageViewControllerShadow implements EventHandler<MouseEvent> {

	private ImageView imgV;
	private ImageView imgV2;
	private ImageView imgV3;
	private ImageView imgV4;
	
	public ImageViewControllerShadow(ImageView imgV, ImageView imgV2, ImageView imgV3, ImageView imgV4) {
		this.imgV = imgV;
		this.imgV2 = imgV2;
		this.imgV3 = imgV3;
		this.imgV4 = imgV4;
	}

	@Override
	public void handle(MouseEvent event) {
		//SHADOWS BUTTONS
		DropShadow shadow = new DropShadow();
		shadow.setRadius(20);
		shadow.setColor(Color.BLACK);
		
		imgV.setEffect(shadow);
		imgV2.setEffect(null);
		imgV3.setEffect(null);
		imgV4.setEffect(null);
	}

		
}
