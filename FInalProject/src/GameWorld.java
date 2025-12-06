import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

/**
 * Game rendering canvas that handles all visual output.
 * Responsible for drawing backgrounds, sprites, and animations.
 * Extends Canvas to provide a drawable surface for the game.
 */
public class GameWorld extends Canvas {
    
    /**
     * Constructor that creates the game canvas.
     * Sets up initial dimensions and draws the background.
     */
    public GameWorld() {
        // Create canvas with 700x700 pixel dimensions
        // Square canvas provides equal space for horizontal and vertical game play
        super(700, 700);
        
        // Draw the initial background
        // Sets up the scene before any characters appear
        drawBackground();
    }

    /**
     * Public method for GameController to trigger scene redraws.
     * Called whenever animation frames change or positions update.
     * Redraws entire scene including background and all visible sprites.
     * @param controller GameController providing current game state
     */
    public void drawScene(GameController controller) {
        // Clear and redraw background
        // Ensures clean slate for each frame (no ghosting)
        drawBackground();
        
     // Draw minotaur if it's on screen and alive
        double minotaurX = controller.getMinotaurX();
        if (minotaurX < 800 && controller.getEnemy().isAlive()) {  
            // Choose which sprite to render based on minotaur's current action
            // Attack animation uses different sprite sheet than walking/idle
            if (controller.getIsMinotaurAttacking()) {
                // Draw minotaur using attack animation frames
                drawSprite(controller.getMinotaurAttackSprite(),
                           controller.getMinotaurAttackController().getCurrentFrame(),
                           minotaurX,
                           controller.getMinotaurY(),
                           0.5,    // Scale to 50% of original size
                           true);  // Flip horizontally to face left
            } else if (controller.getIsMinotaurWalking()){
                // Draw minotaur using walking/idle animation frames
                drawSprite(controller.getMinotaurSprite(), 
                        controller.getMinotaurController().getCurrentFrame(),
                        minotaurX, 
                        controller.getMinotaurY(), 
                        0.5,    // Scale to 50% of original size
                        true);  // Flip horizontally to face left
            }
            
            else {
                // Idle state - draw first frame of attack sprite as standing pose
                // Uses frame 0 which shows minotaur in neutral stance with feet planted
                drawSprite(controller.getMinotaurAttackSprite(),
                        0,
                        minotaurX,
                        controller.getMinotaurY(),
                        0.5,    // Scale to 50% of original size
                        true);  // Flip horizontally to face left
            }
        }
        
        // Always draw skeleton (player character is always visible)
        drawSprite(controller.getSkeletonSprite(), 
                  controller.getSkeletonController().getCurrentFrame(),
                  controller.getSkeletonX(), 
                  controller.getSkeletonY(), 
                  0.5,     // Scale to 50% of original size
                  false);  // Don't flip - skeleton faces right
    }
    
    /**
     * Generic sprite drawing method supporting scaling and flipping.
     * Centers sprites at specified coordinates for consistent positioning.
     * Handles horizontal flipping for directional facing.
     * @param sprite The CharacterSprite containing frame images
     * @param frameIndex Which frame of animation to draw
     * @param x X coordinate for sprite center
     * @param y Y coordinate for sprite center
     * @param scale Size multiplier (0.5 = half size, 2.0 = double size)
     * @param flipHorizontal Whether to mirror the sprite horizontally
     */
    private void drawSprite(CharacterSprite sprite, int frameIndex, double x, double y, double scale, boolean flipHorizontal) {
        GraphicsContext gc = this.getGraphicsContext2D();
        
        // Get the specific frame image to draw
        Image frame = sprite.getFrame(frameIndex);
        if (frame != null) {
            // Calculate scaled dimensions
            double width = frame.getWidth() * scale;
            double height = frame.getHeight() * scale;
            
            /**
             * I had Claude help me with flipping the minotaur image so that 
             * it faced the skeleton. Claude also helped me with scaling the 
             * images
             */
            if (flipHorizontal) {
                // Save the current transformation state
                // Allows us to modify then restore transformations
                gc.save();
                
                // Flip horizontally by scaling x by -1
                // This mirrors the image around the Y axis
                gc.scale(-1, 1);
                
                // When flipped, we need to draw at the negative x position
                // The flip transformation will mirror it back to the correct spot
                gc.drawImage(frame, -x - width/2, y - height/2, width, height);
                
                // Restore the original transformation state
                // Prevents flip from affecting subsequent draws
                gc.restore();
            } else {
                // Normal drawing (not flipped)
                // Center the sprite at the given coordinates
                double drawX = x - width / 2;   // Offset left by half width
                double drawY = y - height / 2;  // Offset up by half height
                gc.drawImage(frame, drawX, drawY, width, height);
            }
        }
    }
    
