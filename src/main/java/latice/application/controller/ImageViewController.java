package latice.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import latice.application.view.Mainjavafx;

public class ImageViewController implements EventHandler<MouseEvent> {

	private ImageView imgV;
	private ImageView imgV2;
	private ImageView imgV3;
	private ImageView imgV4;
	private BackgroundImage bgiParameter;
	private BorderPane root;
	private Image imgbg;
	private static String uriStringMusic;
	private String music;
	private MediaPlayer mdp;
	private static MediaPlayer mdpIndependante;
	private ProgressBar pgb;
	private static int nbr = 0;
	private String theme;
	private GridPane gpGame;
	
	public ImageViewController(ImageView imgV, ImageView imgV2, ImageView imgV3, ImageView imgV4, BackgroundImage bgiParameter, Image imgbg, BorderPane root, String music, ProgressBar pgb, MediaPlayer mdp, String theme, GridPane gpGame) {
		this.imgV = imgV;
		this.imgV2 = imgV2;
		this.imgV3 = imgV3;
		this.imgV4 = imgV4;
		this.bgiParameter = bgiParameter;
		this.root = root;
		this.imgbg = imgbg;
		this.music = music;
		this.pgb = pgb;
		this.mdp = mdp;
		this.theme = theme;
		this.gpGame = gpGame;
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
		
		// Chargement du plateau en accord avec le thème
		System.out.println(theme);
		File fileImagePlate = new File(new File("").getAbsolutePath().concat("/Theme/" + theme + "/Plateau.png"));
		try {
			Image imgPlate = new Image(new FileInputStream(fileImagePlate));
			BackgroundImage bgiPlate = new BackgroundImage(imgPlate, null, null, null, null);
			gpGame.setBackground(new Background(bgiPlate));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		bgiParameter = new BackgroundImage(imgbg, null, null, null, null);
		root.setBackground(new Background(bgiParameter));
		
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
