package latice.application.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
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
import latice.application.controller.ButtonControllerChangeRack;
import latice.application.controller.ButtonControllerCloseApplication;
import latice.application.controller.ButtonControllerEndTurn;
import latice.application.controller.ButtonControllerParametersMenu;
import latice.application.controller.ButtonControllerReturnToMenu;
import latice.application.controller.ButtonControllerShadowOff;
import latice.application.controller.ButtonControllerShadowOn;
import latice.application.controller.ButtonControllerSoundOff;
import latice.application.controller.ButtonControllerSoundOn;
import latice.application.controller.DndTileFx;
import latice.application.controller.ImageViewController;
import latice.application.controller.ProgressBarAnimation;
import latice.application.controller.SourdineOffWhenSliderProgress;
import latice.application.model.Game;
import latice.application.model.Player;
import latice.application.model.Tile;

public class Mainjavafx extends Application {
	
	// Initilisation des conteneurs
	private BorderPane root;
	
		// MENU
	private VBox vbCenter;
	private VBox vbTop;
	
		// SCENE DE CHARGEMENT
	private VBox vbLoadingScene;
	private VBox vbTopLeftLoadingScene;
	private HBox hbLoadingScene;
	
		// JEU
	private VBox vbPlateGame;
	private Group gpPlate;
	private GridPane gpGame;
	private HBox hbRacks;
	private GridPane gpRackOfPlayer1;
	private GridPane gpRackOfPlayer2;
	private HBox hbButtons;
	private VBox vbInfoPlayer1;
	private VBox vbInfoPlayer2;
	
		// THEME PARAMETRES
	private GridPane gpThemeParameters;
	
		// AUDIO PARAMETRES
	private Group groupParameters;
	private GridPane gpAudioParameters;
	private VBox vbParameters;
	
		// REGLES
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
	private File fileBackground;
	private Image imgBackground;
	private BackgroundImage mainBackground;
	
	private File fileImagePlate;
	private Image imgPlate;
	private BackgroundImage bgiPlate;
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
	private Label lblLoadingScene;
	private Label lblPoint1;
	private Label lblPoint2;
	private Label lblPoint3;
	private Label lblLaticeLoadingScene;
	private Label lblSimpleGameLoadingScene;
	
		// SCENE FIN DE JEU
	private Button btnQuitEndingScene;
	private Label lblWinner;
	
		// JEU
	private Timeline tlPlaySceneChange;
	private Button btnChangeRack;
	private Button btnEndTurn;
	private Button btnReturnToMenu;
	private Label nameOfPlayer1;
	private Label nameOfPlayer2;
	public static Label nbrTilesInStack1;
	public static Label nbrTilesInStack2;
	public static Label nbrBonusPoint1;
	public static Label nbrBonusPoint2;
	public static Label lblNumberOfTurn;
	public static String theme;	
	public static DropShadow yellowShadow;

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
	private Label lblUnderLeague;
	private Label lblUnderBeach;
	private Label lblUnderIndian;
	private Label lblUnderHp;
	
		// AUDIO
	private ProgressBar pgbMusic;
	private Label lblProgressBarMusic;
	private MediaPlayer mediaMusic;
	private CheckBox chbxMusic;
	private Slider sliderMusic;
	
	public static ProgressBar pgbSoundEffect;
	private Label lblProgressBarSoundEffect;
	private CheckBox chbxEffect;
	public static MediaPlayer mediaEffects;
	private Slider sliderEffect;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		// Ajout du fond d'écran par défault
		fileBackground = new File(new File("").getAbsolutePath().concat("/Theme/League of Legends/FOND.jpg"));
		imgBackground = new Image(new FileInputStream(fileBackground));
		mainBackground = new BackgroundImage(imgBackground, null, null, null, null);
		root.setBackground(new Background(mainBackground));
		
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
		
		// Implémentation du CENTRE
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
		            	
		            	// Implémentation du GROUP PLAY et de ses composants de sorte que le système recréer une nouvelle partie si l'on quitte celle d'avant
		                vbPlateGame = new VBox();
		                gpPlate = new Group();
		                
		                yellowShadow = new DropShadow();
		                yellowShadow.setRadius(40);
		                yellowShadow.setSpread(0.5);
		                yellowShadow.setColor(Color.YELLOW);
		                