    /**
     * Draws the game background including sky, ground, mountains, and sun.
     * Creates a outdoor scene with gradient effects for visual depth.
     * Called before drawing sprites to provide backdrop.
     */
    private void drawBackground() {
        // Get the graphics context to draw on this canvas
        GraphicsContext gc = this.getGraphicsContext2D();
        
        // Paint the sky - light blue rectangle covering entire canvas
        // Creates daytime atmosphere
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 700, 700);  
        
        // Paint the ground - green rectangle for lower portion
        // Represents grassy terrain where characters stand
        gc.setFill(Color.rgb(100, 200, 100));  // Custom green color
        gc.fillRect(0, 240, 700, 460);         // Starts at y=240, covers rest of canvas
        
        // Create gradient for mountains
        // Simulates snow-capped peaks with rocky base
        Stop[] mountainStops = new Stop[] {
            new Stop(0, Color.WHITE),           // Snow at peak (0% = top)
            new Stop(0.3, Color.LIGHTGRAY),     // Rocky upper slopes (30% down)
            new Stop(0.6, Color.rgb(101, 67, 33)) // Brown earth at base (60% down)
        };
        
        // Linear gradient from top to bottom of mountain
        LinearGradient gradient = new LinearGradient(
            0.5, 0,     // Start point (center-top)
            0.5, 1,     // End point (center-bottom)
            true,       // Proportional (relative to shape bounds)
            CycleMethod.NO_CYCLE,  // Don't repeat gradient
            mountainStops
        );
        
        // Draw first triangle/mountain using gradient
        // Closer/larger mountain on the left
        gc.setFill(gradient);
        gc.setStroke(gradient);
        gc.setLineWidth(2);     // Border width for mountain outline
        gc.beginPath();
        gc.moveTo(100, 20);     // Peak position
        gc.lineTo(15, 250);     // Left base
        gc.lineTo(200, 250);    // Right base
        gc.closePath();
        gc.fill();              // Fill with gradient
        gc.stroke();            // Draw outline
        
        // Draw second triangle/mountain using same gradient
        // Farther/smaller mountain offset to the right
        gc.beginPath();
        gc.moveTo(250, 50);     // Peak position (lower = farther)
        gc.lineTo(150, 300);    // Left base
        gc.lineTo(350, 300);    // Right base
        gc.closePath();
        gc.fill();              // Fill with gradient
        gc.stroke();            // Draw outline
        
        // Create radial gradient for sun
        // Simulates glowing effect with hot center
        Stop[] sunGradient = new Stop[] {
            new Stop(0, Color.rgb(255, 220, 100)),  // Bright yellow center
            new Stop(1, Color.rgb(255, 100, 30))    // Orange-red edges
        };
        
        // Radial gradient emanating from center
        RadialGradient radialGradient = new RadialGradient(
            0, 0,           // Focus point angle and distance
            0.5, 0.5,       // Center point (middle of shape)
            0.5,            // Radius (0.5 = half the shape size)
            true,           // Proportional
            CycleMethod.NO_CYCLE,  // Don't repeat
            sunGradient
        );

        // Draw the sun as an arc in the upper right corner
        // Partial circle creates setting/rising sun effect
        gc.setFill(radialGradient);
        gc.fillArc(580, 0,      // Position (partially off-screen)
                   100, 100,     // Size (width, height)
                   90,           // Starting angle (90° = bottom)
                   360,          // Arc extent (360° = full circle)
                   ArcType.OPEN); // Open arc (no lines to center)
    }
}