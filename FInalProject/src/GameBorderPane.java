import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Main UI layout container for the game window.
 * Uses BorderPane to organize game elements into distinct regions.
 * Manages the overall game interface structure and component placement.
 */
public class GameBorderPane extends BorderPane {
    
    /**
     * The game canvas where all animations and graphics are rendered.
     * Central display area for the actual gameplay visuals.
     */
    private GameWorld gameWorld = new GameWorld();
    
    /**
     * Controller that manages game logic and coordinates animations.
     * Handles the business logic separate from the UI rendering.
     */
    private GameController gameController;
    
    /**
     * Constructor that sets up the entire game interface.
     * Creates all UI regions and initializes the game state.
     */
    public GameBorderPane() {
      
        // Create an instance of GameController and pass it GameWorld
        // This establishes the MVC pattern connection
        gameController = new GameController(gameWorld);
        gameController.initializeScene();      // Draw initial game state
        gameController.startMinotaurApproach(); // Begin minotaur walking animation
        
        // TOP REGION: Game information display
        // Will hold score, level, and other game-wide stats
        Pane topPane = new Pane();
        topPane.setPrefHeight(50);  // Fixed height for consistency
        // Bottom border only to separate from game area
        topPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0;");    
        setTop(topPane);
        
        // LEFT REGION: Player statistics panel
        // Shows health, inventory, and other player-specific information
        VBox leftPane = new VBox();
        leftPane.setPrefWidth(200);   // Fixed width for stat display
        leftPane.setMinWidth(200);    // Prevent shrinking
        // Right border only to separate from game area
        leftPane.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");
        setLeft(leftPane);
        
        // HBox to hold hit point labels horizontally
        // Displays "Hitpoints: " followed by the current value
        HBox hitPointsHBox = new HBox();
        Label hitPointsLabel = new Label("Hitpoints: ");
        Label currentHitPoints = new Label(String.valueOf(gameController.getPlayer().getHitPoints()));
        hitPointsHBox.getChildren().addAll(hitPointsLabel, currentHitPoints);
        
        // HBox to hold attack points labels horizontally
        // Displays "Attack Points: " followed by the value
        HBox attackPointsHBox = new HBox();
        Label attackPointsLabel = new Label("Attack Points: ");
        Label attackPoints = new Label(String.valueOf(gameController.getPlayer().getAttackPoints()));
        attackPointsHBox.getChildren().addAll(attackPointsLabel, attackPoints);
        
        // HBox to hold defense points labels horizontally
        // Displays "Defense: " followed by the value
        HBox defensePointsHBox = new HBox();
        Label defensePointsLabel = new Label("Defense Points: ");
        Label defensePoints = new Label(String.valueOf(gameController.getPlayer().getDefense()));
        defensePointsHBox.getChildren().addAll(defensePointsLabel, defensePoints);
        
        // VBox container for all player statistics
        // Groups related player info with a border
        VBox playerStatsVBox = new VBox();
        playerStatsVBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        playerStatsVBox.setPadding(new Insets(2));  // Small internal padding
        Label playerHeader = new Label("Player");	// Header for playerStatsVBox
        playerHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        playerStatsVBox.getChildren().addAll(playerHeader, hitPointsHBox, attackPointsHBox, defensePointsHBox);
        
        // TODO: enemyStatsVBox
        // HBox to hold hit point labels horizontally
        // Displays "Hitpoints: " followed by the current value
        HBox enemyHitPointsHBox = new HBox();
        Label enemyHitPointsLabel = new Label("Hitpoints: ");
        Label enemyCurrentHitPoints = new Label(String.valueOf(gameController.getEnemy().getHitPoints()));
        enemyHitPointsHBox.getChildren().addAll(enemyHitPointsLabel, enemyCurrentHitPoints);
        
        // HBox to hold attack points labels horizontally
        // Displays "Attack Points: " followed by the value
        HBox enemyAttackPointsHBox = new HBox();
        Label enemyAttackPointsLabel = new Label("Attack Points: ");
        Label enemyAttackPoints = new Label(String.valueOf(gameController.getEnemy().getAttackPoints()));
        enemyAttackPointsHBox.getChildren().addAll(enemyAttackPointsLabel, enemyAttackPoints);
        
        // HBox to hold defense points labels horizontally
        // Displays "Defense: " followed by the value
        HBox enemyDefensePointsHBox = new HBox();
        Label enemyDefensePointsLabel = new Label("Defense Points: ");
        Label enemyDefensePoints = new Label(String.valueOf(gameController.getEnemy().getDefense()));
        enemyDefensePointsHBox.getChildren().addAll(enemyDefensePointsLabel, enemyDefensePoints);
        
        // VBox container for all player statistics
        // Groups related player info with a border
        VBox enemyStatsVBox = new VBox();
        enemyStatsVBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        enemyStatsVBox.setPadding(new Insets(2));  // Small internal padding
        Label enemyHeader = new Label("Enemy");	// Header for playerStatsVBox
        enemyHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        enemyStatsVBox.getChildren().addAll(enemyHeader, enemyHitPointsHBox, enemyAttackPointsHBox, enemyDefensePointsHBox);
        
        
        // Configure left pane layout and styling
        leftPane.getChildren().addAll(playerStatsVBox, enemyStatsVBox);
        leftPane.setAlignment(Pos.TOP_CENTER);  // Center stats at top
        leftPane.setSpacing(10);                // Space between stat groups
        leftPane.setPadding(new Insets(10));    // Padding from edges
        leftPane.setStyle("-fx-background-color: lightgray");
        
        // CENTER REGION: Main game display
        // Canvas where all game graphics and animations are rendered
        gameWorld.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");
        setCenter(gameWorld);
        
        // RIGHT REGION: Action buttons panel
        // Contains combat options: attack, spells, flee
        VBox rightPane = new VBox();
        rightPane.setPrefWidth(200);   // Fixed width for button panel
        rightPane.setMinWidth(200);    // Prevent shrinking
        // Left border only to separate from game area
        rightPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 2;");
        rightPane.setAlignment(Pos.BOTTOM_LEFT);
        rightPane.setStyle("-fx-background-color: lightgray");

        // Button to trigger skeleton attack animation
        // When clicked, initiates the attack sequence
        Button attackButton = new Button("Attack!");
        
        // Event handler for attack button
        // Delegates to controller to maintain separation of concerns
        attackButton.setOnAction(event -> {
            gameController.startSkeletonAttack();
        });
        
        // Configure right pane layout and styling
        rightPane.getChildren().addAll(attackButton);
        rightPane.setAlignment(Pos.BOTTOM_CENTER);  // Buttons at bottom
        rightPane.setSpacing(10);                   // Space between buttons
        rightPane.setPadding(new Insets(10));       // Padding from edges
        
        setRight(rightPane);
    }
}