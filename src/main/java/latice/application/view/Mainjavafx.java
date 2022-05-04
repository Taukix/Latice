package latice.application.view;

import java.io.File;
import java.io.FileInputStream;

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
import latice.application.controller.ButtonControllerCloseApplication;
import latice.application.controller.ButtonControllerParametersMenu;
import latice.application.controller.ButtonControllerShadowOff;
import latice.application.controller.ButtonControllerShadowOn;
import latice.application.controller.ButtonControllerSoundOff;
import latice.application.controller.ButtonControllerSoundOn;
import latice.application.controller.ProgressBarAnimation;

public class Mainjavafx extends Application {
	
	// Initilisation des conteneurs
	private BorderPane root;
	
	private VBox vbCenter;
	
	private VBox vbTop;
	
	private Group groupParameters;
	private VBox vbParametersCenter;
	
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
	
	// JOUER
		// SCENE DE CHARGEMENT
	private ProgressBar pgbLoadingScene;
	private Timeline tlLoadingScene;
	private Timeline tlPgbBarLoadingScene;
	private HBox hbLoadingScene;
	private VBox vbLoadingScene;
	private Label lblLoadingScene;
	private Label lblPoint1;
	private Label lblPoint2;
	private Label lblPoint3;
	private VBox vbTopLeftLoadingScene;
	private Label lblLaticeLoadingScene;
	private Label lblSimpleGameLoadingScene;
	
		// JEU
	private VBox vbPlateGame;
	private Group gpPlate;
	private Button btnQuitGame;
	private Timeline tlPlaySceneChange;
	
	// PARAMETRES
	private Button buttonAudioParameters;
	private Button buttonLangueParameters;
	private Button buttonGeneralParameters;
	private Button buttonQuitParameters;
	private Rectangle recParameters;
	
		// GROUP AUDIO
	private GridPane gpParametersAudio;
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
	
