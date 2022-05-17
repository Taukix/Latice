package latice.application.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import latice.application.controller.AnimationPoint;
import latice.application.controller.ButtonControllerCloseApplication;
import latice.application.controller.ButtonControllerEndTurn;
import latice.application.controller.ButtonControllerParametersMenu;
import latice.application.controller.ButtonControllerShadowOff;
import latice.application.controller.ButtonControllerShadowOn;
import latice.application.controller.ButtonControllerSoundOff;
import latice.application.controller.ButtonControllerSoundOn;
import latice.application.controller.ImageViewController;
import latice.application.controller.ProgressBarAnimation;
import latice.application.controller.SourdineOffWhenSliderProgress;
import latice.application.model.ColorTile;
import latice.application.model.Game;
import latice.application.model.Player;
import latice.application.model.Position;
import latice.application.model.Rack;
import latice.application.model.Shape;
import latice.application.model.Tile;

public class Mainjavafx extends Application {
	
	// Initilisation des conteneurs
	private BorderPane root;
	
		// MENU
	private VBox vbCenter;
	private VBox vbTop;
	
		// LOADING SCENE
	private VBox vbLoadingScene;
	private VBox vbTopLeftLoadingScene;
	
		// GAME
	private VBox vbPlateGame;
	
		// AUDIO PARAMETERS
	private Group groupParameters;
	private VBox vbParameters;
	
		// RULES
	private Group groupRules;
	private VBox vbRulesCenter;

	// Initialisation des composants
	// TOP
	private Label lblTopText1;
	private Label lblTopText2;
	
	// MENU CENTRAL
	private Button buttonPlay;
	private Button buttonRules;
	private Button buttonParameters;
	private Button buttonQuitMenu;
	
	// IMAGES
	private File fileLeague;
	private Image imgLeague;
	private BackgroundImage bgiLeague;
	private ImageView imgVLeague;
	
	private File fileBeach;
	private Image imgBeach;
	private BackgroundImage bgiBeach;
	private ImageView imgVBeach;
	
	private File fileIndian;
	private Image imgIndian;
	private BackgroundImage bgiIndian;
	private ImageView imgVIndian;
	
	private File fileHp;
	private Image imgHp;
	private BackgroundImage bgiHp;
	private ImageView imgVHp;
	
	private File fileBook;
	private Image imgBook;
	private ImageView imgVBook;
	
	private File fileRulesPlate;
	private Image imgRulesPlate;
	private ImageView imgVRulesPlate;
	
	// JOUER
		// SCENE DE CHARGEMENT
	private ProgressBar pgbLoadingScene;
	private Timeline tlLoadingScene;
	private Timeline tlPgbBarLoadingScene;
	private HBox hbLoadingScene;
	private Label lblLoadingScene;
	private Label lblPoint1;
	private Label lblPoint2;
	private Label lblPoint3;
	private Label lblLaticeLoadingScene;
	private Label lblSimpleGameLoadingScene;
	
		// JEU
	private Group gpPlate;
	private GridPane gpGame;
	private HBox hbRacks;
	private GridPane gpRackOfPlayer1;
	private GridPane gpRackOfPlayer2;
	private ArrayList<TileFx> ArrayOfTilesOnRackOnPlayer1;
	private ArrayList<TileFx> ArrayOfTilesOnRackOnPlayer2;
	private Timeline tlPlaySceneChange;
	public static Image imgWhereDragStart;
	public static int floorX;
	public static int floorY;
	private Button btnEndTurn;
	public static Label nbrTilesInStack1;
	public static Label nbrTilesInStack2;

	// REGLES
	private Text txtRulesTitle;
	private Text txtRulesBodyPage1;
	private Text txtRulesBodyPage2;
	private Button buttonQuitRules;
	
	// PARAMETRES
	private Button buttonAudioParameters;
	private Button buttonThemeParameters;
	private Button buttonQuitParameters;
	private Rectangle recParameters;
		
		// GENERAL
	private Label lblTopGeneralParameters;
	private GridPane gpThemeParameters;
	private Label lblUnderLeague;
	private Label lblUnderBeach;
	private Label lblUnderIndian;
	private Label lblUnderHp;
	
