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
	private ArrayList<TileFx> list1;
	private ArrayList<TileFx> list2;
	private Game game;
	
	public TileFx(Tile tile, ArrayList<TileFx> list1, ArrayList<TileFx> list2, Game game) throws FileNotFoundException {
		this.tile = tile;
		this.img = new Image(new FileInputStream(new File(new File("").getAbsolutePath().concat("/Theme/Plage/Tuile/" + tile.getShape() + "/" + tile.getColor() + ".png"))));
		this.imgTile = new ImageView(img);
		this.list1 = list1;
		this.list2 = list2;
		this.game = game;
		
		this.imgTile.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (game.getPlayer1().getTurn() == true && ImageDragFrom() == game.getPlayer1() || game.getPlayer2().getTurn() == true && ImageDragFrom() == game.getPlayer2()) {
					Dragboard db = imgTile.startDragAndDrop(TransferMode.ANY);
				
					ClipboardContent content = new ClipboardContent();
					content.putImage(imgTile.getImage());
					db.setContent(content);
					Mainjavafx.imgWhereDragStart = db.getImage();
				
					event.consume();
				}
			}
		});
		
		imgTile.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasImage()) {
					DropShadow shadow = new DropShadow();
					shadow.setRadius(10);
					shadow.setColor(Color.BLACK);
					
					imgTile.setEffect(shadow);
				}
			}
		});
		
		imgTile.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				imgTile.setEffect(null);
			}
		});
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
	
	public Player ImageDragFrom() {
		Player playerWhereImageFrom = null;
		for (int i=0;i<list1.size();i++) {
			if (img == list1.get(i).getImage()) {
				playerWhereImageFrom =  game.getPlayer1();
				break;
			} else if (img == list2.get(i).getImage()) {
				playerWhereImageFrom =  game.getPlayer2();
				break;
			}
		}
		return playerWhereImageFrom;
	}
}
