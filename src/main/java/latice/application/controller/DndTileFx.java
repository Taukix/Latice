package latice.application.controller;

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
import latice.application.model.Tile;
import latice.application.view.Mainjavafx;
import latice.application.view.TileFx;

public class DndTileFx {
	private static int floorX;
	private static int floorY;
	private static ImageView imageViewOnPlate;

		public static void manageSourceDragAndDrop(TileFx source, Game game, GridPane gpRack1, GridPane gpRack2, GridPane gpGame) {
			source.getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
						Dragboard db = source.getImageView().startDragAndDrop(TransferMode.ANY);
					
						ClipboardContent content = new ClipboardContent();
						content.putImage(source.getImage());
						db.setContent(content);
					
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
					if (event.getTransferMode() == TransferMode.MOVE && game.getPlayer1().getTurn() == true) {
						for (int i=0;i<game.getPlayer1().getRack().getTiles().size();i++) {
							if (event.getGestureSource() == gpRack1.getChildren().get(i) && game.getPlayer1().placeTile(game.getBoard(), new Position(floorX+1,floorY+1), i)) {
								
								gpRack1.getChildren().remove(i);
								
								gpGame.getChildren().remove(floorY*9+floorX);
		    					gpGame.getChildren().add(floorY*9+floorX, imageViewOnPlate);
		    					gpGame.setColumnIndex(imageViewOnPlate, floorX);
		    					gpGame.setRowIndex(imageViewOnPlate, floorY);
		    					
		    					Mainjavafx.nbrBonusPoint1.setText("Point(s) Bonus : " + game.getPlayer1().getBonus());
								break;
							}
						}
					}
				}});
			
			gpRack2.setOnDragDone(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if (event.getTransferMode() == TransferMode.MOVE && game.getPlayer2().getTurn() == true) {
						for (int i=0;i<game.getPlayer2().getRack().getTiles().size();i++) {
							if (event.getGestureSource() == gpRack2.getChildren().get(i) && game.getPlayer2().placeTile(game.getBoard(), new Position(floorX+1,floorY+1), i)) {
								
								gpRack2.getChildren().remove(i);
								
								gpGame.getChildren().remove(floorY*9+floorX);
		    					gpGame.getChildren().add(floorY*9+floorX, imageViewOnPlate);
		    					gpGame.setColumnIndex(imageViewOnPlate, floorX);
		    					gpGame.setRowIndex(imageViewOnPlate, floorY);
		    					
		    					Mainjavafx.nbrBonusPoint2.setText("Point(s) Bonus : " + game.getPlayer2().getBonus());
								break;
							}
						}
					}
				}});
		}
		
		public static void manageTargetDragAndDrop(TileFx target, GridPane gpGame, Game game) {
			
			target.getImageView().setOnDragOver(new EventHandler<DragEvent>() {
    			@Override
    			public void handle(DragEvent event) {
    				Dragboard db = event.getDragboard();
    				
    				if (db.hasImage()) {
    					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    				}
    				event.consume();
    		}});
			
			gpGame.setOnDragDropped(new EventHandler<DragEvent>() {
    			@Override
    			public void handle(DragEvent event) {
    				Dragboard db = event.getDragboard();
    				boolean success = false;
    				
    				if (db.hasImage()) {
    					floorX = (int) Math.floor((event.getX()-18)/62);
    					floorY = (int) Math.floor((event.getY()-18)/62);
    					
    					imageViewOnPlate = new ImageView(db.getImage());
    					imageViewOnPlate.setFitWidth(59);
    					imageViewOnPlate.setFitHeight(60);
    					imageViewOnPlate.setVisible(true);
    					
    					success = true;
    				}
    				event.setDropCompleted(success);
    				event.consume();
    			}	
    		});
		}
}
