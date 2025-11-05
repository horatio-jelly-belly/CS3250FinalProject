import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class GameWorld extends Canvas {
    // Load images for both player and enemy sprites using Character sprite
	private CharacterSprite playerSprite;
	private CharacterSprite enemySprite;
	
	// Game characters
	private Player player;
	private Enemy enemy;
	
    
    // The timer that drives the animation by repeatedly calling handle()
    private AnimationTimer animationTimer;
    
    private SkeletonController skeletonController;
    
    public GameWorld() {
        // Create canvas with 700x700 pixel dimensions
        super(700, 700);
        
        // Draw the background scenery (sky, ground, mountains, sun)
        drawBackground();
        
        // Load all 12 skeleton animation frames into memory
        loadSkeletonFrames();
        
        // Pass the actual number of frames (12) to the controller
        skeletonController = new SkeletonController(12);
        
        // Draw the initial skeleton image (frame 1) so it's visible before animation starts
        drawInitialSkeleton();
    }

    private void drawBackground() {
        // Get the graphics context to draw on this canvas
        GraphicsContext gc = this.getGraphicsContext2D();
        
        // Paint the sky - light blue rectangle covering entire canvas
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 700, 700);  
        
        // Paint the ground - green rectangle for lower portion
        gc.setFill(Color.rgb(100, 200, 100));
        gc.fillRect(0, 240, 700, 460);  
        
        // Create gradient for mountains - white at top, gray in middle, brown at bottom
        Stop[] mountainStops = new Stop[] {
            new Stop(0, Color.WHITE),
            new Stop(0.3, Color.LIGHTGRAY),
            new Stop(0.6, Color.rgb(101, 67, 33))
        };
        
        LinearGradient gradient = new LinearGradient(
            0.5, 0,
            0.5, 1,
            true,
            CycleMethod.NO_CYCLE,
            mountainStops
        );
        
        // Draw first triangle/mountain using gradient
        gc.setFill(gradient);
        gc.setStroke(gradient);
        gc.setLineWidth(2);
        gc.beginPath();
        gc.moveTo(100, 20);
        gc.lineTo(15, 250);
        gc.lineTo(200, 250);
        gc.closePath();
        gc.fill();
        gc.stroke();
        
        // Draw second triangle/mountain using same gradient
        gc.beginPath();
        gc.moveTo(250, 50);
        gc.lineTo(150, 300);
        gc.lineTo(350, 300);
        gc.closePath();
        gc.fill();
        gc.stroke();
        
        // Create radial gradient for sun - yellow in center, orange at edges
        Stop[] sunGradient = new Stop[] {
            new Stop(0, Color.rgb(255, 220, 100)),
            new Stop(1, Color.rgb(255, 100, 30))
        };
        
        RadialGradient radialGradient = new RadialGradient(
            0, 0, 0.5, 0.5, 0.5,
            true,
            CycleMethod.NO_CYCLE,
            sunGradient
        );

        // Draw the sun as an arc in the upper right corner
        gc.setFill(radialGradient);
        gc.fillArc(580, 0, 100, 100, 90, 360, ArcType.OPEN);
    }
    
    private void loadSkeletonFrames() {
        // We have 12 skeleton animation frames (skeleton_1.png through skeleton_12.png)
        // Using array size of 13 to use indices 1-12 to match file names
        skeletonFrames = new Image[13];
        
        // Load each skeleton image file into the array
        // Now loading ALL 12 images (1 through 12)
        // skeletonFrames[0] remains null - it's never accessed
        for (int i = 1; i <= 12; i++) {
            skeletonFrames[i] = new Image("images/skeleton_" + i + ".png");
        }
        
    }
    
    public void startAnimation() {
        // Stop any existing animation
        if (animationTimer != null) {
            animationTimer.stop();
        }
        
        // Reset the controller to start from frame 1
        skeletonController.resetAnimation();
        
        // Get the graphics context for drawing
        GraphicsContext gc = this.getGraphicsContext2D();
        
        // Create an AnimationTimer that will repeatedly call handle() ~60 times per second
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Only update the frame if enough time has passed (50ms)
                // This controls the speed of the animation
                if (skeletonController.update(now)) {
                    
                    // Clear the canvas by redrawing the background
                    drawBackground();
                    
                    int frameIndex = skeletonController.getCurrentFrame();
                    
                    // Make sure the frame index is valid
                    if (frameIndex >= 1 && frameIndex <= 12) {
                        Image currentImage = skeletonFrames[frameIndex];
                        
                        // Draw the current skeleton frame if it exists
                        if (currentImage != null) {
                            
                            // Scale the image to 50% of its original size
                            double scale = 0.5;  
                            
                            double width = currentImage.getWidth() * scale;
                            double height = currentImage.getHeight() * scale;
                            
                            // Calculate position to center the skeleton on the canvas
                            double x = 350 - width / 2;
                            double y = 350 - height / 2;
                            
                            // Draw the skeleton image at the calculated position
                            gc.drawImage(currentImage, x, y, width, height);
                        }
                    }
                    
                    if (skeletonController.isAnimationComplete()) {
                        animationTimer.stop();
                        // Reset to the first frame when animation ends
                        drawInitialSkeleton();
                    }
                }
            }
        };
        
        // Start the animation timer (begins calling handle() repeatedly)
        animationTimer.start();
    }
    
    private void drawInitialSkeleton() {
        // Get the graphics context for drawing
        GraphicsContext gc = this.getGraphicsContext2D();
        
        // Clear and redraw background first
        drawBackground();
        
        // Use the first skeleton frame (frame 1) from the pre-loaded array
        Image initialImage = skeletonFrames[1];
        
        if (initialImage != null) {
            // Scale the image to 50% of its original size
            double scale = 0.5;  
            
            double width = initialImage.getWidth() * scale;
            double height = initialImage.getHeight() * scale;
            
            // Calculate position to center the skeleton on the canvas
            double x = 350 - width / 2;
            double y = 350 - height / 2;
            
            // Draw the skeleton image so it's visible when the game first loads
            gc.drawImage(initialImage, x, y, width, height);
        }
    }
}