import javafx.animation.AnimationTimer;

public class GameController {
	// Sprites and their controllers
	private CharacterSprite skeletonSprite;
	private CharacterSprite minotaurSprite;
	private AnimationController skeletonController;
	private AnimationController minotaurController;
	
	// Position tracking for characters
	private double skeletonX = 500;
	private double skeleonty = 350;
	private double minotaurX = -100;
	private double minotaurY = 350;
	
	// Reference to the GameWorld for rendering
	private GameWorld gameWorld;
	
	// Animation timer for the game loop
	private AnimationTimer gameLoop;
	
	// Game state
	private boolean isMinotaurWalking = false;
	private boolean isSkeletonAttacking = false;
	
	public GameController(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		initializeSprites();
		initializeControllers();
	}
	
	private void initializeSprites() {
		// Create the skeleton sprite
        skeletonSprite = new CharacterSprite("images/skeleton/", "skeleton_", 12, 1, false);
        
        // Create the minotaur sprite
        minotaurSprite = new CharacterSprite("images/minotaur/", "Minotaur_01_Walking_", 18, 0, true);
	}
	
	private void initializeControllers() {
		skeletonController = new AnimationController(12);
		minotaurController = new AnimationController(18);
	}
	
	// Called when attack button is pressed
	public void startSkeletonAttack() {
		if (!isSkeletonAttacking && isInAttackRange()) {
			isSkeletonAttacking = true;
			skeletonController.resetAnimation();
			startGameLoop();
		}
		
	}
	
	// Minotaur walks onto scene
	public void startMinotaurApproach() {
		if (!isMinotaurWalking) {
			isMinotaurWalking = true;
			minotaurController.resetAnimation();
			startGameLoop();
		}
	}
	
	private void startGameLoop() {
		if (gameLoop != null) {
			gameLoop.stop();
		}
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				boolean needsRedraw = false;
				
				// Update skeleton animation if attacking
				if (isSkeletonAttacking && skeletonController.update(now)) {
					needsRedraw = true;
					if (skeletonController.isAnimationComplete()) {
						isSkeletonAttacking = false;
						skeletonController.resetAnimation();
					}
				}
				
				// Update minotaur animation and position if walking
				if (isMinotaurWalking && minotaurController.update(now)) {
					needsRedraw = true;
					updateMinotaurPosition();
					if (minotaurController.isAnimationComplete()) {
						minotaurController.resetAnimation();
					}
				}
				
				if (needsRedraw) {
					gameWorld.drawScene(this);
				}
				
				// Stop game loop if nothing is animating
				if (!isSkeletonAttacking && !isMinotaurWalking) {
					gameLoop.stop();
				}
			}
		};
		
		gameLoop.start();
	}
}
