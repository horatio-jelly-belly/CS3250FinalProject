import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Main game logic controller that manages animations and game state.
 * Implements business logic separate from UI rendering (GameWorld).
 * Coordinates sprite animations, character positions, and game flow.
 */
public class GameController {
    // Sprites and their controllers and character instances
    
	private Label enemyHitPointsLabel;
	
    /**
     * Sprite manager for skeleton character images.
     * Handles loading and accessing skeleton animation frames.
     */
    private CharacterSprite skeletonSprite;
    
    /**
     * Sprite manager for minotaur enemy images.
     * Handles loading and accessing minotaur animation frames.
     */
    private CharacterSprite minotaurSprite;
    
    /**
     * Animation timing controller for skeleton.
     * Manages frame progression and timing for skeleton animations.
     */
    private AnimationController skeletonController;
    
    /**
     * Animation timing controller for minotaur.
     * Manages frame progression and timing for minotaur animations.
     */
    private AnimationController minotaurController;
    
    /**
     * The enemy character instance representing the minotaur.
     * Contains combat statistics (HP, attack, defense, speed) for the minotaur enemy.
     * This is the logical game entity, separate from its visual representation (minotaurSprite).
     * Used for combat calculations, damage tracking, and determining defeat conditions.
     */
    private Enemy minotaurEnemy;

    /**
     * The player character instance representing the skeleton warrior.
     * Contains combat statistics (HP, attack, defense, speed) for the player-controlled character.
     * This is the logical game entity, separate from its visual representation (skeletonSprite).
     * Used for combat calculations, health display, and player progression.
     */
    private Player skeletonPlayer;
    
    // Position tracking for characters
    
    /**
     * Skeleton's X coordinate on the game canvas.
     * Measured in pixels from left edge of canvas.
     */
    private double skeletonX = 200;
    
    /**
     * Skeleton's Y coordinate on the game canvas.
     * Measured in pixels from top edge of canvas.
     */
    private double skeletonY = 350;
    
    /**
     * Minotaur's X coordinate on the game canvas.
     * Starts off-screen to the right (800 > 700 canvas width).
     */
    private double minotaurX = 800;
    
    /**
     * Minotaur's Y coordinate on the game canvas.
     * Same vertical level as skeleton for combat alignment.
     */
    private double minotaurY = 350;
    
    /**
     * Reference to the GameWorld canvas for rendering.
     * Used to trigger redraws when animation frames update.
     */
    private GameWorld gameWorld;
    
    /**
     * JavaFX animation timer for the main game loop.
     * Calls update methods 60 times per second for smooth animation.
     */
    private AnimationTimer gameLoop;
    
    // Game state flags
    
    /**
     * Flag indicating if minotaur is currently walking.
     * Used to control minotaur movement and animation updates.
     */
    private boolean isMinotaurWalking = false;
    
    /**
     * Flag indicating if skeleton is currently attacking.
     * Used to control skeleton attack animation updates.
     */
    private boolean isSkeletonAttacking = false;
    
    /**
     * Constructor that establishes the controller-view relationship.
     * @param gameWorld The canvas where graphics will be rendered
     */
    public GameController(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        
        initializeSprites();     // Load sprite images
        initializeCharacters();  // Create enemy objects
        initializeControllers(); // Create animation controllers
    }
    
    public void setEnemyHitPointsLabel(Label label) {
    	this.enemyHitPointsLabel = label;
    }
    
    /**
     * Creates character instances with their combat statistics.
     * Initializes both player and enemy characters for the game session.
     */
    private void initializeCharacters() {
        minotaurEnemy = new Enemy(150, 20, 15);      // HP, Attack, Defense
        skeletonPlayer = new Player(200, 35, 25);   // HP, Attack, Defense
    }

	/**
     * Creates and configures sprite managers for both characters.
     * Loads all animation frames into memory for quick access.
     */
    private void initializeSprites() {
        // Create the skeleton sprite
        // 12 frames, starting at index 1, no padding in filenames
        skeletonSprite = new CharacterSprite("images/skeleton/", "skeleton_", 12, 1, false);
        
        // Create the minotaur sprite  
        // 18 frames, starting at index 0, with zero-padding in filenames
        minotaurSprite = new CharacterSprite("images/minotaur/", "Minotaur_01_Walking_", 18, 0, true);
    }
    
    /**
     * Creates animation controllers for timing frame advances.
     * Matches controller parameters to sprite indexing schemes.
     */
    private void initializeControllers() {
        skeletonController = new AnimationController(12, 1);  // 12 frames, starts at 1
        minotaurController = new AnimationController(18, 0);  // 18 frames, starts at 0
    }
    
    /**
     * Initiates skeleton attack animation sequence.
     * Called when attack button is pressed by player.
     * Only starts if skeleton is in range of the enemy
     * and there is an enemy to attack.
     */
    public void startSkeletonAttack() {
        // Check conditions: not already attacking and enemy is close enough and there is an enemy to attack
        if (!isSkeletonAttacking && isInAttackRange() && minotaurEnemy.isAlive()) {
            isSkeletonAttacking = true;           // Set attack state flag
            skeletonController.resetAnimation();  // Start from first frame
            startGameLoop();                      // Begin animation updates
        }
    }
    
