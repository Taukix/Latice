package latice.application.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import latice.application.model.Game;
import latice.application.model.Player;
import latice.application.model.Tile;

public class TileFx {
	private Tile tile;
	private Image img;
	private ImageView imgTile;
	private Game game;
	
	public TileFx(Tile tile, Game game) throws FileNotFoundException {
		this.tile = tile;
		this.img = new Image(new FileInputStream(new File(new File("").getAbsolutePath().concat("/Theme/Plage/Tuile/" + tile.getShape() + "/" + tile.getColor() + ".png"))));
		this.imgTile = new ImageView(img);
		this.game = game;
	}
	
	public ImageView getImageView() {
		imgTile.setFitWidth(59);
		imgTile.setFitHeight(60);
		return this.imgTile;
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public Tile getTile() {
		return this.tile;
	}
}
