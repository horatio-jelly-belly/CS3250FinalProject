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
    
    public GameWorld() {
        // Create canvas with 700x700 pixel dimensions
        super(700, 700);
        
        // Draw the initial background
        drawBackground();
    }

    // Public method for GameController to call when it needs to redraw the scene
    public void drawScene(GameController controller) {
        // Clear and redraw background
        drawBackground();
        
        // Draw minotaur if it's on screen
        double minotaurX = controller.getMinotaurX();
        if (minotaurX < 800) {  // Only draw if visible
            drawSprite(controller.getMinotaurSprite(), 
                      controller.getMinotaurController().getCurrentFrame(),
                      minotaurX, 
                      controller.getMinotaurY(), 
                      0.5,
                      true);
        }
        
        // Always draw skeleton
        drawSprite(controller.getSkeletonSprite(), 
                  controller.getSkeletonController().getCurrentFrame(),
                  controller.getSkeletonX(), 
                  controller.getSkeletonY(), 
                  0.5,
                  false);
    }
    
 // Generic method to draw any sprite at any position with any scale
    private void drawSprite(CharacterSprite sprite, int frameIndex, double x, double y, double scale, boolean flipHorizontal) {
        GraphicsContext gc = this.getGraphicsContext2D();
        
        Image frame = sprite.getFrame(frameIndex);
        if (frame != null) {
            double width = frame.getWidth() * scale;
            double height = frame.getHeight() * scale;
            
            if (flipHorizontal) {
                // Save the current state
                gc.save();
                
                // Flip horizontally by scaling x by -1 and translating
                gc.scale(-1, 1);
                
                // When flipped, we need to draw at the negative x position
                gc.drawImage(frame, -x - width/2, y - height/2, width, height);
                
                // Restore the original state
                gc.restore();
            } else {
                // Normal drawing (not flipped)
                double drawX = x - width / 2;
                double drawY = y - height / 2;
                gc.drawImage(frame, drawX, drawY, width, height);
            }
        }
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
}