		                vbInfoPlayer1 = new VBox(30);
		                vbInfoPlayer1.setAlignment(Pos.CENTER);
		                
		                vbInfoPlayer2 = new VBox(30);
		                vbInfoPlayer2.setAlignment(Pos.CENTER);
		                
		                lblNumberOfTurn = new Label("Nombre de tour(s) restant(s) : 10");
		                lblNumberOfTurn.setFont(new Font("Calibri", 40));
		                lblNumberOfTurn.setTextFill(Color.WHITESMOKE);
		                lblNumberOfTurn.setEffect(yellowShadow);
		                
		                nameOfPlayer1 = new Label("Player 1");
		                nameOfPlayer1.setFont(new Font("Calibri", 40));
		                nameOfPlayer1.setTextFill(Color.WHITESMOKE);
		                nameOfPlayer1.setEffect(yellowShadow);
		                
		                nameOfPlayer2 = new Label("Player 2");
		                nameOfPlayer2.setFont(new Font("Calibri", 40));
		                nameOfPlayer2.setTextFill(Color.WHITESMOKE);
		                nameOfPlayer2.setEffect(yellowShadow);
		                
		                nbrTilesInStack1 = new Label("Nombre de tuiles restantes: 31");
		                nbrTilesInStack1.setFont(new Font("Calibri", 40));
		        		nbrTilesInStack1.setTextFill(Color.WHITESMOKE);
		        		nbrTilesInStack1.setEffect(yellowShadow);
		        		
		                nbrTilesInStack2 = new Label("Nombre de tuiles restantes: 31");
		                nbrTilesInStack2.setFont(new Font("Calibri", 40));
		        		nbrTilesInStack2.setTextFill(Color.WHITESMOKE);
		        		nbrTilesInStack2.setEffect(yellowShadow);
		        		
		        		nbrBonusPoint1 = new Label("Point(s) Bonus : 0");
		        		nbrBonusPoint1.setFont(new Font("Calibri", 40));
		        		nbrBonusPoint1.setTextFill(Color.WHITESMOKE);
		        		nbrBonusPoint1.setEffect(yellowShadow);
		        		
		        		nbrBonusPoint2 = new Label("Point(s) Bonus : 0");
		        		nbrBonusPoint2.setFont(new Font("Calibri", 40));
		        		nbrBonusPoint2.setTextFill(Color.WHITESMOKE);
		        		nbrBonusPoint2.setEffect(yellowShadow);
		                
		                hbButtons = new HBox(10);
		                hbButtons.setAlignment(Pos.CENTER);
		                
		                btnReturnToMenu = new Button("<--");
		                btnReturnToMenu.setPadding(new Insets(7,10,7,10));
		                btnReturnToMenu.setStyle("-fx-background-color: #FFF; ");
		                btnReturnToMenu.setAlignment(Pos.TOP_LEFT);
		                
		                btnChangeRack = new Button("Changer le Rack");
		                btnChangeRack.setPadding(new Insets(7,100,7,100));
		                btnChangeRack.setStyle("-fx-background-color: #FFF; ");
		                
		                btnEndTurn = new Button("Fin du tour");
		                btnEndTurn.setPadding(new Insets(7,100,7,100));
		                btnEndTurn.setStyle("-fx-background-color: #FFF; ");
		                
		                // Implémentation du bouton pour la scène de fin de jeu
		                
