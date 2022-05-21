package latice.application.controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import latice.application.model.Game;
import latice.application.model.Position;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class DndTileFx {
	private static int floorX;
	private static int floorY;

		public static void manageSourceDragAndDrop(TileFx source, Game game, GridPane gpRack1, GridPane gpRack2, ArrayList<TileFx> list1, ArrayList<TileFx> list2) {
			source.getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
						Dragboard db = source.getImageView().startDragAndDrop(TransferMode.ANY);
					
						ClipboardContent content = new ClipboardContent();
						content.putImage(source.getImage());
						db.setContent(content);
						Mainjavafx.imgWhereDragStart = db.getImage();
					
						event.consume();
				}
			});
			
			source.getImageView().setOnDragEntered(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if (event.getDragboard().hasImage()) {
						DropShadow shadow = new DropShadow();
						shadow.setRadius(10);
						shadow.setColor(Color.BLACK);
						
						source.getImageView().setEffect(shadow);
					}
				}
			});
			
			source.getImageView().setOnDragExited(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					source.getImageView().setEffect(null);
				}
			});
			
			gpRack1.setOnDragDone(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if (event.getTransferMode() == TransferMode.MOVE) {
						if (game.getPlayer1().getTurn() == true) {
							for (int i=0;i<game.getPlayer1().getRack().getTiles().size();i++) {
								if (Mainjavafx.imgWhereDragStart == list1.get(i).getImage()) {
									gpRack1.getChildren().remove(i);
									list1.remove(i);
								
									game.getPlayer1().placeTile(game, new Position(Mainjavafx.floorX,Mainjavafx.floorY), i);
									break;
								}
							}
						}
					}
				}});
			
			gpRack2.setOnDragDone(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if (event.getTransferMode() == TransferMode.MOVE) {
						if (game.getPlayer2().getTurn() == true) {
							for (int i=0;i<game.getPlayer2().getRack().getTiles().size();i++) {
								if (Mainjavafx.imgWhereDragStart == list2.get(i).getImage()) {
									gpRack2.getChildren().remove(i);
									list2.remove(i);
								
									game.getPlayer2().placeTile(game, new Position(Mainjavafx.floorX,Mainjavafx.floorY), i);
									break;
								}
							}
						}
					}
				}});

		}
		
		public static void manageTargetDragAndDrop(TileFx target, GridPane gpGame) {
			
			target.getImageView().setOnDragOver(new EventHandler<DragEvent>() {
    			@Override
    			public void handle(DragEvent event) {
    				Dragboard db = event.getDragboard();
    				
    				if (db.hasImage()) {
    					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    				}
    				event.consume();
    			}
    		});
			
			gpGame.setOnDragDropped(new EventHandler<DragEvent>() {
    			@Override
    			public void handle(DragEvent event) {
    				Dragboard db = event.getDragboard();
    				boolean success = false;
    				
    				if (db.hasImage()) {
    					floorX = (int) Math.floor((event.getX()-18)/62);
    					floorY = (int) Math.floor((event.getY()-18)/62);
    					
    					ImageView imageViewOnPlate = new ImageView(db.getImage());
    					imageViewOnPlate.setFitWidth(59);
    					imageViewOnPlate.setFitHeight(60);
    					imageViewOnPlate.setVisible(true);
    					
    					gpGame.getChildren().remove(floorY*9+floorX);
    					gpGame.getChildren().add(floorY*9+floorX, imageViewOnPlate);
    					gpGame.setColumnIndex(imageViewOnPlate, floorX);
    					gpGame.setRowIndex(imageViewOnPlate, floorY);
    					
    					success = true;
    				}
    				event.setDropCompleted(success);
    				event.consume();
    			}	
    		});
		}
}