		// AUDIO
	private GridPane gpAudioParameters;
	private ProgressBar pgbMusic;
	private Label lblProgressBarMusic;
	private MediaPlayer mediaMusic;
	private CheckBox chbxMusic;
	private Slider sliderMusic;
	
	private ProgressBar pgbSoundEffect;
	private Label lblProgressBarSoundEffect;
	private CheckBox chbxEffect;
	private MediaPlayer mediaEffects;
	private Slider sliderEffect;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		// Ajout du fond d'écran par défault
		fileLeague = new File(new File("").getAbsolutePath().concat("/Theme/League of Legends/FOND.jpg"));
		imgLeague = new Image(new FileInputStream(fileLeague));
		bgiLeague = new BackgroundImage(imgLeague, null, null, null, null);
		root.setBackground(new Background(bgiLeague));
		
			// Image de chargement
			vbTopLeftLoadingScene = new VBox();
			lblLaticeLoadingScene = new Label("LATICE");
			lblLaticeLoadingScene.setFont(new Font("Calibri", 20));
			lblLaticeLoadingScene.setTextFill(Color.WHITESMOKE);
			lblLaticeLoadingScene.setStyle("-fx-font-weight: bold;");
			lblLaticeLoadingScene.setUnderline(true);
			lblSimpleGameLoadingScene = new Label("Partie Simple");
			lblSimpleGameLoadingScene.setFont(new Font("Calibri", 20));
			lblSimpleGameLoadingScene.setTextFill(Color.WHITESMOKE);
			lblSimpleGameLoadingScene.setStyle("-fx-font-weight: bold;");
			lblSimpleGameLoadingScene.setUnderline(true);
			
			vbTopLeftLoadingScene.getChildren().addAll(lblLaticeLoadingScene,lblSimpleGameLoadingScene);
			vbTopLeftLoadingScene.setSpacing(20);
			vbTopLeftLoadingScene.setAlignment(Pos.TOP_LEFT);
			
			hbLoadingScene = new HBox();
			lblLoadingScene = new Label("CHARGEMENT");
			lblLoadingScene.setFont(new Font("Calibri", 60));
			lblLoadingScene.setTextFill(Color.WHITESMOKE);
			lblPoint1 = new Label(".");
			lblPoint1.setFont(new Font("Calibri", 60));
			lblPoint1.setTextFill(Color.WHITESMOKE);
			lblPoint1.setVisible(false);
			lblPoint2 = new Label(".");
			lblPoint2.setFont(new Font("Calibri", 60));
			lblPoint2.setTextFill(Color.WHITESMOKE);
			lblPoint2.setVisible(false);
			lblPoint3 = new Label(".");
			lblPoint3.setFont(new Font("Calibri", 60));
			lblPoint3.setTextFill(Color.WHITESMOKE);
			lblPoint3.setVisible(false);
			
			hbLoadingScene.getChildren().addAll(lblLoadingScene,lblPoint1,lblPoint2,lblPoint3);
			hbLoadingScene.setSpacing(20);
			hbLoadingScene.setAlignment(Pos.CENTER_RIGHT);
			
			vbLoadingScene = new VBox();
			pgbLoadingScene = new ProgressBar(0);
			pgbLoadingScene.setPrefWidth(1800);
			pgbLoadingScene.setPrefHeight(20);
			
			vbLoadingScene.getChildren().addAll(hbLoadingScene,pgbLoadingScene);
			vbLoadingScene.setMargin(pgbLoadingScene, new Insets(0,0,30,50));
			vbLoadingScene.setMargin(hbLoadingScene, new Insets(0,80,20,0));
			
		// Implémentation du TOP
		lblTopText1 = new Label("Bienvenue dans le jeu");
		lblTopText1.setTextFill(Color.WHITESMOKE);
		lblTopText1.setFont(new Font("Calibri", 100));
		
		lblTopText2 = new Label("LATICE");
		lblTopText2.setTextFill(Color.WHITESMOKE);
		lblTopText2.setFont(new Font("Calibri", 150));
		
		vbTop = new VBox();
		vbTop.getChildren().addAll(lblTopText1,lblTopText2);
		vbTop.setAlignment(Pos.CENTER);
		