	// REGLES
	private Text txtRulesTitle;
	private Text txtRulesBodyPage1;
	private Text txtRulesBodyPage2;
	private Button buttonQuitRules;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		// Ajout du fond d'écran
		File fileBg = new File(new File("").getAbsolutePath().concat("/Image/FOND.jpg"));
		Image imgBg = new Image(new FileInputStream(fileBg));
		BackgroundImage bgi = new BackgroundImage(imgBg, null, null, null, null);
		root.setBackground(new Background(bgi));
		
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
		                root.setCenter(vbParametersCenter);
		                root.setMargin(vbParametersCenter, new Insets(0,0,100,0));
		                buttonGeneralParameters.setStyle("-fx-background-color: #DCDCDC");}});
        		
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonControllerCloseApplication(root));
        		
        // Implémentation du GROUP PLAY et de ses composants
        vbPlateGame = new VBox();
        gpPlate = new Group();
        
        File filePlate = new File(new File("").getAbsolutePath().concat("/Image/Plateau.png"));
		Image imgPlate = new Image(new FileInputStream(filePlate));
		ImageView imgVPlate = new ImageView(imgPlate);
		
		imgVPlate.setFitHeight(700);
		imgVPlate.setFitWidth(700);
		
		gpPlate.getChildren().add(imgVPlate);
        
        btnQuitGame = new Button("REVENIR AU MENU PRINCIPAL");
        btnQuitGame.setPadding(new Insets(7,100,7,100));
        btnQuitGame.setStyle("-fx-background-color: #FFF; ");
        
        	// Action du bouton
        	btnQuitGame.setOnAction(new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent arg0) {
        			root.setCenter(vbCenter);
        			root.setTop(vbTop);
        		}
        	});
        
        vbPlateGame.getChildren().addAll(gpPlate,btnQuitGame);
        vbPlateGame.setAlignment(Pos.CENTER);
        vbPlateGame.setSpacing(50);
        		
        // Implémentation du GROUP PARAMETRE et de ses composants
        groupParameters = new Group();
        vbParametersCenter = new VBox();
        
        recParameters = new Rectangle(800,400);
        recParameters.setFill(Color.WHITESMOKE);
        
        buttonGeneralParameters = new Button("GENERAL");
        buttonGeneralParameters.setPadding(new Insets(7,110,7,110));
        buttonGeneralParameters.setStyle("-fx-background-color: #FFF; ");
        buttonGeneralParameters.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        buttonAudioParameters = new Button("AUDIO");
        buttonAudioParameters.setPadding(new Insets(7,115,7,115));
        buttonAudioParameters.setStyle("-fx-background-color: #FFF; ");
        buttonAudioParameters.setLayoutX(270);
        buttonAudioParameters.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        buttonLangueParameters = new Button("LANGUE");
        buttonLangueParameters.setPadding(new Insets(7,110,7,110));
        buttonLangueParameters.setStyle("-fx-background-color: #FFF; ");
        buttonLangueParameters.setLayoutX(533);
        buttonLangueParameters.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        buttonQuitParameters = new Button("REVENIR AU MENU PRINCIPAL");
        buttonQuitParameters.setPadding(new Insets(7,100,7,100));
        buttonQuitParameters.setStyle("-fx-background-color: #FFF; ");
        
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
        	
        	gpParametersAudio = new GridPane();
        	gpParametersAudio.add(lblProgressBarMusic, 0, 0);
        	gpParametersAudio.add(pgbMusic, 0, 1);
        	gpParametersAudio.add(sliderMusic, 1, 1);
        	gpParametersAudio.add(chbxMusic, 2, 1);
        	gpParametersAudio.add(lblProgressBarSoundEffect, 0, 2);
        	gpParametersAudio.add(pgbSoundEffect, 0, 3);
        	gpParametersAudio.add(sliderEffect, 1, 3);
        	gpParametersAudio.add(chbxEffect, 2, 3);
        	gpParametersAudio.setLayoutX(60);
        	gpParametersAudio.setLayoutY(80);
        	gpParametersAudio.setMargin(pgbMusic, new Insets(0,20,0,0));
        	gpParametersAudio.setMargin(lblProgressBarMusic, new Insets(0,0,20,0));
        	gpParametersAudio.setMargin(lblProgressBarSoundEffect, new Insets(80,0,20,0));
        	gpParametersAudio.setMargin(sliderMusic, new Insets(0,60,0,30));
        	gpParametersAudio.setMargin(sliderEffect, new Insets(0,60,0,30));
        	gpParametersAudio.setVisible(false);
        
        		// Action des boutons de la zone PARAMETRE
        		buttonGeneralParameters.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonControllerParametersMenu(buttonGeneralParameters, buttonAudioParameters, buttonLangueParameters, gpParametersAudio, false));
        		buttonAudioParameters.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonControllerParametersMenu(buttonAudioParameters, buttonGeneralParameters, buttonLangueParameters, gpParametersAudio, true));
        		buttonLangueParameters.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonControllerParametersMenu(buttonLangueParameters, buttonAudioParameters, buttonGeneralParameters, gpParametersAudio, false));
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
        		
        groupParameters.getChildren().addAll(recParameters,buttonGeneralParameters,buttonAudioParameters,buttonLangueParameters, gpParametersAudio);
		vbParametersCenter.getChildren().addAll(groupParameters,buttonQuitParameters);
		vbParametersCenter.setAlignment(Pos.CENTER);
		vbParametersCenter.setSpacing(20);
		
		// Implémentation du GROUP REGLES et de ses composants
		groupRules = new Group();
		vbRulesCenter = new VBox();
		
				//Image du livre vierge ouvert
		File fileBook = new File(new File("").getAbsolutePath().concat("/Image/LIVRE.png"));
		Image imgBook = new Image(new FileInputStream(fileBook));
		ImageView imgVBook = new ImageView(imgBook);
		
				// Image du plateau sur le livre
		File fileRulesPlate = new File(new File("").getAbsolutePath().concat("/Image/PlateauLatice.jpg"));
		Image imgRulesPlate = new Image(new FileInputStream(fileRulesPlate));
		ImageView imgVRulesPlate = new ImageView(imgRulesPlate);
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
		
		// Mise en place de la musique de fond
		String uriString = new File(new File("").getAbsolutePath().concat("/Music/Jerk it out  Lets go fixed remix.mp3")).toURI().toString();
		mediaMusic = new MediaPlayer(new Media(uriString));
		mediaMusic.volumeProperty().bind(pgbMusic.progressProperty());
		mediaMusic.play();
		
		// Mise en place des effets sonore
		String uriStringEffects = new File(new File("").getAbsolutePath().concat("/Music/Jerk it out  Lets go fixed remix.mp3")).toURI().toString();
		mediaEffects = new MediaPlayer( new Media(uriStringEffects));
		mediaEffects.volumeProperty().bind(pgbSoundEffect.progressProperty());
		
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
