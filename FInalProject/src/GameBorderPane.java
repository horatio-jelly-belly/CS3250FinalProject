import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameBorderPane extends BorderPane {
	// I asked Claude where to instantiate an object of the player class because I didn't 
	// know if I should do it here, or in Main.java. Claude said to do it here.
	private Player player;
	private GameWorld gameWorld = new GameWorld();
	private GameController gameController;
	
	
	public GameBorderPane() {
		// Create an instance of Player
		player = new Player(100, 15, 10, 12);
		
		// Create an instance of GameController and pass it GameWorld
		gameController = new GameController(gameWorld);
		gameController.initializeScene();
		gameController.startMinotaurApproach();
		
		// To hold game information such as score and level of player
		Pane topPane = new Pane();
		topPane.setPrefHeight(50);
		topPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0;");	
		setTop(topPane);
		
		// To hold player and enemy statistics such as health level and inventory items.
		VBox leftPane = new VBox();
		leftPane.setPrefWidth(200);
		leftPane.setMinWidth(200);
		leftPane.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");
		setLeft(leftPane);
		
		// HBox to hold hit point labels
		HBox hitPointsHBox = new HBox();
		Label hitPointsLabel = new Label("Hitpoints: ");
		Label currentHitPoints = new Label(String.valueOf(player.getHitPoints()));
		hitPointsHBox.getChildren().addAll(hitPointsLabel, currentHitPoints);
		
		// VBox for Player statistics
		VBox playerStatsVBox = new VBox();
		playerStatsVBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		playerStatsVBox.setPadding(new Insets(2));
		playerStatsVBox.getChildren().addAll(hitPointsHBox);
		
		leftPane.getChildren().add(playerStatsVBox);
		leftPane.setAlignment(Pos.TOP_CENTER);
		leftPane.setSpacing(10);
		leftPane.setPadding(new Insets(10));
		leftPane.setStyle("-fx-background-color: lightgray");
		
		// To hold the actual game animation itself
		gameWorld.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");
		setCenter(gameWorld);
		
		// To hold the option to choose a spell, fight or flee
		VBox rightPane = new VBox();
		rightPane.setPrefWidth(200);
		rightPane.setMinWidth(200);
		rightPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 2;");
		rightPane.setAlignment(Pos.BOTTOM_LEFT);
		rightPane.setStyle("-fx-background-color: lightgray");

		// Button to attack the enemy
		Button attackButton = new Button("Attack!");
		
		// Start the animation when attackButton is called
		attackButton.setOnAction(event -> {
			gameController.startSkeletonAttack();
		});
		
		
		// Put nodes in right pane with styling
		rightPane.getChildren().addAll(attackButton);
		rightPane.setAlignment(Pos.BOTTOM_CENTER);
		rightPane.setSpacing(10);
		rightPane.setPadding(new Insets(10));
		
		setRight(rightPane);
		 
	}
}