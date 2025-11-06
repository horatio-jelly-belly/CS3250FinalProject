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
}
