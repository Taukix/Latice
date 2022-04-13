package latice.application.view;

import java.io.File;
import java.io.FileInputStream;

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
	
	// PARAMETRES
	private Button buttonAudioParameters;
	private Button buttonLangueParameters;
	private Button buttonGeneralParameters;
	private Button buttonQuitParameters;
	private Rectangle recParameters;
	
		// GROUP AUDIO
	private GridPane gpParametersAudio;
	private ProgressBar pgbMusic;
	private Button btnProgressBarMusicDown;
	private Button btnProgressBarMusicUp;
	private Label lblProgressBarMusic;
	private MediaPlayer media;
	private CheckBox chbxMusic;
	private static double VOLUME;
	
	private ProgressBar pgbSoundEffect;
	private Button btnProgressBarSoundEffectDown;
	private Button btnProgressBarSoundEffectUp;
	private Label lblProgressBarSoundEffect;
	private CheckBox chbxEffect;
	
	// REGLES
	private Text txtRulesTitle;
	private Text txtRulesBodyPage1;
	private Text txtRulesBodyPage2;
	private Button buttonQuitRules;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		// Ajout du fond d'�cran
		File fileBg = new File("C:\\Users\\tommy\\eclipse-workspace\\latice\\src\\FOND.jpg");
		Image imgBg = new Image(new FileInputStream(fileBg));
		ImageView imgVBg = new ImageView(imgBg);
		BackgroundImage bgi = new BackgroundImage(imgBg, null, null, null, null);
		root.setBackground(new Background(bgi));
			
		// Impl�mentation du TOP
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
		
		Button buttonRules = new Button("REGLES");
		buttonRules.setPadding(new Insets(7,297,7,297));
		buttonRules.setStyle("-fx-background-color: #FFF; ");
        
        Button buttonParameters = new Button("PARAMETRES");
        buttonParameters.setPadding(new Insets(7,281,7,281));
        buttonParameters.setStyle("-fx-background-color: #FFF; ");
        
        Button buttonQuitMenu = new Button("QUITTER");
        buttonQuitMenu.setPadding(new Insets(7,294,7,294));
        buttonQuitMenu.setStyle("-fx-background-color: #FFF; ");
        
        vbCenter = new VBox();
        vbCenter.getChildren().addAll(buttonPlay,buttonRules,buttonParameters,buttonQuitMenu);
        vbCenter.setAlignment(Pos.CENTER);
        vbCenter.setSpacing(30);
        
        		//SHADOWS BUTTONS
        		DropShadow shadow = new DropShadow();
        		shadow.setRadius(20);
        		shadow.setColor(Color.WHITESMOKE);

        		// Ajoute l'ombre quand le curseur est dessus
        		buttonPlay.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonPlay.setEffect(shadow);}});

        		// Enleve l'ombre quand le curseur n est plus dessus
        		buttonPlay.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonPlay.setEffect(null);}});
        
        		buttonRules.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonRules.setEffect(shadow);}});

        		buttonRules.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonRules.setEffect(null);}});
        
        		buttonParameters.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonParameters.setEffect(shadow);}});

        		buttonParameters.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonParameters.setEffect(null);}});
        		
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonQuitMenu.setEffect(shadow);}});

        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent e) {
        				buttonQuitMenu.setEffect(null);}});
        		
        		// Action des BOUTONS
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
        		
        		buttonQuitMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		        	@Override
		        	public void handle(MouseEvent e) {
		        		((Stage) root.getScene().getWindow()).close();;}});
        		
        // Impl�mentation du GROUP PARAMETRE et de ses composants
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
        	
        	btnProgressBarMusicUp = new Button("Augmenter");
        	btnProgressBarMusicDown = new Button("Diminuer");
        	
        	chbxMusic = new CheckBox("Sourdine");
        		
        		// EFFETS
        	lblProgressBarSoundEffect = new Label("Effets :");
        	lblProgressBarSoundEffect.setStyle("-fx-font: 18 arial;");
        	
        	pgbSoundEffect = new ProgressBar(0.5);
        	pgbSoundEffect.setPrefHeight(15);
        	pgbSoundEffect.setPrefWidth(200);
        	
        	btnProgressBarSoundEffectUp = new Button("Augmenter");
        	btnProgressBarSoundEffectDown = new Button("Diminuer");

        	chbxEffect = new CheckBox("Sourdine");
        	
        	gpParametersAudio = new GridPane();
        	gpParametersAudio.add(lblProgressBarMusic, 0, 0);
        	gpParametersAudio.add(pgbMusic, 0, 1);
        	gpParametersAudio.add(btnProgressBarMusicDown, 1, 1);
        	gpParametersAudio.add(btnProgressBarMusicUp, 2, 1);
        	gpParametersAudio.add(chbxMusic, 3, 1);
        	gpParametersAudio.add(lblProgressBarSoundEffect, 0, 2);
        	gpParametersAudio.add(pgbSoundEffect, 0, 3);
        	gpParametersAudio.add(btnProgressBarSoundEffectDown, 1, 3);
        	gpParametersAudio.add(btnProgressBarSoundEffectUp, 2, 3);
        	gpParametersAudio.add(chbxEffect, 3, 3);
        	gpParametersAudio.setLayoutX(60);
        	gpParametersAudio.setLayoutY(80);
        	gpParametersAudio.setMargin(pgbMusic, new Insets(0,20,0,0));
        	gpParametersAudio.setMargin(lblProgressBarMusic, new Insets(0,0,20,0));
        	gpParametersAudio.setMargin(lblProgressBarSoundEffect, new Insets(80,0,20,0));
        	gpParametersAudio.setMargin(chbxMusic, new Insets(0,0,0,60));
        	gpParametersAudio.setMargin(chbxEffect, new Insets(0,0,0,60));
        	gpParametersAudio.setVisible(false);
        
        		// Action des boutons de la zone PARAMETRE
        		buttonGeneralParameters.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				buttonGeneralParameters.setStyle("-fx-background-color: #DCDCDC");
        				buttonAudioParameters.setStyle("-fx-background-color: #FFF");
        				buttonLangueParameters.setStyle("-fx-background-color: #FFF");
        				gpParametersAudio.setVisible(false);}});
        		
        		buttonAudioParameters.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				buttonAudioParameters.setStyle("-fx-background-color: #DCDCDC");
        				buttonGeneralParameters.setStyle("-fx-background-color: #FFF");
        				buttonLangueParameters.setStyle("-fx-background-color: #FFF");
        				gpParametersAudio.setVisible(true);}});
        		
        		buttonLangueParameters.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				buttonLangueParameters.setStyle("-fx-background-color: #DCDCDC");
        				buttonGeneralParameters.setStyle("-fx-background-color: #FFF");
        				buttonAudioParameters.setStyle("-fx-background-color: #FFF");
        				gpParametersAudio.setVisible(false);}});
        				
        		buttonQuitParameters.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				root.setCenter(vbCenter);}});
        		
        		// Action des boutons AUDIO
        		btnProgressBarMusicDown.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				if (pgbMusic.getProgress() > 0) {
        				pgbMusic.setProgress(pgbMusic.getProgress() - 0.1);
        				media.setVolume(media.getVolume() - 0.1);}}});
        		
        		btnProgressBarMusicUp.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				if (chbxMusic.isSelected()) {
        					return;
        				} else if (pgbMusic.getProgress() < 1) {
        				pgbMusic.setProgress(pgbMusic.getProgress() + 0.1);
        				media.setVolume(media.getVolume() + 0.1);}}});
        		
        		chbxMusic.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				if (chbxMusic.isSelected()) {
        					VOLUME = pgbMusic.getProgress();
        					pgbMusic.setProgress(0);
        					media.setVolume(0);
        				} else {
        					pgbMusic.setProgress(VOLUME);
        					media.setVolume(VOLUME);
        				}
        			}
        		});
        		
        		btnProgressBarSoundEffectDown.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				if (pgbSoundEffect.getProgress() > 0) {
        					pgbSoundEffect.setProgress(pgbSoundEffect.getProgress() - 0.05);}}});
        		
        		btnProgressBarSoundEffectUp.setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent arg0) {
        				if (pgbSoundEffect.getProgress() < 1) {
        					pgbSoundEffect.setProgress(pgbSoundEffect.getProgress() + 0.05);}}});
        		
        groupParameters.getChildren().addAll(recParameters,buttonGeneralParameters,buttonAudioParameters,buttonLangueParameters, gpParametersAudio);
		vbParametersCenter.getChildren().addAll(groupParameters,buttonQuitParameters);
		vbParametersCenter.setAlignment(Pos.CENTER);
		vbParametersCenter.setSpacing(20);
		
		// Impl�mentation du GROUP REGLES et de ses composants
		groupRules = new Group();
		vbRulesCenter = new VBox();
		
				//Image du livre vierge ouvert
		File fileBook = new File("C:\\Users\\tommy\\eclipse-workspace\\latice\\src\\LIVRE.png");
		Image imgBook = new Image(new FileInputStream(fileBook));
		ImageView imgVBook = new ImageView(imgBook);
		
				// Image du plateau sur le livre
		File fileRulesPlate = new File("C:\\Users\\tommy\\eclipse-workspace\\latice\\src\\PlateauLatice.jpg");
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
						root.setCenter(vbCenter);}});
		
		groupRules.getChildren().addAll(imgVBook,imgVRulesPlate,txtRulesTitle, txtRulesBodyPage1,txtRulesBodyPage2);
		vbRulesCenter.getChildren().addAll(groupRules,buttonQuitRules);
		vbRulesCenter.setAlignment(Pos.CENTER);
		vbRulesCenter.setSpacing(20);
		
		// Mise en place de la musique de fond
		String uriString = new File("C:\\Users\\tommy\\eclipse-workspace\\latice\\Music\\Oneeva  Platform 9 NCS Release.mp3").toURI().toString();
		media = new MediaPlayer( new Media(uriString));
		media.setVolume(0.7);
		media.play();
		
		// Mise en place des �l�ments dans le BorderPane 
		root.setTop(vbTop);
		root.setMargin(vbTop, new Insets(20,0,0,0));
		
		root.setCenter(vbCenter);
		
		// Mise en place de la sc�ne
		Scene scene = new Scene(root, 1920, 1010);
		primaryStage.setTitle("Application Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
