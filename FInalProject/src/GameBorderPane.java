import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameBorderPane extends BorderPane {
	// I asked Claude where to instantiate an object of the player class because I didn't 
	// know if I should do it here, or in Main.java. Claude said to do it here.
	private Player player;
	private GameWorld gameWorld = new GameWorld();
	
	public GameBorderPane() {
		// Create an instance of Player
		player = new Player("Hero", 100, 15, 10, 12, true);
		
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
		Label hitPoints = new Label("Hitpoints:");
		Label currentHitPoints = new Label(String.valueOf(player.getHitPoints()));
		hitPointsHBox.getChildren().addAll(hitPoints, currentHitPoints);
		
		leftPane.getChildren().add(hitPointsHBox);
		leftPane.setAlignment(Pos.TOP_CENTER);
		leftPane.setSpacing(10);
		leftPane.setPadding(new Insets(10));
		
		// To hold the actual game animation itself
		gameWorld.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");
		setCenter(gameWorld);
		
		// To hold the option to choose a spell, fight or flee
		VBox rightPane = new VBox();
		rightPane.setPrefWidth(200);
		rightPane.setMinWidth(200);
		rightPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 2;");
		rightPane.setAlignment(Pos.BOTTOM_LEFT);
		
		// Button to attack the enemy
		Button attackButton = new Button("Attack!");
		
		// Radio buttons so that player can choose which spell to cast
		Label chooseSpellLabel = new Label("Choose your spell");
		ToggleGroup toggleGroup = new ToggleGroup();
		
		// Different spells to cast
		RadioButton fireRadioButton = new RadioButton("Fire");
		fireRadioButton.setToggleGroup(toggleGroup);
		
		RadioButton iceRadioButton = new RadioButton("Ice");
		iceRadioButton.setToggleGroup(toggleGroup);
		
		// Put nodes in right pane with styling
		rightPane.getChildren().addAll(chooseSpellLabel, fireRadioButton, iceRadioButton, attackButton);
		rightPane.setAlignment(Pos.BOTTOM_CENTER);
		rightPane.setSpacing(10);
		rightPane.setPadding(new Insets(10));
		
		setRight(rightPane);
		 
	}

}