    /**
     * Initiates minotaur walking onto the scene.
     * Called during game initialization to start enemy approach.
     */
    public void startMinotaurApproach() {
        if (!isMinotaurWalking) {
            isMinotaurWalking = true;             // Set walking state flag
            minotaurController.resetAnimation();  // Start from first frame
            startGameLoop();                      // Begin animation updates
        }
    }
    
    /**
     * Creates and starts the main game animation loop.
     * Uses JavaFX AnimationTimer for smooth 60 FPS updates.
     * Manages all active animations and redraws.
     */
    private void startGameLoop() {
        // Stop any existing game loop to prevent multiple timers
        if (gameLoop != null) {
            gameLoop.stop();
        }
        
        gameLoop = new AnimationTimer() {
            /**
             * Called approximately 60 times per second by JavaFX.
             * @param now Current time in nanoseconds
             */
            @Override
            public void handle(long now) {
                boolean needsRedraw = false;  // Track if any visual changes occurred
                
                // Update skeleton animation if attacking
                if (isSkeletonAttacking && skeletonController.update(now)) {
                    needsRedraw = true;  // Frame changed, need to redraw
                    
                    // Check if attack animation finished
                    if (skeletonController.isAnimationComplete()) {
                    	minotaurEnemy.takeDamage(skeletonPlayer.getAttackPoints());		// When animation is complete reduce minotaur's hitpoints
                    	enemyHitPointsLabel.setText(String.valueOf(minotaurEnemy.getHitPoints()));
                        isSkeletonAttacking = false;      // Clear attack flag
                        skeletonController.resetAnimation(); // Ready for next attack
                        
                    }
                }
                
                // Update minotaur animation and position if walking
                if (isMinotaurWalking && minotaurController.update(now)) {
                    needsRedraw = true;  // Frame changed, need to redraw
                    updateMinotaurPosition();  // Also move the minotaur
                    
                    // Reset walk cycle when it completes to continue walking
                    if (minotaurController.isAnimationComplete()) {
                        minotaurController.resetAnimation();
                    }
                }
                
                // Only redraw canvas if something changed (optimization)
                if (needsRedraw) {
                    gameWorld.drawScene(GameController.this);
                }
                
                // Stop game loop if nothing is animating (optimization)
                if (!isSkeletonAttacking && !isMinotaurWalking) {
                    gameLoop.stop();
                }
            }
        };
        
        gameLoop.start();  // Begin the animation loop
    }
    
    /**
     * Updates minotaur position during walking animation.
     * Moves minotaur leftward toward the skeleton.
     * Stops when within attack range.
     */
    private void updateMinotaurPosition() {
        // Move minotaur to the left (toward skeleton)
        minotaurX -= 3;  // 3 pixels per frame for smooth movement
        
        // Stop walking when close enough to attack
        if (isInAttackRange()) {
            isMinotaurWalking = false;  // Stop walk animation
        }
    }
    
    /**
     * Checks if characters are close enough for combat.
     * Used to enable attacks and stop enemy approach.
     * @return true if within attack range, false otherwise
     */
    private boolean isInAttackRange() {
        double distance = Math.abs(skeletonX - minotaurX);
        return distance < 225;  // 225 pixels is close enough for melee combat
    }
    
    // Getters for GameWorld to access when drawing
    // These allow GameWorld to read game state without modifying it
    
    /**
     * Gets skeleton sprite manager for rendering.
     * @return The skeleton CharacterSprite instance
     */
    public CharacterSprite getSkeletonSprite() { 
        return skeletonSprite; 
    }

    /**
     * Gets minotaur sprite manager for rendering.
     * @return The minotaur CharacterSprite instance
     */
    public CharacterSprite getMinotaurSprite() { 
        return minotaurSprite; 
    }

    /**
     * Gets skeleton animation controller for current frame.
     * @return The skeleton AnimationController instance
     */
    public AnimationController getSkeletonController() { 
        return skeletonController; 
    }

    /**
     * Gets minotaur animation controller for current frame.
     * @return The minotaur AnimationController instance
     */
    public AnimationController getMinotaurController() { 
        return minotaurController; 
    }

    /**
     * Gets skeleton X position for rendering.
     * @return X coordinate in pixels
     */
    public double getSkeletonX() { 
        return skeletonX; 
    }

    /**
     * Gets skeleton Y position for rendering.
     * @return Y coordinate in pixels
     */
    public double getSkeletonY() { 
        return skeletonY;  
    }

    /**
     * Gets minotaur X position for rendering.
     * @return X coordinate in pixels
     */
    public double getMinotaurX() { 
        return minotaurX; 
    }

    /**
     * Gets minotaur Y position for rendering.
     * @return Y coordinate in pixels
     */
    public double getMinotaurY() { 
        return minotaurY; 
    }

    /**
     * Draws the initial game scene before any animations.
     * Called during initialization to show starting positions.
     */
    public void initializeScene() {
        gameWorld.drawScene(this);
    }
    
    /**
     * Gets the player character for UI display and game logic.
     * Allows other classes to read player stats without modifying them.
     * @return The player character instance
     */
    public Player getPlayer() {
        return skeletonPlayer;
    }
    
    /**
     * Gets the enemy character for UI display and game logic.
     * Allows other classes to read enemy stats without modifying them.
     * @return The enemy character instance
     */
    public Enemy getEnemy() {
    	return minotaurEnemy;
    }
    
}