		                btnQuitEndingScene = new Button("Revenir au menu principal");
		                btnQuitEndingScene.setPadding(new Insets(7,100,7,100));
		                btnQuitEndingScene.setStyle("-fx-background-color: #FFF; ");
		                btnQuitEndingScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerReturnToMenu(root, vbCenter, vbTop));
		                
		                // On vide le plateau JAVAFX à chaque lancement de partie
		                if (gpGame.getChildren().size() > 0) {
		                	for (int i=gpGame.getChildren().size()-1;i>=0;i--) {
		                		gpGame.getChildren().remove(i);
		                	}
		                }
		               
		                hbRacks = new HBox(200);
		                hbRacks.setAlignment(Pos.CENTER);
		                gpRackOfPlayer1 = new GridPane();
		                gpRackOfPlayer2 = new GridPane();
		        		
		                vbInfoPlayer1.getChildren().addAll(nameOfPlayer1, nbrTilesInStack1, nbrBonusPoint1);
		                vbInfoPlayer2.getChildren().addAll(nameOfPlayer2, nbrTilesInStack2, nbrBonusPoint2);
		                root.setMargin(vbInfoPlayer1, new Insets(0,0,0,50));
		                root.setMargin(vbInfoPlayer2, new Insets(0,50,0,0));
		                root.setMargin(btnReturnToMenu, new Insets(10,0,0,10));
		                
		                gpPlate.getChildren().add(gpGame);
		                hbRacks.getChildren().addAll(gpRackOfPlayer1,gpRackOfPlayer2);
		                
		                hbButtons.getChildren().addAll(btnChangeRack,btnEndTurn);
		                
		                vbPlateGame.getChildren().addAll(lblNumberOfTurn,gpPlate,hbRacks,hbButtons);
		                vbPlateGame.setAlignment(Pos.CENTER);
		                vbPlateGame.setSpacing(50);
		                
		            	
		            	// Lancement de la Partie
		                Game game = new Game(new Player("Joueur 1"), new Player("Joueur 2"));
		                
		                // Mise en place de chaque Rack avec leurs tuiles
		                for (int i = 0; i < game.getPlayer1().getRack().getTiles().size(); i++) {
							try {
								TileFx tileFxOfPlayer1 = new TileFx(game.getPlayer1().getRack().getTiles().get(i), theme);
								gpRackOfPlayer1.add(tileFxOfPlayer1.getImageView(), i, 0);
								DndTileFx.manageSourceDragAndDrop(tileFxOfPlayer1, game, gpRackOfPlayer1, gpRackOfPlayer2, gpGame);
								
								TileFx tileFxofPlayer2 = new TileFx(game.getPlayer2().getRack().getTiles().get(i), theme);
								gpRackOfPlayer2.add(tileFxofPlayer2.getImageView(), i, 0);
								DndTileFx.manageSourceDragAndDrop(tileFxofPlayer2, game, gpRackOfPlayer1, gpRackOfPlayer2, gpGame);
								
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} 	
		                }
		                
		                // Définition des cases du plateau dans le GridPane
		                for (int i = 0; i < 9; i++) {
		                	for (int j = 0; j < 9; j++) {
		                		
		                		Tile tile = new Tile(null, null);
		                		TileFx defaulttilefx;
								try {
									defaulttilefx = new TileFx(tile, theme);
									DndTileFx.manageTargetDragAndDrop(defaulttilefx, gpGame, game);
									
									GridPane.setRowIndex(defaulttilefx.getImageView(), i);
									GridPane.setColumnIndex(defaulttilefx.getImageView(), j);
									
									gpGame.getChildren().add(defaulttilefx.getImageView());
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
		                		
		                }}
		                
		                // Le player 1 commence à chaque fois
		                game.getPlayer1().startTurn();
		        		
		        		gpRackOfPlayer1.setEffect(yellowShadow);
		        		
		        		// Action des trois boutons
		                btnReturnToMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerReturnToMenu(root, vbCenter, vbTop));
		                btnEndTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerEndTurn(game, gpRackOfPlayer1, gpRackOfPlayer2, gpGame, btnQuitEndingScene, root));
		                btnChangeRack.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerChangeRack(game, gpRackOfPlayer1, gpRackOfPlayer2, gpGame, btnQuitEndingScene, root));
		                
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
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setTop(btnReturnToMenu)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setLeft(vbInfoPlayer1)),
		            			new KeyFrame(Duration.seconds(7.5), e -> root.setRight(vbInfoPlayer2)),
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
        
        fileImagePlate = new File(new File("").getAbsolutePath().concat("/Theme/League of Legends/Plateau.png"));
        imgPlate = new Image(new FileInputStream(fileImagePlate));
        bgiPlate = new BackgroundImage(imgPlate, null, null, null, null);
        		
        gpGame = new GridPane();
        gpGame.setBackground(new Background(bgiPlate));
        gpGame.setPrefSize(600, 600); 
        gpGame.setPadding(new Insets(15,17,15,18));
        gpGame.setHgap(4);
        gpGame.setVgap(3);
        
        theme = "League of Legends";
        
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
        imgVRulesPlate.setY(260);
        
        txtRulesTitle = new Text("Règles du jeu :");
        txtRulesTitle.setStyle("-fx-font-weight: bold;");
        txtRulesTitle.setX(100);
        txtRulesTitle.setY(50);
        txtRulesTitle.setUnderline(true);
        
        txtRulesBodyPage1 = new Text("Le jeu débute lorsqu'un des deux joueurs (choisi aléatoirement) pose"
        		+ "\nsa première tuile sur la lune au centre du plateau de jeu."
        		+ "\n\nEn effet, tout au long de la partie, chaque joueur possède un rack avec"
        		+ "\nun maximumde 5 tuiles qui devront vider le plus vite possible."
        		+ "\n\nLa suite se déroule en posant une tuile possédant la même couleur"
        		+ "\nou le même dessin que la tuile déjà en place."
        		+ "\n\nDe plus, vous ne pouvez jouer une "
        		+ "\nautre tuile uniquement si vous avez au"
        		+ "\nmoins deux points pour un maximum"
        		+ "\nde 6 points au total."
        		+ "\n\nVous pouvez également changer"
        		+ "\nl'entièreté de votre rack au début de"
        		+ "\nvotre tour gratuitement, mais cela"
        		+ "\npassera également le tour, ou alors"
        		+ "\nsi vous avez déjà joué, cela vous"
        		+ "\ncoûtera 2 points." 
        		+ "\n\nVous ne pourrez en aucun cas jouer"
        		+ "\nen diagonal, le jeu s'effectue toujours"
        		+ "\nde haut en bas ou de droite à gauche."
        		+ "\n\nIl y a tout de même quelques subtilités, sinon cela serait trop facile,"
        		+ "\nsi sur le plateau vous avez par exemple un dauphin vert, vous ne"
        		+ "\npouvez accoler une tortue bleu ou un oiseau rouge.");
        txtRulesBodyPage1.setX(100);
        txtRulesBodyPage1.setY(90);
        
        txtRulesBodyPage2 = new Text("En revanche, toujours avec notre dauphin vert, vous pourrez tout à"
        		+ "\nfait accoler une tortue vert ou un dauphin bleu, je m'explique,"
        		+ "\nla tortue possède la bonne couleur, et le dauphin le bon dessin."
        		+ "\n\nUne fois toutes les tuiles d'un joueur posées sur le plateau, ce dernier"
        		+ "\nsera désigné vainqueur !"
        		+ "\n\nCe jeu Latice allie la stratégie et le hasard, ce qui vous permettra de"
        		+ "\npasser d'excellent moment entre stratèges avertis ou au sein de votre"
        		+ "\nfamille."
        		+ "\n\nLes enfants encadrés pouvant jouer aisément."
        		+ "\n\nIl vous sera nécessaire de faire une partie ou deux pour assimiler"
        		+ "\ntoutes les petites subtilités des règles du jeu."
        		+ "\n\nLes parties peuvent être assez rapide, car il y a un maximum de dix "
        		+ "\ntours par joueur, ce qui est un avantage car contrairement à"
        		+ "\nbeaucoup de jeu, il ne nécessite pas un long moment"
        		+ "\nde disponibilité, ce qui évite d'abandonner une partie en cours de jeu"
        		+ "\npar manque de temps."
        		+ "\n\nEn conclusion, c'est un jeu ludique, toutes les finitions sont parfaites"
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
        
        imgVLeague = new ImageView(imgBackground);
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
		mediaMusic.setCycleCount(Integer.MAX_VALUE);
			
			// Actions des images THEME
			imgVLeague.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVLeague, imgVBeach, imgVIndian, imgVHp, mainBackground, imgBackground, root,"League of Legends/Imagine Dragons x JID  Enemy Lyrics.mp3", pgbMusic, mediaMusic, "League of Legends", gpGame));
			imgVBeach.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVBeach, imgVLeague, imgVIndian, imgVHp, bgiBeach, imgBeach, root,"Plage/Calvin Harris  Summer Audio.mp3", pgbMusic, mediaMusic, "Plage", gpGame));
			imgVIndian.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVIndian, imgVBeach, imgVLeague, imgVHp, bgiIndian, imgIndian, root, "Indien/Panjabi MC  Mundian To Bach Ke The Dictator Soundtrack.mp3", pgbMusic, mediaMusic, "Indien", gpGame));
			imgVHp.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageViewController(imgVHp, imgVBeach, imgVIndian, imgVLeague, bgiHp, imgHp, root, "Harry Potter/Harry Potter Theme Song.mp3", pgbMusic, mediaMusic, "Harry Potter", gpGame));
			
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
