public class SkeletonController {
    
    // Tracks which frame of the animation is currently being displayed
    // Start at frame 1 since images are skeleton_1.png through skeleton_12.png
    private int currentFrame = 1;
    
    // Stores the time stamp of when the last frame change occurred (in nanoseconds)
    private long lastFrameTime = 0;
    
    // How long to wait between frame changes (50 milliseconds = 0.05 seconds)
    private final long FRAME_DURATION = 50_000_000;
    
    private boolean animationComplete = false;
    
    private int totalFrames;
    
    public SkeletonController(int numOfFrames) {
        this.totalFrames = numOfFrames;
    }
    
    public boolean update(long currentTime) {
        // Check if enough time has passed since the last frame update
        if (currentTime - lastFrameTime < FRAME_DURATION) {
            return false;
        }
        
        // Don't update if animation is already complete
        if (animationComplete) {
            return false;
        }
        
        // Move to the next frame
        currentFrame++;
        
        // Check if all frames (1 through 12) are shown
        if (currentFrame > totalFrames) {
            // Mark animation as complete
            animationComplete = true;
            // Set to last frame (12) so it displays before stopping
            currentFrame = totalFrames;
        }
        
        // Record the time of this frame change
        lastFrameTime = currentTime;
        
        return true;
    }
    
    public boolean isAnimationComplete() {
        return animationComplete;
    }
    
    public void resetAnimation() {
        currentFrame = 1;
        animationComplete = false;
        lastFrameTime = 0;
    }
    
    public int getCurrentFrame() {
        return currentFrame;
    }
}