		// Impl�mentation du CENTRE
		buttonPlay = new Button("JOUER");
		buttonPlay.setPadding(new Insets(7,300,7,300));
		buttonPlay.setStyle("-fx-background-color: #FFF; ");
		
		buttonRules = new Button("REGLES");
		buttonRules.setPadding(new Insets(7,297,7,297));
		buttonRules.setStyle("-fx-background-color: #FFF; ");
        
        buttonParameters = new Button("PARAMETRES");
        buttonParameters.setPadding(new Insets(7,281,7,281));
        buttonParameters.setStyle("-fx-background-color: #FFF; ");
        
        buttonQuitMenu = new Button("QUITTER");
        buttonQuitMenu.setPadding(new Insets(7,294,7,294));
        buttonQuitMenu.setStyle("-fx-background-color: #FFF; ");
        
        vbCenter = new VBox();
        vbCenter.getChildren().addAll(buttonPlay,buttonRules,buttonParameters,buttonQuitMenu);
        vbCenter.setAlignment(Pos.CENTER);
        vbCenter.setSpacing(30);

        		// Ajoute l'ombre quand le curseur est dessus + Enleve l'ombre quand le curseur n est plus dessus
        		buttonPlay.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonControllerShadowOn(buttonPlay));
        		buttonPlay.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonControllerShadowOff(buttonPlay));
        		buttonRules.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonControllerShadowOn(buttonRules));
        		buttonRules.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonControllerShadowOff(buttonRules));
        		buttonParameters.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonControllerShadowOn(buttonParameters));
        		buttonParameters.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonControllerShadowOff(buttonParameters));
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonControllerShadowOn(buttonQuitMenu));
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonControllerShadowOff(buttonQuitMenu));
        		
        		// Action des BOUTONS du menu principal
        		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent arg0) {
		            	// Chargement
		            	root.setCenter(null);
		            	root.setBottom(vbLoadingScene);
		            	root.setTop(vbTopLeftLoadingScene);
		            	root.setMargin(vbTopLeftLoadingScene, new Insets(100,0,0,100));
		            	
		            	// Timeline pour les 3 petits points
		            	tlLoadingScene = new Timeline(new KeyFrame(Duration.seconds(1), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint1,true)),
		            			new KeyFrame(Duration.seconds(1.25), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint2,true)),
		            			new KeyFrame(Duration.seconds(1.5), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint3,true)),
		            			new KeyFrame(Duration.seconds(2), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint1,false)),
		            			new KeyFrame(Duration.seconds(2.25), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint2,false)),
		            			new KeyFrame(Duration.seconds(2.5), e -> AnimationPoint.PointAnimationLoadingScene(lblPoint3,false)));
		            	tlLoadingScene.setCycleCount(3);
		            	
		            	// Timeline pour la barre fluide
		            	tlPgbBarLoadingScene = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> ProgressBarAnimation.increaseProgress(pgbLoadingScene)));
		            	tlPgbBarLoadingScene.setCycleCount(750);
		            	
		            	// Timeline pour le changement de scène à la fin du timing de la barre
		            	tlPlaySceneChange = new Timeline(new KeyFrame(Duration.seconds(7.5), e -> root.setCenter(vbPlateGame)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setBottom(null)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setTop(null)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setLeft(nbrTilesInStack1)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setRight(nbrTilesInStack2)),
		            			new KeyFrame(Duration.seconds(7.6), e -> pgbLoadingScene.setProgress(0)));
		            	
		            	tlPgbBarLoadingScene.play();
		            	tlLoadingScene.play();
		            	tlPlaySceneChange.play();}});
		            	
        		buttonRules.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent arg0) {
		                root.setCenter(vbRulesCenter);}});
        		
        		buttonParameters.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent arg0) {
		                root.setCenter(vbParameters);
		                root.setMargin(vbParameters, new Insets(0,0,100,0));}});
        		
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerCloseApplication(root));
        		
        // Implémentation du GROUP PLAY et de ses composants
        vbPlateGame = new VBox();
        gpPlate = new Group();
        
        DropShadow yellowShadow = new DropShadow();
        yellowShadow.setRadius(40);
        yellowShadow.setSpread(0.5);
        yellowShadow.setColor(Color.YELLOW);
        
        nbrTilesInStack1 = new Label("Nombre de tuiles restantes: 31");
        nbrTilesInStack1.setFont(new Font("Calibri", 40));
		nbrTilesInStack1.setTextFill(Color.WHITESMOKE);
		root.setAlignment(nbrTilesInStack1, Pos.CENTER);
		root.setMargin(nbrTilesInStack1, new Insets(0,0,0,50));
		nbrTilesInStack1.setEffect(yellowShadow);
		
        nbrTilesInStack2 = new Label("Nombre de tuiles restantes: 31");
        nbrTilesInStack2.setFont(new Font("Calibri", 40));
		nbrTilesInStack2.setTextFill(Color.WHITESMOKE);
		root.setAlignment(nbrTilesInStack2, Pos.CENTER);
		root.setMargin(nbrTilesInStack2, new Insets(0,50,0,0));
		nbrTilesInStack2.setEffect(yellowShadow);
        
        fileLeague = new File(new File("").getAbsolutePath().concat("/Theme/Plage/Plateau.png"));
        imgLeague = new Image(new FileInputStream(fileLeague));
        bgiLeague = new BackgroundImage(imgLeague, null, null, null, null);
        
        btnEndTurn = new Button("Fin du tour");
        btnEndTurn.setPadding(new Insets(7,100,7,100));
        btnEndTurn.setStyle("-fx-background-color: #FFF; ");
        
        gpGame = new GridPane();
        gpGame.setBackground(new Background(bgiLeague));
        gpGame.setPrefSize(600, 600); 
        gpGame.setPadding(new Insets(15,17,15,18));
        gpGame.setHgap(4);
        gpGame.setVgap(3);
        
        hbRacks = new HBox();
        hbRacks.setAlignment(Pos.CENTER);
        gpRackOfPlayer1 = new GridPane();
        gpRackOfPlayer2 = new GridPane();
        
        ArrayOfTilesOnRackOnPlayer1 = new ArrayList<TileFx>();
        ArrayOfTilesOnRackOnPlayer2 = new ArrayList<TileFx>();
        
        
        // Début de la partie
        Game game = new Game(new Player("alexandre"), new Player("toto"));
        
        // Mise en place de chaque Rack avec leurs tuiles
        for (int i = 0; i < game.getPlayer1().getRack().getTiles().size(); i++) {
        	TileFx tileFxOfPlayer1 = new TileFx(game.getPlayer1().getRack().getTiles().get(i), ArrayOfTilesOnRackOnPlayer1, ArrayOfTilesOnRackOnPlayer2, game);
        	ArrayOfTilesOnRackOnPlayer1.add(tileFxOfPlayer1);
        	gpRackOfPlayer1.add(ArrayOfTilesOnRackOnPlayer1.get(i).getImageView(), i, 0);
        	
        	TileFx tileFxofPlayer2 = new TileFx(game.getPlayer2().getRack().getTiles().get(i), ArrayOfTilesOnRackOnPlayer1, ArrayOfTilesOnRackOnPlayer2, game);
        	ArrayOfTilesOnRackOnPlayer2.add(tileFxofPlayer2);
        	gpRackOfPlayer2.add(ArrayOfTilesOnRackOnPlayer2.get(i).getImageView(), i, 0);
        }
        
        // Définition des cases du plateau dans le GridPane
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		
        		Tile tile = new Tile(null, null);
        		TileFx defaulttilefx = new TileFx(tile, ArrayOfTilesOnRackOnPlayer1, ArrayOfTilesOnRackOnPlayer2, game);
        		
        		defaulttilefx.getImageView().setOnDragOver(new EventHandler<DragEvent>() {
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
        		
        		GridPane.setRowIndex(defaulttilefx.getImageView(), i);
        		GridPane.setColumnIndex(defaulttilefx.getImageView(), j);
        		
        		gpGame.getChildren().add(defaulttilefx.getImageView());
        	}}
        
        
        DropShadow shadow = new DropShadow();
		shadow.setRadius(40);
		shadow.setColor(Color.YELLOW);
		
		gpRackOfPlayer1.setEffect(shadow);
        
        // Le player 1 commence à chaque fois
        game.getPlayer1().startTurn();
        
        gpRackOfPlayer1.setOnDragDone(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.MOVE) {
					if (game.getPlayer1().getTurn() == true) {
						for (int i=0;i<game.getPlayer1().getRack().getTiles().size();i++) {
							if (Mainjavafx.imgWhereDragStart == ArrayOfTilesOnRackOnPlayer1.get(i).getImage()) {
								gpRackOfPlayer1.getChildren().remove(i);
								ArrayOfTilesOnRackOnPlayer1.remove(i);
							
								game.getPlayer1().placeTile(game, new Position(Mainjavafx.floorX,Mainjavafx.floorY), i);
								break;
							}
						}
					}
				}
			}});
 
        gpRackOfPlayer2.setOnDragDone(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.MOVE) {
					if (game.getPlayer2().getTurn() == true) {
						for (int i=0;i<game.getPlayer2().getRack().getTiles().size();i++) {
							if (Mainjavafx.imgWhereDragStart == ArrayOfTilesOnRackOnPlayer2.get(i).getImage()) {
								gpRackOfPlayer2.getChildren().remove(i);
								ArrayOfTilesOnRackOnPlayer2.remove(i);
							
								game.getPlayer2().placeTile(game, new Position(Mainjavafx.floorX,Mainjavafx.floorY), i);
								break;
							}
						}
					}
				}
			}});
        
        btnEndTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerEndTurn(game, gpRackOfPlayer1, gpRackOfPlayer2, ArrayOfTilesOnRackOnPlayer1, ArrayOfTilesOnRackOnPlayer2));
		
        gpPlate.getChildren().add(gpGame);
        hbRacks.getChildren().addAll(gpRackOfPlayer1,gpRackOfPlayer2);
        hbRacks.setSpacing(200);
        
        vbPlateGame.getChildren().addAll(gpPlate,hbRacks, btnEndTurn);
        vbPlateGame.setAlignment(Pos.CENTER);
        vbPlateGame.setSpacing(50);
        
        // Implémentation du GROUP REGLES et de ses composants
        groupRules = new Group();
        vbRulesCenter = new VBox();
        
        //Image du livre vierge ouvert
        fileBook = new File(new File("").getAbsolutePath().concat("/Theme/ImagePourRegles/LIVRE.png"));
        imgBook = new Image(new FileInputStream(fileBook));
        imgVBook = new ImageView(imgBook);
        
        // Image du plateau sur le livre
        fileRulesPlate = new File(new File("").getAbsolutePath().concat("/Theme/ImagePourRegles/PlateauLatice.jpg"));
        imgRulesPlate = new Image(new FileInputStream(fileRulesPlate));
        imgVRulesPlate = new ImageView(imgRulesPlate);
        imgVRulesPlate.setFitHeight(150);
        imgVRulesPlate.setFitWidth(150);
        imgVRulesPlate.setX(312);
        imgVRulesPlate.setY(220);
        
        txtRulesTitle = new Text("R�gles du jeu :");
        txtRulesTitle.setStyle("-fx-font-weight: bold;");
        txtRulesTitle.setX(100);
        txtRulesTitle.setY(50);
        txtRulesTitle.setUnderline(true);
        
        txtRulesBodyPage1 = new Text("Le jeu d�bute lorsqu'un des deux joueurs (choisi al�atoirement) pose"
        		+ "\nsa premi�re tuile sur la lune au centre du plateau de jeu."
        		+ "\n\nEn effet, tout au long de la partie, chaque joueur poss�de un rack avec"
        		+ "\nun maximumde 5 tuiles qui devront vider le plus vite possible."
        		+ "\n\nLa suite se d�roule en posant une tuile poss�dant la m�me couleur"
        		+ "\nou le m�me dessin que la tuile d�j� en place."
        		+ "\n\nVous ne pourrez en aucun cas jouer"
        		+ "\nen diagonal, le jeu s�effectue toujours"
        		+ "\nde haut en bas ou de droite � gauche."
        		+ "\n\nIl y a tout de m�me quelques "
        		+ "\nsubtilit�s, sinon cela serait trop facile,"
        		+ "\nsi sur le plateau vous avez par"
        		+ "\nexemple un dauphin vert, vous ne"
        		+ "\npouvez accoler une tortue bleu ou"
        		+ "\nun oiseau rouge. "
        		+ "\n\nEn revanche, toujours avec notre"
        		+ "\ndauphin vert, vous pourrez tout �"
        		+ "\nfait accoler une tortue vert ou un"
        		+ "\ndauphin bleu, je m�explique, la tortue poss�de la bonne couleur, et le"
        		+ "\ndauphin le bon dessin."
        		+ "\n\nUne fois toutes les tuiles d'un joueur pos�es sur le plateau, ce dernier"
        		+ "\nsera d�sign� vainqueur !");
        txtRulesBodyPage1.setX(100);
        txtRulesBodyPage1.setY(90);
        
        txtRulesBodyPage2 = new Text("Ce jeu Latice allie la strat�gie et le hasard, ce qui vous permettra de"
        		+ "\npasser d�excellent moment entre strat�ges avertis ou au sein de votre"
        		+ "\nfamille."
        		+ "\n\nLes enfants encadr�s pouvant jouer ais�ment."
        		+ "\n\nIl vous sera n�cessaire de faire une partie ou deux pour assimiler"
        		+ "\ntoutes les petites subtilit�s des r�gles du jeu."
        		+ "\n\nLes parties peuvent �tre assez rapide, ce qui est un avantage car"
        		+ "\ncontrairement � beaucoup de jeu, il ne n�cessite pas un long moment"
        		+ "\nde disponibilit�, ce qui �vited�abandonner une partie en cours de jeu"
        		+ "\npar manque de temps."
        		+ "\n\nEn conclusion, c�est un jeu ludique, toutes les finitions sont parfaites"
        		+ "\net il vous octroiera des heures de rigolade, ou de crises de nerfs"
        		+ "\npour trouver la bonne combinaison !");
        txtRulesBodyPage2.setX(510);
        txtRulesBodyPage2.setY(90);
        
        buttonQuitRules = new Button("REVENIR AU MENU PRINCIPAL");
        buttonQuitRules.setPadding(new Insets(7,100,7,100));
        buttonQuitRules.setStyle("-fx-background-color: #FFF; ");
        
        // Actions des boutons
        buttonQuitRules.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		root.setCenter(vbCenter);
        	}
        });
        
        groupRules.getChildren().addAll(imgVBook,imgVRulesPlate,txtRulesTitle, txtRulesBodyPage1,txtRulesBodyPage2);
        vbRulesCenter.getChildren().addAll(groupRules,buttonQuitRules);
        vbRulesCenter.setAlignment(Pos.CENTER);
        vbRulesCenter.setSpacing(20);
        		
        // Implémentation du GROUP PARAMETRE et de ses composants
        groupParameters = new Group();
        vbParameters = new VBox();
        
        recParameters = new Rectangle(800,400);
        recParameters.setFill(Color.WHITESMOKE);
        
        buttonThemeParameters = new Button("THEME");
        buttonThemeParameters.setPadding(new Insets(7,185,7,185));
        buttonThemeParameters.setStyle("-fx-background-color: #DCDCDC");
        buttonThemeParameters.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        buttonAudioParameters = new Button("AUDIO");
        buttonAudioParameters.setPadding(new Insets(7,181,7,181));
        buttonAudioParameters.setStyle("-fx-background-color: #FFF; ");
        buttonAudioParameters.setLayoutX(400);
        buttonAudioParameters.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
        buttonQuitParameters = new Button("REVENIR AU MENU PRINCIPAL");
        buttonQuitParameters.setPadding(new Insets(7,100,7,100));
        buttonQuitParameters.setStyle("-fx-background-color: #FFF; ");
        
        // GRIDPANE GENERAL PARAMETRE
        lblTopGeneralParameters = new Label("THEMES");
        lblTopGeneralParameters.setStyle("-fx-font: 30 arial;");
        
        imgVLeague = new ImageView(imgLeague);
        imgVLeague.setFitHeight(125);
        imgVLeague.setFitWidth(175);
		imgVLeague.setEffect(new DropShadow(20, Color.BLACK));
        
        fileBeach = new File(new File("").getAbsolutePath().concat("/Theme/Plage/BEACH.jpg"));
		imgBeach = new Image(new FileInputStream(fileBeach));
		imgVBeach = new ImageView(imgBeach);
		imgVBeach.setFitHeight(125);
		imgVBeach.setFitWidth(175);
		
		fileIndian = new File(new File("").getAbsolutePath().concat("/Theme/Indien/INDIAN.jpg"));
		imgIndian = new Image(new FileInputStream(fileIndian));
		imgVIndian = new ImageView(imgIndian);
		imgVIndian.setFitHeight(125);
		imgVIndian.setFitWidth(175);
		
		fileHp = new File(new File("").getAbsolutePath().concat("/Theme/Harry Potter/HP.jpg"));
		imgHp = new Image(new FileInputStream(fileHp));
		imgVHp = new ImageView(imgHp);
		imgVHp.setFitHeight(125);
		imgVHp.setFitWidth(175);
		
		lblUnderLeague = new Label("League of Legends");
		lblUnderLeague.setStyle("-fx-font-weight: bold;");
		lblUnderBeach = new Label("Plage");
		lblUnderBeach.setStyle("-fx-font-weight: bold;");
		lblUnderIndian = new Label("Indien");
		lblUnderIndian.setStyle("-fx-font-weight: bold;");
		lblUnderHp = new Label("Harry Potter");
		lblUnderHp.setStyle("-fx-font-weight: bold;");
		
		gpThemeParameters = new GridPane();
		gpThemeParameters.add(lblTopGeneralParameters, 1, 0);
		gpThemeParameters.add(imgVLeague, 0, 1);
		gpThemeParameters.add(imgVBeach, 1, 1);
		gpThemeParameters.add(imgVIndian, 2, 1);
		gpThemeParameters.add(imgVHp, 3, 1);
		gpThemeParameters.add(lblUnderLeague, 0, 2);
		gpThemeParameters.add(lblUnderBeach, 1, 2);
		gpThemeParameters.add(lblUnderIndian, 2, 2);
		gpThemeParameters.add(lblUnderHp, 3, 2);
		gpThemeParameters.setLayoutX(20);
       	gpThemeParameters.setLayoutY(80);
       	gpThemeParameters.setMargin(imgVLeague, new Insets(0,20,0,0));
       	gpThemeParameters.setMargin(imgVIndian, new Insets(0,20,0,20));
       	gpThemeParameters.setMargin(lblUnderLeague, new Insets(30,0,0,40));
       	gpThemeParameters.setMargin(lblUnderBeach, new Insets(30,0,0,75));
       	gpThemeParameters.setMargin(lblUnderIndian, new Insets(30,0,0,90));
       	gpThemeParameters.setMargin(lblUnderHp, new Insets(30,0,0,55));
       	gpThemeParameters.setMargin(lblTopGeneralParameters, new Insets(10,0,40,0));
		
        // GRIDPANE AUDIO PARAMETRE
       		// MUSIQUE
       	lblProgressBarMusic = new Label("Musique :");
       	lblProgressBarMusic.setStyle("-fx-font: 18 arial;");
       
       	pgbMusic = new ProgressBar(0.7);
       	pgbMusic.setPrefHeight(15);
       	pgbMusic.setPrefWidth(200);
        	
       	sliderMusic = new Slider(0, 1, 0.7);
       	pgbMusic.progressProperty().bind(sliderMusic.valueProperty());
        	
       	chbxMusic = new CheckBox("Sourdine");
        		
       		// EFFETS
       	lblProgressBarSoundEffect = new Label("Effets :");
       	lblProgressBarSoundEffect.setStyle("-fx-font: 18 arial;");
        	
        pgbSoundEffect = new ProgressBar(0.5);
       	pgbSoundEffect.setPrefHeight(15);
       	pgbSoundEffect.setPrefWidth(200);
        	
       	sliderEffect = new Slider(0, 1, 0.7);
       	sliderEffect.setPrefWidth(200);
       	pgbSoundEffect.progressProperty().bind(sliderEffect.valueProperty());

       	chbxEffect = new CheckBox("Sourdine");
        	
       	gpAudioParameters = new GridPane();
       	gpAudioParameters.add(lblProgressBarMusic, 0, 0);
       	gpAudioParameters.add(pgbMusic, 0, 1);
       	gpAudioParameters.add(sliderMusic, 1, 1);
       	gpAudioParameters.add(chbxMusic, 2, 1);
       	gpAudioParameters.add(lblProgressBarSoundEffect, 0, 2);
       	gpAudioParameters.add(pgbSoundEffect, 0, 3);
       	gpAudioParameters.add(sliderEffect, 1, 3);
       	gpAudioParameters.add(chbxEffect, 2, 3);
       	gpAudioParameters.setLayoutX(60);
       	gpAudioParameters.setLayoutY(80);
       	gpAudioParameters.setMargin(pgbMusic, new Insets(0,20,0,0));
       	gpAudioParameters.setMargin(lblProgressBarMusic, new Insets(0,0,20,0));
       	gpAudioParameters.setMargin(lblProgressBarSoundEffect, new Insets(80,0,20,0));
       	gpAudioParameters.setMargin(sliderMusic, new Insets(0,60,0,30));
       	gpAudioParameters.setMargin(sliderEffect, new Insets(0,60,0,30));
       	gpAudioParameters.setVisible(false);
        
        groupParameters.getChildren().addAll(recParameters,buttonThemeParameters, buttonAudioParameters, gpAudioParameters, gpThemeParameters);
        vbParameters.getChildren().addAll(groupParameters,buttonQuitParameters);
        vbParameters.setAlignment(Pos.CENTER);
        vbParameters.setSpacing(20);
		
        	// Action des boutons de la zone PARAMETRE
        	buttonThemeParameters.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonControllerParametersMenu(buttonThemeParameters, buttonAudioParameters, gpThemeParameters, gpAudioParameters));
        	buttonAudioParameters.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonControllerParametersMenu(buttonAudioParameters, buttonThemeParameters, gpAudioParameters, gpThemeParameters));       	
        	buttonQuitParameters.setOnAction(new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent arg0) {
        			root.setCenter(vbCenter);
        		}
        	});
			
        	// Action des boutons AUDIO
			chbxMusic.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerSoundOff(sliderMusic, mediaMusic, chbxMusic));
			chbxMusic.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerSoundOn(sliderMusic, mediaMusic, chbxMusic));
			chbxEffect.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerSoundOff(sliderEffect, mediaEffects, chbxEffect));
			chbxEffect.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerSoundOn(sliderEffect, mediaEffects, chbxEffect));
			
			sliderMusic.addEventHandler(MouseEvent.DRAG_DETECTED, new SourdineOffWhenSliderProgress(chbxMusic));
			sliderEffect.addEventHandler(MouseEvent.DRAG_DETECTED, new SourdineOffWhenSliderProgress(chbxEffect));
			
		
		// Mise en place de la musique de fond
		mediaMusic = new MediaPlayer(new Media(new File(new File("").getAbsolutePath().concat("/Theme/League of Legends/Imagine Dragons x JID  Enemy Lyrics.mp3")).toURI().toString()));
		mediaMusic.volumeProperty().bind(pgbMusic.progressProperty());
		mediaMusic.play();
			
			// Actions des images THEME
			imgVLeague.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVLeague, imgVBeach, imgVIndian, imgVHp, bgiLeague, imgLeague, root,"League of Legends/Imagine Dragons x JID  Enemy Lyrics.mp3", pgbMusic, mediaMusic));
			imgVBeach.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVBeach, imgVLeague, imgVIndian, imgVHp, bgiBeach, imgBeach, root,"Plage/Calvin Harris  Summer Audio.mp3", pgbMusic, mediaMusic));
			imgVIndian.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVIndian, imgVBeach, imgVLeague, imgVHp, bgiIndian, imgIndian, root, "Indien/Panjabi MC  Mundian To Bach Ke The Dictator Soundtrack.mp3", pgbMusic, mediaMusic));
			imgVHp.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVHp, imgVBeach, imgVIndian, imgVLeague, bgiHp, imgHp, root, "Harry Potter/Harry Potter Theme Song.mp3", pgbMusic, mediaMusic));
		
		// Mise en place des éléments dans le BorderPane
		root.setTop(vbTop);
		root.setMargin(vbTop, new Insets(20,0,0,0));
		
		root.setCenter(vbCenter);
		
		// Mise en place de la scène
		Scene MainScene = new Scene(root, 1920, 1010);
		primaryStage.setTitle("Application Latice");
		primaryStage.setScene(MainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
