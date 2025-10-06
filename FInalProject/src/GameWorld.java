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

// I created this class in a different project that I used to practice drawing.
// When I finished I copied and pasted this class into this project, but
// the size of the window in Main.java and the various panes within GameBorderPane
// did not fit the dimensions I used to create this class so all I did was ask 
// Claude to change the coordinates of the objects in the canvas and the canvas size
// to fit in the center pane of the GameBorderPane. 
public class GameWorld extends Canvas {
	private Image[] skeletonFrames;
	private int currentFrame = 0;
	private AnimationTimer animationTimer;
	private long lastFrameTime = 0;
	private final long FRAME_DURATION = 50_000_000;
	
    public GameWorld() {
        super(700, 700);
        
        drawBackground();
        
        loadSkeletonFrames();
        
        startAnimation();
    }
    
    private void drawBackground() {
    	GraphicsContext gc = this.getGraphicsContext2D();
        
        // Paint the sky
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 700, 700);  
        
        // Paint the ground
        gc.setFill(Color.rgb(100, 200, 100));
        gc.fillRect(0, 240, 700, 460);  
        
        // Mountains/Triangle gradient 
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
        
        // Triangle/Mountain 1 
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
        
        // Triangle/Mountain 2 
        gc.beginPath();
        gc.moveTo(250, 50);
        gc.lineTo(150, 300);
        gc.lineTo(350, 300);
        gc.closePath();
        gc.fill();
        gc.stroke();
        
        // Sun gradient 
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

        // Sun
        gc.setFill(radialGradient);
        gc.fillArc(580, 0, 100, 100, 90, 360, ArcType.OPEN);  // Changed from 900 to 580
    }
    
    private void loadSkeletonFrames() {
    	int numberOfFrames = 12;
    	skeletonFrames = new Image[numberOfFrames];
    	
    	for (int i = 1; i < numberOfFrames; i++) {
    		skeletonFrames[i] = new Image("Images/skeleton_" + i + ".png");
    	}
    }
    
    private void startAnimation() {
        GraphicsContext gc = this.getGraphicsContext2D();
        
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFrameTime >= FRAME_DURATION) {
                    
                    drawBackground();
                    
                    if (skeletonFrames[currentFrame] != null) {
                        Image currentImage = skeletonFrames[currentFrame];
                        
                        double scale = 0.5;  
                        
                        double width = currentImage.getWidth() * scale;
                        double height = currentImage.getHeight() * scale;
                        
                        double x = 350 - width / 2;
                        double y = 350 - height / 2;
                        
                        gc.drawImage(currentImage, x, y, width, height);
                    }
                    
                    currentFrame = (currentFrame + 1) % skeletonFrames.length;
                    lastFrameTime = now;
                }
            }
        };
        
        animationTimer.start();
    }
}