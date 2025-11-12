public class AnimationController {
    private int currentFrame;
    private int startFrame;
    
    // Stores the time stamp of when the last frame change occurred (in nanoseconds)
    private long lastFrameTime = 0;
    
    // How long to wait between frame changes (50 milliseconds = 0.05 seconds)
    private final long FRAME_DURATION = 50_000_000;
    
    private boolean animationComplete = false;
    
    private int totalFrames;
    
    public AnimationController(int numOfFrames, int startFrame) {
        this.totalFrames = numOfFrames;
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
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
        if (currentFrame >= startFrame + totalFrames) {
            // Mark animation as complete
            animationComplete = true;
            // Set to last frame (12) so it displays before stopping
            currentFrame = startFrame + totalFrames - 1;
        }
        
        // Record the time of this frame change
        lastFrameTime = currentTime;
        
        return true;
    }
    
    public boolean isAnimationComplete() {
        return animationComplete;
    }
    
    public void resetAnimation() {
        currentFrame = startFrame;
        animationComplete = false;
        lastFrameTime = 0;
    }
    
    public int getCurrentFrame() {
        return currentFrame;
    }
}