package latice.application.controller;

import java.io.File;
import java.net.URI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import latice.application.view.Mainjavafx;

public class ImageViewController implements EventHandler<MouseEvent> {

	private ImageView imgV;
	private ImageView imgV2;
	private ImageView imgV3;
	private ImageView imgV4;
	private BackgroundImage bgi;
	private BorderPane root;
	private Image imgbg;
	private static String uriStringMusic;
	private String music;
	private MediaPlayer mdp;
	private static MediaPlayer mdpIndependante;
	private ProgressBar pgb;
	private static int nbr = 0;
	
	public ImageViewController(ImageView imgV, ImageView imgV2, ImageView imgV3, ImageView imgV4, BackgroundImage bgi, Image imgbg, BorderPane root, String music, ProgressBar pgb, MediaPlayer mdp) {
		this.imgV = imgV;
		this.imgV2 = imgV2;
		this.imgV3 = imgV3;
		this.imgV4 = imgV4;
		this.bgi = bgi;
		this.root = root;
		this.imgbg = imgbg;
		this.music = music;
		this.pgb = pgb;
		this.mdp = mdp;
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
		
		bgi = new BackgroundImage(imgbg, null, null, null, null);
		root.setBackground(new Background(bgi));
		
		if (nbr == 0) {
			mdp.stop();
			nbr++;
		} else {
			mdpIndependante.stop();
		}
		
		uriStringMusic = new File(new File("").getAbsolutePath().concat("/Theme/"+music)).toURI().toString();
		mdpIndependante = new MediaPlayer(new Media(uriStringMusic));
		mdpIndependante.volumeProperty().bind(pgb.progressProperty());
		mdpIndependante.play();
	}
}
